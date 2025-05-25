package com.diplom.zip_way_backend.model.request_personalization;

import com.diplom.zip_way_backend.model.RequestModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserProfileStats {
    private final boolean prefersRemote;
    private final boolean prefersWithPhysicalStrength;
    private final List<String> preferredUsersToHelp;
    private final List<String> preferredRequestTypes;


    public static UserProfileStats fromWorkHistory(List<RequestModel> history) {
        if (history.isEmpty()) {
            return new UserProfileStats(true, false, List.of(), List.of());
        }
        boolean prefersRemote = history.stream()
                .filter(RequestModel::isRemote)
                .count() > history.size() / 2;
        boolean prefersWithPhysicalStrength = history.stream()
                .filter(RequestModel::isRequiresPhysicalStrength)
                .count() > history.size() / 2;
        List<String> preferredUsersToHelp = history.stream()
                .map(RequestModel::getUserId)
                .distinct()
                .toList();


        List<String> preferredRequestTypes = history.stream()
                .map(RequestModel::getRequestType)
                .distinct()
                .toList();
        return new UserProfileStats(prefersRemote, prefersWithPhysicalStrength, preferredUsersToHelp, preferredRequestTypes);
    }
}
