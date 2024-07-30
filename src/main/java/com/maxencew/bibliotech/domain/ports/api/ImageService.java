package com.maxencew.bibliotech.domain.ports.api;

import org.bson.types.ObjectId;

public interface ImageService {

    ObjectId downloadAndStoreImage(String imageUrl);
    byte[] getImageById(ObjectId id);
    void deleteImageById(ObjectId id);
}