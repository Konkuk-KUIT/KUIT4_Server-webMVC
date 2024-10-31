package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import jwp.model.Answer;
import jwp.model.KeyHolder;
import java.sql.SQLException;
import java.util.List;

public class AnswerDao {

    private final JdbcTemplate<Answer> jdbcTemplate = new JdbcTemplate<>();

    public Answer insert(Answer answer) throws SQLException {

        String sql = "INSERT INTO ANSWERS (questionId, writer, contents, createdDate) " +
                "VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new KeyHolder();

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setLong(1, answer.getQuestionId());
            pstmt.setString(2, answer.getWriter());
            pstmt.setString(3, answer.getContents());
            pstmt.setTimestamp(4, answer.getCreatedDate());
        };

        jdbcTemplate.update(sql, pstmtSetter, keyHolder);
        return findByAnswerId(keyHolder.getId());
    }

    public Answer findByAnswerId(Long answerId) throws SQLException {

        String sql = "SELECT * FROM ANSWERS WHERE answerId = ?";
        return jdbcTemplate.queryForObject(sql, pstmt -> pstmt.setLong(1, answerId), rs ->
                new Answer(
                        rs.getLong("answerId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate"),
                        rs.getLong("questionId")
                )
        );
    }

    public List<Answer> findByQuestionId(Long questionId) throws SQLException {
        String sql = "SELECT * FROM ANSWERS WHERE questionId = ?";
        return jdbcTemplate.queryForObjects(sql, pstmt -> pstmt.setLong(1, questionId), rs ->
                new Answer(
                        rs.getLong("answerId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate"),
                        rs.getLong("questionId")
                )
        );
    }
}