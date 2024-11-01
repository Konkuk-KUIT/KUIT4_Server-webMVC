package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;

import java.sql.SQLException;
import java.util.List;

public class QuestionDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<Question> read() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS";
        RowMapper rowMapper = rs -> new Question(
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getInt("countOfAnswer"),
                rs.getInt("questionId"));

        return jdbcTemplate.query(sql, rowMapper);
    }

    public Question insert(Question question) throws SQLException {

        String sql = "INSERT INTO QUESTIONS (writer,title,contents,createdDate) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new KeyHolder();

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setDate(4, question.getCreatedDate());
        };
        jdbcTemplate.update(sql, pstmtSetter, keyHolder);
        return findByQuestionId(keyHolder.getId());
    }

    public Question findByQuestionId(int questionId) throws SQLException {
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, questionId);
        };

        RowMapper rowMapper = rs -> new Question(
                rs.getString("writer"),
                rs.getInt("countOfAnswer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getInt("questionId")
        );

        return (Question) jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }

    public void update(Question question) throws SQLException {
        String sql = "UPDATE QUESTIONS SET title = ?, contents = ? WHERE questionId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getTitle());
            pstmt.setString(2, question.getContents());
            pstmt.setInt(3, question.getQuestionId());
        };
        jdbcTemplate.update(sql, pstmtSetter);

    }

    public void updateCountOfAnswer(Question question) throws SQLException {
        String sql = "UPDATE QUESTIONS SET countOfAnswer = ? WHERE questionId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, question.getCountOfAnswer());
            pstmt.setInt(2, question.getQuestionId());
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }
}
