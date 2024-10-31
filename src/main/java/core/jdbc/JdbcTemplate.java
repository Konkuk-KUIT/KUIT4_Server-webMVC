package core.jdbc;

import jwp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class JdbcTemplate<T> {

    // insert, update, delete 모두 DB의 데이터를 udpate한다는 공통점이 존재하기 때문에 udpate라는 이름을 붙어줌

    // udpate 메서드에서, 파라미터를 설정하는 PreparedStatementSetter 라는 동작을 파라미터로 받아서,
    // 각 메서드에서 파라미터에 맞춰 다른 동작을 수행할 수 있도록 해준다.
    public void update(String sql, PreparedStatementSetter pstmtSetter) throws SQLException {

        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmtSetter.setParameters(pstmt);
            pstmt.executeUpdate();
            // executeUpdate()라는 메서드를 활용하면 쿼리문이 실행된다.
        }
        // try 문에서 사용한 Connection, PreparedStatement 자원을 close 해준다.
    }

    // Question 용 -> 오버로딩 활용
    public void update(String sql, PreparedStatementSetter pstmtSetter, KeyHolder holder) {
        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmtSetter.setParameters(pstmt);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                holder.setId(rs.getLong(1));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // UserDao, QuestionDao 에서 사용할 query 메소드
    public <T> List<T> query(String sql, RowMapper<T> rowMapper) throws SQLException {

        List<T> objects = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                T object = rowMapper.mapRow(rs);
                objects.add(object);
            }
        }
        return objects;
    }

    // AnswerDao 에서 사용할 query 메소드
    public <T> List<T> query(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws SQLException {
        List<T> objects = new ArrayList<>();
        ResultSet rs = null;

        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmtSetter.setParameters(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                T object = rowMapper.mapRow(rs);
                objects.add(object);
            }
        }
        return objects;
    }



    public T queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws SQLException {
        ResultSet rs = null;
        T object = null;

        // 이 경우는 ResultSet을 try with resource 문에 집어넣게 되면 파라미터를 세팅하기도 전에 쿼리가 실행되어서 결과가 반환되는 문제가 존재한다.
        // 따라서 ResultSet은 따로 빼주어서 try 블록 안으로 분리해준다.
        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmtSetter.setParameters(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                object = rowMapper.mapRow(rs);
            }
        } finally {
            if (rs != null)
                rs.close();
        }
        return object;
    }
}
