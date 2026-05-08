package com.project.DigitalAssetLendingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.DigitalAssetLendingSystem.dto.ApproveRejectRequest;
import com.project.DigitalAssetLendingSystem.entity.Lending;
import com.project.DigitalAssetLendingSystem.entity.User;
import com.project.DigitalAssetLendingSystem.repository.LendingRepository;
import com.project.DigitalAssetLendingSystem.service.LendingService;

@RestController
@RequestMapping("/lendings")
public class LendingController {

    @Autowired
    private LendingRepository lendingRepo;

    @Autowired
    private LendingService service;

    
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public Lending request(@RequestParam Long userId,
                           @RequestParam Long assetId) {

        return service.request(userId, assetId);
    }

   
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}/return")
    public void returnAsset(@PathVariable Long id) {

        service.returnAsset(id);
    }

   
    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{id}/approve")
    public void approve(@PathVariable Long id,
                        @RequestBody ApproveRejectRequest request) {

        service.approve(id, request.getManagerId());
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{id}/reject")
    public void reject(@PathVariable Long id,
                       @RequestBody ApproveRejectRequest request) {

        service.reject(id, request.getManagerId());
    }

    
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping
    public List<Lending> getAll() {

        return lendingRepo.findAll();
    }

    
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    @GetMapping("/user/{id}")
    public List<Lending> getByUser(@PathVariable Long id) {

        return lendingRepo.findByUserId(id);
    }
}