package entity;// Created by sky-vd on 17.07.2017.

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "profession")
public class Profession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profession_id")
    private Long professionId;

    @Column(name = "profession_name")
    private String professionName;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "SPECIALITY_SUBJECT",
            joinColumns = @JoinColumn(name = "PROFESSION_ID"),
            inverseJoinColumns = @JoinColumn(name = "SUBJECT_ID"))
    private Set<Subject> subjectList = new HashSet<Subject>(); //many-to-many. Обычно такая связь организуется в виде дополнительной таблицы. Называется она SPECIALITY_SUBJECT. Как видите отношение записывается как множество (Set). Если вы внимательно посмотрите на запись отношения один-ко-многим для классаApplicant, то увидите, что я использовал тэг bag и в классах исползовался List. В отношении много-ко-многим мы будем использовать другой тэг — set. Hibernate в этом случае более оптимально удаляет и вставляет записи об отношениях. Если хотите поэкспериментировать — поменяйте setна bag (соответственно Set на List — для инициализации можно использовать ArrayList. Помните, что Set и List всего лишь интерфейсы). И посмотрите какие запросы формирует Hibernate при обращении к базе данных. В случае List он просто удалит все записи из таблицы SPECIALITY-SUBJECT и заново вставит. При использовании Set это будет более умное решение.

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

    public Set<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(Set<Subject> subjectList) {
        this.subjectList = subjectList;
    }
}