package com.crud.response;

import java.util.List;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralResponse<T> {
    private int responseCode;
    private String responseMessage;
    private T data;
    private List<String> errorList;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPage;
    private Long totalData;
}