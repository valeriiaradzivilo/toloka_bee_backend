package com.diplom.toloka_bee_backend.model.request_personalization;

import com.diplom.toloka_bee_backend.model.RequestModel;

public class RequestScoringService {


    public int computeScore(RequestModel request, UserProfileStats stats, double distanceKm) {
        int score = 0;

        if (request.isRemote() == stats.isPrefersRemote()) score += 10;

        if (!request.isRemote()) {
            if (distanceKm < 1) score += 15;
            else if (distanceKm < 5) score += 13;
            else if (distanceKm < 15) score += 2;
        }

        if (request.isRequiresPhysicalStrength() == stats.isPrefersWithPhysicalStrength()) score += 2;

        if (stats.getPreferredRequestTypes() != null && !stats.getPreferredRequestTypes().isEmpty() && request.getRequestType() != null) {
            if (stats.getPreferredRequestTypes().contains(request.getRequestType())) score += 10;
        }

        if (stats.getPreferredUsersToHelp() != null && !stats.getPreferredUsersToHelp().isEmpty() && request.getUserId() != null) {
            if (stats.getPreferredUsersToHelp().contains(request.getUserId())) score += 5;
        }

        long timeUntilDeadline = request.getDeadline().getTime() - System.currentTimeMillis();
        if (timeUntilDeadline < 3600000) score += 5;
        else if (timeUntilDeadline < 86400000) score += 2;

        return score;
    }
}