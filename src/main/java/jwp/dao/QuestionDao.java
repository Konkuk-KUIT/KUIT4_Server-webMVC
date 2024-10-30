package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import jwp.model.Question;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {

    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();

    public List<Question> getAllQuestion() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS";
        List<Question> questions = new ArrayList<>();

        RowMapper rowMapper = rs -> new Question(
                rs.getInt("questionId"),            // bigint -> int
                rs.getString("writer"),              // varchar -> String
                rs.getString("title"),               // varchar -> String
                rs.getString("contents"),            // varchar -> String
                rs.getTimestamp("createdDate"),      // timestamp -> java.sql.Timestamp
                rs.getInt("countOfAnswer")
        );

        return jdbcTemplate.query(sql, rowMapper);
    }
}
