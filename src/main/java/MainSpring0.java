import facade.ProfessionFacade;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import view.ProfessionView;

/*давайте посмотрим, как Spring может загрузить нашу конфигурацию.*/
public class MainSpring0 {

    public static void main(String[] args) {
        /*Загрузка контекста. В качестве параметра мы передаем список файлов, в которых прописана конфигурация. Как вы уже догадались, файлов может быть несколько — если конечно вам это надо.
       Важно: учтите, что путь до файла является относительным текущего каталога, даже если вы указали начальный «/».*/
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/StudentExample.xml", "src/main/resources/StudentDatabase.xml"); //

        /*IoC
        В общем-то и все — ваш бин готов к работе. Можете посмотреть, что выводится на консоль. Также вы можете заменой одного символа поменять реализацию нашего DAO — просто меняем professionDAO_1 на professionDAO_2 у бина professionFacade и запускаем наше приложения снова. Пример находится в проекте Spring_01.

Ради эксперимента вы можете сделать два свойства у класса ProfessionFacade2 — для двух DAO. Одному присвоить значениеprofessionDAO_1, а второму professionDAO_2.*/

        ProfessionFacade pf = (ProfessionFacade) context.getBean("professionFacade"); // После загрузки контекста вы просто обращаетесь к нему с просьбой дать бин по имени
        ProfessionView pv = new ProfessionView();
        pf.addProfession(pv);
        pf.updateProfession(pv);
        pf.deleteProfession(pv);
        pf.findProfession();


        /*AOP*/
        /*
        1.Мы теперь получаем бин по имени professionFacadeProxy. Но что еще более важно — приводим его к типу ProfessionFacade2. Т.е. для нас он остается все тем же ProfessionFacade2, хотя по сути это не совсем так
        2. Приведение к типу ProfessionFacade2 дает нам возможность работать с этим объектом, как с нашим изначальным классом.
        Теперь у нас появился замечательный механизм — мы описали интерсептор и указали в конфигурации какой класс и какими интерсепторами надо «оборачивать». Теперь по сути при входе в нужный нам метод мы можем что-то сделать перед его реальным исполнением и также сделать что-то после его окончания. В нашем случае мы сделали просто два вывода на экран. Но ведь можем сделать что-то более существенное. Например — открыть транзакцию и закрыть. О чем мы собственно и думали. А значит мы можем перейти к третьей части.
        Как мы видели раньше, Spring предоставляет нам практически все необходимое. Конечно же мы рассмотрели достаточно простой вариант работы интерсептора. Он может быть гораздо сложнее — мы можем указать методы, которые мы хотим «окружить заботой», можем указать не один, а несколько интерсепторов и т.д. Но опять повторюсь — данная статья не претендует на роль глубокого исследования возможностей Spring — я хочу показать основные возможности.*/
        ProfessionFacade pf2 = (ProfessionFacade) context.getBean("professionFacadeProxy");
        ProfessionView pv2 = new ProfessionView();
        pf2.addProfession(pv2);
        pf2.updateProfession(pv2);
        pf2.deleteProfession(pv2);
        pf2.findProfession();

        /*БД*/
        ProfessionFacade pf3 = (ProfessionFacade) context.getBean("professionFacadeDB");
        ProfessionView pv3 = new ProfessionView();
        pv3.setProfessionName("Java Developer");
        Long id = (Long) pf3.addProfession(pv3); // !!! самостоятельно привел к Long
        pv3.setProfessionId(id);
        pf3.updateProfession(pv3);
        pf3.deleteProfession(pv3);
        pf3.findProfession();

    }
}