package com.sitswift.ledger.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.ValueGenerationType;

@Entity
@Data
@Table(name = "master_file")
public class Ledger {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name="name")
    private String name;
    @Column(name="address")
    private String address;
    @Column(name="city")
    private String city;
    @Column(name="gst_number")
    private String gstNumber;
}
