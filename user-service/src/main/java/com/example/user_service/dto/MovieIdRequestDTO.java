package com.example.user_service.dto;

import java.util.List;

public class MovieIdRequestDTO {
    private List<Long> ids;

    public List<Long> getIds() {
        return this.ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public MovieIdRequestDTO(){

    }

    public MovieIdRequestDTO(List<Long> ids){
        this.ids  = ids;
    }

}
