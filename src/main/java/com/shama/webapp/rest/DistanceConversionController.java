package com.shama.webapp.rest;

import com.shama.webapp.models.Distance;
import com.shama.webapp.models.Unit;
import com.shama.webapp.service.DistanceConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/distance")
public class DistanceConversionController {

    @GetMapping("/convert/{from}/{quantity}/{to}")
    public ResponseEntity<Double> convert(@PathVariable String from, @PathVariable Double quantity, @PathVariable String to){
        Unit fromUnit= Unit.valueOf(from);
        Unit toUnit= Unit.valueOf(to);
        return ResponseEntity.ok(DistanceConversion.convert(quantity,fromUnit,toUnit));
    }

}
