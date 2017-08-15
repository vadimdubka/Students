package dao;

import entity.Applicant;
import entity.ApplicantResult;

import java.util.List;

public class ApplicantResultDAOImpl extends BaseStudentDAO implements ApplicantResultDAO {

    public Long addApplicantResult(ApplicantResult ar) {
        return (Long)template.save(ar);
    }

    public void updateApplicantResult(ApplicantResult ar) {
        template.saveOrUpdate(ar);
    }

    public void deleteApplicantResult(ApplicantResult ar) {
        template.delete(ar);
    }

    public ApplicantResult getApplicantResult(Long applicantResultId) {
        return (ApplicantResult) template.load(ApplicantResult.class, applicantResultId);
    }

    public List<ApplicantResult> findApplicantResult(Applicant a) {
        return (List<ApplicantResult>) template.findByNamedParam("FROM ApplicantResult a WHERE a.applicant=:applicant " +
                "ORDER BY subject.subjectName", "applicant", a);
    }

}
