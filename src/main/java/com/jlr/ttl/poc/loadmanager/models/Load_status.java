package com.jlr.ttl.poc.loadmanager.models;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "load_status")
public class Load_status {

    @Id
    @Column(name = "id")
    private String status_code;

    @Column
    private String description;

    @Column(name = "end_date")
    private Date date;

    public Load_status() {
    }

    public Load_status(String status_code) {
        this.status_code = status_code;
    }

    public Load_status(String status_code, String description) {
        this.status_code = status_code;
        this.description = description;
    }

    public Load_status(String status_code, String description, Date date) {
        this.status_code = status_code;
        this.description = description;
        this.date = date;
    }

    public String getStatus_code() {
        return status_code;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
