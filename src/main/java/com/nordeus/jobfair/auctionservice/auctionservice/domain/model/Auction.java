package com.nordeus.jobfair.auctionservice.auctionservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Auction {

    private final AuctionId auctionId;

    @Setter
    private Bid currentBid;

    private final List<User> users;

    private final long startTime;

    private int price;

    public Auction() {
        this.auctionId = new AuctionId();
        this.users = new ArrayList<>();
        this.startTime = System.currentTimeMillis();
        this.price = 1;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "auctionId=" + auctionId +
                ", currentBid=" + currentBid +
                ", price=" + price +
                '}';
    }

    public void addUser(User u){
        users.add(u);
    }

    public void increasePrice(){
        this.price++;
    }
}
