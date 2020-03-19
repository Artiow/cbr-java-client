package com.artiow.cbr.api.model.mapper;

import com.artiow.cbr.api.model.CentralBankRate;
import com.artiow.cbr.api.model.schema.ValCurs;
import com.artiow.cbr.api.model.schema.Valute;
import lombok.val;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public class CentralBankMapper {

    public List<CentralBankRate> map(ValCurs valCurs) {
        return ofNullable(valCurs)
                .map(ValCurs::getValuteList)
                .map(List::stream)
                .map(stream -> stream.map(this::map))
                .map(stream -> stream.collect(toList()))
                .orElse(null);
    }

    private CentralBankRate map(Valute valute) {
        val result = new CentralBankRate();
        result.setId(valute.getId());
        result.setNumCode(parseInteger(valute.getNumCode()));
        result.setCharCode(valute.getCharCode());
        result.setName(valute.getName());
        result.setNominal(parseBigDecimal(valute.getNominal()));
        result.setValue(parseBigDecimal(valute.getValue()));
        return result;
    }


    private Integer parseInteger(String value) {
        if (isNull(value)) {
            return null;
        }

        return new Integer(value);
    }

    private BigDecimal parseBigDecimal(String value) {
        if (isNull(value)) {
            return null;
        }

        return new BigDecimal(value.replace(',', '.'));
    }
}
