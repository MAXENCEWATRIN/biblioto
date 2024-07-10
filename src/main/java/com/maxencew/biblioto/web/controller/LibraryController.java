package com.maxencew.biblioto.web.controller;

import com.maxencew.biblioto.application.mapper.dto.LibraryDtoMapper;
import com.maxencew.biblioto.application.request.LibraryRequest;
import com.maxencew.biblioto.application.response.BibliotoHttpResponse;
import com.maxencew.biblioto.application.response.LibraryResponse;
import com.maxencew.biblioto.application.service.api.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libraries")
public class LibraryController {

    @Autowired
    private LibraryService libraryServiceAdapter;

    @Autowired
    private LibraryDtoMapper libraryDtoMapper;

    @PostMapping
    public BibliotoHttpResponse<LibraryResponse> addLibrary(@RequestBody LibraryRequest library) {
        return BibliotoHttpResponse.success(libraryDtoMapper.toDto(libraryServiceAdapter.addLibrary(libraryDtoMapper.toDomain(library))));
    }

    @PutMapping("/{id}")
    public BibliotoHttpResponse<LibraryResponse> updateLibrary(@PathVariable Long id, @RequestBody LibraryRequest library) {
        library.setId(id);
        return BibliotoHttpResponse.success(libraryDtoMapper.toDto(libraryServiceAdapter.addLibrary(libraryDtoMapper.toDomain(library))));
    }

    @DeleteMapping("/{id}")
    public void deleteLibrary(@PathVariable Long id, @RequestBody LibraryRequest library) {
        library.setId(id);
        libraryServiceAdapter.removeLibrary(libraryDtoMapper.toDomain(library));
    }

    @GetMapping
    public BibliotoHttpResponse<List<LibraryResponse>> getAllLibraries() {
        return BibliotoHttpResponse.success(libraryDtoMapper.toDtoList(libraryServiceAdapter.getLibraries()));
    }

    @GetMapping("/{id}")
    public BibliotoHttpResponse<LibraryResponse> getLibraryById(@PathVariable Long id) {
        return BibliotoHttpResponse.success(libraryDtoMapper.toDto(libraryServiceAdapter.getLibraryById(id)));
    }

}
