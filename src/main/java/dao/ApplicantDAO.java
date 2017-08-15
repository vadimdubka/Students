package dao;

import entity.Applicant;
import entity.Profession;

import java.util.List;

public interface ApplicantDAO {

    public Long addApplicant(Applicant a);

    public void updateApplicant(Applicant a);

    public void deleteApplicant(Applicant a);

    public Applicant getApplicant(Long applicantId);

    public List<Applicant> findApplicant();

    public List<Applicant> findApplicantForProfession(Profession p);
}
