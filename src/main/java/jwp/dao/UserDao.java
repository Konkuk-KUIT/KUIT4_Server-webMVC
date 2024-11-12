package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    //1. insert, update, delete

    //2. findAll, findByUserId

    private final JdbcTemplate<User> jdbcTemplate = new JdbcTemplate();

    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, user.getUserId());           //pstmt 와 관련이 없는 user.getUserId() 등은 람다를 넘겨주기 전에 실행 되어 string 형식으로 전달되는 느낌?
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        };

        jdbcTemplate.update(sql, pstmtSetter);
    }

    public void update(User user) throws SQLException {
            String sql = "UPDATE USERS SET password = ?, name = ?, email = ?, userId = ? WHERE userId = ?";

            PreparedStatementSetter pstmtSetter = pstmt -> {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId());
            };

            jdbcTemplate.update(sql, pstmtSetter);
        }

    public void delete(User user) throws SQLException {
        String sql = "DELETE FROM USERS WHERE userId = ?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, user.getUserId());
        };

        jdbcTemplate.update(sql, pstmtSetter);
    }

    public List<User> findAll() throws SQLException{
        String sql = "SELECT * FROM USERS";
        RowMapper rowMapper = rs -> new User(rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));

        return jdbcTemplate.query(sql, rowMapper);
    }

    public User findByUserId(String userId) throws SQLException{
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userID=?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, userId);
        };

        RowMapper rowMapper = rs -> new User(rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));

        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }
}
