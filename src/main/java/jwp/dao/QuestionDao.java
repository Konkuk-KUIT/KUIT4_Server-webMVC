package jwp.dao;

import core.jdbc.JdbcTemplete;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;

import java.sql.SQLException;
import java.util.List;

public class QuestionDao {

    private final JdbcTemplete<Question> jdbcTemplete = new JdbcTemplete<>();

    public Question insert(Question question) throws SQLException {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new KeyHolder();
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setObject(4, question.getCreatedDate());
            pstmt.setInt(5, question.getCountOfAnswer());
        };

        jdbcTemplete.update(sql, pstmtSetter, keyHolder);

        return findByQuestionId(keyHolder.getId());
    }

    public Question findByQuestionId(int questionId) throws SQLException {
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS WHERE questionId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> pstmt.setInt(1, questionId);

        RowMapper<Question> rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate").toLocalDateTime(),
                rs.getInt("countOfAnswer")
        );

        return jdbcTemplete.queryForObject(sql, pstmtSetter, rowMapper);
    }

    public List<Question> findAll() throws SQLException {
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS";

        RowMapper<Question> rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate").toLocalDateTime(),
                rs.getInt("countOfAnswer")
        );
        return jdbcTemplete.query(sql, rowMapper);
    }

    public void update(Question question) throws SQLException {
        String sql = "UPDATE QUESTIONS SET title = ?, contents = ?, createdDate = ? WHERE questionId = ?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getTitle());
            pstmt.setString(2, question.getContents());
            pstmt.setObject(3, question.getCreatedDate());
            pstmt.setLong(4, question.getQuestionId());
        };

        jdbcTemplete.update(sql, pstmtSetter);
    }
}
