package jwp.dao;

import core.jdbc.*;
import jwp.model.Question;
import jwp.model.User;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class QuestionDao {
    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate();

    public List<Question> findAll() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS";

        //todo 이건 questionId 까지 가져와야하지않나 흐으으음
        RowMapper<Question> rowMapper = rs -> new Question(
                rs.getInt("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate").toLocalDateTime(),
                rs.getInt("countOfAnswer"));

        return jdbcTemplate.query(sql, rowMapper);
    }
    public Question insert(Question question) throws SQLException {
        KeyHolder keyHolder=new KeyHolder();
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
        PreparedStatementSetter pstmtSetter= pstmt->{
            pstmt.setString(1,question.getWriter());
            pstmt.setString(2,question.getTitle());
            pstmt.setString(3,question.getContents());
            pstmt.setTimestamp(4, Timestamp.valueOf(question.getCreatedDate()));
            pstmt.setInt(5, question.getCountOfAnswer());
        };
        jdbcTemplate.update(sql,pstmtSetter,keyHolder);

        return findQuestionById(keyHolder.getId());
    }



    public Question findQuestionById(int questionId) throws SQLException {
        String sql = "SELECT questionId ,writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS WHERE questionId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, questionId);
        };
        //객체 반환

        //todo questionId 까지 ??
        RowMapper<Question> rowMapper = rs ->  new Question(
                    rs.getInt("questionId"),
                    rs.getString("writer"),
                    rs.getString("title"),
                    rs.getString("contents"),
                    rs.getTimestamp("createdDate").toLocalDateTime(),
                    rs.getInt("countOfAnswer")
            );
        return jdbcTemplate.queryforObject(sql, pstmtSetter, rowMapper);
    }
    public void update(Question question) throws SQLException {
        String sql="UPDATE QUESTIONS SET writer = ?, title = ? WHERE questionId = ?";
        PreparedStatementSetter pstmtSetter=pstmt->{
            pstmt.setString(2,question.getWriter());
            pstmt.setString(3,question.getTitle());
        };
        //todo: title과 contents만 고칠 수 있게 하자
        jdbcTemplate.update(sql,pstmtSetter);
    }

}
