package com.jlr.ttl.ds.api.dto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @Column(name="id")
    private String lId;
    @Column(name = "description")
    private String lDescription;

}
