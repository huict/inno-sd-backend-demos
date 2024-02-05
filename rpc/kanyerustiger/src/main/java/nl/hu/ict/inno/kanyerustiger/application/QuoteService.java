package nl.hu.ict.inno.kanyerustiger.application;

import nl.hu.ict.inno.kanyerustiger.domain.Quote;
import nl.hu.ict.inno.kanyerustiger.apis.QuoteApi;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

    private final QuoteApi api;

    public QuoteService(QuoteApi api){
        this.api = api;
    }

    public Quote getQuote(){
        return api.fetchQuote();
    }
}
