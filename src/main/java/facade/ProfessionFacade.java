package facade;// Created by sky-vd on 19.07.2017.

import dao.ProfessionDAO;
import entity.Profession;
import view.ProfessionView;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ProfessionFacade {
    private ProfessionDAO dao;

    public void setDao(ProfessionDAO dao) {
        this.dao = dao;
    }

    public Serializable addProfession(ProfessionView pv) {
        return dao.addProfession(createProfessionFromView(pv));
    }

    public void updateProfession(ProfessionView pv) {
        dao.updateProfession(createProfessionFromView(pv));
    }

    public void deleteProfession(ProfessionView pv) {
        dao.deleteProfession(createProfessionFromView(pv));
    }

    public List<ProfessionView> findProfession() {
        List<ProfessionView> pvList = new LinkedList<ProfessionView>();
        List<Profession> pList = (List<Profession>) dao.findProfession();
        for(Profession p : pList) {
            pvList.add(new ProfessionView(p));
        }

        return pvList;
    }

    private Profession createProfessionFromView(ProfessionView pv) {
        Profession p = new Profession();
        p.setProfessionId(pv.getProfessionId());
        p.setProfessionName(pv.getProfessionName());
        return p;
    }
}