package com.diplom.toloka_bee_backend.controller;

import com.diplom.toloka_bee_backend.model.dto.BlocUserDTO;
import com.diplom.toloka_bee_backend.service.AdminService;
import com.diplom.toloka_bee_backend.service.BlockUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/blocking")
public class AdminBlockingController {

    @Autowired
    private BlockUserService blockUserService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/block")
    @Operation(summary = "Block user for a number of days (admin only)")
    public void blockUser(@RequestParam BlocUserDTO blocUserDTO) {
        if (!adminService.isAdmin(blocUserDTO.getAdminUserId())) {
            throw new RuntimeException("Access denied");
        }
        blockUserService.blockUser(blocUserDTO.getUserId(), blocUserDTO.getDays());
    }

    @PostMapping("/block-permanent")
    @Operation(summary = "Block user permanently (admin only)")
    public void blockUserPermanently(@RequestParam BlocUserDTO blocUserDTO) {
        if (!adminService.isAdmin(blocUserDTO.getAdminUserId())) {
            throw new RuntimeException("Access denied");
        }
        blockUserService.blockUserPermanently(blocUserDTO.getUserId());
    }

    @PostMapping("/unblock/{userId}")
    @Operation(summary = "Unblock user (admin only)")
    public void unblockUser(@RequestParam String adminUserId, @PathVariable String userId) {
        if (!adminService.isAdmin(adminUserId)) {
            throw new RuntimeException("Access denied");
        }
        blockUserService.unblockUser(userId);
    }
}
