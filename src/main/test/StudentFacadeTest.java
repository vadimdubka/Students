import facade.ApplicantFacade;
import facade.ProfessionFacade2;
import facade.SubjectFacade;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import view.ApplicantResultView;
import view.ApplicantView;
import view.ProfessionView2;
import view.SubjectView;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/*Для тестирования проще всего просто пересоздать базу данных и запустить тест — он специально написан в расчете на пустую базу данных. Зато сразу получим какие-нибудь тестовые данные.*/

/*
Наш класс StudentFacadeTest, который занимается тестированием, наследуется от классаAbstractTransactionalJUnit4SpringContextTests. Это позволяет нам использовать транзакции и даже отменять сделанные изменения. Что достаточно удобно.

Мы используем аннотацию @ContextConfiguration для указания, какие файлы используются для конфигурации Spring

Аннотация @TransactionConfiguration позволяет нам выбрать тот менеджер транзакций, который нами будет использоваться в случае тестирования. В нашем случае он у нас один, но вполне может быть ситуация, что ваше приложение в реальности будет работать под управлением Application Server J2EE. Значит и менеджер транзакций будет использоваться от самого сервера. Когда же мы тестируем нашу программу, то вполне вероятно, что использование J2EE сервера не очень удачная мысль и тогда нам потребуется иной менеджер транзакций. Вот для этого и нужна данная аннотация.*/
@ContextConfiguration(locations = {"classpath:StudentExample2.xml", "classpath:StudentDatabase2.xml"})
//@TransactionConfiguration(transactionManager = "txManager")
@Transactional(transactionManager = "txManager")
public class StudentFacadeTest extends AbstractTransactionalJUnit4SpringContextTests {

    /* аннотация — @Autowired. Она позволяет автоматически установить значение поля, что конечно же удобно.*/
    @Autowired
    private SubjectFacade subjectFacade;
    @Autowired
    private ProfessionFacade2 professionFacade;
    @Autowired
    private ApplicantFacade applicantFacade;

    @Test //@Test служит для обозначения методов, которые надо вызывать в процессе тестирования.
    @Rollback(false) //@Rollback — значение false говорит, что изменения в базе данных, которые сделаны в этом методе должны быть оставлены. Я это сделал намеренно.
    public void subjectTest() {
        SubjectView sv = new SubjectView();

        // Установим данные для предмета
        sv.setSubjectName("Mathematic");
        // Добавим
        Long idSubj = subjectFacade.addSubject(sv);
        // Перечитаем
        sv = subjectFacade.getSubject(idSubj);
        // Убедимся, что считывание совпадает с тем, что записывали
        Assert.assertTrue(sv.getSubjectName().equals("Mathematic"));
        // Изменим название предмета, запишем и снова убедимся, что все в порядке
        sv.setSubjectName("Mathematics");
        subjectFacade.updateSubject(sv);
        sv = subjectFacade.getSubject(idSubj);
        Assert.assertTrue(sv.getSubjectName().equals("Mathematics"));
        // Убедимся, что всего предметов пока один
        Assert.assertTrue(subjectFacade.findSubject().size() == 1);

        sv.setSubjectName("Physics");
        idSubj = subjectFacade.addSubject(sv);
        sv = subjectFacade.getSubject(idSubj);
        Assert.assertTrue(sv.getSubjectName().equals("Physics"));
        Assert.assertTrue(subjectFacade.findSubject().size() == 2);

        sv.setSubjectName("Chemist");
        idSubj = subjectFacade.addSubject(sv);
        sv = subjectFacade.getSubject(idSubj);
        Assert.assertTrue(sv.getSubjectName().equals("Chemist"));
        Assert.assertTrue(subjectFacade.findSubject().size() == 3);

        sv.setSubjectName("Chemist2");
        idSubj = subjectFacade.addSubject(sv);
        sv = subjectFacade.getSubject(idSubj);
        Assert.assertTrue(sv.getSubjectName().equals("Chemist2"));
        Assert.assertTrue(subjectFacade.findSubject().size() == 4);
        // Удалим предмет и убедимся, что общее количество уменьшилось
        subjectFacade.deleteSubject(sv);
        Assert.assertTrue(subjectFacade.findSubject().size() == 3);

        sv.setSubjectName("Literature");
        idSubj = subjectFacade.addSubject(sv);
        sv = subjectFacade.getSubject(idSubj);
        Assert.assertTrue(sv.getSubjectName().equals("Literature"));
        Assert.assertTrue(subjectFacade.findSubject().size() == 4);

        // Проверим, что работает поиск по списку ID
        List<SubjectView> list = subjectFacade.findSubject();
        List<Long> check = new LinkedList<Long>();
        for (SubjectView s : list) {
            check.add(s.getSubjectId());
        }
        Assert.assertTrue(subjectFacade.findSubjectById(check).size() == 4);
    }

    @Test
    @Rollback(false)
    public void professionTest() {
        ProfessionView2 pv = new ProfessionView2();

        // Добавим новую специальность
        pv.setProfessionName("Chemists");
        Long idProf = professionFacade.addProfession(pv);
        pv = professionFacade.getProfession(idProf);
        Assert.assertTrue(pv.getProfessionName().equals("Chemists"));
        // Исправим значение и убедимся. что так и сделано
        pv.setProfessionName("Chemist");
        professionFacade.updateProfession(pv);
        pv = professionFacade.getProfession(idProf);
        Assert.assertTrue(pv.getProfessionName().equals("Chemist"));
        // Всего специальностей одна штука
        Assert.assertTrue(professionFacade.findProfession().size() == 1);
        // Создадим список предметов для специальности
        List<SubjectView> svList = subjectFacade.findSubject();
        List<Long> check = new LinkedList<Long>();
        for (SubjectView sv : svList) {
            if (sv.getSubjectName().equals("Chemist") || sv.getSubjectName().equals("Physics")) {
                check.add(sv.getSubjectId());
            }
        }
        professionFacade.updateSubjectList(idProf, check);
        Assert.assertTrue(subjectFacade.findSubjectByProfession(idProf).size() == 2);

        pv.setProfessionName("Mathematician");
        idProf = professionFacade.addProfession(pv);
        pv = professionFacade.getProfession(idProf);
        Assert.assertTrue(pv.getProfessionName().equals("Mathematician"));
        Assert.assertTrue(professionFacade.findProfession().size() == 2);
        svList = subjectFacade.findSubject();
        check = new LinkedList<Long>();
        for (SubjectView sv : svList) {
            if (sv.getSubjectName().equals("Mathematics")
                    || sv.getSubjectName().equals("Physics") || sv.getSubjectName().equals("Literature")) {
                check.add(sv.getSubjectId());
            }
        }
        professionFacade.updateSubjectList(idProf, check);
        Assert.assertTrue(subjectFacade.findSubjectByProfession(idProf).size() == 3);

        pv.setProfessionName("Removed");
        pv.setSubjectList(new HashSet(svList));
        Long idProf2 = professionFacade.addProfession(pv);
        pv = professionFacade.getProfession(idProf2);
        Assert.assertTrue(pv.getProfessionName().equals("Removed"));
        Assert.assertTrue(professionFacade.findProfession().size() == 3);
        professionFacade.deleteProfession(pv);
        Assert.assertTrue(professionFacade.findProfession().size() == 2);
    }

    @Test
    @Rollback(false)
    public void applicantTest() {
        // Получаем список специальностей
        List<ProfessionView2> pList = professionFacade.findProfession();
        Assert.assertTrue(professionFacade.findProfession().size() == 2);
        ProfessionView2 pr1 = professionFacade.getProfession(pList.get(0).getProfessionId());
        ProfessionView2 pr2 = professionFacade.getProfession(pList.get(1).getProfessionId());
        Long applicantId = 0L;

        // Заполняем данные для абитуриента
        ApplicantView av = new ApplicantView();
        av.setLastName("Стрельцов1");
        av.setFirstName("Павел");
        av.setMiddleName("Сергеевич");
        av.setEntranceYear(2009);
        av.setProfessionId(pr1.getProfessionId());
        // Записываем
        applicantId = applicantFacade.addApplicant(av);
        // Считываем
        av = applicantFacade.getApplicant(applicantId);
        // Проверяем, что оценок у только что введенного абитуриента нет
        Assert.assertTrue(av.getApplicantResultList().size() == 0);
        // Добавляем оценки абитуриенту
        av.setApplicantResultList(createMark(pr1, applicantId, 1));
        applicantFacade.updateApplicantResult(av);
        // Перечитываем и убеждаемся, что оценки теперь есть
        av = applicantFacade.getApplicant(applicantId);
        Assert.assertTrue(av.getApplicantResultList().size() == pr1.getSubjectList().size());
        // Поробуем поменять фамилию у абитуриента
        av.setLastName("Стрельцов");
        applicantFacade.updateApplicant(av);
        av = applicantFacade.getApplicant(applicantId);
        // Убеждаемся, что изменения произошли
        Assert.assertTrue(av.getLastName().equals("Стрельцов"));
        // Перечитываем и убеждаемся, что оценки остались
        Assert.assertTrue(av.getApplicantResultList().size() == pr1.getSubjectList().size());

        av.setLastName("Иванов");
        av.setFirstName("Андрей");
        av.setMiddleName("Васильевич");
        av.setEntranceYear(2009);
        av.setProfessionId(pr1.getProfessionId());
        applicantId = applicantFacade.addApplicant(av);
        av = applicantFacade.getApplicant(applicantId);
        Assert.assertTrue(av.getApplicantResultList().size() == 0);
        av.setApplicantResultList(createMark(pr1, applicantId, 2));
        applicantFacade.updateApplicantResult(av);
        av = applicantFacade.getApplicant(applicantId);
        Assert.assertTrue(av.getApplicantResultList().size() == pr1.getSubjectList().size());

        av.setLastName("Смирнов");
        av.setFirstName("Сергей");
        av.setMiddleName("Петрович");
        av.setEntranceYear(2009);
        av.setProfessionId(pr2.getProfessionId());
        applicantId = applicantFacade.addApplicant(av);
        av = applicantFacade.getApplicant(applicantId);
        Assert.assertTrue(av.getApplicantResultList().size() == 0);
        av.setApplicantResultList(createMark(pr2, applicantId, 3));
        applicantFacade.updateApplicantResult(av);
        av = applicantFacade.getApplicant(applicantId);
        Assert.assertTrue(av.getApplicantResultList().size() == pr2.getSubjectList().size());

        av.setLastName("Затейников");
        av.setFirstName("Виктор");
        av.setMiddleName("Капитонович");
        av.setEntranceYear(2009);
        av.setProfessionId(pr2.getProfessionId());
        applicantId = applicantFacade.addApplicant(av);
        av = applicantFacade.getApplicant(applicantId);
        Assert.assertTrue(av.getApplicantResultList().size() == 0);
        av.setApplicantResultList(createMark(pr2, applicantId, 4));
        applicantFacade.updateApplicantResult(av);
        av = applicantFacade.getApplicant(applicantId);
        Assert.assertTrue(av.getApplicantResultList().size() == pr2.getSubjectList().size());

        av.setLastName("Федоров");
        av.setFirstName("Алексей");
        av.setMiddleName("Дмитриевич");
        av.setEntranceYear(2009);
        av.setProfessionId(pr2.getProfessionId());
        applicantId = applicantFacade.addApplicant(av);
        av = applicantFacade.getApplicant(applicantId);
        Assert.assertTrue(av.getApplicantResultList().size() == 0);
        av.setApplicantResultList(createMark(pr2, applicantId, 5));
        applicantFacade.updateApplicantResult(av);
        av = applicantFacade.getApplicant(applicantId);
        Assert.assertTrue(av.getApplicantResultList().size() == pr2.getSubjectList().size());

        Assert.assertTrue(applicantFacade.findApplicant().size() == 5);
    }

    @Test
    @Rollback(false)
    public void applicantDeleteTest() {
        List<ApplicantView> avList = applicantFacade.findApplicant();
        Assert.assertTrue(avList.size() == 5);
        applicantFacade.deleteApplicant(avList.get(0));
        Assert.assertTrue(applicantFacade.findApplicant().size() == 4);
    }

    // Вспомогательная процедура для установки оценок
    private List<ApplicantResultView> createMark(ProfessionView2 pv, Long applicantId, Integer mark) {
        List<ApplicantResultView> arvList = new LinkedList<ApplicantResultView>();
        for (SubjectView sv : pv.getSubjectList()) {
            ApplicantResultView ar = new ApplicantResultView();
            ar.setApplicantId(applicantId);
            ar.setSubjectId(sv.getSubjectId());
            ar.setMark(mark);
            arvList.add(ar);
        }

        return arvList;
    }
}