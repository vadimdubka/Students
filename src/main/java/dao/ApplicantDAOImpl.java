package dao;

import entity.Applicant;
import entity.Profession;

import java.util.List;

public class ApplicantDAOImpl extends BaseStudentDAO implements ApplicantDAO {

    public Long addApplicant(Applicant a) {
        return (Long) template.save(a);
    }

    public void updateApplicant(Applicant a) {
        template.saveOrUpdate(a);
    }

    public void deleteApplicant(Applicant a) {
        template.delete(a);
    }

    public Applicant getApplicant(Long applicantId) {
        return (Applicant) template.load(Applicant.class, applicantId);
    }

    public List<Applicant> findApplicant() {
        return (List<Applicant>) template.find("FROM Applicant ORDER BY lastName, firstName, middleName, profession.professionName, entranceYear");
    }

    public List<Applicant> findApplicantForProfession(Profession p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
