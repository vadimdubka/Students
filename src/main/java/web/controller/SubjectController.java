package web.controller;

import facade.SubjectFacade;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import view.SubjectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubjectController extends AbstractController {

    private SubjectFacade subjectFacade;

    public void setSubjectFacade(SubjectFacade subjectFacade) {
        this.subjectFacade = subjectFacade;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        List<SubjectView> l = subjectFacade.findSubject();
        Map<String,List<SubjectView>>  data = new HashMap<String,List<SubjectView>>();
        data.put("subjectList", l);
        return new ModelAndView("subject/subject", data);
    }

}
