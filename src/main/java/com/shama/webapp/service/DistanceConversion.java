package com.shama.webapp.service;

import com.shama.webapp.models.Unit;
import org.springframework.stereotype.Service;

@Service
public class DistanceConversion {

    public Double convert(Double quantity, Unit fromUnit, Unit toUnit) {
        Double result = null;
        if (Unit.MILE == fromUnit && Unit.KM == toUnit) {
            result = quantity * 1.609344;
        } else if (Unit.MILE == fromUnit && Unit.MT == toUnit) {
            result = quantity * 1.609344 * 100;
        } else if (Unit.KM == fromUnit && Unit.MILE == toUnit) {
            result = quantity / 1.609344;
        } else if (Unit.KM == fromUnit && Unit.MT == toUnit) {
            result = quantity * 100;
        } else if (Unit.MT == fromUnit && Unit.MILE == toUnit) {
            result = quantity / 1.609344 * 100;
        } else if (Unit.MT == fromUnit && Unit.KM == toUnit) {
            result = quantity / 100;
        }
        return result;
    }
}