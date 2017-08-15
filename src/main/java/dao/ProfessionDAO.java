package dao;// Created by sky-vd on 19.07.2017.

import entity.Profession;

import java.io.Serializable;
import java.util.List;

public interface ProfessionDAO {

    public Serializable addProfession(Profession p);

    public void updateProfession(Profession p);

    public void deleteProfession(Profession p);

    public List<?> findProfession();

    
}