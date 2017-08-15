package view;

import entity.Profession;
import entity.Subject;

import java.util.HashSet;
import java.util.Set;

/*А теперь коротко рассмотрим функциональность фасадов. Только предварительно я хочу сделать небольшое отступление. При написании приложения возникает некоторая сложность при работе со списком и единичным экземпляром. Суть ее в том, что информация, которая требуется для списка, может быть более экономичной, чем для одного экземпляра. Когда же мы смотрим например одну специальность, то хорошо сразу иметь и список предметов для этой специальности. Для списка специальностей эта информация будет скорее всего излишней. Можно сделать отдельный класс View для списка и для одного экземпляра. Здесь я предлагаю иной вариант — класс один, но его заполнение может проиходить двумя способами — полное и частичное. Дополнительный логический аргумент в конструкторе View позволяет выбрать режим заполнения.*/
public class ProfessionView2 {

    private Long professionId;
    private String professionName;
    private Set<SubjectView> subjectList;

    public ProfessionView2() {
    }

    public ProfessionView2(Profession p) {
        this(p, false);
    }

    public ProfessionView2(Profession p, boolean full) {
        this.professionId = p.getProfessionId();
        this.professionName = p.getProfessionName();
        if (full) {
            subjectList = new HashSet<SubjectView>();
            Set<Subject> sList = p.getSubjectList();
            for (Subject s : sList) {
                subjectList.add(new SubjectView(s));
            }
        }
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

    public Set<SubjectView> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(Set<SubjectView> subjectList) {
        this.subjectList = subjectList;
    }
}
