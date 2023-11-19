package com.nordeus.jobfair.auctionservice.auctionservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class User {

    private final UserId userId;
    private final String name;

    public User(String name){
        this.userId = new UserId();
        this.name = name;
    }
}
