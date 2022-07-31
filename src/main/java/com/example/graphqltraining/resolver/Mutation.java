package com.example.graphqltraining.resolver;

import com.example.graphqltraining.bean.Client;
import com.example.graphqltraining.bean.Compte;
import com.example.graphqltraining.bean.Operation;
import com.example.graphqltraining.repository.ClientRepository;
import com.example.graphqltraining.repository.CompteRepository;
import com.example.graphqltraining.repository.OperationRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Component
@AllArgsConstructor
public class Mutation implements GraphQLMutationResolver {
    private ClientRepository clientRepository;
    private CompteRepository compteRepository;
    private OperationRepository operationRepository;

    @Transactional
    public Client createClient(String CNE,
                                   String firstName,
                                   String lastName,
                                   String phoneNumber,
                                   String email,
                                   String address,
                                   Date dateNaissance) {
        Client client = new Client(CNE, firstName, lastName, phoneNumber, email, address, dateNaissance);
        client.setCode("code101");
        clientRepository.save(client);
        return client;
    }

    @Transactional
    public Client updateClient(Long id,
                                String CNE,
                                String firstName,
                                String lastName,
                                String phoneNumber,
                                String email,
                                String address,
                                Date dateNaissance){
        Client client = clientRepository.getReferenceById(id);
        client.setCNE(CNE);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setPhoneNumber(phoneNumber);
        client.setEmail(email);
        client.setAddress(address);
        client.setDateNaissance(dateNaissance);
        return clientRepository.save(client);
    }

    @Transactional
    public boolean deleteClient(Long id){
        clientRepository.deleteById(id);
        return true;
    }

    @Transactional
    public Compte createCompte(float solde,
                                Date dateCreation,
                                String typeCompte,
                                Long idClient){
        Client client = clientRepository.getReferenceById(idClient);
        Compte compte = new Compte(solde, dateCreation, typeCompte, client);
        compte.setNumero(1101);
        return compteRepository.save(compte);
    }

    @Transactional
    public Compte updateCompte(Long id,
                               float solde,
                               Date dateCreation,
                               String typeCompte,
                               Long idClient){
        Client client = clientRepository.getReferenceById(idClient);
        Compte compte = compteRepository.getReferenceById(id);
        compte.setSolde(solde);
        compte.setDateCreation(dateCreation);
        compte.setTypeCompte(typeCompte);
        compte.setClient(client);
        return compteRepository.save(compte);
    }

    @Transactional
    public boolean deleteCompte(Long id){
        compteRepository.deleteById(id);
        return true;
    }

    @Transactional
    public Operation createOperation(Date date,
                                     float montant,
                                     String typeOpration,
                                     Long idCompte){
        Compte compte = compteRepository.getReferenceById(idCompte);
        Operation operation = new Operation(date, montant, typeOpration, compte);
        operation.setNumero(1101);
        return operationRepository.save(operation);
    }

    @Transactional
    public Operation updateOperation(Long id,
                                     Date date,
                                     float montant,
                                     String typeOpration,
                                     Long idCompte){
        Operation operation = operationRepository.getReferenceById(id);
        Compte compte = compteRepository.getReferenceById(idCompte);
        operation.setDate(date);
        operation.setMontant(montant);
        operation.setTypeOperation(typeOpration);
        operation.setCompte(compte);
        return operationRepository.save(operation);
    }

    @Transactional
    public boolean deleteOperation(Long id){
        operationRepository.deleteById(id);
        return true;
    }

}
