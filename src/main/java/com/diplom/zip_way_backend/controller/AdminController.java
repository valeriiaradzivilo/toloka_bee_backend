package com.diplom.zip_way_backend.controller;

import com.diplom.zip_way_backend.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/add-admin/{userId}")
    @Operation(summary = "Grant admin rights to a user")
    public void addAdmin(@PathVariable String userId) {
        adminService.addAdmin(userId);
    }

    @PostMapping("/remove-admin/{userId}")
    @Operation(summary = "Revoke admin rights from a user")
    public void removeAdmin(@PathVariable String userId) {
        adminService.removeAdmin(userId);
    }

    @GetMapping("/is-admin/{userId}")
    @Operation(summary = "Check if user is an admin")
    public boolean isAdmin(@PathVariable String userId) {
        return adminService.isAdmin(userId);
    }
}
