package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IncentiveApiService {
    private final RestTemplate restTemplate;

    public IncentiveApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Incentive getIncentive(Transaction transaction) {
        return restTemplate.postForObject("http://localhost:8080/incentive", transaction, Incentive.class);
    }
}