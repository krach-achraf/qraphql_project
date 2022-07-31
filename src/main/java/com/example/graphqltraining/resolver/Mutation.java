package com.example.graphqltraining.resolver;

import com.example.graphqltraining.bean.Client;
import com.example.graphqltraining.bean.Compte;
import com.example.graphqltraining.bean.Operation;
import com.example.graphqltraining.exception.ClientAlreadyExist;
import com.example.graphqltraining.exception.NotFoundException;
import com.example.graphqltraining.exception.ValidationException;
import com.example.graphqltraining.repository.ClientRepository;
import com.example.graphqltraining.repository.CompteRepository;
import com.example.graphqltraining.repository.OperationRepository;
import com.example.graphqltraining.utils.CodeUtil;
import com.example.graphqltraining.utils.NumeroUtil;
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
        if(clientDataIsValid(CNE, firstName, lastName, phoneNumber, email)){
            if(clientRepository.findByEmail(email) != null
                    || clientRepository.findByCNE(CNE) != null
                    || clientRepository.findByPhoneNumber(phoneNumber) != null)
                throw new ClientAlreadyExist("Client is already exist");
            Client client = new Client(CNE, firstName, lastName, phoneNumber, email, address, dateNaissance);
            client.setCode(CodeUtil.generateCode(10));
            return clientRepository.save(client);
        }else
            throw new ValidationException("Send all the values");
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
        if(clientDataIsValid(CNE, firstName, lastName, phoneNumber, email)){
            Client client = clientRepository.getReferenceById(id);
            client.setCNE(CNE);
            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setPhoneNumber(phoneNumber);
            client.setEmail(email);
            client.setAddress(address);
            client.setDateNaissance(dateNaissance);
            return clientRepository.save(client);
        }else
            throw new ValidationException("Send all the values");
    }

    @Transactional
    public boolean deleteClient(Long id){
        if (clientRepository.findById(id).isPresent()){
            clientRepository.deleteById(id);
            return true;
        }else throw new NotFoundException("Client not found");
    }

    @Transactional
    public Compte createCompte(float solde,
                                Date dateCreation,
                                String typeCompte,
                                Long idClient){
        if(!typeCompte.trim().equals("")){
            if(clientRepository.findById(idClient).isPresent()){
                Client client = clientRepository.getReferenceById(idClient);
                Compte compte = new Compte(solde, dateCreation, typeCompte, client);
                compte.setNumero(NumeroUtil.generateNumero());
                return compteRepository.save(compte);
            }else throw new NotFoundException("Client not found");
        }else throw new ValidationException("Send all the values");

    }

    @Transactional
    public Compte updateCompte(Long id,
                               float solde,
                               Date dateCreation,
                               String typeCompte,
                               Long idClient){
        if(!typeCompte.trim().equals("")){
            if(compteRepository.findById(id).isPresent()){
                if(clientRepository.findById(idClient).isPresent()){
                    Client client = clientRepository.getReferenceById(idClient);
                    Compte compte = compteRepository.getReferenceById(id);
                    compte.setSolde(solde);
                    compte.setDateCreation(dateCreation);
                    compte.setTypeCompte(typeCompte);
                    compte.setClient(client);
                    return compteRepository.save(compte);
                }else throw new NotFoundException("Client not found");
            }else throw new NotFoundException("Compte not found");
        }else throw new ValidationException("Send all the values");
    }

    @Transactional
    public boolean deleteCompte(Long id){
        if (compteRepository.findById(id).isPresent()){
            compteRepository.deleteById(id);
            return true;
        }else throw new NotFoundException("Compte not found");
    }

    @Transactional
    public Operation createOperation(Date date,
                                     float montant,
                                     String typeOpration,
                                     Long idCompte){
        if(!typeOpration.trim().equals("")){
            if(compteRepository.findById(idCompte).isPresent()){
                Compte compte = compteRepository.getReferenceById(idCompte);
                Operation operation = new Operation(date, montant, typeOpration, compte);
                operation.setNumero(NumeroUtil.generateNumero());
                return operationRepository.save(operation);
            }else throw new NotFoundException("Compte not found");
        }else throw new ValidationException("Send all the values");

    }

    @Transactional
    public Operation updateOperation(Long id,
                                     Date date,
                                     float montant,
                                     String typeOpration,
                                     Long idCompte){
        if(!typeOpration.trim().equals("")){
            if(operationRepository.findById(id).isPresent()){
                if(compteRepository.findById(idCompte).isPresent()){
                    Operation operation = operationRepository.getReferenceById(id);
                    Compte compte = compteRepository.getReferenceById(idCompte);
                    operation.setDate(date);
                    operation.setMontant(montant);
                    operation.setTypeOperation(typeOpration);
                    operation.setCompte(compte);
                    return operationRepository.save(operation);
                }else throw new NotFoundException("Compte not found");
            }else throw new NotFoundException("Operation not found");

        }else throw new ValidationException("Send all the values");
    }

    @Transactional
    public boolean deleteOperation(Long id){
        if (operationRepository.findById(id).isPresent()) {
            operationRepository.deleteById(id);
            return true;
        }else throw new NotFoundException("Operation not found");
    }

    private boolean clientDataIsValid(String CNE,
                                       String firstName,
                                       String lastName,
                                       String phoneNumber,
                                       String email){
        return !CNE.trim().equals("") &&
                !firstName.trim().equals("") &&
                !lastName.trim().equals("") &&
                !phoneNumber.trim().equals("") &&
                !email.trim().equals("");
    }
}
