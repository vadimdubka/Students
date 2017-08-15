package dao;

import entity.Profession;
import entity.Subject;

import java.util.List;

public class Profession2DAOImpl extends BaseStudentDAO implements Profession2DAO {

    public Long addProfession(Profession p) {
        return (Long) template.save(p);
    }

    public void updateProfession(Profession p) {
        template.saveOrUpdate(p);
    }

    public void updateSubjectList(Profession p, List<Subject> sList) {
        p.getSubjectList().clear();
        p.getSubjectList().addAll(sList);
    }

    public void deleteProfession(Profession p) {
        template.delete(p);
    }

    public Profession getProfession(Long professionId) {
        return (Profession) template.load(Profession.class, professionId);
    }

    public List<Profession> findProfession() {
        return (List<Profession>) template.find("FROM Profession ORDER BY professionName");
    }
}
