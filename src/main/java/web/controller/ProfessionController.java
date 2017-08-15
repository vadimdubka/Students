package web.controller;// Created by sky-vd on 15.08.2017.

import facade.ProfessionFacade2;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import view.ProfessionView2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*Мы унаследовали наш контроллер от класса AbstractController и переопределили метод handleRequestInternal, который в качестве параметров имеет то же, что и обычный сервлет. */
public class ProfessionController extends AbstractController {

    private ProfessionFacade2 professionFacade;

    public void setProfessionFacade(ProfessionFacade2 professionFacade) {
        this.professionFacade = professionFacade;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1)
            throws Exception {
        List<ProfessionView2> l = professionFacade.findProfession();

        //Мы создали объект Map, который содержит имя объекта с данными и сам объект (именно по этому имени мы будем обращаться к данным из страницы JSP)
        Map<String, List<ProfessionView2>> data = new HashMap<String, List<ProfessionView2>>();
        data.put("professionList", l);
        //Нащ метод возвращает объект класса ModelAndView, в конструкторе которого мы указали имя View (которое позволит нам сконструировать имя для JSP) и объект с данными.
        return new ModelAndView("students/profession", data);
    }

}