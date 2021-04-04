package com.intercorpretail.mschallenge.controller;

import com.intercorpretail.mschallenge.dto.Response;
import com.intercorpretail.mschallenge.dto.StatsResponse;
import com.intercorpretail.mschallenge.service.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import model.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @ApiOperation(value = "Lista todos los Clientes", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Devuelve una respuesta con la lista de clientes completa"),
            @ApiResponse(code = 404, message = "El recurso consultado no se encuentra disponible"),
            @ApiResponse(code = 500, message = "Se produjo un error interno en el servidor")
    })
    @GetMapping("/listclientes")
    public ResponseEntity<Response> getAllClients() {
        return new ResponseEntity<Response> (new Response<Client>("Lista de todos los clientes", clientService.findAll()), HttpStatus.OK);
    }


    @ApiOperation(value = "Información descriptiva de los Clientes", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Devuelve una respuesta con informacion descriptiva completa de los clientes"),
            @ApiResponse(code = 404, message = "El recurso consultado no se encuentra disponible"),
            @ApiResponse(code = 500, message = "Se produjo un error interno en el servidor")
    })
    @GetMapping("/kpideclientes")
    public ResponseEntity<StatsResponse> getKPIClients() {
        return new ResponseEntity<>(clientService.getStats(), HttpStatus.OK);
    }


    @ApiOperation(value = "Agregar un Cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Devuelve una respuesta con el Cliente guardado exitosamente"),
            @ApiResponse(code = 404, message = "El recurso consultado no se encuentra disponible"),
            @ApiResponse(code = 500, message = "Se produjo un error interno en el servidor")
    })
    @PostMapping("/creacliente")
    public ResponseEntity<Response<Client>> addClient(@Valid @RequestBody Client client) {
        Client clientSaved = clientService.save(client);
        return new ResponseEntity<>(new Response<>("Se guardó exitosamente un cliente con id = " + clientSaved.getId(), List.of(clientSaved)), HttpStatus.CREATED);
    }
}
