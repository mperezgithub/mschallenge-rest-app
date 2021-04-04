package com.intercorpretail.mschallenge.service;

import com.intercorpretail.mschallenge.dto.StatsResponse;
import model.Client;

import java.util.List;

public interface ClientService {
    Client save(Client cliente);
    List<Client> findAll();
    StatsResponse getStats();
}
