package web;// Created by sky-vd on 27.03.2017.

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloWorldServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*Вызов setContentType. Этот метод важен для тех, кто использует русский язык — прежде чем показывать страницу с русским тесктом броузер должен быть проинформирован, что именно русский текст мы собираемся выводить на экран и броузер должен использовать кодировку. которая нам подходит.*/
        resp.setContentType("text/html;charset=utf-8");

        /*Получение Writer-класса для вывода данных. Здесь очень важно отметить следующий момент — вывод может осуществляться как посимвольно, так и побайтно. Для вывода текста используется посимвольный вывод, для вывода двоичных файлов используется побайтный. Для более полного понимания лучше посмотреть информацию как устроены потоки ввода/вывода для Java.*/
        PrintWriter pw = resp.getWriter();
        pw.println("<H1>Hello, world! или Привет мир</H1>");




    }
}