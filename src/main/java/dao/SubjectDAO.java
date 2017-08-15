package dao;

import entity.Profession;
import entity.Subject;

import java.util.List;

public interface SubjectDAO {

    public Long addSubject(Subject s);

    public void updateSubject(Subject s);

    public void deleteSubject(Subject s);

    public Subject getSubject(Long subjectId);

    public List<Subject> findSubject();

    public List<Subject> findSubjectById(List<Long> ids);

    public List<Subject> findSubjectByProfession(Profession p);
}
