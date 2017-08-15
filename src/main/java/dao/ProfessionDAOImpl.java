package dao;// Created by sky-vd on 19.07.2017.

import entity.Profession;

import java.util.LinkedList;
import java.util.List;

public class ProfessionDAOImpl implements ProfessionDAO {

    public Long addProfession(Profession p) {
        System.out.println("addProfession called");
        return null;
    }

    public void updateProfession(Profession p) {
        System.out.println("updateProfession called");
    }

    public void deleteProfession(Profession p) {
        System.out.println("deleteProfession called");
    }

    public List<Profession> findProfession() {
        System.out.println("findProfession called");
        List<Profession> list = new LinkedList<Profession>();
        list.add(new Profession());
        return list;
    }

}