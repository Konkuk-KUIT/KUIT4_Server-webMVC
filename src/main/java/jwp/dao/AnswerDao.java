package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class AnswerDao {
    private final JdbcTemplate<Answer> jdbcTemplate = new JdbcTemplate<>();

    public List<Answer> findAll(int questionId) throws SQLException {
        String sql = "SELECT * FROM ANSWERS WHERE questionId = ?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, questionId);
        };

        RowMapper<Answer> rowMapper = rs -> {
            Answer answer = new Answer(
                    rs.getString("writer"),
                    rs.getString("contents"),
                    rs.getTimestamp("createdDate").toLocalDateTime(),
                    rs.getInt("questionId")
            );
            answer.setAnswerId(rs.getInt("answerId"));
            return answer;
        };

        return jdbcTemplate.queryForList(sql, pstmtSetter, rowMapper);
    }

    public Answer insert(Answer answer) throws SQLException {
        String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new KeyHolder();

        jdbcTemplate.update(sql, pstmt -> {
            pstmt.setString(1, answer.getWriter());
            pstmt.setString(2, answer.getContents());
            pstmt.setTimestamp(3, Timestamp.valueOf(answer.getCreatedDate()));
            pstmt.setInt(4, answer.getQuestionId());
        }, keyHolder);

        return answer;
    }

}
