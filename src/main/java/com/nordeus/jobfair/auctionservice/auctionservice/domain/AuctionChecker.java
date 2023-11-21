package com.nordeus.jobfair.auctionservice.auctionservice.domain;

import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.Auction;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.service.AuctionNotifer;

import java.util.Collection;
import java.util.Iterator;
import java.util.TimerTask;

public class AuctionChecker extends TimerTask {
    private Collection<Auction> auctions;

    private final AuctionNotifer auctionNotifer;

    public AuctionChecker(Collection<Auction> auctions, AuctionNotifer auctionNotifer) {
        this.auctions = auctions;
        this.auctionNotifer = auctionNotifer;
    }

    @Override
    public void run() {
        long currentTime = System.currentTimeMillis();
        synchronized (auctions) {
            Iterator<Auction> it = auctions.iterator();
            while (it.hasNext()) {
                Auction a = it.next();
                long timeElapsed = currentTime - a.getStartTime();
                if(timeElapsed > 60_000){
                    if(a.getCurrentBid() == null || currentTime - a.getCurrentBid().getTime() > 5_000){
                        auctionNotifer.auctionFinished(a);
                        it.remove();
                    }
                }
            }
        }
    }
}
