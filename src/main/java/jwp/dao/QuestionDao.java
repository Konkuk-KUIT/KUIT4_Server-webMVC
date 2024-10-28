package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;
import jwp.model.User;

import java.sql.SQLException;
import java.util.List;

public class QuestionDao {

    private JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate();

    public List<Question> read() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS";
        RowMapper rowMapper = rs -> new Question(rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer"));

        return jdbcTemplate.query(sql, rowMapper);
    }

    // insert문을 활용하여 테이블에 row를 넣는 메소드
    public Question insert(Question question) throws SQLException {

        KeyHolder keyHolder = new KeyHolder();

        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
        // 파라미터를 어떻게 설정할 지 람다식을 활용해서 표현해주면 됨
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


    public void update(Question question) throws SQLException {

        String sql = "UPDATE QUESTIONS SET writer = ?, title = ?, contents = ?, createdDate = ?, countOfAnswer = ? WHERE questionId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setTimestamp(4, question.getCreatedDate());
            pstmt.setInt(5, question.getCountOfAnswer());
            pstmt.setLong(6, question.getQuestionId());
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }


    // id를 활용해서 question을 얻어오는 메소드
    public Question findByQuestionId(Long questionId) throws SQLException {
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS WHERE questionId = ?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setLong(1, questionId);
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
