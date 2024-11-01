package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;

import java.sql.SQLException;
import java.util.List;

public class QuestionDao {

    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate();

    // TODO 1. insert, update, delete
    public Question insert(Question question) throws SQLException {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setDate(4, question.getCreatedDate());
            pstmt.setInt(5, question.getCountOfAnswer());
        };
        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(sql, pstmtSetter, keyHolder);
        return findByQuestionId(keyHolder.getId());
    }

    public void update(Question question) throws SQLException {
        String sql = "UPDATE QUESTIONS SET writer = ?, title = ?, contents = ?, createdDate = ?, countOfAnswer = ? WHERE questionId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setDate(4, question.getCreatedDate());
            pstmt.setInt(5, question.getCountOfAnswer());
            pstmt.setInt(6, question.getQuestionId());
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }

    public void delete(Question question) throws SQLException {
        String sql = "DELETE FROM QUESTIONS WHERE userId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, question.getQuestionId());
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }

    // TODO 2. findAll, findByUserId
    public List<Question> findAll() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS ORDER BY questionId";
        RowMapper rowMapper = rs -> new Question(rs.getInt("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getDate("createdDate"),
                rs.getInt("countOfAnswer"));
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Question findByQuestionId(int questionId) throws SQLException {
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS WHERE questionId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, questionId);
        };
        RowMapper rowMapper = rs -> new Question(rs.getInt("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getDate("createdDate"),
                rs.getInt("countOfAnswer"));
        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }
}
