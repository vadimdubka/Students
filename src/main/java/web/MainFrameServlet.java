package web;

import entity.Group;
import service.ManagementSystem2;
import entity.Student;
import web.forms.MainFrameForm;
import web.forms.StudentForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/*JSP служит для того, чтобы показать данные которые ей передал сервлет (из MainFrameForm in this case). Профессионалы могут поспорить, но важно, чтобы вы поняли вот какой момент — сервлет собирает все данные по разным таблицам, по разным файла и т.д., кладет это все в некоторую структуру (из MainFrameForm in this case) и передает JSP, которая занимается тем, что из этой структуры (из MainFrameForm in this case) «вытаскивает» нужные данные и кладет их на соотвтествующее место на странице.*/

/**
 * Код для сервлета нашей главной страницы
 */
public class MainFrameServlet extends HttpServlet {
    // Переопределим стандартные методы
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Установка кодировки для принятия параметров
        req.setCharacterEncoding("UTF-8");
        int answer = 0;
        try {
            answer = checkAction(req);
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        }
        if (answer == 1) {
            // Тут надо сделать вызов другой формы, которая перенаправит сервлет на другую JSP для ввода данных о новом студенте
            try {
                Student s = new Student();
                s.setStudentId(0);
                s.setDateOfBirth(new Date());
                s.setEducationYear(Calendar.getInstance().get(Calendar.YEAR));
                Collection groups = ManagementSystem2.getInstance().getGroups();
                StudentForm sForm = new StudentForm();
                sForm.initFromStudent(s);
                sForm.setGroups(groups);
                req.setAttribute("student", sForm);
                getServletContext().getRequestDispatcher("/StudentFrame.jsp").forward(req, resp);
                return;
            } catch (SQLException sql_e) {
                throw new IOException(sql_e.getMessage());
            }

        }

        if (answer == 2) {
            // Тут надо сделать вызов другой формы, которая перенаправит сервлет на другую JSP для ввода данных о студенте
            try {
                if (req.getParameter("studentId") != null) {
                    int stId = Integer.parseInt(req.getParameter("studentId"));
                    Student s = ManagementSystem2.getInstance().getStudentById(stId);
                    Collection groups = ManagementSystem2.getInstance().getGroups();
                    StudentForm sForm = new StudentForm();
                    sForm.initFromStudent(s);
                    sForm.setGroups(groups);
                    req.setAttribute("student", sForm);
                    getServletContext().getRequestDispatcher("/StudentFrame.jsp").forward(req, resp);
                    return;
                }
            } catch (SQLException sql_e) {
                throw new IOException(sql_e.getMessage());
            }
        }
        String gs = req.getParameter("groupId");
        String ys = req.getParameter("year");

        if (answer == 3) {
            // Здесь мы перемещаем стедунтов в другую группу
            String newGs = req.getParameter("newGroupId");
            String newYs = req.getParameter("newYear");
            try {
                Group g = new Group();
                g.setGroupId(Integer.parseInt(gs));
                Group ng = new Group();
                ng.setGroupId(Integer.parseInt(newGs));
                ManagementSystem2.getInstance().moveStudentsToGroup(g, Integer.parseInt(ys), ng, Integer.parseInt(newYs));
                // Теперь мы будем показывать группу, куда переместили
                gs = newGs;
                ys = newYs;
            } catch (SQLException sql_e) {
                throw new IOException(sql_e.getMessage());
            }
        }
        int groupId = -1;
        if (gs != null) {
            groupId = Integer.parseInt(gs);
        }
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if (ys != null) {
            year = Integer.parseInt(ys);
        }
        MainFrameForm form = new MainFrameForm();
        try {
            Collection groups = ManagementSystem2.getInstance().getGroups();
            Group g = new Group();
            g.setGroupId(groupId);
            if (groupId == -1) {
                Iterator i = groups.iterator();
                while (i.hasNext())
                    g = (Group) i.next();
            }
            Collection students = ManagementSystem2.getInstance().getStudentsFromGroup(g, year);
            form.setGroupId(g.getGroupId());
            form.setYear(year);
            form.setGroups(groups);
            form.setStudents(students);
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        }

        req.setAttribute("form", form);
        getServletContext().getRequestDispatcher("/MainFrame.jsp").forward(req, resp);
    }

    // Здесь мы проверям какое действие нам надо сделать – и возвращаем ответ
    private int checkAction(HttpServletRequest req) throws SQLException {
        if (req.getParameter("Add") != null) {
            return 1;
        }
        if (req.getParameter("Edit") != null) {
            return 2;
        }
        if (req.getParameter("MoveGroup") != null) {
            return 3;
        }
        if (req.getParameter("Delete") != null) {
            if (req.getParameter("studentId") != null) {
                Student s = new Student();
                s.setStudentId(Integer.parseInt(req.getParameter("studentId")));
                ManagementSystem2.getInstance().deleteStudent(s);
            }
            return 0;
        }
        return 0;
    }
}
