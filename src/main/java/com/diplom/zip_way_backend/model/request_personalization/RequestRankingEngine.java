package com.diplom.zip_way_backend.model.request_personalization;

import com.diplom.zip_way_backend.model.RequestModel;

import java.util.Comparator;
import java.util.List;

public class RequestRankingEngine {

    private final RequestDecisionTreeBuilder treeBuilder = new RequestDecisionTreeBuilder();
    private final RequestScoringService scoringService = new RequestScoringService();

    public List<RequestModel> rankRequests(List<RequestModel> requests, UserProfileStats stats, double distanceKm) {
        RequestDecisionNode root = treeBuilder.buildTree(stats, distanceKm);

        return requests.stream()
                .filter(request -> treeBuilder.isRelevant(request, root))
                .sorted(Comparator.comparingInt(
                        (RequestModel r) -> scoringService.computeScore(r, stats, distanceKm)
                ).reversed())
                .toList();
    }
}