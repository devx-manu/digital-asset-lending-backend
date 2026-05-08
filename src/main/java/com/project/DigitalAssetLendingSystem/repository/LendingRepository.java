package com.project.DigitalAssetLendingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.DigitalAssetLendingSystem.entity.Lending;
import com.project.DigitalAssetLendingSystem.entity.Status;

public interface LendingRepository extends JpaRepository<Lending, Long> {
    List<Lending> findByUserId(Long userId);

    boolean existsByUserIdAndAssetIdAndStatusIn(
            Long userId,
            Long assetId,
            List<Status> statuses
    );
}
