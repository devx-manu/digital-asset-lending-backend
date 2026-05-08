package com.project.DigitalAssetLendingSystem.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DigitalAssetLendingSystem.entity.Asset;
import com.project.DigitalAssetLendingSystem.entity.Lending;
import com.project.DigitalAssetLendingSystem.entity.Status;
import com.project.DigitalAssetLendingSystem.entity.User;
import com.project.DigitalAssetLendingSystem.exception.BadRequestException;
import com.project.DigitalAssetLendingSystem.exception.ResourceNotFoundException;
import com.project.DigitalAssetLendingSystem.exception.UnauthorizedException;
import com.project.DigitalAssetLendingSystem.repository.AssetRepository;
import com.project.DigitalAssetLendingSystem.repository.LendingRepository;
import com.project.DigitalAssetLendingSystem.repository.UserRepository;

@Service
public class LendingService {

    @Autowired private LendingRepository lendingRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private AssetRepository assetRepo;

    public Lending request(Long userId, Long assetId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Asset asset = assetRepo.findById(assetId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Asset not found"));

        if (asset.getAvailableLicenses() <= 0)
            throw new BadRequestException(
                    "No licenses available"
            );

        if (!user.getDepartment().equals(asset.getDepartment()))
        	throw new BadRequestException(
        	        "Department mismatch"
        	);

        boolean already =
                lendingRepo.existsByUserIdAndAssetIdAndStatusIn(
                        userId,
                        assetId,
                        List.of(Status.PENDING, Status.ACTIVE)
                );

        if (already)
            throw new BadRequestException(
                    "Already borrowed"
            );

        Lending lending = new Lending();

        lending.setUser(user);
        lending.setAsset(asset);
        lending.setBorrowDate(LocalDate.now());
        lending.setStatus(Status.PENDING);

        return lendingRepo.save(lending);
    }

    public void approve(Long lendingId, Long managerId) {

        Lending lending = lendingRepo.findById(lendingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Lending not found"
                        ));

        User manager = userRepo.findById(managerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Manager not found"
                        ));

        if (!manager.getDepartment()
                .equals(lending.getAsset().getDepartment())) {

            throw new UnauthorizedException(
                    "Not allowed"
            );
        }

        if (lending.getStatus() != Status.PENDING) {

            throw new BadRequestException(
                    "Only PENDING requests can be approved"
            );
        }

        Asset asset = lending.getAsset();

        if (asset.getAvailableLicenses() <= 0) {

            throw new BadRequestException(
                    "No licenses available"
            );
        }

        lending.setStatus(Status.ACTIVE);

        asset.setAvailableLicenses(
                asset.getAvailableLicenses() - 1
        );

        assetRepo.save(asset);
        lendingRepo.save(lending);
    }

    public void returnAsset(Long lendingId) {

        Lending lending = lendingRepo.findById(lendingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Lending not found"));

        if (lending.getStatus() != Status.ACTIVE) {

            throw new BadRequestException(
                    "Only ACTIVE lendings can be returned"
            );
        }

        lending.setReturnDate(LocalDate.now());
        lending.setStatus(Status.RETURNED);

        Asset asset = lending.getAsset();

        asset.setAvailableLicenses(
                asset.getAvailableLicenses() + 1
        );

        assetRepo.save(asset);
        lendingRepo.save(lending);
    }

    public void reject(Long lendingId, Long managerId) {

        Lending lending = lendingRepo.findById(lendingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Lending not found"
                        ));

        User manager = userRepo.findById(managerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Manager not found"
                        ));

        if (!manager.getDepartment()
                .equals(lending.getAsset().getDepartment())) {

            throw new UnauthorizedException(
                    "Not allowed"
            );
        }

        if (lending.getStatus() != Status.PENDING) {

            throw new BadRequestException(
                    "Only PENDING requests can be rejected"
            );
        }

        lending.setStatus(Status.REJECTED);

        lendingRepo.save(lending);
    }
}