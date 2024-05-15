package com.university.msclient.controller;

import com.university.msclient.request.RegisterClientRequest;
import com.university.msclient.response.ClientInfoResponse;
import com.university.msclient.response.DeleteClientResponse;
import com.university.msclient.response.RegisterClientResponse;
import com.university.msclient.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class InternalClientController {

    private final ClientService clientService;

    public InternalClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @PostMapping("/client/register")
    @PreAuthorize("hasAuthority('CLIENT')")
    public RegisterClientResponse registerClient(@Valid @RequestBody RegisterClientRequest request) {
        return clientService.registerClient(request);
    }

    @GetMapping("/client")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ClientInfoResponse getClientInfo() {
        return clientService.getClientInfo();
    }

    @DeleteMapping("/client")
    @PreAuthorize("hasAuthority('ADMIN')")
    public DeleteClientResponse deleteClient(@RequestParam String email) {
        return clientService.deleteClient(email);
    }

}
