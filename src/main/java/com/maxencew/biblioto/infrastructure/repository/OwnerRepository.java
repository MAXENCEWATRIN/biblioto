package com.maxencew.biblioto.infrastructure.repository;

import com.maxencew.biblioto.infrastructure.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {
}