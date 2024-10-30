package jwp.dao;

import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import core.jdbc.JdbcTemplate;
import jwp.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    //TODO 1. insert, update, delete

    private final JdbcTemplate <User> jdbcTemplate = new JdbcTemplate();

    public void insert(User user) throws SQLException {
        String sql = "insert into user values(?,?,?,?)";
        PreparedStatementSetter pstmtSetter = pstmt ->{
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }


    public void update(User user) throws SQLException {
        String sql = "update users set password=?,name=?,email=? where userId=?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());
        };
        jdbcTemplate.update(sql,pstmtSetter);
    }

    public void delete(User user) throws SQLException {
        String sql = "delete from users where userId=?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, user.getUserId());
        };
        jdbcTemplate.update(sql,pstmtSetter);
    }

    //TODO 2. findAll, findByUserId
    public List<User> findAll() throws SQLException {
        String sql = " SELECT * FROM users ";
        RowMapper rowMapper = rs -> new User(rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));
        return jdbcTemplate.query(sql, rowMapper);
    }

    public User findByUserId(String userId) throws SQLException {
        String sql = " SELECT userId, password, name, email FROM users where userId=? ";
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
