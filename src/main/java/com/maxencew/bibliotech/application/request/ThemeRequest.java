package com.maxencew.bibliotech.application.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThemeRequest {

    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    private List<String> keywords;

}
