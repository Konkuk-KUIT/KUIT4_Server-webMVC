package core.db;

import jwp.dao.QuestionDao;
import jwp.model.Question;

import java.sql.SQLException;
import java.util.Collection;

public class DBQuestionRepository implements QuestionRepository {

    private QuestionDao questionDao;
    private static DBQuestionRepository dbQuestionRepository;

    public DBQuestionRepository() {
        this.questionDao = new QuestionDao();
    }

    public static DBQuestionRepository getInstance() {
        if (dbQuestionRepository == null) {
            dbQuestionRepository = new DBQuestionRepository();
            return dbQuestionRepository;
        }
        return dbQuestionRepository;
    }

    @Override
    public void addQuestion(Question question) throws SQLException {
        questionDao.insert(question);
    }

    @Override
    public Question findQuestionById(Long id) throws SQLException {
        return questionDao.findByQuestionId(id);
    }

    @Override
    public Collection<Question> findAll() throws SQLException {
        return questionDao.findAll();
    }

    public void update(Question question) throws SQLException {
        questionDao.update(question);
    }

    public void delete(Long questionId) throws SQLException {
        Question foundQuestion = questionDao.findByQuestionId(questionId);
        if(foundQuestion != null) questionDao.delete(foundQuestion);
    }
}
