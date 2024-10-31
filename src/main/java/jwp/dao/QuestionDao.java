package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;
import jwp.model.KeyHolder;
import jwp.model.Question;

import java.sql.SQLException;
import java.util.List;

public class QuestionDao {

    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate();

    public Question insert(Question question) throws SQLException {

        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) " +
                "VALUES (?, ?, ?, ?, ?)";

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

    public Question findByQuestionId(Long id) throws SQLException {

        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";
        return jdbcTemplate.queryForObject(sql, pstmt -> pstmt.setLong(1, id), rs ->
                new Question(
                        rs.getLong("questionId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate"),
                        rs.getInt("countOfAnswer")
                )
        );
    }

    public List<Question> findAllQuestions() throws SQLException {

        String sql = "SELECT * FROM QUESTIONS";
        RowMapper rowMapper = rs -> new Question(rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer"));
        return jdbcTemplate.query(sql, rowMapper);
    }
}
