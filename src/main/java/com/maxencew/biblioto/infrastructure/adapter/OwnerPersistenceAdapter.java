package com.maxencew.biblioto.infrastructure.adapter;

import com.maxencew.biblioto.application.mapper.entity.OwnerEntityMapper;
import com.maxencew.biblioto.domain.model.Owner;
import com.maxencew.biblioto.domain.ports.spi.OwnerPersistencePort;
import com.maxencew.biblioto.infrastructure.entity.OwnerEntity;
import com.maxencew.biblioto.infrastructure.exception.AppPersistenceException;
import com.maxencew.biblioto.infrastructure.repository.OwnerRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OwnerPersistenceAdapter implements OwnerPersistencePort {

    private final OwnerRepository ownerRepository;
    private final OwnerEntityMapper ownerEntityMapper;

    public OwnerPersistenceAdapter(OwnerRepository ownerRepository, OwnerEntityMapper ownerEntityMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerEntityMapper = ownerEntityMapper;
    }

    @Override
    public Owner addOwner(Owner owner) {
        OwnerEntity ownerEntity = ownerEntityMapper.toEntity(owner);
        OwnerEntity persistedOwner;
        try {
            persistedOwner = ownerRepository.save(ownerEntity);
        } catch (Exception e) {
            throw new AppPersistenceException(e.getMessage(), e);
        }
        return  ownerEntityMapper.toDomain(persistedOwner);
    }

    @Override
    public void removeOwner(Owner owner) {
        OwnerEntity ownerEntity = ownerEntityMapper.toEntity(owner);
        try {
            ownerRepository.delete(ownerEntity);
        } catch (Exception e) {
            throw new AppPersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Owner> getOwners() {
        List<OwnerEntity> ownerEntities;
        try {
            ownerEntities = ownerRepository.findAll();
        } catch (Exception e) {
            throw new AppPersistenceException(e.getMessage(), e);
        }
        return ownerEntityMapper.toDomainList(ownerEntities);
    }

    @Override
    public Owner getOwnerById(Long ownerId) {
        OwnerEntity referenceById;
        try {
            referenceById = this.ownerRepository.getReferenceById(ownerId);
        } catch (Exception e) {
            throw new AppPersistenceException(e.getMessage(), e);
        }
        return ownerEntityMapper.toDomain(referenceById);
    }

}
