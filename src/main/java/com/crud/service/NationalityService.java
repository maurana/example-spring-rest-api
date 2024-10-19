package com.crud.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.crud.constant.Constant;
import com.crud.model.Nationality;
import com.crud.dto.NationalityDto;
import com.crud.response.GeneralResponse;
import com.crud.exception.NotFoundException;
import com.crud.exception.DataExistException;
import com.crud.exception.BadRequestCustomException;
import com.crud.repository.NationalityRepository;

@Service
public class NationalityService {

    @Autowired
    private NationalityRepository nationalityRepository;

    public GeneralResponse<Object> list() {
        List<Nationality> nationalityList = nationalityRepository.findAll();
        return GeneralResponse.builder()
            .responseCode(Constant.Response.SUCCESS_CODE)
            .responseMessage(Constant.Response.SUCCESS_MESSAGE)
            .data(nationalityList)
            .totalData((long)nationalityList.size())
            .build();
    }

    public GeneralResponse<Object> show(String id) {
        Nationality nationality = nationalityRepository.getById(id);
        if(Objects.isNull(nationality)) {
            throw new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE);
        }

        return GeneralResponse.builder()
            .responseCode(Constant.Response.SUCCESS_CODE)
            .responseMessage(Constant.Response.SUCCESS_MESSAGE)
            .data(nationality)
            .build();
    }

    @Transactional
    public GeneralResponse<Object> create(NationalityDto nationalityDto) {
        Nationality exsistName = nationalityRepository.findByName(nationalityDto.getN_name().toLowerCase());
        if (Objects.nonNull(exsistName)) {
            throw new DataExistException(Constant.Message.EXIST_DATA_MESSAGE);
        }

        Nationality savedNationality = nationalityRepository.save(mappingNationality(nationalityDto));
        return GeneralResponse.builder()
            .responseCode(Constant.Response.SUCCESS_CODE)
            .responseMessage(Constant.Response.SUCCESS_MESSAGE)
            .data(savedNationality)
            .build();
    }

    @Transactional
    public GeneralResponse<Object> update(String id, NationalityDto nationalityDto) {
        Nationality nationality = nationalityRepository.getById(id);
        if (Objects.isNull(nationality)) {
            throw new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE);
        }

        if (!nationalityDto.getN_name().equalsIgnoreCase(nationality.getN_name())) {
            throw new BadRequestCustomException(Constant.Message.FORBIDDEN_REQUEST_MESSAGE.replace("{value}", "n_name"));
        }

        Nationality updatedNationality = Nationality.builder()
            .n_id(id)
            .n_name(nationalityDto.getN_name())
            .n_desc(nationalityDto.getN_desc())
            .build();

        nationalityRepository.save(updatedNationality);
        return GeneralResponse.builder()
            .responseCode(Constant.Response.SUCCESS_CODE)
            .responseMessage(Constant.Response.SUCCESS_MESSAGE)
            .data(updatedNationality)
            .build();
    }

    @Transactional
    public GeneralResponse<Object> delete(String id) {
        Nationality nationality = nationalityRepository.getById(id);
        if (Objects.isNull(nationality)) {
            throw new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE);
        }

        nationalityRepository.deleteById(id);
        return GeneralResponse.builder()
            .responseCode(Constant.Response.SUCCESS_CODE)
            .responseMessage(Constant.Response.SUCCESS_MESSAGE)
            .build();
    }

    private Nationality mappingNationality(NationalityDto nationalityDto) {
        return Nationality.builder()
            .n_name(nationalityDto.getN_name())
            .n_desc(nationalityDto.getN_desc())
            .build();
    }

}