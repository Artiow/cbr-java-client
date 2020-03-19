package com.artiow.cbr.api;

import com.artiow.cbr.api.model.CentralBankRate;

import java.time.LocalDate;
import java.util.List;

public interface CentralBankResource {

    /**
     * Returns quotes on the specified date set by the Central Bank of the Russian Federation.
     *
     * @param date the rating date;
     * @return the list of central bank currency rates.
     * @see <a href="https://www.cbr.ru/development/SXML/"/>
     */
    List<CentralBankRate> daily(LocalDate date);
}
