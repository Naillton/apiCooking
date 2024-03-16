package com.nailton.apiCooking.services;

import com.nailton.apiCooking.models.Revenues;
import com.nailton.apiCooking.repositories.RevenuesRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class RevenuesService {

    @Autowired
    private RevenuesRepository revenuesRepository;

    public List<Revenues> getRevenues() {
        return (List<Revenues>) this.revenuesRepository.findAll();
    }

    public void insertRevenues(Revenues revenues) {
        this.revenuesRepository.save(revenues);
    }
}
