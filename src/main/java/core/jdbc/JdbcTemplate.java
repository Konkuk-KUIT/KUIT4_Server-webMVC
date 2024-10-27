package core.jdbc;

import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 중복되는 부분 구현
// -> DB에 데이터 삽입 및 업데이트 하는 공통로직구현

// todo <User>라고 되어있는 의존성 제거 -> Generic문법 이용
public class JdbcTemplate<T>{

//    // connection을 가져온다 (DB와의 연결)
//    conn = ConnectionManager.getConnection();
//
//    // sql에 포함된 SQL쿼리를 pstmt에 담는다
//    pstmt = conn.prepareStatement(sql);
//
//    // 람다식을 이용해, pstmt의 placeholder에 데이터 넣는다.
//    pstmtSetter.setParameters(pstmt);
//
//    // pstmt로 쿼리문 실행
//    // 데이터가 실제 DB에 삽입
//    pstmt.executeUpdate();

    // PreparedStateSetter : 동작파라미터화.. (각 메소드에서 파라미터에 맞춰 다른 동작을 수행)
    // sql문, placeholder("?")에 인터페이스에 세팅된 데이터들 삽입
    public void update(String sql, PreparedStatementSetter pstmtSetter) throws SQLException {
        // connection : DB와의 연결담당
        // SQL문 미리 컴파일
        // 실제 SQL쿼리를 실행하는 역할

        // try resource : 예외발생 시 자원 자동해제
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmtSetter.setParameters(pstmt);
            pstmt.executeUpdate();
        }

    }

    public <T>List<T> query(String sql, RowMapper<T> rowMapper) throws SQLException {
        // sql 쿼리의 결과를 저장
        List<T> objects = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    T object = rowMapper.mapRow(rs);
                    objects.add(object);
                }
        }

        return objects;
    }

    public T queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws SQLException {
        // todo ResultSet을 따로 분리 한 이유 알아보기
        ResultSet rs = null;
        T user = null;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmtSetter.setParameters(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = rowMapper.mapRow(rs);
            }
        } finally {
            if(rs != null)
                rs.close();
        }

        return user;
    }
}
