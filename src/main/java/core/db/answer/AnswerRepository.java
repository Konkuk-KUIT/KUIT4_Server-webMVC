package core.db.answer;

import jwp.model.Answer;

import java.sql.SQLException;
import java.util.Collection;

public interface AnswerRepository {
    void addAnswer(Answer answer) throws SQLException;
    Answer findAnswerById(Long id) throws SQLException;
    Collection<Answer> findAll() throws SQLException;
    Collection<Answer> findAllByQuestionId(Long questionId) throws SQLException;
}
