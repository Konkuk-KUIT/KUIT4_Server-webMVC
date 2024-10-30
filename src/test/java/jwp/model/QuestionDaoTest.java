package jwp.model;

import jwp.dao.QuestionDao;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class QuestionDaoTest {
    static QuestionDao questionDao = new QuestionDao();
    static Timestamp timestamp = new Timestamp(0);
    static Question question = new Question("yuna", "kuit week5", "너무 어려워요 ㅠㅠ");

    @BeforeEach
    void insertQuestion() throws SQLException {
        questionDao.insert(question);
    }

    @AfterAll
    static void deleteQuestion() throws SQLException {
        questionDao.delete(question);
    }

//    @Test
//    void updateQuestion() throws SQLException {
//        Question newQuestion =
//
//        questionDao.update(question, newQuestion);
//
//
//    }

    @Test
    void getAllQuestions() throws SQLException {
        List<Question> questions = questionDao.findAll();
        List<Question> allQuestions = null;
        allQuestions.add(question);

        Assertions.assertEquals(allQuestions, questions);
    }

    @Test
    void getQuestionByWriter() throws SQLException {
        List<Question> questions = questionDao.findByWriter("yuna");

        List<Question> answerList = null;
        answerList.add(question);

        Assertions.assertEquals(answerList, questions);
    }

    @Test
    void getQuestionByTitle() throws SQLException {
        Assertions.assertEquals(question, questionDao.findByTitle("kuit week5"));
    }

    @Test
    void getQuestionById() throws SQLException {
        List<Question> questions = questionDao.findByWriter("yuna");
        int questionId = questionDao.findId(questions.get(0));

        Assertions.assertEquals(1, questionId);
    }


}
