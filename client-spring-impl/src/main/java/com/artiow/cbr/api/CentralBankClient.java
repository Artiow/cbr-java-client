package com.artiow.cbr.api;

import com.artiow.cbr.api.model.CentralBankRate;
import com.artiow.cbr.api.model.mapper.CentralBankMapper;
import com.artiow.cbr.api.model.schema.ValCurs;
import lombok.val;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.util.List;

import static com.artiow.cbr.api.model.util.QueryParamsBuilder.queryParamsBuilder;
import static java.time.temporal.ChronoField.*;
import static java.util.Optional.ofNullable;

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


    private final RestTemplate restTemplate;
    private final CentralBankMapper mapper;


    public CentralBankClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.mapper = new CentralBankMapper();
    }


    private static String format(LocalDate date) {
        return ofNullable(date).map(d -> d.format(PARAMETER_DATE)).orElse(null);
    }


    private static URI buildUri(String uriString) {
        return UriComponentsBuilder.fromUriString(uriString).encode().build().toUri();
    }

    private static URI buildUri(String uriString, MultiValueMap<String, String> queryParams) {
        return UriComponentsBuilder.fromUriString(uriString).queryParams(queryParams).encode().build().toUri();
    }


    @Override
    public List<CentralBankRate> daily(LocalDate date) {
        val queryParams = queryParamsBuilder(1)
                .set("date_req", format(date));

        return mapper.map(request(
                buildUri("https://www.cbr.ru/scripts/XML_daily.asp", queryParams.build()),
                ValCurs.class
        ));
    }


    private <T> T request(URI url, Class<T> responseType) {
        return restTemplate.getForEntity(url, responseType).getBody();
    }
}
