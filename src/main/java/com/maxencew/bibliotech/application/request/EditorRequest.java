package com.maxencew.bibliotech.application.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditorRequest {

    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    private String category;
    private String edition;
}
