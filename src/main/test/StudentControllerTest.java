import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import web.controller.ApplicantController;
import web.controller.ProfessionController;
import web.controller.SubjectController;

/*ВНИМАНИЕ !!! Перед запуском тестов база данных должна быть пустой. Вы можете это сделать запустить скрипт создания базы — Часть 15 — Новая структура данных. Такой вариант не является удачным, но в данном случае мне хотелось сразу наполнить базу данными. Ну и заодно увидеть, что не так именно в таком тесте. Вы можете сделать тесты более удобными и правильными.*/

/*В нем самое главное — это использование так называемых mock-объектов (я бы перевел это как подставных/тренировочных). Как вы уже видели в метод контроллера мы должны передать объекты, которые реализуют интерфейс HttpServletRequest и HttpServletResponse. Но это интерфейсы, а нам нухны реальные объекты. И Spring предоставляет нам такой набор — их возможности достаточно большие — я настоятельно советую вам посмотреть документацию на них.*/
@ContextConfiguration(locations = {"classpath:StudentExample2.xml", "classpath:StudentDatabase2.xml", "classpath:StudentController.xml"})
//@TransactionConfiguration(transactionManager = "txManager")
@Transactional(transactionManager = "txManager")
public class StudentControllerTest extends AbstractTransactionalJUnit4SpringContextTests {


    @Autowired
    private ProfessionController profession;
    @Autowired
    private SubjectController subject;
    @Autowired
    private ApplicantController applicant;

    @Test
    public void professionTest() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("GET");
        try {
            profession.handleRequest(req, new MockHttpServletResponse());
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void subjectTest() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("GET");
        try {
            subject.handleRequest(req, new MockHttpServletResponse());
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void applicantTest() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("GET");
        try {
            applicant.handleRequest(req, new MockHttpServletResponse());
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail();
        }
    }
}
