package com.example.reddit.controller;

import com.example.reddit.domain.Link;
import com.example.reddit.domain.Vote;
import com.example.reddit.service.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class VoteController {
    private VoteService voteService;
    private LinkService linkService;

    public VoteController(VoteService voteService, LinkService linkService) {
        this.voteService = voteService;
        this.linkService = linkService;
    }

    @Secured("ROLE_USER")
    @GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
    public int vote(@PathVariable Long linkID, @PathVariable short direction, @PathVariable int voteCount){
        Optional<Link> link = linkService.findById(linkID);
        if(link.isPresent()){
            Link l = link.get();
            Vote v = new Vote(direction, l);
            voteService.save(v);
            int updatedVoteCount = voteCount + direction;
            l.setVoteCount(updatedVoteCount);
            linkService.save(l);
            return updatedVoteCount;
        }
        return voteCount;
    }
}
