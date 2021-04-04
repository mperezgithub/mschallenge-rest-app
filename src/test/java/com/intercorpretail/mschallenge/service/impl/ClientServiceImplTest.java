package com.intercorpretail.mschallenge.service.impl;

import com.intercorpretail.mschallenge.dto.StatsResponse;
import com.intercorpretail.mschallenge.repository.ClientRepository;
import com.intercorpretail.mschallenge.utils.StatsUtil;
import model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.Duration;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class ClientServiceImplTest {

    @Mock
    private ClientRepository mockRepository;
    @Mock
    private StatsUtil mockStatsUtil;

    private ClientServiceImpl target;

    @BeforeEach
    void setUp() {
        initMocks(this);
        target = new ClientServiceImpl(mockRepository, mockStatsUtil);
    }

    @Test
    void testSave() {
        final Date dob = new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime();
        final Date expectedDob = new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime();
        final Date dod = new GregorianCalendar(2050, Calendar.DECEMBER, 1).getTime();
        final Client client = new Client("id", "name", "lastname", 0, dob, dod);
        when(mockRepository.save(any())).thenReturn(client);

        final Client result = target.save(client);

        assertNotNull(result);
        assertEquals(result.getName(), "name");
        assertEquals(result.getLastname(), "lastname");
        assertEquals(result.getAge(), 0);
        assertEquals(result.getDob(), expectedDob);
        assertTrue(Duration.between(result.getDob().toInstant(), result.getDod().toInstant()).toDays() >= 0);
        verify(mockRepository, times(1)).save(anyObject());
    }

    @Test
    void testFindAll() {
        final List<Client> resultList = buildClientResponse();
        final List<Client> expectedResult = List.of(new Client("id", "name", "lastname", 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime()));

        when(mockRepository.findAll()).thenReturn(resultList);

        final List<Client> result = target.findAll();

        assertEquals(expectedResult, result);
        verify(mockRepository, times(1)).findAll();
    }

    @Test
    void testGetStats() {
        final List<Client> resultList = buildClientResponse();
        StatsResponse statsResponse = buildStatsResponse(resultList);
        when(mockRepository.findAll()).thenReturn(resultList);

        final StatsResponse result = target.getStats();

        assertTrue(statsResponse.getData().size() > 0);
        verify(mockRepository, times(1)).findAll();
    }


    private List<Client> buildClientResponse() {
        List<Client> response = new ArrayList<>();
        response.add(new Client("id", "name", "lastname", 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime()));
        return response;
    }

    private StatsResponse buildStatsResponse(List<Client> clients) {
        StatsResponse<Client> response = new StatsResponse();
        response.setData(clients);
        response.setAverage(10.00d);
        response.setStandardDeviation(1.00d);
        return response;
    }


}
