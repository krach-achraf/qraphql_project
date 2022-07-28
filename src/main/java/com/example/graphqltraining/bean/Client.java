package com.example.graphqltraining.bean;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "clients")
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
    @NotBlank
    @Column(nullable = false, length = 8)
    @Size(min = 6, max = 8)
    private String CNE;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String code;

    @NotNull
    @NotBlank
    @Column(nullable = false, length = 50)
    @Size(min= 3, max = 50)
    private String firstName;

    @NotNull
    @NotBlank
    @Column(nullable = false, length = 50)
    @Size(min= 3, max = 50)
    private String lastName;

    @NotNull
    @NotBlank
    @Column(nullable = false, length = 20)
    @Size(min= 9, max = 20)
    private String phoneNumber;

    private String address;

    private Date dateNaissance;
}
