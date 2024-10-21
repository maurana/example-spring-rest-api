package com.crud.dto;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.FutureOrPresent;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.crud.enums.Gender;
import com.crud.constant.Constant;
import com.crud.utils.BooleanDeserializer;

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
    @Pattern(regexp = Constant.Regex.ALPHANUMERIC_WITH_DOT_AND_SPACE, message = "Invalid format name !")
    private String p_name;
    @NotNull(message = "Age is not null !")
    @Positive(message = "Age must positive numeric !")
    private Integer p_age;
    @NotBlank(message = "Email is not blank !")
    @NotNull(message = "Email is not null !")
    @Email(message = "Invalid format email !")
    private String p_email;
    @NotNull(message = "Birthday is not null !")
    @FutureOrPresent(message = "Birthday must date type")
    private Date p_birthday;
    @NotNull(message = "Income is not null !")
    @Positive(message = "Income must positive numeric !")
    private BigDecimal p_income;
    @NotNull(message = "Gender is not null !")
    private Gender p_gender;
    @NotNull(message = "Status is not null !")
    @JsonDeserialize(using = BooleanDeserializer.class)
    private Boolean p_status;
}