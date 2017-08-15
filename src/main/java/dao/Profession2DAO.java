package dao;

import entity.Profession;
import entity.Subject;

import java.util.List;

public interface Profession2DAO {

    public Long addProfession(Profession p);

    public void updateProfession(Profession p);

    public void updateSubjectList(Profession p, List<Subject> sList);

    public void deleteProfession(Profession p);

    public Profession getProfession(Long professionId);

    public List<Profession> findProfession();
}
