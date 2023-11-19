package com.nordeus.jobfair.auctionservice.auctionservice.domain.model;

import lombok.Getter;

import java.util.Random;
@Getter
public class UserId {
    private final int id;

    public UserId(){
        this.id = new Random().nextInt();
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
