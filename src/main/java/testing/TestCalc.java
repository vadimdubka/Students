package testing;

import org.junit.*;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import static org.junit.Assert.assertEquals;

public class TestCalc {
    // Вот где у нас будет вызов нашего тестирующего класса
    public static void main(String[] args) {
        JUnitCore core = new JUnitCore();
        core.addListener(new CalcListener());
        core.run(TestCalc.class);
    }


    /*@Test - это аннотация, которая обозначает, что метод должен быть вызван для тестирования чего-нибудь
    МЫ НИГДЕ не вызываем метод getSumTest. За нас его исполняет JUnitCore. Он находит методы, которые мы обозначили аннотацией @Test и выполняет их.*/
    @Test
    public void getSumTest() {
        Calc c = new Calc();
        // Этот метод вызовет исключение, если результат нашего калькулятора будет отличен от 50
        assertEquals(c.getSum(20, 30), 50);
    }

    @Test
    public void getSubtractionTest() {
        Calc c = new Calc();
        // Этот метод вызовет исключение, если результат нашего калькулятора будет отличен от -10
        assertEquals(-10, c.getSubtraction(20, 30));
    }

    @Test
    // Обратите внимание аннотацию - она говорит, что тест будет проигнорирован. Если ее убрать, то сообщение появиться
    @Ignore
    public void testIgnored() {
        System.out.println("Ignored test");
    }

    static class CalcListener extends RunListener {
        /*Открою небольшую тайну — сам JUnitCore не генерит сообщений. Он использует классы, которые унаследованый от RunListener и вызывает его методы при наступлении определенных событий. Если залезть в исходники, то можно увидеть, что JUnitCore регистрирует TextListener, который является наследником RunListener’а.
      Давайте попробуем написать свой слушатель.
      А вот его реализация.
      Если Вы откроете документацию и найдете описание RunListener, то сможете написать свои собственные методы для обработки начала тестирования метода, начала тестирования вообще и т.д. Советую прочитать внимательно.*/
        @Override
        public void testStarted(Description desc) {
            System.out.println("Started:" + desc.getDisplayName());
        }

        @Override
        public void testFinished(Description desc) {
            System.out.println("Finished:" + desc.getDisplayName());
        }

        @Override
        public void testFailure(Failure fail) {
            System.out.println("Failed:" + fail.getDescription().getDisplayName() + " [" + fail.getMessage() + "]");
        }
    }

    /*        Если Вам не требуется отслеживать имена методов, а просто получить известие о том, что что-то пошло не так можно использовать аннотации@Before, @After, @BeforeClass, @AfterClass. Методы, которые снабжены такой аннотацией будут вызываться: в начале каждого метода, в конце каждого метода, в начале тестирования, в конце тестирования.
        Вот для примера код — обратите внимание, что методы с аннотацией @Before и @After не должны быть static. А методы с аннотацией@BeforeClass и @AfterClass — должны быть static.
        */

    @BeforeClass
    public static void allTestsStarted() {
        System.out.println("All tests started");
    }

    @AfterClass
    public static void allTestsFinished() {
        System.out.println("All tests finished");
    }

    @Before
    public void testStarted() {
        System.out.println("Started");
    }

    @After
    public void testFinished() {
        System.out.println("Finished");
    }



/*Бывают случаи, когда порядок вызовов методов важен для ятестирования. В таком случае можно воспользоваться сортировкой.
Тогда надо переписать код следующим образом:
@Test
    public void get001SumTest() {
        testing.Calc c = new testing.Calc();
        assertEquals(50, c.getSum(20, 30));
    }

    // Обратите внимание на название - оно теперь сделано таким для соблюдения порядка
    @Test
    public void get002SubtractionTest() {
        testing.Calc c = new testing.Calc();
        assertEquals(-10, c.getSubtraction(20, 30));
    }

    // Метод возвращает компаратор, который позволяет отсортировать методы в алфавитном порядке
    private static Comparator forward() {
        return new Comparator() {

            public int compare(Object o1, Object o2) {
                Description d1 = (Description) o1;
                Description d2 = (Description) o2;
                return d1.getDisplayName().compareTo(d2.getDisplayName());
            }
        };
    }

    public static void main(String[] args) {
        JUnitCore core = new JUnitCore();
        core.addListener(new CalcListener());
        // Обратите внимание на этот вызов. Здесь использован объект Request, который позволяет запускать не один, а сразу много классов для тестирования. В документации внимательно посмотрите на его методы и Вы сможете разобраться.
        core.run(Request.aClass(testing.TestCalc.class).sortWith(forward()));
    }
 */


}
