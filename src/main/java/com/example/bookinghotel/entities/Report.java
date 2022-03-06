package com.example.bookinghotel.entities;


import javax.persistence.*;


@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String message;
    private int hotel_id;


}
