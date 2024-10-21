package com.crud.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.crud.constant.Constant;
import com.crud.model.Person;
import com.crud.model.Nationality;
import com.crud.dto.PersonDto;
import com.crud.request.RequestPage;
import com.crud.response.GeneralResponse;
import com.crud.exception.NotFoundException;
import com.crud.exception.DataExistException;
import com.crud.exception.BadRequestCustomException;
import com.crud.repository.PersonRepository;
import com.crud.repository.NationalityRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private NationalityRepository nationalityRepository;

    public GeneralResponse<Object> paging(RequestPage requestPage) {
        String p_name = filterMapping(requestPage.getP_name());
        String n_id = filterMapping(requestPage.getN_id());
        Pageable paging = PageRequest.of(requestPage.getPageNumber(), requestPage.getPageSize());
        Page<Person> personPage = personRepository.findPerPage(p_name, n_id, paging);

        return GeneralResponse.builder()
            .responseCode(Constant.Response.SUCCESS_CODE)
            .responseMessage(Constant.Response.SUCCESS_MESSAGE)
            .data(Objects.nonNull(personPage) ? personPage.getContent() : new ArrayList<>())
            .totalPage(Objects.nonNull(personPage) ? personPage.getTotalPages() : null)
            .totalData(Objects.nonNull(personPage) ?  personPage.getTotalElements() : null)
            .pageNumber(requestPage.getPageNumber())
            .pageSize(requestPage.getPageSize())
            .build();
    }

    public GeneralResponse<Object> list() {
        List<Person> personList = personRepository.findAll();
        return GeneralResponse.builder()
            .responseCode(Constant.Response.SUCCESS_CODE)
            .responseMessage(Constant.Response.SUCCESS_MESSAGE)
            .data(personList)
            .totalData((long)personList.size())
            .build();
    }

    public GeneralResponse<Object> show(String id) {
        Person person = personRepository.getById(id);
        if(Objects.isNull(person)) {
            throw new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE);
        }

        return GeneralResponse.builder()
            .responseCode(Constant.Response.SUCCESS_CODE)
            .responseMessage(Constant.Response.SUCCESS_MESSAGE)
            .data(person)
            .build();
    }

    @Transactional
    public GeneralResponse<Object> create(PersonDto personDto) {
        Nationality nationality = nationalityRepository.getById(personDto.getP_n_id());
        if(Objects.isNull(nationality)) {
            throw new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE);
        }

        Person exsistName = personRepository.findByName(personDto.getP_name().toLowerCase());
        if (Objects.nonNull(exsistName)) {
            throw new DataExistException(Constant.Message.EXIST_DATA_MESSAGE);
        }

        Person savedPerson = personRepository.save(mappingPerson(personDto, nationality));
        return GeneralResponse.builder()
            .responseCode(Constant.Response.SUCCESS_CODE)
            .responseMessage(Constant.Response.SUCCESS_MESSAGE)
            .data(savedPerson)
            .build();
    }

    @Transactional
    public GeneralResponse<Object> update(String id, PersonDto personDto) {
        Nationality nationality = nationalityRepository.getById(personDto.getP_n_id());
        if(Objects.isNull(nationality)) {
            throw new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE);
        }

        Person person = personRepository.getById(id);
        if (Objects.isNull(person)) {
            throw new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE);
        }

        if (!personDto.getP_name().equalsIgnoreCase(person.getP_name())) {
            throw new BadRequestCustomException(Constant.Message.FORBIDDEN_REQUEST_MESSAGE.replace("{value}", "n_name"));
        }

        Person updatedPerson = Person.builder()
            .p_id(id)
            .p_n_id(nationality)
            .p_name(personDto.getP_name())
            .p_age(personDto.getP_age())
            .p_email(personDto.getP_email())
            .p_birthday(personDto.getP_birthday())
            .p_income(personDto.getP_income())
            .p_gender(personDto.getP_gender())
            .p_status(personDto.getP_status())
            .build();

        personRepository.save(updatedPerson);
        return GeneralResponse.builder()
            .responseCode(Constant.Response.SUCCESS_CODE)
            .responseMessage(Constant.Response.SUCCESS_MESSAGE)
            .data(updatedPerson)
            .build();
    }

    @Transactional
    public GeneralResponse<Object> delete(String id) {
        Person person = personRepository.getById(id);
        if (Objects.isNull(person)) {
            throw new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE);
        }

        personRepository.deleteById(id);
        return GeneralResponse.builder()
            .responseCode(Constant.Response.SUCCESS_CODE)
            .responseMessage(Constant.Response.SUCCESS_MESSAGE)
            .build();
    }

    private String filterMapping(String value) {
        if(Objects.isNull(value) || ObjectUtils.isEmpty(value.trim())) {
            return "%%";
        } else {
            return "%".concat(value.toLowerCase()).concat("%");
        }
    }

    private Person mappingPerson(PersonDto personDto, Nationality nationality) {
        return Person.builder()
            .p_n_id(nationality)
            .p_name(personDto.getP_name())
            .p_age(personDto.getP_age())
            .p_email(personDto.getP_email())
            .p_birthday(personDto.getP_birthday())
            .p_income(personDto.getP_income())
            .p_gender(personDto.getP_gender())
            .p_status(personDto.getP_status())
            .build();
    }

}