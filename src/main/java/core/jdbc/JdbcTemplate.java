package core.jdbc;

import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate<T> {

    public void update(String sql, PreparedStatementSetter pstmtSetter) throws SQLException {

        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmtSetter.setParameters(pstmt);
            pstmt.executeUpdate();
        }
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper) throws SQLException {

        ResultSet rs = null;
        List<T> Objects = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){

            rs = pstmt.executeQuery(); // 파라미터를 세팅하기도 전에 쿼리가 실행되서 결과가 반환됨
            while (rs.next()) {
                T object = rowMapper.mapRow(rs);
                Objects.add(object);
            }
        }

        return Objects;
    }

    public List<T> query(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws SQLException {
        List<T> objects = new ArrayList<>();
        ResultSet rs = null;
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmtSetter.setParameters(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                T object = rowMapper.mapRow(rs);
                objects.add(object);
            }
        } finally {
            if (rs != null)
                rs.close();
        }
        return objects;
    }

    public T queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws SQLException {

        ResultSet rs = null;
        T object = null;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);){

            pstmtSetter.setParameters(pstmt);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                object = rowMapper.mapRow(rs);
            }
        }

        return object;
    }



}
