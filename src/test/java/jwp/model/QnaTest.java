// QuestionDaoTest.java
package jwp.dao;

import jwp.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class QnaTest {
    private QuestionDao questionDao;

    @BeforeEach
    void setUp() {
        questionDao = new QuestionDao();
    }

    @Test
    void insert_and_findById() throws Exception {
        // given
        String writer = "testUser";
        String title = "테스트 제목";
        String contents = "테스트 내용";
        Question question = new Question(writer, title, contents, LocalDateTime.now(), 0);

        // when
        Question savedQuestion = questionDao.insert(question);

        // then
        assertNotNull(savedQuestion.getQuestionId(), "저장된 Question의 ID는 null이 아니어야 합니다.");
        assertEquals(writer, savedQuestion.getWriter(), "작성자가 일치해야 합니다.");
        assertEquals(title, savedQuestion.getTitle(), "제목이 일치해야 합니다.");
        assertEquals(contents, savedQuestion.getContents(), "내용이 일치해야 합니다.");

        // verify with separate query
        Question foundQuestion = questionDao.findByQuestionId(savedQuestion.getQuestionId());
        assertEquals(savedQuestion.getQuestionId(), foundQuestion.getQuestionId(), "ID가 일치해야 합니다.");
        assertEquals(savedQuestion.getWriter(), foundQuestion.getWriter(), "작성자가 일치해야 합니다.");
        assertEquals(savedQuestion.getTitle(), foundQuestion.getTitle(), "제목이 일치해야 합니다.");
        assertEquals(savedQuestion.getContents(), foundQuestion.getContents(), "내용이 일치해야 합니다.");
    }

    @Test
    void insertWithKeyHolder() throws Exception {
        // given
        String writer = "testUser";
        String title = "KeyHolder 테스트";
        String contents = "KeyHolder를 통한 ID 검증";
        LocalDateTime now = LocalDateTime.now();
        Question question = new Question(writer, title, contents, now, 0);

        // when
        Question savedQuestion = questionDao.insert(question);

        // then
        assertTrue(savedQuestion.getQuestionId() > 0, "생성된 ID는 0보다 커야 합니다.");

        // KeyHolder를 통해 받은 ID로 다시 조회
        Question foundQuestion = questionDao.findByQuestionId(savedQuestion.getQuestionId());
        assertNotNull(foundQuestion, "KeyHolder로 받은 ID로 질문을 찾을 수 있어야 합니다.");
        assertEquals(savedQuestion.getQuestionId(), foundQuestion.getQuestionId());
    }

    @Test
    void insertAndVerifyAllFields() throws Exception {
        // given
        String writer = "testUser";
        String title = "상세 필드 테스트";
        String contents = "모든 필드 검증";
        LocalDateTime now = LocalDateTime.now();
        Question question = new Question(writer, title, contents, now, 0);

        // when
        Question savedQuestion = questionDao.insert(question);
        Question foundQuestion = questionDao.findByQuestionId(savedQuestion.getQuestionId());

        // then
        assertAll(
                () -> assertEquals(writer, foundQuestion.getWriter()),
                () -> assertEquals(title, foundQuestion.getTitle()),
                () -> assertEquals(contents, foundQuestion.getContents()),
                () -> assertEquals(0, foundQuestion.getCountOfAnswer()),
                () -> assertNotNull(foundQuestion.getCreatedDate()),
                () -> assertTrue(foundQuestion.getQuestionId() > 0)
        );
    }

    // 실제 운영 환경에서 발생할 수 있는 상황 테스트
    @Test
    void insertConcurrently() throws Exception {
        int threadCount = 5;
        Thread[] threads = new Thread[threadCount];
        Question[] savedQuestions = new Question[threadCount];

        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                try {
                    Question question = new Question(
                            "user" + index,
                            "title" + index,
                            "content" + index,
                            LocalDateTime.now(),
                            0
                    );
                    savedQuestions[index] = questionDao.insert(question);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }

        // Verify all questions were saved with unique IDs
        for (int i = 0; i < threadCount; i++) {
            assertNotNull(savedQuestions[i], "모든 Question이 저장되어야 합니다.");
            assertNotNull(savedQuestions[i].getQuestionId(), "모든 Question은 ID를 가져야 합니다.");

            // Verify each question can be found
            Question foundQuestion = questionDao.findByQuestionId(savedQuestions[i].getQuestionId());
            assertNotNull(foundQuestion, "저장된 모든 Question을 찾을 수 있어야 합니다.");
        }

        // Verify all IDs are unique
        for (int i = 0; i < threadCount; i++) {
            for (int j = i + 1; j < threadCount; j++) {
                assertNotEquals(
                        savedQuestions[i].getQuestionId(),
                        savedQuestions[j].getQuestionId(),
                        "모든 Question은 고유한 ID를 가져야 합니다."
                );
            }
        }
    }
}