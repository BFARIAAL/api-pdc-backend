package com.jlr.ttl.poc.loadmanager.models;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "loads")
public class Load {

    @Id
    @Column(name = "id")
    private String load_ref;
    @Column
    private String dep_loc;
    @Column
    private String dest_loc;
    @Column
    private String status;
    @Column
    private Carrier carrier;
    @Column(name = "end_date")
    private Date date;

    public Load() {}

    public Load(String load_ref, String dep_loc, String dest_loc, String status) {
        this.load_ref = load_ref;
        this.dep_loc = dep_loc;
        this.dest_loc = dest_loc;
        this.status = status;
    }

    public String getLoad() {
        return load_ref;
    }

    public String getDep_loc() {
        return dep_loc;
    }

    public String getDest_loc() {
        return dest_loc;
    }

    public String getStatus() {
        return status;
    }

    public void setDep_loc(String dep_loc) {
        this.dep_loc = dep_loc;
    }

    public void setDest_loc(String dest_loc) {
        this.dest_loc = dest_loc;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
