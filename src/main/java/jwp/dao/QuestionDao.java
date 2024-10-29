package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;



public class QuestionDao {
    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();

    public List<Question> findAll() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS";
        RowMapper<Question> rowMapper = rs -> {
            Question question = new Question(
                    rs.getString("writer"),
                    rs.getString("title"),
                    rs.getString("contents"),
                    rs.getTimestamp("createdDate").toLocalDateTime(),
                    rs.getInt("countOfAnswer")
            );
            question.setQuestionId(rs.getInt("questionId")); // 이 줄을 추가
            return question;
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Question insert(Question question) throws SQLException {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new KeyHolder();

        jdbcTemplate.update(sql, pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setTimestamp(4, Timestamp.valueOf(question.getCreatedDate()));
            pstmt.setInt(5, question.getCountOfAnswer());
        }, keyHolder);

        return findByQuestionId(keyHolder.getId());
    }

    public Question findByQuestionId(int questionId) throws SQLException {
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, questionId);
        };

        RowMapper<Question> rowMapper = rs -> {
            Question question = new Question(
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate").toLocalDateTime(),
                rs.getInt("countOfAnswer")
            );
            question.setQuestionId(rs.getInt("questionId"));
            return question;
        };

        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }

    public void update(Question question) throws SQLException {
        String sql = "UPDATE QUESTIONS SET title = ?, contents = ? WHERE questionId = ?";

        jdbcTemplate.update(sql, pstmt -> {
            pstmt.setString(1, question.getTitle());
            pstmt.setString(2, question.getContents());
            pstmt.setInt(3, question.getQuestionId());
        });
    }

}
