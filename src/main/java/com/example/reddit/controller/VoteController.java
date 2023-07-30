package com.example.reddit.controller;

import com.example.reddit.domain.Link;
import com.example.reddit.domain.Vote;
import com.example.reddit.repository.LinkRepository;
import com.example.reddit.repository.VoteRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class VoteController {
    private VoteRepository voteRepository;
    private LinkRepository linkRepository;

    public VoteController(VoteRepository voteRepository, LinkRepository linkRepository){
        this.voteRepository = voteRepository;
        this.linkRepository = linkRepository;
    }

    @Secured("ROLE_USER")
    @GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
    public int vote(@PathVariable Long linkID, @PathVariable short direction, @PathVariable int voteCount){
        Optional<Link> link = linkRepository.findById(linkID);
        if(link.isPresent()){
            Link l = link.get();
            Vote v = new Vote(direction, l);
            voteRepository.save(v);
            int updatedVoteCount = voteCount + direction;
            l.setVoteCount(updatedVoteCount);
            linkRepository.save(l);
            return updatedVoteCount;
        }
        return voteCount;
    }
}
