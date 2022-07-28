package com.example.graphqltraining.bean;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "operations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Operation {
    @SequenceGenerator(name = "operations_seq", sequenceName = "operations_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operations_seq")
    private Long id;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private int numero;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private Date date;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private float montant;

    @NotNull
    @NotBlank
    @Column(nullable = false, length = 10)
    @Size(min = 5, max = 10)
    private String typeOperation;

    @ManyToOne
    private Compte compte;
}
