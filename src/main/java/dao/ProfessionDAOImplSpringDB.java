package dao;// Created by sky-vd on 19.07.2017.

import entity.Profession;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;

public class ProfessionDAOImplSpringDB implements ProfessionDAO {

    private HibernateTemplate template;

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    public Long addProfession(Profession p) {
        return (Long) template.save(p);
    }

    public void updateProfession(Profession p) {
        template.saveOrUpdate(p);
    }

    public void deleteProfession(Profession p) {
        template.delete(p);
    }

    public Profession getProfession(Long id) {
        return (Profession) template.load(Profession.class, id);
    }

    public List<Profession> findProfession() {
        return (List<Profession>) template.find("FROM Profession ORDER BY professionName");
    }

}