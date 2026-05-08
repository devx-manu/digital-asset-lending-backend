package com.project.DigitalAssetLendingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.project.DigitalAssetLendingSystem.dto.AssetRequest;
import com.project.DigitalAssetLendingSystem.entity.Asset;
import com.project.DigitalAssetLendingSystem.repository.AssetRepository;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetRepository repo;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Asset create(@RequestBody AssetRequest request) {

        Asset asset = new Asset();

        asset.setName(request.getName());
        asset.setType(request.getType());
        asset.setDepartment(request.getDepartment());
        asset.setTotalLicenses(request.getTotalLicenses());
        asset.setAvailableLicenses(request.getAvailableLicenses());

        return repo.save(asset);
    }

    @GetMapping
    public List<Asset> getAll() {
        return repo.findAll();
    }
}