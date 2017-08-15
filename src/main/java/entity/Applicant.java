package entity;// Created by sky-vd on 17.07.2017.

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "applicant")
public class Applicant {

    @Id // говорит о том, что данное поле является идентификатором
    @GeneratedValue(strategy = GenerationType.IDENTITY) // аннотация дает указание, что данная величина будет генериться, причем указание strategy=GenerationType.IDENTITY говорит о том, что значение будет создано с помощью базы данных — точнее значение столбца, который связан с полем будет генерится с помощью базы данных.
    @Column(name = "applicant_id") // для указания имени столбца, с которым связано поле класса.
    private Long applicantId;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY) //аннотация указывает, что поле указывает на другой класс, который связан с текущим классом связью многие-к-одному. Здесь также указывается правило каскада cascade= {CascadeType.REFRESH} — в упрощенном виде оно гласит «что сделали с тобой, то сделай и со связью». В нашем случае единственная операция, которая будет передаваться на класс Profession — это перечитывание. Все остальные операции не будут распространяться на связанный класс. Запись fetch=FetchType.LAZY указывает, что загрузка данного поля будет только в случае обращения к данному полю. Пока программа этого не делает, поле будет пустым.
    @JoinColumn(name = "profession_id") // данная анотация по сути похожа на @Column. Разве что она указывает, что колонка к тому же является связующей
    private Profession profession; // отношение много-к одному. Т.е. существует много абитуриентов, у которых будет одна и та же специальность. Также такое отношение показывает, что абитуриент может поступать только на одну специальность.

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "applicant") //такая запись говорит о том, что поле служить для связи один-ко-многим (потому и список). Единственный параметр, который мы еще не рассмотрели — mappedBy=»applicant». Он указывает имя поля в классе ApplicantResult, которое будет указывать на «родителя» — на класс Applicant
    private List<ApplicantResult> applicantResultList; //отношение — один-ко-многим. Т.е. у одного абитуриента существует много сданных предметов. Точнее может быть 0 и больше.

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "entrance_year")
    private Integer entranceYear;

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public List<ApplicantResult> getApplicantResultList() {
        return applicantResultList;
    }

    public void setApplicantResultList(List<ApplicantResult> applicantResultList) {
        this.applicantResultList = applicantResultList;
    }

    public Integer getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(Integer entranceYear) {
        this.entranceYear = entranceYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }
}