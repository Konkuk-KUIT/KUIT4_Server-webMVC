package jwp.dao;

import core.jdbc.*;
import jwp.model.Question;

import java.sql.*;
import java.util.List;

public class QuestionDao {
    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();

    //TODO 1. findAll
    public List<Question> findAll() throws SQLException{
        String sql = "SELECT * FROM QUESTIONS";
        RowMapper<Question> rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer")
        );
        return jdbcTemplate.query(sql, rowMapper);
    }

    //TODO 2. insert
    public Question insert(Question question) throws SQLException {
        String sql = "INSERT INTO questions (writer, title, contents, createdDate) VALUES (?, ?, ?, NOW())";
        KeyHolder keyHolder = new KeyHolder();  // KeyHolder 인스턴스 생성

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.executeUpdate();

            // 생성된 키(자동 증가 ID)를 얻어와서 KeyHolder에 저장
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    keyHolder.setId(rs.getLong(1));
                }
            }
        }

        // 삽입된 Question 객체의 ID를 사용하여 데이터베이스에서 다시 조회
        return findByQuestionId(keyHolder.getId());
    }

    //TODO 3. findByQuestionId
    //ID를 기반으로 Question 객체를 조회
    public Question findByQuestionId(long questionId) throws SQLException {
        String sql = "SELECT * FROM questions WHERE questionId = ?";
        /*
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, questionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Question(
                            rs.getLong("questionId"),
                            rs.getString("writer"),
                            rs.getString("title"),
                            rs.getString("contents"),
                            rs.getTimestamp("createdDate"),
                            rs.getInt("countOfAnswer")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error while retrieving question", e);
        }
        return null;*/
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setLong(1, questionId);
        };
        RowMapper rowMapper = rs -> new Question(rs.getInt("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer"));
        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }
}
