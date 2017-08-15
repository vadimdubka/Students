package entity;// Created by sky-vd on 17.07.2017.

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "subject_name")
    private String subjectName;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY) //эта аннотация говорит о том, что поле будет использовано для связи много-ко-многим.
    @JoinTable(name = "SPECIALITY_SUBJECT", // указывает имя таблицы, которая используется для организации связи много-ко-многим. Параметр joinColumns указывает название столбца, которые явялется ссылкой на текущий класс, т.е. на Subject (точнее ссылкой на таблицу SUBJECT). Параметр inverseJoinColumns указывает на колонку, в которй находится ссылка на класс с другой стороны отношения — в данном случае этоProfession. Думаю, что при внимательном рассмотрении вы поймете, что и как
            joinColumns = @JoinColumn(name = "SUBJECT_ID"),
            inverseJoinColumns = @JoinColumn(name = "PROFESSION_ID"))
    private Set<Profession> professionList; //many-to-many

    public Set<Profession> getProfessionList() {
        return professionList;
    }

    public void setProfessionList(Set<Profession> professionList) {
        this.professionList = professionList;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}