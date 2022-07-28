package com.example.graphqltraining.resolver;

import com.example.graphqltraining.bean.Client;
import com.example.graphqltraining.bean.Compte;
import com.example.graphqltraining.bean.Operation;
import com.example.graphqltraining.repository.ClientRepository;
import com.example.graphqltraining.repository.CompteRepository;
import com.example.graphqltraining.repository.OperationRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class Query implements GraphQLQueryResolver {

    private ClientRepository clientRepository;
    private CompteRepository compteRepository;
    private OperationRepository operationRepository;

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Client getClient(Long id){
        return clientRepository.getReferenceById(id);
    }

    public List<Compte> getAllComptes(){
        return compteRepository.findAll();
    }

    public Compte getCompte(Long id){
        return compteRepository.getReferenceById(id);
    }

    public List<Operation> getAllOperations(){
        return operationRepository.findAll();
    }

    public Operation getOperation(Long id){
        return operationRepository.getReferenceById(id);
    }

}
