package jwp.dao;

import core.jdbc.*;
import jwp.model.Question;
import jwp.model.User;

import java.sql.*;
import java.util.List;

public class QuestionDao {
    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate();

    public Question insert(Question question) throws SQLException {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new KeyHolder();

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setTimestamp(4, question.getCreatedDate());
            pstmt.setInt(5, question.getCountOfAnswer());
        };

        jdbcTemplate.update(sql, pstmtSetter, keyHolder);

        return findByQuestionId(keyHolder.getId());
    }

    public void update(Question question) throws SQLException {
        String sql = "UPDATE QUESTIONS SET writer=?, title=?, contents=?, createdDate=?, countOfAnswer=? WHERE questionId=?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setTimestamp(4, question.getCreatedDate());
            pstmt.setInt(5, question.getCountOfAnswer());
            pstmt.setLong(6, question.getQuestionId());
        };

        jdbcTemplate.update(sql,pstmtSetter);
    }

    public List<Question> findAll() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS";
        RowMapper rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer"));

        return jdbcTemplate.query(sql, rowMapper);
    }

    public Question findByQuestionId(int questionId) throws SQLException{
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS WHERE questionId=?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, questionId);
        };

        RowMapper rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer"));

        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }

    public void updateCountOfAnswer(Question question) throws SQLException {
        String sql = "UPDATE QUESTIONS SET countOfAnswer=? WHERE questionId=?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, question.getCountOfAnswer());
            pstmt.setLong(2, question.getQuestionId());
        };

        jdbcTemplate.update(sql, pstmtSetter);
    }
}


//    private Long questionId;
//    private String writer;
//    private String title;
//    private String contents;
//    private Timestamp createdDate;
//    private int countOfAnswer;