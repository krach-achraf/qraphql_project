package com.example.graphqltraining.bean;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Client {

    @SequenceGenerator(name = "clients_seq", sequenceName = "clients_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clients_seq")
    private Long id;

    @NotNull
    @Column(nullable = false, length = 8)
    @Size(min = 6, max = 8)
    private String CNE;

    @NotNull
    @Column(nullable = false)
    private String code;

    @NotNull
    @Column(nullable = false, length = 50)
    @Size(min= 3, max = 50)
    private String firstName;

    @NotNull
    @Column(nullable = false, length = 50)
    @Size(min= 3, max = 50)
    private String lastName;

    @NotNull
    @Column(nullable = false, length = 50)
    @Size(min = 10, max = 50)
    private String email;

    @NotNull
    @Column(nullable = false, length = 20)
    @Size(min= 9, max = 20)
    private String phoneNumber;

    private String address;

    private Date dateNaissance;

    public Client(String CNE,
                  String firstName,
                  String lastName,
                  String email,
                  String phoneNumber,
                  String address,
                  Date dateNaissance) {
        this.CNE = CNE;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateNaissance = dateNaissance;
    }
}
