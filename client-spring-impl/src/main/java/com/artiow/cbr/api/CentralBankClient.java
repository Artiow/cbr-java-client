package com.artiow.cbr.api;

import com.artiow.cbr.api.model.CentralBankRate;
import com.artiow.cbr.api.model.mapper.CentralBankMapper;
import com.artiow.cbr.api.model.schema.ValCurs;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.util.List;

import static java.time.temporal.ChronoField.*;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class CentralBankClient implements CentralBankResource {

    private static final DateTimeFormatter PARAMETER_DATE;

    static {
        PARAMETER_DATE = new DateTimeFormatterBuilder()
                .appendValue(DAY_OF_MONTH, 2)
                .appendLiteral('-')
                .appendValue(MONTH_OF_YEAR, 2)
                .appendLiteral('-')
                .appendValue(YEAR, 4, 4, SignStyle.NEVER)
                .toFormatter();
    }


    private final CentralBankMapper mapper = CentralBankMapper.instance();
    private final RestTemplate restTemplate;


    @Override
    public List<CentralBankRate> daily(LocalDate date) {
        val uriBuilder = UriComponentsBuilder.fromUriString("https://www.cbr.ru/scripts/XML_daily.asp");
        if (nonNull(date)) {
            uriBuilder.queryParam("date_req", date.format(PARAMETER_DATE));
        }
        return mapper.map(restTemplate.getForEntity(uriBuilder.encode().build().toUri(), ValCurs.class).getBody());
    }
}
