package facade;

import dao.Profession2DAO;
import dao.SubjectDAO;
import entity.Profession;
import entity.Subject;
import view.ProfessionView2;
import view.SubjectView;

import java.util.LinkedList;
import java.util.List;

public class ProfessionFacade2 {

    private Profession2DAO profession2DAO;
    private SubjectDAO subjectDAO;

    public void setProfession2DAO(Profession2DAO profession2DAO) {
        this.profession2DAO = profession2DAO;
    }

    public void setSubjectDAO(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    /*добавить новую специальность*/
    public Long addProfession(ProfessionView2 pv) {
        pv.setProfessionId(null);
        Long id = profession2DAO.addProfession(createProfessionFromView(pv));
        pv.setProfessionId(id);
        updateSubjectList(pv);
        return id;
    }

    /*изменить существующую специальность*/
    public void updateProfession(ProfessionView2 pv) {
        profession2DAO.updateProfession(createProfessionFromView(pv));
        updateSubjectList(pv);
    }

    /*изменить список прдеметов, которые соответствуют данной специальности*/
    private void updateSubjectList(ProfessionView2 pv) {
        if (pv.getSubjectList() != null) {
            List<Long> sList = new LinkedList<Long>();
            for (SubjectView sv : pv.getSubjectList()) {
                sList.add(sv.getSubjectId());
            }
            updateSubjectList(pv.getProfessionId(), sList);
        }
    }

    /*иной вариант изменить список предметов*/
    public void updateSubjectList(Long professionId, List<Long> sList) {
        Profession p = profession2DAO.getProfession(professionId);
        List<Subject> subjList = subjectDAO.findSubjectById(sList);
        profession2DAO.updateSubjectList(p, subjList);
    }

    /*удалить специальность*/
    public void deleteProfession(ProfessionView2 pv) {
        profession2DAO.deleteProfession(profession2DAO.getProfession(pv.getProfessionId()));
    }

    /*получить одну специальность*/
    public ProfessionView2 getProfession(Long id) {
        return new ProfessionView2(profession2DAO.getProfession(id), true);
    }

    /*получить полный список специальностей*/
    public List<ProfessionView2> findProfession() {
        List<Profession> pList = profession2DAO.findProfession();
        List<ProfessionView2> pvList = new LinkedList<ProfessionView2>();
        for (Profession p : pList) {
            pvList.add(new ProfessionView2(p));
        }

        return pvList;
    }

    private Profession createProfessionFromView(ProfessionView2 pv) {
        Profession p = null;
        if (pv.getProfessionId() != null && pv.getProfessionId() > 0) {
            p = profession2DAO.getProfession(pv.getProfessionId());
        } else {
            p = new Profession();
        }
        p.setProfessionName(pv.getProfessionName());

        return p;
    }
}
