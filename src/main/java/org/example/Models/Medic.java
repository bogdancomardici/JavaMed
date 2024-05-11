package org.example.Models;

import org.example.Enums.MedicSpecialities;

import java.util.Date;

public class Medic extends Person{
    private MedicSpecialities speciality;
    private int yearsOfExperience;

    public String getSpeciality() {
        return speciality.toString();
    }

    public void setSpeciality(MedicSpecialities speciality) {
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
        this.speciality = MedicSpecialities.valueOf(speciality);
        this.yearsOfExperience = yearsOfExperience;
    }
}
