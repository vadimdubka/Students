package web.controller;

import facade.ApplicantFacade;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import view.ApplicantView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASaburov
 */
public class ApplicantController extends AbstractController {

    private ApplicantFacade applicantFacade;

    public void setApplicantFacade(ApplicantFacade applicantFacade) {
        this.applicantFacade = applicantFacade;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        List<ApplicantView> l = applicantFacade.findApplicant();
        Map<String,List<ApplicantView>>  data = new HashMap<String,List<ApplicantView>>();
        data.put("applicantList", l);
        return new ModelAndView("students/applicant", data);
    }
}
