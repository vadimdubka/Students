package entity;// Created by sky-vd on 17.07.2017.

import javax.persistence.*;

@Entity
@Table(name = "applicant_result")
public class ApplicantResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicant_result_id")
    private Long applicantResultId;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant; // many-to-one, информация о том, кто сдавал данный экзамен

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject; // many-to-one, по какому предмету был экзамен

    @Column(name = "mark")
    private Integer mark;

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Long getApplicantResultId() {
        return applicantResultId;
    }

    public void setApplicantResultId(Long applicantResultId) {
        this.applicantResultId = applicantResultId;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}