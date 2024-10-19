package com.crud.request;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestPage {
    @NotNull(message = "Page Number is mandatory")
    private int pageNumber;
    @NotNull(message = "Page Size is mandatory")
    private int pageSize;
    private String p_name;
    private String n_id;
}
