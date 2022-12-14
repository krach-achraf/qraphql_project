package com.example.graphqltraining.repository;

import com.example.graphqltraining.bean.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
    Client findByCNE(String CNE);
    Client findByPhoneNumber(String phone);
}
