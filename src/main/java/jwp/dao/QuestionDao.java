package jwp.dao;

import core.jdbc.*;
import jwp.model.Question;

import java.sql.*;
import java.util.List;

public class QuestionDao {
    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate();

    public int insert(Question question) throws SQLException {
        KeyHolder keyHolder = new KeyHolder();

        String sql = "INSERT INTO Questions (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?);";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setTimestamp(4, question.getCreatedDate());
            pstmt.setInt(5, question.getCountOfAnswer());
        };

        jdbcTemplate.update(sql, pstmtSetter, keyHolder);

        return keyHolder.getId();
    }

    public void update(int questionId, Question question) throws SQLException {
        String sql = "UPDATE Questions SET title=?, contents=? WHERE questionId=?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getTitle());
            pstmt.setString(2, question.getContents().toString());
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
        String sql = "SELECT questionId FROM Questions WHERE writer=? AND createdDate=? AND title=?";       // 나중에 시간 유지 되면 제목은 빼고 싶음
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setTimestamp(2, new Timestamp(question.getCreatedDate().getTime()));
            pstmt.setString(3, question.getTitle());
        };
        RowMapper<Integer> rowMapper = rs ->
            rs.getInt("questionId");

        return jdbcTemplate.queryId(sql, pstmtSetter, rowMapper);
    }

    public List<Question> findAll() throws SQLException {
        String sql = "SELECT * FROM Questions";
        RowMapper<Question> rowMapper = rs ->
                new Question(
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate"),
                        rs.getInt("countOfAnswer")
                );

        return jdbcTemplate.queryAll(sql, rowMapper);
    }

    public Question findById(int questionId) throws SQLException {
        String sql = "SELECT writer, title, createdDate, contents, countOfAnswer FROM Questions WHERE questionId=?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, questionId);
        };
        RowMapper<Question> rowMapper = rs ->
                new Question(
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate"),
                        rs.getInt("countOfAnswer")
                );

        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }

    public List<Question> findByWriter(String writer) throws SQLException {
        String sql = "SELECT writer, title, createdDate, contents, countOfAnswer FROM Questions WHERE writer=?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, writer);
        };
        RowMapper<Question> rowMapper= rs ->
                new Question(
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate"),
                        rs.getInt("countOfAnswer")
                );

        return jdbcTemplate.queryByAtt(sql, pstmtSetter, rowMapper);
    }

    public List<Question> findByTitle(String title) throws SQLException {
        String sql = "SELECT title, writer, createdDate, contents, countOfAnswer FROM Questions WHERE title LIKE '%?%'";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, title);
        };
        RowMapper<Question> rowMapper = rs -> new Question(
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer")
        );

        return jdbcTemplate.queryByAtt(sql, pstmtSetter, rowMapper);
    }

}
