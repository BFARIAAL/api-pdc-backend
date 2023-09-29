package com.jlr.ttl.poc.loadmanager.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "carrier")
public class Carrier {

    @Id
    @Column(name = "id")
    private String carrierRef;

    private String description;
}
