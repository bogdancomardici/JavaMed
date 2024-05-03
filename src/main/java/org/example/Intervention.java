package org.example;

public class Intervention {

    private int interventionId;
    private String pacientCnp;
    private String type;
    private String description;

    public int getInterventionId() {
        return interventionId;
    }

    public void setInterventionId(int interventionId) {
        this.interventionId = interventionId;
    }

    public String getPacientCnp() {
        return pacientCnp;
    }

    public void setPacientCnp(String pacientCnp) {
        this.pacientCnp = pacientCnp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Intervention(String pacientCnp, String type, String description) {
        this.pacientCnp = pacientCnp;
        this.type = type;
        this.description = description;
    }
}
