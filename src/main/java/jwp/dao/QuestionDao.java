package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import jwp.model.Question;

import java.sql.SQLException;
import java.util.List;

public class QuestionDao {
    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate();

//    public void insert(Question question) throws SQLException {
//        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
//
//        PreparedStatementSetter pstmtSetter = pstmt -> {
//            pstmt.setString(1, question.getUserId());
//            pstmt.setString(2, question.getPassword());
//            pstmt.setString(3, question.getName());
//            pstmt.setString(4, question.getEmail());
//        };
//
//        jdbcTemplate.update(sql, pstmtSetter);
//    }

//    public void update(Question question) throws SQLException {
//        String sql = "UPDATE USERS SET password = ?, name = ?, email = ?, userId = ? WHERE userId = ?";
//
//        PreparedStatementSetter pstmtSetter = pstmt -> {
//            pstmt.setString(1, question.getPassword());
//            pstmt.setString(2, question.getName());
//            pstmt.setString(3, question.getEmail());
//            pstmt.setString(4, question.getUserId());
//        };
//
//        jdbcTemplate.update(sql, pstmtSetter);
//    }

//    public void delete(Question question) throws SQLException {
//        String sql = "DELETE FROM USERS WHERE userId = ?";
//
//        PreparedStatementSetter pstmtSetter = pstmt -> {
//            pstmt.setString(1, question.getUserId());
//        };
//
//        jdbcTemplate.update(sql, pstmtSetter);
//    }

    public List<Question> findAll() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS";
        RowMapper rowMapper = rs -> new Question(rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer"));

        return jdbcTemplate.query(sql, rowMapper);
    }

//    public Question findByUserId(String userId) throws SQLException{
//        String sql = "SELECT userId, password, name, email FROM USERS WHERE userID=?";
//
//        PreparedStatementSetter pstmtSetter = pstmt -> {
//            pstmt.setString(1, userId);
//        };
//
//        RowMapper rowMapper = rs -> new Question(rs.getString("userId"),
//                rs.getString("password"),
//                rs.getString("name"),
//                rs.getString("email"));
//
//        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
//    }
}


//    private Long questionId;
//    private String writer;
//    private String title;
//    private String contents;
//    private Timestamp createdDate;
//    private int countOfAnswer;