package view;// Created by sky-vd on 19.07.2017.

import entity.Profession;

public class ProfessionView {

    private Long professionId;
    private String professionName;

    public ProfessionView() {
    }

    public ProfessionView(Profession p) {
        this.professionId = p.getProfessionId();
        this.professionName = p.getProfessionName();
    }

    public Long getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Long professionId) {
        this.professionId = professionId;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }
}