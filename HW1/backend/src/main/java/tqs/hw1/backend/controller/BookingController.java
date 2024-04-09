package tqs.hw1.backend.controller;

import tqs.hw1.backend.cache.Currency;
import tqs.hw1.backend.cache.CurrencyAPI;
import tqs.hw1.backend.cache.TTLCache;
import tqs.hw1.backend.entity.*;
import tqs.hw1.backend.service.BookingService;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.ui.Model;

@Controller 
public class BookingController {

    @Autowired
    private BookingService bookingService;
    
    private static Logger logger = LogManager.getLogger(BookingController.class);

    @GetMapping(value = {"/", "/home"})
    public String showHome(Model model) {
        return "index";
    }

    /*
    @PostMapping("/search")
    public String searchTrips(@ModelAttribute SearchForm searchForm) {
        // Access the search criteria from the searchForm object
        String from = searchForm.getFrom();
        String to = searchForm.getTo();
        LocalDate date = searchForm.getDate();
    
        // TODO: Process the search criteria and return the appropriate response
        
        return "search-results"; // Return the name of the view to render the search results
    }
    */
}
