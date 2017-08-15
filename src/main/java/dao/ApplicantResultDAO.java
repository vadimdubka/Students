package dao;

import entity.Applicant;
import entity.ApplicantResult;

import java.util.List;

public interface ApplicantResultDAO {

    public Long addApplicantResult(ApplicantResult ar);

    public void updateApplicantResult(ApplicantResult ar);

    public void deleteApplicantResult(ApplicantResult ar);

    public ApplicantResult getApplicantResult(Long applicantResultId);

    public List<ApplicantResult> findApplicantResult(Applicant a);
}
