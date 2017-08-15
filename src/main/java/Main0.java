import entity.Profession;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/* В последующем методы данного класса лучше реализовывать в отдельном классе DAO, а сам класс Main будет использоваться для вызова методов DAO*/
@SuppressWarnings("ALL")
public class Main0 {

    // Демонстрация запроса при работе на уровне JDBC (старый способ)
    private void oldJDBC() {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_applicant", "root", "int1984");

            statement = connection.createStatement();
            List<Profession> list = new ArrayList<Profession>();
            rs = statement.executeQuery("select profession_id, profession_name from profession " +
                    "order by profession_name");
            while (rs.next()) {
                Profession r = new Profession();
                r.setProfessionId(rs.getLong("profession_id"));
                r.setProfessionName(rs.getString("profession_name"));
                list.add(r);
                System.out.println(r.getProfessionId() + ":" + r.getProfessionName());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error SQL execution: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.err.println("Error SQL execution: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.err.println("Error: " + ex.getMessage());
            }
        }
    }

    /* -------- Демонстрация запросов новым способом через Hibernate --------
    Вся суть методов для редактирования сводится к одному —
    1. Создание объекта сессии (коннекта) к базе (объект класса Session) с помощью уже описанных классов HibernateUtil и SessionFactory.
    Объект session в общем-то не является непосредственным коннектом — более правильно обозначит его функцию как
    «ответственный за работу с базой данных».
    2. После чего запускается транзакция session.beginTransaction();
    3. делается нужное действие (сохранение, удаление или получение данных),
    4. после чего коннект закрывается  session.getTransaction().commit();

    Как вы можете догадаться теперь вам не надо создавать каждый раз 4 SQL-запроса для каждой таблицы. Я уже не говорю о ситуациях, когда вы должны получать связанные данные. Вы просто вызываете метод, который делает то, что вам надо. */

    // Метод добавляет новую запись в таблицу PROFESSION
    private void addProfession(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Profession r = new Profession();
        r.setProfessionName(name);
        session.save(r);
        session.getTransaction().commit();
    }

    // Метод возвращает список профессий
    private List<Profession> listProfession() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Profession> result = session.createQuery("from Profession order by professionName").list();
        session.getTransaction().commit();
        return result;
    }

    /* Обратить вниманиее: Hibernate на самом деле не выполняет SQL-команды после каждого вашего действия.
    Он копит их, пока не сочтет нужным их выполнить.
    Посмотрите на порядок выполнения операторов в методе deleteProfessions. Там я вставил печать информации. Так вот операторы удаления будут выполняться не в перемешку с этими строчками.
    Это можно увидеть на консоли.
    Но есть специальный метод flush, который заставляет Hibernate выполнить операторы в принудительном порядке.
    Советую раскомментировать строку session.flush() и посмотреть на вывод.*/

    // Метод удаляет по очереди все записи, которые ему переданы в виде списка
    private void deleteProfessions(List<Profession> result) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for (Profession p : result) {
            System.out.println("Delete:" + p.getProfessionId() + ":" + p.getProfessionName());
            session.delete(p);
            session.flush();
        }
        session.getTransaction().commit();
    }

    // Методу удаляет одну запись
    private void deleteEntity(Object o) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(o);
//        session.flush();
        session.getTransaction().commit();
    }

    public static void main(String[] args) {
        Main0 main = new Main0();

        // Вызов "старого стиля"
        main.oldJDBC();

        // Добавление новых профессий
        main.addProfession("Profession_1");
        main.addProfession("Profession_2");
        main.addProfession("Profession_3");
        main.addProfession("Profession_4");
        main.addProfession("Profession_5");

        // Вариант вызова списка
        List<Profession> result = main.listProfession();

        // Вариант вызова удаления одной записи
        result = main.listProfession();
        main.deleteEntity(result.get(0));

        // Вариант вызова списка и последующее удаление
        result = main.listProfession();
        main.deleteProfessions(result);
    }
}