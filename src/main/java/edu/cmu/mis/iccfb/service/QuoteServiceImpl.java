package edu.cmu.mis.iccfb.service;

import java.util.List;
import java.util.Random;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import edu.cmu.mis.iccfb.model.Quote;


public class QuoteServiceImpl implements QuoteServiceCustom {

    Random random = new Random();
    
    @Autowired
    private QuoteService quoteService;
    
    @Override
    public Quote randomQuote() {
        List<Quote> quotes = Lists.newArrayList(this.quoteService.findAll());
        Quote q = quotes.get(random.nextInt(quotes.size()));
        return q;
    }

}
