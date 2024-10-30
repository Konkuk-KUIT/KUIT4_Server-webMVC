package core.db.question;

import jwp.model.Question;

import java.sql.SQLException;
import java.util.Collection;

public interface QuestionRepository {
    void addQuestion(Question question) throws SQLException;
    Question findQuestionById(Long id) throws SQLException;
    Collection<Question> findAll() throws SQLException;
}
