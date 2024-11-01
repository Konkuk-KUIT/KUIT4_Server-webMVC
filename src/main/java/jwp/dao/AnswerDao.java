package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;

import java.sql.SQLException;
import java.util.List;

public class AnswerDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<Answer> read(int questionId) throws SQLException {
        String sql = "SELECT * FROM ANSWERS WHERE questionId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, questionId);
        };
        RowMapper rowMapper = rs -> new Answer(
                rs.getInt("answerId"),
                rs.getInt("questionId"),
                rs.getString("writer"),
                rs.getString("contents")
        );
        return jdbcTemplate.query(sql, pstmtSetter, rowMapper);
    }

    public Answer insert(Answer answer) throws SQLException {
        KeyHolder keyHolder = new KeyHolder();
        String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, answer.getWriter());
            pstmt.setString(2, answer.getContents());
            pstmt.setDate(3, answer.getCreatedDate());
            pstmt.setInt(4, answer.getQuestionId());
        };
        jdbcTemplate.update(sql, pstmtSetter, keyHolder);
        return findByAnswerId(keyHolder.getId());
    }

    public Answer findByAnswerId(int answerId) throws SQLException {
        String sql = "SELECT answerId, writer, contents, createdDate, questionId " +
                "FROM ANSWERS WHERE answerId=?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, answerId);
        };

        RowMapper<Answer> rowMapper = rs -> new Answer(rs.getInt("questionId"),
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getDate("createdDate"),
                rs.getInt("answerId"));

        return (Answer) jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }
}
