package nl.hu.ict.inno.kanyerustiger.apis;

import nl.hu.ict.inno.kanyerustiger.domain.Quote;
import org.springframework.stereotype.Component;

@Component
public class HardCodedApi implements QuoteApi{
    private String quote;

    public HardCodedApi(){
        this.quote = "Zomaar een testquote";
    }

    @Override
    public Quote fetchQuote() {
        return new Quote(this.quote);
    }
}
