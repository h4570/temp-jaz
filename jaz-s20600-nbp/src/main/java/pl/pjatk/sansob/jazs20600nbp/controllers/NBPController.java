package pl.pjatk.sansob.jazs20600nbp.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.sansob.jazs20600nbp.MyDumbHttpErrorHandlerBecauseImLazyAndIWasNotInLectures;
import pl.pjatk.sansob.jazs20600nbp.fascades.NBPFascade;
import pl.pjatk.sansob.jazs20600nbp.models.Price;
import pl.pjatk.sansob.jazs20600nbp.services.external.NBPService;
import pl.pjatk.sansob.jazs20600nbp.services.internal.PriceService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/nbp")
public class NBPController {

    private NBPFascade fascade;

    public NBPController(PriceService priceService, NBPService nbpService) {
        this.fascade = new NBPFascade(priceService, nbpService);
    }

    @ApiOperation(
            value = "Get average gold price between dates",
            response = Price.class
    )
    @GetMapping("{from}/{to}")
    public ResponseEntity<Price> GetAverageGoldPrice(
            @ApiParam(name = "from",
                    type = "Date",
                    example = "2021-06-25",
                    required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @PathVariable String from,

            @ApiParam(name = "to",
                    type = "Date",
                    example = "2021-07-04",
                    required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @PathVariable String to
    ) {

        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            var fromDate = df.parse(from);
            var toDate = df.parse(to);
            return ResponseEntity.ok(fascade.getAverageGoldPriceAndAddToDb(fromDate, toDate));
        } catch (ParseException ex) {
            return MyDumbHttpErrorHandlerBecauseImLazyAndIWasNotInLectures.HandleParseException(ex);
        } catch (Exception ex) { // too bad
            return MyDumbHttpErrorHandlerBecauseImLazyAndIWasNotInLectures.HandleNBPExceptions(ex);
        }
    }


}
