package core.db;

import jwp.dao.AnswerDao;
import jwp.model.Answer;

import java.sql.SQLException;
import java.util.Collection;

public class DBAnswerRepository implements AnswerRepository {

    private AnswerDao answerDao;
    private static DBAnswerRepository dbAnswerRepository;

    public DBAnswerRepository() {
        this.answerDao = new AnswerDao();
    }

    public static DBAnswerRepository getInstance() {
        if (dbAnswerRepository == null) {
            dbAnswerRepository = new DBAnswerRepository();
            return dbAnswerRepository;
        }
        return dbAnswerRepository;
    }

    @Override
    public void addAnswer(Answer answer) throws SQLException {
        answerDao.insert(answer);
    }

    @Override
    public Answer findAnswerById(Long id) throws SQLException {
        return answerDao.findByAnswerId(id);
    }

    @Override
    public Collection<Answer> findAll() throws SQLException {
        return answerDao.findAll();
    }

    @Override
    public Collection<Answer> findAllByQuestionId(Long questionId) throws SQLException {
        return answerDao.findAllByQuestionId(questionId);
    }

    public void update(Answer answer) throws SQLException {
        answerDao.update(answer);
    }

    public void delete(Long answerId) throws SQLException {
        Answer foundQuestion = answerDao.findByAnswerId(answerId);
        if(foundQuestion != null) answerDao.delete(foundQuestion);
    }
}
