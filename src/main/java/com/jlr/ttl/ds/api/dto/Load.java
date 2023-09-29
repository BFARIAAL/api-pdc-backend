package com.jlr.ttl.ds.api.dto;


import jakarta.persistence.*;

@Entity
@Table(name = "loads")
public class Load {

    @Id
    private String load_ref;
    @Column
    private String dep_loc;
    @Column
    private String dest_loc;
    @Column
    private String status;


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

}
