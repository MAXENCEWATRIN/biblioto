package com.maxencew.bibliotech.domain.ports.api;

import com.maxencew.bibliotech.domain.model.Library;

import java.util.List;

public interface LibraryService {

    Library addLibrary(Library library);

    void removeLibrary(Library library);

    List<Library> getLibraries();

    Library getLibraryById(Long libraryId);
}