package com.example.exam1;

public class Diagnosis {
    private String patientId;
    private String patientName;
    private String diagnosis;
    private String symptoms;
    private String medicines;
    private String date;

    public Diagnosis(String patientId, String patientName, String diagnosis, String symptoms, String medicines, String date) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.diagnosis = diagnosis;
        this.symptoms = symptoms;
        this.medicines = medicines;
        this.date = date;
    }

    // getters and setters
}
