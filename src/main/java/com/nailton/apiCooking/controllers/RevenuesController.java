package com.nailton.apiCooking.controllers;

import com.nailton.apiCooking.configuration.Util.Middlewares;
import com.nailton.apiCooking.dto.RevenuesDTO;
import com.nailton.apiCooking.models.Revenues;
import com.nailton.apiCooking.services.RevenuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/revenues")
public class RevenuesController {

    @Autowired
    private RevenuesService revenuesService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<Revenues>> getRevenues() {
        try {
            List<Revenues> rev = this.revenuesService.getRevenues();
            return ResponseEntity.status(HttpStatus.OK).body(rev);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<String> insertRevenues(@RequestBody  RevenuesDTO revenuesDTO) {
        try {
            boolean istrue = Middlewares.validCredentialsRevenues(revenuesDTO.title(), revenuesDTO.products(), revenuesDTO.description());
            if (istrue) {
                this.revenuesService.insertRevenues(revenuesDTO.toEntity());
                return ResponseEntity.status(HttpStatus.OK).body("Inserted Revenues");
            }
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid Fields");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
