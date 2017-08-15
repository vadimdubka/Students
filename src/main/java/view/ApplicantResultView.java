package view;

import entity.ApplicantResult;

public class ApplicantResultView {

    private Long applicantResultId;
    private Long applicantId;
    private Long subjectId;
    private String subjectName;
    private Integer mark;

    public ApplicantResultView() {
    }

    public ApplicantResultView(ApplicantResult ar) {
        this.applicantResultId = ar.getApplicantResultId();
        this.applicantId = ar.getApplicant().getApplicantId();
        this.subjectId = ar.getSubject().getSubjectId();
        this.subjectName = ar.getSubject().getSubjectName();
        this.mark = ar.getMark();
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
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
