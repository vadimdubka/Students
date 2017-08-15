package view;

import entity.Applicant;
import entity.ApplicantResult;

import java.util.LinkedList;
import java.util.List;

public class ApplicantView {

    private Long applicantId;
    private String firstName;
    private String lastName;
    private String middleName;
    private Integer entranceYear;
    private Long professionId;
    private String professionName;
    private List<ApplicantResultView> applicantResultList;

    public ApplicantView() {
    }

    public ApplicantView(Applicant a) {
        this(a, false);
    }

    public ApplicantView(Applicant a, boolean full) {
        this.applicantId = a.getApplicantId();
        this.firstName = a.getFirstName();
        this.lastName = a.getLastName();
        this.middleName = a.getMiddleName();
        this.entranceYear = a.getEntranceYear();
        this.professionId = a.getProfession().getProfessionId();
        this.professionName = a.getProfession().getProfessionName();
        if (full) {
            applicantResultList = new LinkedList<ApplicantResultView>();
            List<ApplicantResult> arList = a.getApplicantResultList();
            for (ApplicantResult ar : arList) {
                applicantResultList.add(new ApplicantResultView(ar));
            }
        }
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
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

    public List<ApplicantResultView> getApplicantResultList() {
        return applicantResultList;
    }

    public void setApplicantResultList(List<ApplicantResultView> applicantResultList) {
        this.applicantResultList = applicantResultList;
    }
}
