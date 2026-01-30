package com.example.carVatika.dto;

import java.util.List;

public class MergeCartRequestDto {

    private List<MergeCartItemDto> items;

    public List<MergeCartItemDto> getItems() {
        return items;
    }

    public void setItems(List<MergeCartItemDto> items) {
        this.items = items;
    }
}
