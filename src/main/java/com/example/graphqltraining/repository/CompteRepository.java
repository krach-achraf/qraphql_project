package com.example.graphqltraining.repository;

import com.example.graphqltraining.bean.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository  extends JpaRepository<Compte, Long> {
}
