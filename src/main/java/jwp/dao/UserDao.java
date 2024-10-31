package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserDao {

    private JdbcTemplate<User> jdbcTemplate = new JdbcTemplate();

    /* 리팩토링 전 코드
    public void insert(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionManager.getConnection();
            String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
            // ? = 쿼리문의 파라미터를 뜻함
            // setString() 메소드를 통해서 각각의 파라미터에 값을 넣어줄 수 있다.
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
            // 인덱스를 명시하고 그에 맞는 값을 입력한다. 이 때 인덱스는 1부터 시작함을 볼 수 있다.

            pstmt.executeUpdate();
            // executeUpdate()라는 메서드를 활용하면 쿼리문이 실행된다.
        } finally {
            // try 문에서 사용한 Connection, PreparedStatement 자원을 close 해준다.
            if (pstmt != null)
                pstmt.close();
            if (conn != null)
                conn.close();
        }
    }
     */

    public void insert(User user) throws SQLException {

        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        // 파라미터를 어떻게 설정할 지 람다식을 활용해서 표현해주면 됨
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        };
        jdbcTemplate.update(sql, pstmtSetter);

    }


    /*
    public void update(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionManager.getConnection();
            String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userId = ?";
            pstmt = conn.prepareStatement(sql);
            // ?의 위치가 바뀌었으니 그에 맞게 인덱스 값도 변화를 주어야함
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());

            pstmt.executeUpdate();
        } finally {
            if (pstmt != null)
                pstmt.close();
            if (conn != null)
                conn.close();
        }
    }
     */

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

    /* 리팩토링 전
    public void delete(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionManager.getConnection();
            String sql = "DELETE FROM USERS WHERE userId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserId());

            pstmt.executeUpdate();
        } finally {
            if (pstmt != null)
                pstmt.close();
            if (conn != null)
                conn.close();
        }
    }
     */

    public void delete(User user) throws SQLException {
        String sql = "DELETE FROM USERS WHERE userId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, user.getUserId());
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }


    /* 리팩토링 전
    public List<User> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();

        try {
            conn = ConnectionManager.getConnection();
            String sql = "SELECT * FROM USERS";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            // DB의 정보를 update하는 것이 아니라, 조회해서 가져오는 것이기 때문에 executeUpdate()가 아니라
            // executeQuery()를 사용한 모습을 볼 수 있다.

            while (rs.next()) { // resultSet에 남은 행이 있으면 계속해서 반복
                // getString()을 통해서 해당 행의 각각의 열에서의 데이터를 하나씩 가져올 때는
                // User 클래스 생성자의 파라미터 순서를 지켜서 작성해주어야한다.
                User user = new User(rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));

                users.add(user);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return users;
    }
     */

    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM USERS";
        RowMapper rowMapper = rs -> new User(rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));

        return jdbcTemplate.query(sql, rowMapper);
    }

    /* 리팩토링 전
    public User findByUserId(String userId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            conn = ConnectionManager.getConnection();
            String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            // DB의 정보를 update하는 것이 아니라, 조회해서 가져오는 것이기 때문에 executeUpdate()가 아니라
            // executeQuery()를 사용한 모습을 볼 수 있다.

            if (rs.next()) { // resultSet에 남은 행이 있으면 계속해서 반복
                // getString()을 통해서 해당 행의 각각의 열에서의 데이터를 하나씩 가져올 때는
                // User 클래스 생성자의 파라미터 순서를 지켜서 작성해주어야한다.
                user = new User(rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }
     */


    public User findByUserId(String userId) throws SQLException {
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";

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
