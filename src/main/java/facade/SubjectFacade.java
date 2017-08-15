package facade;

import dao.Profession2DAO;
import dao.SubjectDAO;
import entity.Subject;
import view.SubjectView;

import java.util.LinkedList;
import java.util.List;

public class SubjectFacade {

    private SubjectDAO subjectDAO;
    private Profession2DAO profession2DAO;

    public void setSubjectDAO(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    public void setProfession2DAO(Profession2DAO profession2DAO) {
        this.profession2DAO = profession2DAO;
    }

    public Long addSubject(SubjectView sv) {
        sv.setSubjectId(null);
        return subjectDAO.addSubject(createSubjectFromView(sv));
    }

    public void updateSubject(SubjectView pv) {
        subjectDAO.updateSubject(createSubjectFromView(pv));
    }

    public void deleteSubject(SubjectView sv) {
        subjectDAO.deleteSubject(subjectDAO.getSubject(sv.getSubjectId()));
    }

    public SubjectView getSubject(Long id) {
        return new SubjectView(subjectDAO.getSubject(id));
    }

    public List<SubjectView> findSubject() {
        List<Subject> sList = subjectDAO.findSubject();
        return createSubjectList(sList);
    }

    public List<SubjectView> findSubjectById(List<Long> ids) {
        List<Subject> sList = subjectDAO.findSubjectById(ids);
        return createSubjectList(sList);
    }

    public List<SubjectView> findSubjectByProfession(Long professionId) {
        List<Subject> sList = subjectDAO.findSubjectByProfession(profession2DAO.getProfession(professionId));
        return createSubjectList(sList);
    }

    private Subject createSubjectFromView(SubjectView sv) {
        Subject s = null;
        if (sv.getSubjectId() != null && sv.getSubjectId() > 0) {
            s = subjectDAO.getSubject(sv.getSubjectId());
        } else {
            s = new Subject();
        }
        s.setSubjectName(sv.getSubjectName());

        return s;
    }

    private List<SubjectView> createSubjectList(List<Subject> pList) {
        List<SubjectView> svList = new LinkedList<SubjectView>();
        for (Subject p : pList) {
            svList.add(new SubjectView(p));
        }
        return svList;
    }
}
