package dao;

import entity.Profession;
import entity.Subject;

import java.util.List;

public class SubjectDAOImpl extends BaseStudentDAO implements SubjectDAO {

    public Long addSubject(Subject s) {
        return (Long) template.save(s);
    }

    public void updateSubject(Subject s) {
        template.saveOrUpdate(s);
    }

    public void deleteSubject(Subject s) {
        template.delete(s);
    }

    public Subject getSubject(Long subjectId) {
        return (Subject) template.load(Subject.class, subjectId);
    }

    public List<Subject> findSubject() {
        return (List<Subject>) template.find("FROM Subject ORDER BY subjectName");
    }

    public List<Subject> findSubjectById(List<Long> ids) {
        StringBuffer sb = new StringBuffer("");
        if (ids != null && ids.size() > 0) {
            for (Long id : ids) {
                sb.append(sb.length() > 0 ? "," + id : id);
            }
            sb.insert(0, "WHERE subjectId in (");
            sb.append(")");
        }
        return (List<Subject>) template.find("FROM Subject " + sb.toString() + " ORDER BY subjectName");
    }

    public List<Subject> findSubjectByProfession(Profession p) {
        return (List<Subject>) template.findByNamedParam("FROM Subject s " +
                        "WHERE :profession in elements(s.professionList) ORDER BY subjectName",
                "profession", p);
    }
}
