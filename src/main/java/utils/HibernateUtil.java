package utils;// Created by sky-vd on 17.07.2017.

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//import org.hibernate.cfg.AnnotationConfiguration; // если используются аннотации
//import org.hibernate.cfg.Configuration; // если не используются аннотации



/*Вспомогательный класс для инициализации системы Hibernate. Его название уже настолько устоялось (оно приводилось в самых ранних версиях документации), что уже почти стандартно. Хотя может быт и другим. Я не стал даже комментарии убирать — у меня этот файл существует уже несколько лет. И я его просто иногда копирую.
* При загрузке класса происходит считывание конфигурации Hibernate и все необходимые параметры позволяют создать объект sessionFactory. Он объявлен как static — т.е. существует в одном экземпляре. И теперь к нему можно обращаться для получения сессии Hibernate, которая позволяет делать запросы к базе данных.*/
public class HibernateUtil {

    /*является по сути фабрикой (создателем) сессий (коннектов) к базе данных. Мы указали в файлеhibernate.cfg.xml все необходимые параметры, которые наш объект sessionFactory использует при создании*/
    private static final SessionFactory sessionFactory;

    static {
        try {
//             Create the SessionFactory from hibernate.cfg.xml. Класс Configuration — этот класс используется для загрузки конфигурации Hibernate
            /* first variant*/
//            sessionFactory = new Configuration().configure().buildSessionFactory();
            /* second variant*/
//            sessionFactory = new Configuration().configure("/resources/hibernate.cfg.xml").buildSessionFactory();
            /* third variant - если собираем проект через maven и файл в папке resources. Подходит для Hibernate 5*/
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

            /* forth variant - если собираем проект через maven и файл в папке resources. Мы просто изменили конфигуратор — теперь наш конфигуратор умеет работать с аннотациями. ---There is not AnnotationConfiguration in Hibernate 4 and Hibernate 5. To configure Hibernate 5 нужно использовать new Configuration().configure().buildSessionFactory();*/
//            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}