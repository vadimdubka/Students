package web;

import logic.Group;
import logic.ManagementSystem2;
import logic.Student;
import web.forms.MainFrameForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

public class StudentFrameServlet extends HttpServlet {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Установка кодировки для принятия параметров
        req.setCharacterEncoding("UTF-8");
        String sId = req.getParameter("studentId");
        // Если пользователь нажал кнопку ОК – тогда мы обновляем данные (добавляем нового студента)
        if (sId != null && req.getParameter("OK") != null) {
            try {
                // Если ID студента больше 0, то мы редактируем его данные
                if (Integer.parseInt(sId) > 0) {
                    updateStudent(req);
                } // Иначе это новый студент
                else {
                    insertStudent(req);
                }
            } catch (SQLException sql_e) {
                sql_e.printStackTrace();
                throw new IOException(sql_e.getMessage());
            } catch (ParseException p_e) {
                throw new IOException(p_e.getMessage());
            }
        }
        // А теперь опять получаем данные для отображения на главной форме
        String gs = req.getParameter("groupId");
        String ys = req.getParameter("educationYear");
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


    private void updateStudent(HttpServletRequest req) throws SQLException, ParseException {
        Student s = prepareStudent(req);
        ManagementSystem2.getInstance().updateStudent(s);
    }

    private void insertStudent(HttpServletRequest req) throws SQLException, ParseException {
        Student s = prepareStudent(req);
        ManagementSystem2.getInstance().insertStudent(s);
    }

    private Student prepareStudent(HttpServletRequest req) throws ParseException {
        Student s = new Student();
        s.setStudentId(Integer.parseInt(req.getParameter("studentId")));
        s.setFirstName(req.getParameter("firstName").trim());
        s.setSurName(req.getParameter("surName").trim());
        s.setPatronymic(req.getParameter("patronymic").trim());
        s.setDateOfBirth(sdf.parse(req.getParameter("dateOfBirth").trim()));
        if (req.getParameter("sex").equals("0")) {
            s.setSex('М');
        } else {
            s.setSex('Ж');
        }
        s.setGroupId(Integer.parseInt(req.getParameter("groupId").trim()));
        s.setEducationYear(Integer.parseInt(req.getParameter("educationYear").trim()));
        return s;
    }
}