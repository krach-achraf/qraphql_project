package com.example.graphqltraining.bean;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "comptes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Compte {

    @SequenceGenerator(name = "comptes_seq", sequenceName = "comptes_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comptes_seq")
    private Long id;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private int numero;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private float solde;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private Date dateCreation;

    @NotNull
    @NotBlank
    @Column(nullable = false, length = 15)
    @Size(min = 9, max = 15)
    private String typeCompte;

    @ManyToOne
    private Client client;
}
