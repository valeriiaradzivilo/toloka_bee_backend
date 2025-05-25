package com.diplom.zip_way_backend.controller;

import com.diplom.zip_way_backend.model.LocationSubscriptionModel;
import com.diplom.zip_way_backend.service.LocationSubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location-subscription")
public class LocationSubscriptionController {

    @Autowired
    private LocationSubscriptionService service;

    @PostMapping("/subscribe")
    @Operation(summary = "Subscribe user to location")
    public void subscribe(@RequestBody LocationSubscriptionModel request) {
//        System.out.println("Subscribing user: " + request.getUserId() + " to topic: " + request.getTopic());
        service.subscribe(request.getUserId(), request.getTopic());
    }

    @PostMapping("/unsubscribe")
    @Operation(summary = "Unsubscribe user from location")
    public void unsubscribe(@RequestBody LocationSubscriptionModel request) {
        service.unsubscribe(request.getUserId(), request.getTopic());
    }

    @GetMapping("/count/{location}")
    @Operation(summary = "Get number of subscribers for location")
    public List<LocationSubscriptionModel> getSubscriptionCount(@PathVariable String location) {
        return service.getSubscriptionByTopic(location);
    }
}
