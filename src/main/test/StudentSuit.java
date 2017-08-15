import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import javax.naming.NamingException;

/*ВНИМАНИЕ !!! Перед запуском тестов база данных должна быть пустой. Вы можете это сделать запустить скрипт создания базы — Часть 15 — Новая структура данных. Такой вариант не является удачным, но в данном случае мне хотелось сразу наполнить базу данными. Ну и заодно увидеть, что не так именно в таком тесте. Вы можете сделать тесты более удобными и правильными.*/
/**
 *
 * Spring предоставляет немало интересных вохможностей по тестированию. Одна из них — возможность создания объектов, к котороым можно обратиться через JNDI — мы ведь используем данный способ. Чтобы не переделывать конфигурацию можно использовать нужные классы. Мы создаем эмулятор JNDI и помещаем туда DriverManagerDataSource, который связан с тем же именем, что и при использовании Tomcat. Также следует обратить внимание, каким образом создается целый набор классов, который мы запускаем для тестирование — я имею в виду аннотацию@Suite.SuiteClasses
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    StudentFacadeTest.class,
    StudentControllerTest.class
})
public class StudentSuit {

    @BeforeClass
    public static void setUpClass() throws Exception {
        try {
            SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
            DriverManagerDataSource ds = new DriverManagerDataSource("jdbc:mysql://localhost:3306/db_applicant", "root", "int1984");
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            builder.bind("java:comp/env/studentDS", ds);
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
            Assert.fail();
        } catch (NamingException ex) {
            ex.printStackTrace();
            Assert.fail();
        }
    }
}