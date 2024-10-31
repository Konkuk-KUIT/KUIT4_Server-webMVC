package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;

import java.sql.SQLException;
import java.util.List;

public class AnswerDao {

    private JdbcTemplate<Answer> jdbcTemplate = new JdbcTemplate();

    public List<Answer> findAnswersByQuestionId(Long questionId) throws SQLException {
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

    public Answer findAnswerById(Long answerId) throws SQLException {
        String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE answerId = ?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setLong(1, answerId);
        };

        RowMapper rowMapper = rs -> new Answer(rs.getLong("answerId"),
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getLong("questionId"));

        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }

    // insert문을 활용하여 테이블에 row를 넣는 메소드
    public Answer insert(Answer answer) throws SQLException {

        KeyHolder keyHolder = new KeyHolder();

        String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";
        // 파라미터를 어떻게 설정할 지 람다식을 활용해서 표현해주면 됨
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, answer.getWriter());
            pstmt.setString(2, answer.getContents());
            pstmt.setTimestamp(3, answer.getCreatedDate());
            pstmt.setLong(4, answer.getQuestionId());
        };

        jdbcTemplate.update(sql, pstmtSetter, keyHolder);

        return findAnswerById(keyHolder.getId());
    }
}
