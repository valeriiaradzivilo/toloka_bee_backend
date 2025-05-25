package com.diplom.zip_way_backend.service;

import com.diplom.zip_way_backend.model.RequestModel;
import com.diplom.zip_way_backend.repository.RequestsDataRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ExpiredRequestChecker {

    private final RequestsDataRepository requestRepository;

    public ExpiredRequestChecker(
            final RequestsDataRepository requestRepository
    ) {
        this.requestRepository = requestRepository;

    }

    @Scheduled(fixedDelay = 3_600_000) // every hour
    public void checkExpiredRequests() {
        Date now = new Date();
        List<RequestModel> expiredRequests = requestRepository
                .findByDeadlineBefore(now);

        for (RequestModel request : expiredRequests) {
            if (!request.getStatus().equalsIgnoreCase("expired"))
                request.setStatus("expired");
            requestRepository.save(request);
        }
    }
}
