package dao;// Created by sky-vd on 19.07.2017.

import entity.Profession;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ProfessionDAOImpl2 implements ProfessionDAO {

    public Serializable addProfession(Profession p) {
        System.out.println("addProfession called for 2");
        return null;
    }

    public void updateProfession(Profession p) {
        System.out.println("updateProfession called for 2");
    }

    public void deleteProfession(Profession p) {
        System.out.println("deleteProfession called for 2");
    }

    public List<Profession> findProfession() {
        System.out.println("findProfession called for 2");
        List<Profession> list = new LinkedList<Profession>();
        list.add(new Profession());
        return list;
    }

}