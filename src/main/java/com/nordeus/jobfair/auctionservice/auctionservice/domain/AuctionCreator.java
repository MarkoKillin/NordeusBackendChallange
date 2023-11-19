package com.nordeus.jobfair.auctionservice.auctionservice.domain;

import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.Auction;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.service.AuctionNotifer;

import java.util.Collection;
import java.util.TimerTask;

public class AuctionCreator extends TimerTask {

    private Collection<Auction> auctions;

    private final AuctionNotifer auctionNotifer;

    public AuctionCreator(Collection<Auction> auctions, AuctionNotifer auctionNotifer) {
        this.auctions = auctions;
        this.auctionNotifer = auctionNotifer;
    }

    @Override
    public void run() {
        synchronized (auctions) {
            for (int i = 0; i < 10; i++) {
                auctions.add(new Auction());
            }
        }
        auctionNotifer.activeAuctionsRefreshed(auctions);
    }
}
