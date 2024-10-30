package jwp.dao;

import core.jdbc.ConnectionManager;
import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;

import java.sql.*;
import java.util.List;

public class QuestionDao {
    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate();

    public void insert(Question question) throws SQLException {
        String sql = "INSERT INTO Questions VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getTitle());
            pstmt.setString(2, question.getWriter());
            pstmt.setString(3, question.getContents().toString());
            pstmt.setTimestamp(4, question.getCreatedDate());
            pstmt.setInt(5, question.getCountOfAnswer());
        };

        jdbcTemplate.update(sql, pstmtSetter);
    }

    public void update(Question question, Question newQuestion) throws SQLException {
        Connection conn = ConnectionManager.getConnection();

        String sql = "UPDATE FROM Questions SET title=?, contents=? WHERE questionId=?";
        int questionId = findId(question);
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, newQuestion.getTitle());
            pstmt.setString(2, newQuestion.getContents().toString());
            pstmt.setInt(3, questionId);
        };

        jdbcTemplate.update(sql, pstmtSetter);
    }

    public void delete(Question question) throws SQLException {
        String sql = "DELETE FROM Questions WHERE questionId=?";
        int questionId = findId(question);
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, questionId);
        };

        jdbcTemplate.update(sql, pstmtSetter);
    }



    public int findId(Question question) throws SQLException {
        String sql = "SELECT questionId FROM Questions WHERE writer=? AND createdDate=?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setTimestamp(2, new Timestamp(question.getCreatedDate().getTime()));
        };
        RowMapper<Integer> rowMapper = rs ->
            rs.getInt("questionId");

        return jdbcTemplate.queryId(sql, pstmtSetter, rowMapper);
    }

    public List<Question> findAll() throws SQLException {
        String sql = "SELECT * FROM Questions";
        RowMapper<Question> rowMapper = rs ->
                new Question(
                        rs.getString("title"),
                        rs.getString("writer"),
                        rs.getTimestamp("createdDate"),
                        rs.getString("contents"),
                        rs.getInt("countOfAnswer")
                );

        return jdbcTemplate.queryAll(sql, rowMapper);
    }

    public Question findById(int questionId) throws SQLException {
        String sql = "SELECT title, writer, createdDate, contents FROM Questions WHERE questionId=?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, questionId);
        };
        RowMapper<Question> rowMapper = rs ->
                new Question(
                        rs.getString("title"),
                        rs.getString("writer"),
                        rs.getTimestamp("createdDate"),
                        rs.getString("contents"),
                        rs.getInt("countOfAnswer")
                );

        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }

    public List<Question> findByWriter(String writer) throws SQLException {
        String sql = "SELECT title, writer, createdDate, contents FROM Questions WHERE writer=?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, writer);
        };
        RowMapper<Question> rowMapper= rs ->
                new Question(
                        rs.getString("title"),
                        rs.getString("writer"),
                        rs.getTimestamp("createdDate"),
                        rs.getString("contents"),
                        rs.getInt("countOfAnswer")
                );

        return jdbcTemplate.queryByAtt(sql, pstmtSetter, rowMapper);
    }

    public List<Question> findByTitle(String title) throws SQLException {
        String sql = "SELECT title, writer, createdDate, contents FROM Questions WHERE title LIKE '%?%'";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, title);
        };
        RowMapper<Question> rowMapper = rs -> new Question(
                rs.getString("title"),
                rs.getString("writer"),
                rs.getTimestamp("createdDate"),
                rs.getString("contents"),
                rs.getInt("countOfAnswer")
        );

        return jdbcTemplate.queryByAtt(sql, pstmtSetter, rowMapper);
    }

}
