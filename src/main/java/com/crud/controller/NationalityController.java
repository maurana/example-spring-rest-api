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

import com.crud.dto.NationalityDto;
import com.crud.service.NationalityService;
import com.crud.response.GeneralResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/crud/nationality")
public class NationalityController {

    @Autowired
    private NationalityService nationalityService;

    @GetMapping("/list")
    public ResponseEntity<GeneralResponse> list() {
        return ResponseEntity.ok(nationalityService.list());
    }

    @GetMapping("/show")
    public ResponseEntity<GeneralResponse> show(@RequestParam(value = "n_id", defaultValue = "")String id) {
        return ResponseEntity.ok(nationalityService.show(id));
    }

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> create(@Valid @RequestBody NationalityDto nationalityDto) {
        return ResponseEntity.ok(nationalityService.create(nationalityDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GeneralResponse> update(@PathVariable String id, @Valid @RequestBody NationalityDto nationalityDto) {
        return ResponseEntity.ok(nationalityService.update(id, nationalityDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<GeneralResponse> delete(@RequestParam(value = "n_id", defaultValue = "")String id) {
        return ResponseEntity.ok(nationalityService.delete(id));
    }
}