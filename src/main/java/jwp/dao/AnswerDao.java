package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;

import java.sql.SQLException;
import java.util.List;

public class AnswerDao {
    private final JdbcTemplate<Answer> jdbcTemplate = new JdbcTemplate();

    public void insert(Answer answer) throws SQLException {
        String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, answer.getWriter());
            pstmt.setString(2, answer.getContents());
            pstmt.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
            pstmt.setLong(4, answer.getQuestionId());
        };

        jdbcTemplate.update(sql, pstmtSetter);
    }

    public void update(Answer answer) throws SQLException {
        String sql = "UPDATE ANSWERS SET writer = ?, contents = ? WHERE answerId = ?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, answer.getWriter());
            pstmt.setString(2, answer.getContents());
            pstmt.setLong(3, answer.getAnswerId());
        };

        jdbcTemplate.update(sql, pstmtSetter);
    }

    public void delete(Answer answer) throws SQLException {
        String sql = "DELETE FROM ANSWERS WHERE answerId = ?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setLong(1, answer.getAnswerId());
        };

        jdbcTemplate.update(sql, pstmtSetter);
    }

    public List<Answer> findAll() throws SQLException {
        String sql = "SELECT * FROM ANSWERS";

        RowMapper rowMapper = rs -> new Answer(rs.getLong("answerId"),
                    rs.getString("writer"),
                    rs.getString("contents"),
                    rs.getTimestamp("createdDate"),
                    rs.getLong("questionId"));

        return jdbcTemplate.query(sql, rowMapper);
    }

    public Answer findByAnswerId(Long answerId) throws SQLException {
        String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE answerId = ?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setLong(1, answerId);
        };

        RowMapper rowMapper = rs -> new Answer(rs.getLong("answerId"),
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getLong("questionId"));

        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }

    public List<Answer> findAllByQuestionId(Long questionId) throws SQLException {
        String sql = "SELECT * FROM ANSWERS WHERE questionId = ?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setLong(1, questionId);
        };

        RowMapper rowMapper = rs -> new Answer(rs.getLong("answerId"),
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getLong("questionId"));

        return jdbcTemplate.queryForObjects(sql, pstmtSetter, rowMapper);
    }
}
