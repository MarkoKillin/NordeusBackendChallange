package com.nordeus.jobfair.auctionservice.auctionservice.api;

import com.nordeus.jobfair.auctionservice.auctionservice.domain.AuctionService;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.AuctionServiceImpl;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.Auction;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.AuctionId;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.User;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.UserId;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/auctions")
public class HttpController {
    @Autowired
    private AuctionService auctionService;

    @GetMapping("/active")
    public Collection<Auction> getAllActive() {
        return auctionService.getAllActive();
    }

    @PostMapping("/join/{aId}/{user}")
    public void joinAucition(@RequestParam("aId") AuctionId aId, @RequestParam("user") User user) {
        auctionService.join(aId, user);
    }

    @PostMapping("/bid/{aId}/{uId}")
    public void bidAuction(@RequestParam("aId") AuctionId aId, @RequestParam("uId") UserId uId){
        auctionService.bid(aId, uId);
    }

}
