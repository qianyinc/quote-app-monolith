package edu.cmu.mis.iccfb.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.cmu.mis.iccfb.model.Author;
import edu.cmu.mis.iccfb.model.Quote;
import edu.cmu.mis.iccfb.service.AuthorService;
import edu.cmu.mis.iccfb.service.QuoteService;

@RestController
public class QuoteController {
    
    @Autowired
    private QuoteService quoteService;
    
    @Autowired
    private AuthorService authorService;
    
    @RequestMapping("/api/quote/random")
    public Quote random() {
        return quoteService.randomQuote();
    }
    
    @RequestMapping(value = "/api/quote/by")
    public List<Quote> findByName(@RequestParam(required = true)String name) {
        return quoteService.findByAuthor_Name(name);
    }

    @RequestMapping(value = "/api/author", method = RequestMethod.GET)
    public List<Author> listAuthors() {
        List<Author> authors = new ArrayList<>();
        authorService.findAll().iterator().forEachRemaining(authors::add);
        return authors;
    }
    
    
    
    
    

    @RequestMapping(value = "/api/quote", method = RequestMethod.POST)
    public void saveQuote(@RequestBody Quote quote) {
        System.out.println(quote);
        
        Author a = authorService.findByName(quote.getAuthor().getName());
        
        if (a == null) {
            System.out.println("Saving author");
            authorService.save(quote.getAuthor());
        }
        else {
            quote.setAuthor(a);
        }
        
        
        System.out.println("Saving quote");
        quoteService.save(quote);
    }
    
    
    
    public Quote fallback() {
        Quote q = new Quote();
        Author a = new Author("Confucius");
        q.setText("The superior man is modest in his speech, but exceeds in his actions.");
        q.setAuthor(a);
        return q; 
    }
}
