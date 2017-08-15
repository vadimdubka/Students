package dao;// Created by sky-vd on 17.07.2017.

import entity.Applicant;
import entity.Profession;
import entity.Subject;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;

public class StudentDAO {

    public Long addApplicant(Applicant applicant) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Long result = (Long) session.save(applicant);
        session.getTransaction().commit();
        return result;
    }

    public void updateApplicant(Applicant applicant) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(applicant);
        session.getTransaction().commit();
    }

    /*Здесь рассматрвиается одна важная особенность Hibernate — «ленивая» инициализация (lazy). Смысл ее в том, что когда вы загружаете объект с помощью того же вызова session.load() это сосвсем не означает, что Hibernate сразу же загрузит все данные. Можно сказать, что Hibernate «зарегистрирует» Ваше желание загрузить данные, но реальное обращение к базе данных может произойти только при попытке получить значение какого-то поля. еще более заметно это становится при работе с объектами, которые связаны с основным какими-либо отношениями.
    *
    * Еще раз возвращаясь в понятию lazy (ленивый). Посмотрите, что я в некоторых случаях делаю вызов Hibernate.initialize(). Этот вызов насильно загружает данные для поля. Особенно это бывает актуально для отношений один-ко-многим, многие-ко-многим. Такие коллекции конечно же делаются «ленивыми» и не инициализируются. Попробуйте закомментировать где-нибудь этот вызов — получите ошибку. Например в методеStudentDAO.findSubject. В классе Main нам хочется узнать размер коллекции специальностей, для которых надо сдавать этот предмет. И вы увидите последствия.

Если углубляться в данную проблему, то в коде я оставил вариант вызова openSession вместо getCurrentSession.
При работе с базой данных создается объект, который реализует интерфейс CurrentSessionContext. Hibernate включает три реализации этого интерфеса — JTASessionContext, ManagedSessionContext, ThreadLocalSessionContext. Никто не мешает написать свой :). Задача контекста — описать как и когда сессия открывается и закрывается
Первый контекст управляется транзакциями, которыми в свою очередь управляет AplpicationServer (J2EE). Второй позволяет управлять транзакциями внешним пакетам. И в этом случае разработчик отвечает за открытие и закрытие (уничтожение) контекста. И наконец третий — локальный контекст. Так вот особенность вызова getCurrentSession в том, что он позволяет создать контекст автоматически при начале транзакции (обратите внимание на вызов session.beginTransaction() — если его убрать, то получим ошибку — createQuery is not valid without active transaction). И что достаточно удобно — при закрытии транзакции контекст «закрывается» тоже автоматически. Если же мы воспользуемся методомopenSesion, то все будет хорошо. Вы можете даже убрать вызовы для транзакций. Но возникает другая проблема — в случае такого открытия сессии ее надо закрыть самому. И делать это придется в нашем случае там, где мы ее создали — доступна она нам только внутри метода. И если мы ее там закроем, то получим исеключение LazyInitializationException. Т.е. надо вытаскивать session наружу метода, что не хотелось бы.*/
    public Applicant getApplicant(Long applicantId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Applicant result = (Applicant) session.load(Applicant.class, applicantId);
        // Насильная инициализация списка. Не очень хорошая практика так делать
        Hibernate.initialize(result.getApplicantResultList());
        session.getTransaction().commit();
        return result;
    }

    public List<Applicant> findApplicant() {
        // Если поменять первую строку на вторую, то исключение о неинициализированной коллекции в классе Main1 уйдет.
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Applicant> result = session.createQuery("from Applicant order by lastName, firstName").list();
        // Насильная инициализация списка. Не очень хорошая практика так делать
        for (Applicant a : result) {
            Hibernate.initialize(a.getProfession());
        }
        session.getTransaction().commit();
        return result;
    }

    public Long addProfession(Profession profession) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Long result = (Long) session.save(profession);
        session.getTransaction().commit();
        return result;
    }

    public void updateProfession(Profession profession) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(profession);
        session.getTransaction().commit();
    }

    public Profession getProfession(Long professionId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Profession result = (Profession) session.load(Profession.class, professionId);
        // Насильная инициализация списка. Не очень хорошая практика так делать
        Hibernate.initialize(result.getSubjectList());
        session.getTransaction().commit();
        return result;
    }

    public List<Profession> findProfession() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Profession> result = session.createQuery("from Profession order by professionName").list();
        // Насильная инициализация списка. Не очень хорошая практика так делать
        for (Profession a : result) {
            Hibernate.initialize(a.getSubjectList());
        }
        session.getTransaction().commit();
        return result;
    }

    public Long addSubject(Subject subject) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Long result = (Long) session.save(subject);
        session.getTransaction().commit();
        return result;
    }

    public void updateSubject(Subject subject) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(subject);
        session.getTransaction().commit();
    }

    public Subject getSubject(Long subjectId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Subject result = (Subject) session.load(Subject.class, subjectId);
        // Насильная инициализация списка. Не очень хорошая практика так делать
        Hibernate.initialize(result.getProfessionList());
        session.getTransaction().commit();
        return result;
    }

    public List<Subject> findSubject() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Subject> result = session.createQuery("from Subject order by subjectName").list();
        // Насильная инициализация списка. Не очень хорошая практика так делать
        for (Subject a : result) {
            Hibernate.initialize(a.getProfessionList());
        }
        session.getTransaction().commit();
        return result;
    }
}