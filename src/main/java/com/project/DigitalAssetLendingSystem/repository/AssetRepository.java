package com.project.DigitalAssetLendingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.DigitalAssetLendingSystem.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
