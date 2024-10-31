package jwp.dao;

import core.jdbc.*;
import jwp.model.User;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private final JdbcTemplate<User> jdbcTemplate = new JdbcTemplate();
    private final KeyHolder keyHolder = new KeyHolder();

    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        };
        jdbcTemplate.update(sql,pstmtSetter,keyHolder);
    }

    public void update(User user) throws SQLException {
        String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());
        };
        jdbcTemplate.update(sql,pstmtSetter,keyHolder);

//        Connection conn = null;
//        PreparedStatement pstmt = null;
//
//        try {
//            conn = ConnectionManager.getConnection();
//            String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, user.getPassword());
//            pstmt.setString(2, user.getName());
//            pstmt.setString(3, user.getEmail());
//            pstmt.setString(4, user.getUserId());
//
//            pstmt.executeUpdate();
//        } finally {
//            if (pstmt != null) {
//                pstmt.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
        }

    public void delete(User user) throws SQLException {
        String sql = "DELETE FROM USERS WHERE userId=?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, user.getUserId());
        };
        jdbcTemplate.update(sql,pstmtSetter,keyHolder);

//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        try {
//            conn = ConnectionManager.getConnection();
//            String sql = "DELETE FROM USERS WHERE userId=?";
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, user.getUserId());
//            pstmt.executeUpdate();
//        } finally {
//            if (pstmt != null) {
//                pstmt.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
    }

    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM USERS";
        RowMapper rowMapper = rs -> new User(rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));
        return jdbcTemplate.query(sql,rowMapper);

//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        List<User> users = new ArrayList<>();
//
//        try {
//            conn = ConnectionManager.getConnection();
//            String sql = "SELECT * FROM USERS";
//            pstmt = conn.prepareStatement(sql);
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                User user = new User(rs.getString("userId"),
//                        rs.getString("password"),
//                        rs.getString("name"),
//                        rs.getString("email"));
//                users.add(user);
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (pstmt != null) {
//                pstmt.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//
//        return users;
    }

    public User findById(String userId) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE userId=?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, userId);
        };
        RowMapper rowMapper =  rs -> new User(rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));
        return jdbcTemplate.queryForObject(sql,pstmtSetter,rowMapper);

//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        User user = null;

//        try {
//            conn = ConnectionManager.getConnection();
//            String sql = "SELECT * FROM USERS WHERE userId=?";
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, userId);
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                user = new User(rs.getString("userId"),
//                        rs.getString("password"),
//                        rs.getString("name"),
//                        rs.getString("email"));
//            }
//
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (pstmt != null) {
//                pstmt.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
    }

}
