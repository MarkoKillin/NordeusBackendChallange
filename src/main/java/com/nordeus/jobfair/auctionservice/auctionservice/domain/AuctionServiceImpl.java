package com.nordeus.jobfair.auctionservice.auctionservice.domain;

import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.*;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.service.AuctionNotifer;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.service.AuctionNotifierLogger;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class AuctionServiceImpl implements AuctionService {

    private final AuctionNotifer auctionNotifer;

    private final Collection<Auction> auctions;
    private final Timer timer;
    public AuctionServiceImpl() {
        this.auctionNotifer = new AuctionNotifierLogger();
        this.auctions = new LinkedList<>();
        this.timer = new Timer();
        this.timer.schedule(new AuctionCreator(auctions, auctionNotifer), 0, 60_000);
        this.timer.schedule(new AuctionChecker(auctions, auctionNotifer), 2_000, 2_000);
    }

    @Override
    public Collection<Auction> getAllActive() {
        return auctions;
    }

    @Override
    public Auction getAuction(AuctionId auctionId) {
        synchronized (auctions){
            for (Auction a: auctions) {
                if (a.getAuctionId() == auctionId)
                    return a;
            }
            return null;
        }
    }

    @Override
    public void join(AuctionId auctionId, User user) {
        synchronized (auctions){
            for (Auction a: auctions) {
                if(a.getAuctionId() == auctionId){
                    a.addUser(user);
                }
            }
        }
    }

    @Override
    public void bid(AuctionId auctionId, UserId userId) {
        Bid nBid = new Bid(auctionId, userId);
        synchronized (auctions){
            for (Auction a: auctions) {
                if(a.getAuctionId() == auctionId){
                    a.setCurrentBid(nBid);
                    a.increasePrice();
                }
            }
        }
        auctionNotifer.bidPlaced(nBid);
    }
}
