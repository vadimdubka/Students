package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloWorldServlet2 extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //позволяет нам получить ресурс (в нашем случае это hello.jsp) и передать этому ресурсу наши данные.
        getServletContext().getRequestDispatcher("/hello.jsp").forward(req, resp);
    }


}

