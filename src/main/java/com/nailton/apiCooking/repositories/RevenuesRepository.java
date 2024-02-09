package com.nailton.apiCooking.repositories;

import com.nailton.apiCooking.models.Revenues;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RevenuesRepository extends CrudRepository<Revenues, UUID> {
}
