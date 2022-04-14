package com.example.ExchangeRates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.math.BigDecimal;

@Controller
public class ExchangeRatesController {

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    @GetMapping("/conversion")
    public String indexForm(Model model) {
        model.addAttribute("conversion", new Conversion());
        return "conversion";
    }

    @PostMapping("/conversion")
    public String conversionSubmit(@ModelAttribute Conversion conversion, Model model) {
        BigDecimal exchangedCurrency = exchangeRatesService.exchangeRates(conversion.getMonetaryValue(), conversion.getSourceCurrency(), conversion.getTargetCurrency());
        model.addAttribute("exchangedCurrency", exchangedCurrency);
        model.addAttribute("targetCurrency", conversion.getTargetCurrency());
        return "conversion";
    }

}
