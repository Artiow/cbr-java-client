package com.artiow.cbr.api.model.mapper;

import com.artiow.cbr.api.model.CentralBankRate;
import com.artiow.cbr.api.model.schema.ValCurs;
import com.artiow.cbr.api.model.schema.Valute;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public abstract class CentralBankMapper {

    public static CentralBankMapper instance() {
        return Mappers.getMapper(CentralBankMapper.class);
    }


    public List<CentralBankRate> map(ValCurs valCurs) {
        if (valCurs == null) {
            return null;
        }

        return map(valCurs.getValuteList());
    }

    protected abstract List<CentralBankRate> map(List<Valute> valuteList);

    protected abstract CentralBankRate map(Valute valute);


    protected BigDecimal parse(String value) {
        if (value == null) {
            return null;
        }

        return new BigDecimal(value.replace(',', '.'));
    }
}
