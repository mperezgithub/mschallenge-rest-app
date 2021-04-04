package com.intercorpretail.mschallenge.service.impl;

import com.intercorpretail.mschallenge.dto.StatsResponse;
import com.intercorpretail.mschallenge.repository.ClientRepository;
import com.intercorpretail.mschallenge.service.ClientService;
import com.intercorpretail.mschallenge.utils.DodGeneratorUtil;
import com.intercorpretail.mschallenge.utils.StatsUtil;
import lombok.RequiredArgsConstructor;
import model.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    private final StatsUtil statsUtil;

    @Override
    public Client save(Client client) {
        client.setDod(DodGeneratorUtil.generateDOD(client.getAge()));
        return repository.save(client);
    }

    @Override
    public List<Client> findAll() {
        List<Client> clientes = repository.findAll();
        return clientes;
    }

    @Override
    public StatsResponse getStats() {
        List<Client> clients = this.findAll();
        List<Integer> agesList = clients.stream().map(Client::getAge).collect(Collectors.toList());
        Double average = statsUtil.calculateArithmeticMean(agesList);
        Double standardDeviation = statsUtil.calculateStandardDeviation(agesList);
        StatsResponse<Client> response = new StatsResponse<>(average, standardDeviation, "Información descriptiva de los clientes", clients);
        return response;
    }
}
