package core.jdbc;

import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DB에 추가, 삭제, 수정하는 것
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
             // PreapredStatement에 SQL쿼리 객체를 담는다
             // PreparedStatement : SQL쿼리를 DB에 제출하여 실행하는 객체
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // placeholder에 데이터 삽입
            pstmtSetter.setParameters(pstmt);
            // 실제로 SQL쿼리 실행
            pstmt.executeUpdate();
        }

    }

    // sql 쿼리 실행 후 결과를 리스트형태로 가져오기
    public <T>List<T> query(String sql, RowMapper<T> rowMapper) throws SQLException {
        // sql 쿼리의 결과를 저장
        List<T> objects = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    // 각 sql의 단어를 추출해서 User생성자에 대입
                    T object = rowMapper.mapRow(rs);
                    // 그렇게 만들어진 인스턴스 add
                    objects.add(object);
                }
        }

        return objects;
    }

    // sql쿼리 실행 후 단일 객체반환
    public T queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws SQLException {
        // todo ResultSet을 따로 분리 한 이유 알아보기 (try resource에 포함 안시키고)
        // 단일 행만을 다루므로, 찾지못할 경우를 대비, null을 대입하여 명시적으로 닫기
        ResultSet rs = null;
        T user = null;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            // sql문 완성
            pstmtSetter.setParameters(pstmt);
            rs = pstmt.executeQuery();

            // 만약 userId가 존재한다면
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
