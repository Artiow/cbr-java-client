package com.artiow.cbr.api.model.schema;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@XmlRootElement(name = "Valute")
@XmlAccessorType(XmlAccessType.NONE)
public class Valute {

    @XmlAttribute(name = "ID")
    private String id;

    @XmlElement(name = "NumCode")
    private String numCode;

    @XmlElement(name = "CharCode")
    private String charCode;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Nominal")
    private String nominal;

    @XmlElement(name = "Value")
    private String value;
}
