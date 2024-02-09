package com.nailton.apiCooking.services;

import com.nailton.apiCooking.repositories.RevenuesRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RevenuesService {

    @Autowired
    private RevenuesRepository revenuesRepository;
}
