//package com.example.insurance;
//
//import com.example.insurance.domain.exceptions.ProviderNotFoundException;
//import com.example.insurance.domain.exceptions.QuoteNotFoundException;
//import com.example.insurance.application.service.QuoteService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
//class QuoteServiceTest {
//
//    @Mock
//    private QuoteRepository quoteRepository;
//
//    @Mock
//    private ProviderRepository providerRepository;
//
//    @InjectMocks
//    private QuoteService quoteService;
//
//    @Test
//    void createQuote_ShouldSaveAndReturnQuote_WhenCreated() {
//        var provider = new Provider("TestInsurance");
//        var quote = new Quote("Health", 500.0, provider);
//        when(quoteRepository.save(any(Quote.class))).thenReturn(quote);
//        when(providerRepository.findByName("TestInsurance")).thenReturn(Optional.of(new Provider("TestInsurance")));
//
//        var created = quoteService.createQuote("Health", 500.0, provider.getName());
//
//        assertEquals("Health", created.getCoverageType());
//        assertEquals(500.0, created.getPrice());
//        assertEquals("TestInsurance", created.getProvider().getName());
//    }
//
//    @Test
//    void createQuote_ShouldThrowException_WhenProviderNotFound() {
//        var provider = new Provider("TestInsurance");
//        assertThrows(ProviderNotFoundException.class,
//                () -> quoteService.createQuote("Health", 500.0, provider.getName()));
//    }
//
//    @Test
//    void getQuoteById_ShouldReturnQuote_WhenCalled() throws QuoteNotFoundException {
//        var quote = new Quote("Car", 300.0, new Provider("DNI"));
//        var id = UUID.randomUUID();
//        when(quoteRepository.findById(id)).thenReturn(Optional.of(quote));
//
//        var found = quoteService.getQuoteById(id);
//        assertEquals("Car", found.getCoverageType());
//    }
//
//    @Test
//    void getQuoteById_ShouldThrowException_WhenQuoteNotFound() {
//        var id = UUID.randomUUID();
//        when(quoteRepository.findById(id)).thenReturn(Optional.empty());
//        assertThrows(QuoteNotFoundException.class, () -> quoteService.getQuoteById(id));
//    }
//
//    @Test
//    void getAllQuotes_ShouldReturnList_WhenCalled() {
//        List<Quote> quotes = List.of(
//                new Quote("Health", 500.0, new Provider("A")),
//                new Quote("Car", 300.0, new Provider("B"))
//        );
//        when(quoteRepository.findAll()).thenReturn(quotes);
//
//        List<Quote> all = quoteService.getAllQuotes();
//        assertEquals(2, all.size());
//    }
//
//    @Test
//    void updateQuote_ShouldModifyAndReturnQuote() throws QuoteNotFoundException {
//        var provider = new Provider("DNI");
//        var existingQuote = new Quote("Car", 400.0, provider);
//        var quoteId = UUID.randomUUID();
//
//        when(quoteRepository.findById(quoteId)).thenReturn(Optional.of(existingQuote));
//        when(providerRepository.findByName("Updated")).thenReturn(Optional.of(new Provider("Updated")));
//        when(quoteRepository.save(any(Quote.class))).thenReturn(existingQuote);
//        var updatedQuote = new QuoteRequest("Car", 300.0, "Updated");
//
//        var updated = quoteService.updateQuote(quoteId, updatedQuote);
//
//        assertEquals("Car", updated.getCoverageType());
//        assertEquals(300.0, updated.getPrice());
//    }
//
//    @Test
//    void deleteQuote_ShouldCallDelete() {
//        var id = UUID.randomUUID();
//        var quote = new Quote("Health", 100.0, new Provider("P"));
//        quote.setId(id);
//        when(quoteRepository.existsById(id)).thenReturn(true);
//
//        quoteService.deleteQuote(id);
//        verify(quoteRepository, times(1)).deleteById(id);
//    }
//
//    @Test
//    void deleteQuote_ShouldThrow_WhenNotExists() {
//        var quoteId = UUID.randomUUID();
//        when(quoteRepository.existsById(quoteId)).thenReturn(false);
//        assertThrows(QuoteNotFoundException.class, () -> quoteService.deleteQuote(quoteId));
//    }
//
//    @Test
//    void getLowestPriceQuote_ShouldReturnMin() {
//        var q1 = new Quote();
//        q1.setPrice(400.0);
//        var q2 = new Quote();
//        q2.setPrice(250.0);
//        when(quoteRepository.findAll()).thenReturn(List.of(q1, q2));
//
//        var best = quoteService.getLowestPriceQuote();
//
//        assertEquals(250, best.getPrice());
//    }
//
//    @Test
//    void getHighestPriceQuote_ShouldReturnMax() {
//        var q1 = new Quote();
//        q1.setPrice(100.0);
//        var q2 = new Quote();
//        q2.setPrice(300.0);
//        when(quoteRepository.findAll()).thenReturn(List.of(q1, q2));
//
//        var best = quoteService.getHighestPriceQuote();
//
//        assertEquals(300, best.getPrice());
//    }
//
//    @Test
//    void getSortedQuotes_ShouldReturnAscendingAndDescending() {
//        var q1 = new Quote();
//        q1.setPrice(50.0);
//        var q2 = new Quote();
//        q2.setPrice(150.0);
//        when(quoteRepository.findAll()).thenReturn(List.of(q2, q1));
//
//        List<Quote> asc = quoteService.getSortedQuotes(true);
//        assertEquals(List.of(q1, q2), asc);
//
//        List<Quote> desc = quoteService.getSortedQuotes(false);
//        assertEquals(List.of(q2, q1), desc);
//    }
//}
