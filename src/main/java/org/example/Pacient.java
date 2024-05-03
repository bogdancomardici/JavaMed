package org.example;

import java.util.Date;

public class Pacient extends Person{
    private String bloodType;
    private int height;
    private int weight;
    private String chronicDiseases;

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getChronicDiseases() {
        return chronicDiseases;
    }

    public void setChronicDiseases(String chronicDiseases) {
        this.chronicDiseases = chronicDiseases;
    }

    public Pacient(String cnp, String firstName, String lastName, String phoneNumber, Date birthDate, String address, String bloodType, int height, int weight, String chronicDiseases) {
        super(cnp, firstName, lastName, phoneNumber, birthDate, address);
        this.bloodType = bloodType;
        this.height = height;
        this.weight = weight;
        this.chronicDiseases = chronicDiseases;
    }
}
