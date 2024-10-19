package com.crud.dto;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;
import com.crud.enums.Gender;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    @NotBlank(message = "Nationality is not blank !")
    @NotNull(message = "Nationality is not null !")
    private String p_n_id;
    @NotBlank(message = "Name is not blank !")
    @NotNull(message = "Name is not null !")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Invalid format name")
    private String p_name;
    // @NotBlank(message = "Age is not blank !")
    // @NotNull(message = "Age is not null !")
    private Integer p_age;
    // @NotBlank(message = "Birthday is not blank !")
    // @NotNull(message = "Birthday is not null !")
    private Date p_birthday;
    // @NotBlank(message = "Income is not blank !")
    // @NotNull(message = "Income is not null !")
    private BigDecimal p_income;
    // @NotBlank(message = "Gender is not blank !")
    // @NotNull(message = "Gender is not null !")
    private Gender p_gender;
    // @NotBlank(message = "Status is not blank !")
    // @NotNull(message = "Status is not null !")
    private Boolean p_status;
}