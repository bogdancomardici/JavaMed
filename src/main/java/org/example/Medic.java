package org.example;

import java.util.Date;

public class Medic extends Person{
    private String speciality;
    private int yearsOfExperience;

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Medic(String cnp, String firstName, String lastName, String phoneNumber, Date birthDate, String address, String speciality, int yearsOfExperience) {
        super(cnp, firstName, lastName, phoneNumber, birthDate, address);
        this.speciality = speciality;
        this.yearsOfExperience = yearsOfExperience;
    }
}
