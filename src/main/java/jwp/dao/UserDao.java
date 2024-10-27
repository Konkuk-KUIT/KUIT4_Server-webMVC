package jwp.dao;

import core.jdbc.ConnectionManager;
import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Dao : Data access object
public class UserDao {
    // Todo 1. addUser -> insert / update, delete 메소드 제작

    // insert method
    public void insert(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        // DB에 실행 할 SQL쿼리 담기

        try {
            // connection을 가져온다 (DB와의 연결)
            conn = ConnectionManager.getConnection();
            // db에 보낼 sql문
            // ? : 쿼리문의 파라미터
            String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
            // DB에 보낼 SQL 쿼리 준비
            // 쿼리 컴파일 및 DB에 준비
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
            // 배열과 다르게 1부터 시작


            // 쿼리문 실행 (sql변수)
            // db가 지금까지의 데이터 저장
            pstmt.executeUpdate();
        } finally {
            // close하여 자원 해제
            if(pstmt != null)
                pstmt.close();
            if (conn != null)
                conn.close();
        }
    }

    // update method
    public void update(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionManager.getConnection();
            // ? : 쿼리문의 파라미터
            String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userId = ?";
            // ?의 위치에 맞게 바꾼다.
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());

            // db가 지금까지의 데이터 저장
            pstmt.executeUpdate();
        } finally {
            if(pstmt != null)
                pstmt.close();
            if (conn != null)
                conn.close();
        }
    }

    // delete method
    public void delete(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionManager.getConnection();
            // ? : 쿼리문의 파라미터
            String sql = "DELETE FROM USERS WHERE userId = ?";
            // INSERT가 SQLExeption을 던지게 바꾼다..
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserId());

            // db가 지금까지의 데이터 저장
            pstmt.executeUpdate();
        } finally {
            if(pstmt != null)
                pstmt.close();
            if (conn != null)
                conn.close();
        }
    }

    // Todo 2. findAll, findByUserId
    public List<User> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        // sql 쿼리의 결과를 저장
        List<User> users = new ArrayList<>();

        try {
            conn = ConnectionManager.getConnection();
            String sql = "SELECT * FROM USERS";
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            // 정보를 update X / 정보를 조회하고 가져온다 (1명씩)
            // <-> executeUpdate()

            while (rs.next()) { // 여러명이 저장되있을 수 있으므로
                User user = new User(rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
                users.add(user);
            }
        } finally {
            if (conn != null)
                conn.close();
            if (pstmt != null)
                pstmt.close();
            if (rs != null)
                rs.close();
        }
        return users;
    }

    public User findByUserId(String userId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            conn = ConnectionManager.getConnection();
            String sql = "SELECT userId password, name, email FROM USERS WHERE userId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();
            if (rs.next()) { // 여러명이 저장되있을 수 있으므로
                user = new User(rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
            }
        } finally {
            if (conn != null)
                conn.close();
            if (pstmt != null)
                pstmt.close();
            if (rs != null)
                rs.close();
        }
        return user;
    }

}
