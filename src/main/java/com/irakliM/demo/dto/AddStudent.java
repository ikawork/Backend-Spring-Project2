package com.irakliM.demo.dto;

public class AddStudent {

    private String firstName;

    private String lastName;

    private String studyingYear;

    private Integer active;

    private Long addressId;

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

    public String getStudyingYear() {
        return studyingYear;
    }

    public void setStudyingYear(String studyingYear) {
        this.studyingYear = studyingYear;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
