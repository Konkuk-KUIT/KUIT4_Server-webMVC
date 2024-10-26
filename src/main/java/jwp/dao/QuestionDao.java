package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;
import jwp.model.User;

import java.sql.SQLException;
import java.util.List;

public class QuestionDao {
    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate();

    public void insert(Question question) throws SQLException {
        String sql = "INSERT INTO QUESTIONS VALUES (?, ?, ?, ?, ?)";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
            pstmt.setInt(5, 0);
        };

        jdbcTemplate.update(sql, pstmtSetter);
    }

    public void update(Question question) throws SQLException {
        String sql = "UPDATE QUESTIONS SET writer = ?, title = ?, contents = ? WHERE questionId = ?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setLong(4, question.getQuestionId());
        };

        jdbcTemplate.update(sql, pstmtSetter);
    }

    // todo id값을 인자로 받도록 변경?
    public void delete(Question question) throws SQLException {
        String sql = "DELETE FROM QUESTIONS WHERE questionId = ?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setLong(4, question.getQuestionId());
        };

        jdbcTemplate.update(sql, pstmtSetter);
    }

    public List<Question> findAll() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS";

        RowMapper rowMapper = rs -> new Question(rs.getLong("questionId"),
                    rs.getString("writer"),
                    rs.getString("title"),
                    rs.getString("contents"),
                    rs.getTimestamp("createdDate"),
                    rs.getInt("countOfAnswer"));

        return jdbcTemplate.query(sql, rowMapper);
    }

    public Question findByQuestionId(String questionId) throws SQLException {
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM Question WHERE questionId = ?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, questionId);
        };

        RowMapper rowMapper = rs -> new Question(rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer"));

        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }
}
