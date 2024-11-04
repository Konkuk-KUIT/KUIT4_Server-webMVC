package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;

import java.sql.SQLException;
import java.util.List;

public class QuestionDao {

    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();

    public List<Question> findAll() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS";
        RowMapper<Question> rowMapper = rs -> new Question(
                rs.getInt("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getDate("createdDate"),
                rs.getInt("countOfAnswer")
        );
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void insert(Question question) throws SQLException {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setDate(4, question.getCreatedDate());
            pstmt.setInt(5, question.getCountOfAnswer());
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }
}
