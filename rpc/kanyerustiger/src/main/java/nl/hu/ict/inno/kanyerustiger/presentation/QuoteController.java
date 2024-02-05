package nl.hu.ict.inno.kanyerustiger.presentation;

import nl.hu.ict.inno.kanyerustiger.application.QuoteService;
import nl.hu.ict.inno.kanyerustiger.domain.Quote;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quote")
public class QuoteController {

    private final QuoteService service;

    public QuoteController(QuoteService service){
        this.service = service;
    }

    public record QuoteDTO(String quote) {
    }

    @GetMapping
    public ResponseEntity<QuoteDTO> getQuote() {
        Quote q = this.service.getQuote();
        return ResponseEntity.ok(new QuoteDTO(q.getQuote()));
    }

}
