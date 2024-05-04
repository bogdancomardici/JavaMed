package org.example;
import java.time.LocalDateTime;
public class Appointment implements CronInterface{

    private int appointmentId;
    private String medicCnp;
    private String pacientCnp;
    private LocalDateTime date;

    public String getMedicCnp() {
        return medicCnp;
    }

    public void setMedicCnp(String medicCnp) {
        this.medicCnp = medicCnp;
    }

    public String getPacientCnp() {
        return pacientCnp;
    }

    public void setPacientCnp(String pacientCnp) {
        this.pacientCnp = pacientCnp;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Appointment(String medicCnp, String pacientCnp, LocalDateTime date) {
        this.medicCnp = medicCnp;
        this.pacientCnp = pacientCnp;
        this.date = date;
    }

    @Override
    public boolean isInThePast() {
        return date.isBefore(LocalDateTime.now());
    }

    @Override
    public boolean isToday() {
        return date.toLocalDate().isEqual(LocalDateTime.now().toLocalDate());
    }

    @Override
    public boolean isInTheFuture() {
        return date.isAfter(LocalDateTime.now());
    }
}
