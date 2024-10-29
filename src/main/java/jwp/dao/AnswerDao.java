package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import jwp.model.Answer;

import java.sql.SQLException;
import java.util.List;

public class AnswerDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<Answer> read() throws SQLException {
        String sql = "SELECT * FROM ANSWERS";
        RowMapper rowMapper = rs -> new Answer(
                rs.getInt("answerId"),
                rs.getInt("questionId"),
                rs.getString("writer"),
                rs.getString("contents")
        );

        return jdbcTemplate.query(sql, rowMapper);
    }
}
