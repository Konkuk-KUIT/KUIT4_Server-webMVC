package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;
import jwp.util.KeyHolder;

import java.sql.SQLException;
import java.util.List;

public class QuestionDao {

    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();

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
        String sql = "UPDATE QUESTIONS SET title = ?, contents = ? WHERE questionId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getTitle());
            pstmt.setString(2, question.getContents());
            pstmt.setLong(3, question.getQuestionId());
        };
        jdbcTemplate.update(sql, pstmtSetter);
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

    public Question findByQuestionId(long questionId) throws SQLException {
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> pstmt.setLong(1, questionId);
        RowMapper<Question> rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer")
        );
        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }
}
