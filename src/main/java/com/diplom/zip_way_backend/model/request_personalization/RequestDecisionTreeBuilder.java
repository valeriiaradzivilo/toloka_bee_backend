package com.diplom.zip_way_backend.model.request_personalization;

import com.diplom.zip_way_backend.model.RequestModel;

import java.util.HashMap;
import java.util.Map;

public class RequestDecisionTreeBuilder {

    Map<String, Boolean> cache = new HashMap<>();

    public RequestDecisionNode buildTree(UserProfileStats stats, double distanceKm) {
        RequestDecisionNode root = new RequestDecisionNode(
                request -> request.isRemote() == stats.isPrefersRemote()
        );

        root.yes = new RequestDecisionNode(
                request -> request.isRemote() || distanceKm < 5
        );

        root.no = new RequestDecisionNode(
                request -> request.getDeadline().getTime() - System.currentTimeMillis() < 86400000
        );

        return root;
    }

    public boolean isRelevant(RequestModel request, RequestDecisionNode node) {
        if (cache.containsKey(request.getId())) {
            return cache.get(request.getId());
        }

        boolean result;
        if (node == null) {
            result = true;
        } else if (node.evaluate(request)) {
            result = isRelevant(request, node.yes);
        } else {
            result = isRelevant(request, node.no);
        }

        cache.put(request.getId(), result);
        return result;
    }

}