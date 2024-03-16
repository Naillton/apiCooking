package com.nailton.apiCooking.dto;

import com.nailton.apiCooking.models.Revenues;

public record RevenuesDTO(String title, String products, String description) {

    public Revenues toEntity() {
        return new Revenues(title, products, description);
    }
}
