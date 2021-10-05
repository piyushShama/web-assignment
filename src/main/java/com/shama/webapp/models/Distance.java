package com.shama.webapp.models;

public class Distance {
    private Double quantity;
    private Unit fromUnit;
    private Unit toUnit;
    private Double convertedValue;

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Unit getFromUnit() {
        return fromUnit;
    }

    public void setFromUnit(Unit fromUnit) {
        this.fromUnit = fromUnit;
    }

    public Unit getToUnit() {
        return toUnit;
    }

    public void setToUnit(Unit toUnit) {
        this.toUnit = toUnit;
    }

    public Double getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(Double convertedValue) {
        this.convertedValue = convertedValue;
    }
}
