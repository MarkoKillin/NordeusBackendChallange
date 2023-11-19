package com.nordeus.jobfair.auctionservice.auctionservice.domain.model;

import lombok.Getter;

import java.util.Random;
@Getter
public class AuctionId {
    private final int id;

    public AuctionId(){
        this.id = new Random().nextInt();
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
