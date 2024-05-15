package com.university.msclient.service;

import com.university.msclient.entity.Client;
import com.university.msclient.repository.ClientRepository;
import com.university.msclient.request.RegisterClientRequest;
import com.university.msclient.response.ClientInfoResponse;
import com.university.msclient.response.DeleteClientResponse;
import com.university.msclient.response.RegisterClientResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientTokenInfoService clientTokenInfoService;

    public ClientService(ClientRepository clientRepository,
                         ClientTokenInfoService clientTokenInfoService) {
        this.clientRepository = clientRepository;
        this.clientTokenInfoService = clientTokenInfoService;
    }

    public RegisterClientResponse registerClient(RegisterClientRequest request) {
        String email = clientTokenInfoService.getEmailFromToken();
        clientRepository.save(toEntity(request, email));
        return new RegisterClientResponse("OK");
    }

    public ClientInfoResponse getClientInfo() {
        String email = clientTokenInfoService.getEmailFromToken();
        return map(clientRepository.findByEmail(email).orElse(new Client()));
    }

    public DeleteClientResponse deleteClient(String email) {
        var client = clientRepository.deleteByEmail(email);
        if (client.isEmpty()) {
            return new DeleteClientResponse("Client with email " + email + " not found");
        }
        return new DeleteClientResponse("OK");
    }

    private Client toEntity(RegisterClientRequest request, String email) {
        Client client = new Client();
        client.setEmail(email);
        client.setFirstName(request.getFirstName());
        client.setSurname(request.getSurname());
        client.setThirdName(request.getThirdName());
        client.setPhoneNumber(request.getPhoneNumber());
        client.setTaxId(request.getTaxId());
        client.setIsCompany(request.getCompany());
        client.setBirthDate(request.getBirthDate());
        return client;
    }

    private ClientInfoResponse map(Client client) {
        ClientInfoResponse response = new ClientInfoResponse();
        response.setBirthDate(client.getBirthDate());
        response.setEmail(client.getEmail());
        response.setCompany(client.getIsCompany());
        response.setFirstName(client.getFirstName());
        response.setSurname(client.getSurname());
        response.setThirdName(client.getThirdName());
        response.setTaxId(client.getTaxId());
        response.setPhoneNumber(client.getPhoneNumber());
        return response;
    }

}
