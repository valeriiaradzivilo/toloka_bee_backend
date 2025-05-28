package com.diplom.toloka_bee_backend.controller;

import com.diplom.toloka_bee_backend.model.dto.BlocUserDTO;
import com.diplom.toloka_bee_backend.service.AdminService;
import com.diplom.toloka_bee_backend.service.BlockUserService;
import com.diplom.toloka_bee_backend.service.UserDeletionService;
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

    @Autowired
    private UserDeletionService userDeletionService;

    @PostMapping("/block")
    @Operation(summary = "Block user for a number of days (admin only)")
    public void blockUser(@RequestBody BlocUserDTO blocUserDTO) {
        if (!adminService.isAdmin(blocUserDTO.getAdminUserId())) {
            throw new RuntimeException("Access denied");
        }
        blockUserService.blockUser(blocUserDTO.getUserId(), blocUserDTO.getDays());
        userDeletionService.blockUser(blocUserDTO.getUserId());
    }

    @PostMapping("/block-permanent")
    @Operation(summary = "Block user permanently (admin only)")
    public void blockUserPermanently(@RequestBody BlocUserDTO blocUserDTO) {
        if (!adminService.isAdmin(blocUserDTO.getAdminUserId())) {
            throw new RuntimeException("Access denied");
        }
        blockUserService.blockUserPermanently(blocUserDTO.getUserId());
        userDeletionService.blockUser(blocUserDTO.getUserId());
    }

}
