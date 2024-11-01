package core.db;

import core.jdbc.JdbcTemplate;

import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;
import jwp.model.QuestionRequest;


import java.sql.SQLException;
import java.util.List;

public class QuestionDAO {
    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();
    KeyHolder keyHolder = new KeyHolder();
    public void insert(QuestionRequest question) throws SQLException {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setTimestamp(4, question.getCreatedDate());
            pstmt.setInt(5, question.getCountOfAnswer());
        };
        jdbcTemplate.updateQ(sql, pstmtSetter, keyHolder);
    }

    public void update(Question question) throws SQLException {
        String sql = "UPDATE QUESTIONS SET title = ?, contents = ? WHERE questionId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getTitle());
            pstmt.setString(2, question.getContents());
            pstmt.setLong(3, question.getQuestionId());
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }

    public void delete(Long questionId) throws SQLException {
        String sql = "DELETE FROM QUESTIONS WHERE questionId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setLong(1, questionId);
        };
        // jdbcTemplate.update(sql, pstmtSetter);
    }

    public List<Question> findAll() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS ORDER BY questionId DESC";
        RowMapper rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer")
        );
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Question findById(Long questionId) throws SQLException {
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setLong(1, questionId);
        };
        RowMapper rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer")
        );
        return jdbcTemplate.QueryForObject(sql, pstmtSetter, rowMapper);
    }

    /*public List<Question> findByWriter(String writer) throws SQLException {
        String sql = "SELECT * FROM QUESTIONS WHERE writer = ? ORDER BY questionId DESC";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, writer);
        };
        RowMapper rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer")
        );
        return jdbcTemplate.query(sql, pstmtSetter, rowMapper);
    }*/
}
