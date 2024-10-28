package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;
import jwp.model.User;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class AnswerDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<Answer> findAnswerByQuestionId(Long questionId) throws SQLException {
        String sql = "SELECT * FROM ANSWERS WHERE questionId = ?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setLong(1, questionId);
        };

        RowMapper rowMapper = rs -> new Answer(rs.getLong("answerId"),
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getLong("questionId"));

        return jdbcTemplate.query(sql, pstmtSetter, rowMapper);
    }
}
