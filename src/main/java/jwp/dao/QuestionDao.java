package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;
import jwp.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {

    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();

    public void insert(Question question) throws SQLException {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setTimestamp(4, question.getCreatedDate());
            pstmt.setInt(5, question.getCountOfAnswer());
        };

        jdbcTemplate.update(sql, pstmtSetter);
    }

    public void update(Question question) throws SQLException {
        String sql = "UPDATE QUESTIONS SET writer=?, title=?, contents=?, createdDate=?, countOfAnswer=? WHERE questionId=?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setTimestamp(4, question.getCreatedDate());
            pstmt.setInt(5, question.getCountOfAnswer());
            pstmt.setInt(6, question.getQuestionId());
        };
        jdbcTemplate.update(sql,pstmtSetter);

    }

    public List<Question> getAllQuestion() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS";
        List<Question> questions = new ArrayList<>();

        RowMapper rowMapper = rs -> new Question(
                rs.getInt("questionId"),            // bigint -> int
                rs.getString("writer"),              // varchar -> String
                rs.getString("title"),               // varchar -> String
                rs.getString("contents"),            // varchar -> String
                rs.getTimestamp("createdDate"),      // timestamp -> java.sql.Timestamp
                rs.getInt("countOfAnswer")
        );

        return jdbcTemplate.query(sql, rowMapper);
    }

    public Question findById(String questionId) throws SQLException {
        String sql = "SELECT * FROM QUESTIONS WHERE questionId=?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, questionId);
        };

        RowMapper rowMapper = rs -> new Question(
                rs.getInt("questionId"),            // bigint -> int
                rs.getString("writer"),              // varchar -> String
                rs.getString("title"),               // varchar -> String
                rs.getString("contents"),            // varchar -> String
                rs.getTimestamp("createdDate"),      // timestamp -> java.sql.Timestamp
                rs.getInt("countOfAnswer")
        );

        return jdbcTemplate.queryForObject(sql,pstmtSetter,rowMapper);
    }

    public void updateCountOfAnswer(Question question) throws SQLException {
        String sql = "UPDATE QUESTIONS SET countOfAnswer=? WHERE questionId=?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, question.getCountOfAnswer());
            pstmt.setInt(2, question.getQuestionId());
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }
}
