package com.crud.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.crud.dto.PersonDto;
import com.crud.service.PersonService;
import com.crud.request.RequestPage;
import com.crud.response.GeneralResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/crud/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/list")
    public ResponseEntity<GeneralResponse> list() {
        return ResponseEntity.ok(personService.list());
    }

    @PostMapping("/paging")
    public ResponseEntity<GeneralResponse> paging(@Valid @RequestBody RequestPage requestPage) {
        return ResponseEntity.ok(personService.paging(requestPage));
    }

    @GetMapping("/show")
    public ResponseEntity<GeneralResponse> show(@RequestParam(value = "p_id", defaultValue = "")String id) {
        return ResponseEntity.ok(personService.show(id));
    }

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> create(@Valid @RequestBody PersonDto personDto) {
        return ResponseEntity.ok(personService.create(personDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GeneralResponse> update(@PathVariable String id, @Valid @RequestBody PersonDto personDto) {
        return ResponseEntity.ok(personService.update(id, personDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<GeneralResponse> delete(@RequestParam(value = "p_id", defaultValue = "")String id) {
        return ResponseEntity.ok(personService.delete(id));
    }
}