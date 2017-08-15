package facade;

import dao.ApplicantDAO;
import dao.ApplicantResultDAO;
import dao.SubjectDAO;
import entity.ApplicantResult;
import view.ApplicantResultView;
import view.ApplicantView;

import java.util.LinkedList;
import java.util.List;

public class ApplicantResultFacade {

    private ApplicantResultDAO applicantResultDAO;
    private ApplicantDAO applicantDAO;
    private SubjectDAO subjectDAO;

    public void setApplicantResultDAO(ApplicantResultDAO applicantResultDAO) {
        this.applicantResultDAO = applicantResultDAO;
    }

    public void setApplicantDAO(ApplicantDAO applicantDAO) {
        this.applicantDAO = applicantDAO;
    }

    public void setSubjectDAO(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    public Long addApplicantResult(ApplicantResultView arv) {
        arv.setApplicantResultId(null);
        return applicantResultDAO.addApplicantResult(createApplicantResultFromView(arv));
    }

    public void updateApplicantResult(ApplicantResultView arv) {
        applicantResultDAO.updateApplicantResult(createApplicantResultFromView(arv));
    }

    public void deleteApplicant(ApplicantResultView arv) {
        applicantResultDAO.deleteApplicantResult(applicantResultDAO.getApplicantResult(arv.getApplicantResultId()));
    }

    public ApplicantResultView getApplicantResult(Long id) {
        return new ApplicantResultView(applicantResultDAO.getApplicantResult(id));
    }

    public List<ApplicantResultView> findApplicantResult(ApplicantView av) {
        List<ApplicantResultView> pvList = new LinkedList<ApplicantResultView>();
        List<ApplicantResult> aList = applicantResultDAO.findApplicantResult(applicantDAO.getApplicant(av.getApplicantId()));
        for (ApplicantResult ar : aList) {
            pvList.add(new ApplicantResultView(ar));
        }
        return pvList;
    }

    public ApplicantResult createApplicantResultFromView(ApplicantResultView arv) {
        ApplicantResult ar = null;
        if (arv.getApplicantResultId() != null && arv.getApplicantResultId() > 0) {
            ar = applicantResultDAO.getApplicantResult(arv.getApplicantResultId());
        } else {
            ar = new ApplicantResult();
        }
        ar.setApplicant(applicantDAO.getApplicant(arv.getApplicantId()));
        ar.setSubject(subjectDAO.getSubject(arv.getSubjectId()));
        ar.setMark(arv.getMark());
        return ar;
    }
}
