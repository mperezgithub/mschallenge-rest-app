package com.intercorpretail.mschallenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercorpretail.mschallenge.service.ClientService;
import model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
class ClientControllerTest {

    @Mock
    private ClientService mockClientService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ClientController clientControllerUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(clientControllerUnderTest).build();
    }

    @Test
    void testGetAllClients() throws Exception {
        when(mockClientService.findAll()).thenReturn(buildClientResponse());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/clientes/listclientes"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void testGetKPIClients() throws Exception {
        when(mockClientService.findAll()).thenReturn(buildClientResponse());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/clientes/listclientes"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void testAddClient() throws Exception {
        Client client = buildClient();
        when(mockClientService.save(any())).thenReturn(client);
        mockMvc.perform(post("/api/clientes/creacliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(client)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());
    }

    private List<Client> buildClientResponse() {
        List<Client> response = new ArrayList<>();
        response.add(new Client("id", "name", "lastname", 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime()));
        return response;
    }

    private Client buildClient() {
        return new Client("id", "name", "lastname", 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
    }
}
