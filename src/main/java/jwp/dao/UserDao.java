package jwp.dao;

import core.jdbc.ConnectionManager;
import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Dao : Data access object
// JdbcTemplate을 이용해서 DB 추가, 삭제, 수정
// => 데이터 접근 객체
public class UserDao {
    // Todo 1. addUser -> insert / update, delete 메소드 제작

    private final JdbcTemplate<User> jdbcTemplate = new JdbcTemplate();

    // insert method
    public void insert(User user) throws SQLException {
        // db에 보낼 sql문
        // ? : 쿼리문의 파라미터
        // DB에 보낼 SQL 쿼리 준비
        // 쿼리 컴파일 및 DB에 준비
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";

        // pstmtSetter의 setParameters 구현 (by익명함수, 람다식)
        // pstmt : PreparedStatement타입의 매개변수 (setParameters 원형의)
        // Pstmt를 받으면 괄호처럼 행동해라
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        };
        // 람다식으로 (익명클래스로 구현해도 된다)

        // SQL문에 데이터 설정된 인터페이스 객체 전달
        jdbcTemplate.update(sql, pstmtSetter);

    }

    // update method
    public void update(User user) throws SQLException {
        String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }

    // delete method
    public void delete(User user) throws SQLException {
        String sql = "DELETE FROM USERS WHERE userId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, user.getUserId());
        };
        jdbcTemplate.update(sql, pstmtSetter);

    }

    // Todo 2. findAll, findByUserId
    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM USERS";

        RowMapper rowMapper = rs -> new User(rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email")
        );

        return jdbcTemplate.query(sql, rowMapper);
    }

    public User findByUserId(String userId) throws SQLException {
        String sql = "SELECT userId password, name, email FROM USERS WHERE userId = ?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, userId);
        };

        RowMapper rowMapper = rs -> new User(rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email")
        );

        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }

}
