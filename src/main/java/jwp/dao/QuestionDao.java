package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import jwp.model.Question;

import java.sql.SQLException;
import java.util.List;

public class QuestionDao {

    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate();

    public List<Question> showQuestionList() throws SQLException {
        String sql = "select * from QUESTIONS";

        RowMapper rowMapper = rs -> new Question(rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getString("createdDate"),
                rs.getString("countOfAnswer")
        );

        return jdbcTemplate.query(sql, rowMapper);
    }
}
