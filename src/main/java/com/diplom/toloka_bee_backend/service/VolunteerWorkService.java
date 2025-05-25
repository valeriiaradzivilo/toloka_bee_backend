package com.diplom.toloka_bee_backend.service;

import com.diplom.toloka_bee_backend.model.VolunteerWorkModel;
import com.diplom.toloka_bee_backend.repository.VolunteerWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VolunteerWorkService {

    @Autowired
    private VolunteerWorkRepository volunteerWorkRepository;

    public VolunteerWorkModel startWork(String volunteerId, String requesterId, String requestId) {
        VolunteerWorkModel work = new VolunteerWorkModel();
        work.setVolunteerId(volunteerId);
        work.setRequesterId(requesterId);
        work.setRequestId(requestId);
        work.setStartedAt(new Date());
        work.setVolunteerConfirmed(false);
        work.setRequesterConfirmed(false);
        return volunteerWorkRepository.save(work);
    }

    public List<VolunteerWorkModel> getWorksByVolunteer(String volunteerId) {
        return volunteerWorkRepository.findByVolunteerId(volunteerId);
    }

    public List<VolunteerWorkModel> getWorksByRequester(String requesterId) {
        return volunteerWorkRepository.findByRequesterId(requesterId);
    }

    public Optional<VolunteerWorkModel> getWorkById(String id) {
        return volunteerWorkRepository.findById(id);
    }

    public void confirmByVolunteer(String workId) {
        volunteerWorkRepository.findById(workId).ifPresent(work -> {
            work.setVolunteerConfirmed(true);
            if (work.isRequesterConfirmed()) {
                work.setFinishedAt(new Date());
            }
            volunteerWorkRepository.save(work);
        });
    }

    public void confirmByRequester(String workId) {
        volunteerWorkRepository.findById(workId).ifPresent(work -> {
            work.setRequesterConfirmed(true);
            if (work.isVolunteerConfirmed()) {
                work.setFinishedAt(new Date());
            }
            volunteerWorkRepository.save(work);
        });
    }

    public void deleteByUserId(String userId) {
        volunteerWorkRepository.deleteAllByVolunteerId(userId);
    }

    public List<VolunteerWorkModel> getWorksByRequestId(String requestId) {
        return volunteerWorkRepository.findByRequestId(requestId);
    }

    public void cancelWork(String workId) {
        volunteerWorkRepository.deleteById(workId);
    }
}
