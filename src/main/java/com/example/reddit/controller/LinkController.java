package com.example.reddit.controller;

import com.example.reddit.SpringitApplication;
import com.example.reddit.domain.Comment;
import com.example.reddit.domain.Link;
import com.example.reddit.repository.*;
import com.example.reddit.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class LinkController {

    private static final Logger log = LoggerFactory.getLogger(LinkController.class);
    private LinkService linkService;
    private CommentService commentService;

    public LinkController(LinkService linkService, CommentService commentService) {
        this.linkService = linkService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String list(Model m){
        m.addAttribute("links", linkService.findAll());
        return "link/list";
    }

    @GetMapping("/link/{id}")
    public String read(@PathVariable Long id, Model model){
        Optional<Link> link = linkService.findById(id);
        if(link.isPresent()){
            Link currLink = link.get();
            Comment c = new Comment();
            c.setLink(currLink);
            model.addAttribute("link", currLink);
            model.addAttribute("comment", c);
            model.addAttribute("success", model.containsAttribute("success"));
            return "link/view";
        }
        else
            return "redirect:/";
    }

    @GetMapping("/link/submit")
    public String newLinkForm(Model model){
        model.addAttribute("link", new Link());
        return "link/submit";
    }
    @PostMapping("/link/submit")
    public String createLink(@Valid Link link, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            log.info("Validation errors found while submitting link");
            model.addAttribute("link", link);
            return "link/submit";
        }
        else {
            linkService.save(link);
            log.info("link saved successfully");
            redirectAttributes.addAttribute("id", link.getId()).addFlashAttribute("success", true);
            return "redirect:/link/{id}";
        }
    }

    @PostMapping("/link/comments")
    public String addComment(@Valid Comment comment, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            log.info("Comment could not be added");
        else{
            commentService.save(comment);
            log.info("Comment was added successfully!");
        }
        return "redirect:/link/" + comment.getLink().getId();
    }

}
