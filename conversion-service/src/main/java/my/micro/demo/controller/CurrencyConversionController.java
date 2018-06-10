package my.micro.demo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import my.micro.demo.dto.CurrencyConversionBean;
import my.micro.demo.proxy.CurrencyExchangeServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RefreshScope
@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    @Value("${app.message}")
    private String appMessage;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    @HystrixCommand(fallbackMethod = "conversionFallback")
    public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {

        CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }

    public CurrencyConversionBean conversionFallback(String a, String b, BigDecimal c) {
        return CurrencyConversionBean.builder().build();
    }

    @GetMapping("/currency-converter/message")
    public String getMessage() {
        return appMessage;
    }

}