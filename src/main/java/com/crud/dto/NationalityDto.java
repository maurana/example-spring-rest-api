package com.crud.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NationalityDto {
    @NotBlank(message = "Name is not blank !")
    @NotNull(message = "Name is not null !")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Invalid format name")
    private String n_name;
    @NotBlank(message = "Desc is not blank !")
    @NotNull(message = "Desc is not null !")
    private String n_desc;
    // private List<PersonDto> persons;
}