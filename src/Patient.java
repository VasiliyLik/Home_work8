//класс Пациент
public class Patient {
    private String name;
    private String surName;
    private String birthDate;
    private boolean healthy;

    public Patient() {
    }

    public Patient(String name, String surName, String birthDate, boolean healthy) {
        this.name = name;
        this.surName = surName;
        this.birthDate = birthDate;
        this.healthy = healthy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }

    @Override
    public String toString() {
        return "Patient: " +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", healthy=" + healthy;
    }
}
