package org.example;

public class Treatment {

    private int treatmentId;
    private String pacientCnp;
    private String treatmentName;
    private int durationDays;
    private int frequencyPerDay;

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getPacientCnp() {
        return pacientCnp;
    }

    public void setPacientCnp(String pacientCnp) {
        this.pacientCnp = pacientCnp;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(int durationDays) {
        this.durationDays = durationDays;
    }

    public int getFrequencyPerDay() {
        return frequencyPerDay;
    }

    public void setFrequencyPerDay(int frequencyPerDay) {
        this.frequencyPerDay = frequencyPerDay;
    }

    public Treatment(String pacientCnp, String treatmentName, int durationDays, int frequencyPerDay) {
        this.pacientCnp = pacientCnp;
        this.treatmentName = treatmentName;
        this.durationDays = durationDays;
        this.frequencyPerDay = frequencyPerDay;
    }
}
