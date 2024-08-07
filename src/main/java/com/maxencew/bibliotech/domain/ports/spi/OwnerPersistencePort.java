package com.maxencew.bibliotech.domain.ports.spi;

import com.maxencew.bibliotech.domain.model.Owner;

import java.util.List;

public interface OwnerPersistencePort {

    Owner addOwner(Owner owner);

    void removeOwner(Owner owner);

    List<Owner> getOwners();

    Owner getOwnerById(Long ownerId);

}
