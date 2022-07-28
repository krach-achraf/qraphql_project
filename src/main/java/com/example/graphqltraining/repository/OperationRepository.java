package com.example.graphqltraining.repository;

import com.example.graphqltraining.bean.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository  extends JpaRepository<Operation, Long> {
}
