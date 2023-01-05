package com.intercorpretail.mschallenge.controller;

import com.intercorpretail.mschallenge.dto.ClientDTO;
import com.intercorpretail.mschallenge.dto.Response;
import com.intercorpretail.mschallenge.dto.StatsResponse;
import com.intercorpretail.mschallenge.service.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import model.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Lista todos los Clientes", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Devuelve una respuesta con la lista de clientes completa"),
            @ApiResponse(code = 404, message = "El recurso consultado no se encuentra disponible"),
            @ApiResponse(code = 500, message = "Se produjo un error interno en el servidor")
    })
    @GetMapping("/listclientes")
    public ResponseEntity<Response> getAllClients() {
        List<ClientDTO> clientDTOList = clientService.findAll()
                .stream()
                .map(post -> modelMapper.map(post, ClientDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<Response>(new Response<ClientDTO>("Lista de todos los clientes", clientDTOList), HttpStatus.OK);
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
    public ResponseEntity<Response<ClientDTO>> addClient(@Valid @RequestBody ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        Client clientSaved = clientService.save(client);
        return new ResponseEntity<>(new Response<>("Se guardó exitosamente un cliente con id = " + clientSaved.getId(), List.of(clientDTO)), HttpStatus.CREATED);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>(HttpStatus.OK.name(), HttpStatus.OK);
    }
}
