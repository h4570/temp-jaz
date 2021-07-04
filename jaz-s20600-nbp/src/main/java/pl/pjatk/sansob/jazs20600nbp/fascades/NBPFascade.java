package pl.pjatk.sansob.jazs20600nbp.fascades;

import pl.pjatk.sansob.jazs20600nbp.dtos.NBPResponseDto;
import pl.pjatk.sansob.jazs20600nbp.enums.NBPType;
import pl.pjatk.sansob.jazs20600nbp.exceptions.NBPHttp400Exception;
import pl.pjatk.sansob.jazs20600nbp.exceptions.NBPHttp404Exception;
import pl.pjatk.sansob.jazs20600nbp.exceptions.NBPHttpUnknownException;
import pl.pjatk.sansob.jazs20600nbp.exceptions.NBPNoDataException;
import pl.pjatk.sansob.jazs20600nbp.models.Price;
import pl.pjatk.sansob.jazs20600nbp.services.external.NBPService;
import pl.pjatk.sansob.jazs20600nbp.services.internal.PriceService;

import java.util.Date;
import java.util.List;

public class NBPFascade {

    public NBPFascade(PriceService priceService, NBPService nbpService) {
        this.priceService = priceService;
        this.nbpService = nbpService;
    }

    /**
     * Database
     */
    private PriceService priceService;
    /**
     * NBP Api
     */
    private NBPService nbpService;

    public Price getAverageGoldPriceAndAddToDb(Date from, Date to) throws NBPNoDataException, NBPHttp400Exception, NBPHttpUnknownException, NBPHttp404Exception {
        var nbpPrices = nbpService.getGoldPrices(from, to);
        if (nbpPrices.isEmpty())
            throw new NBPNoDataException("NBP sent empty body"); // some defensive programming (to prevent divide by 0)
        var avg = getAveragePrice(nbpPrices);
        var dateNow = new Date(System.currentTimeMillis());
        var timeNow = (java.time.LocalTime.now());
        var price = new Price(0L, NBPType.Gold, from, to, avg, dateNow, timeNow);
        var dbPrice = priceService.add(price);
        return dbPrice;
    }

    private float getAveragePrice(List<NBPResponseDto> prices) {
        var res = 0.0F;
        for (var price : prices)
            res += price.getCena();
        return res / prices.size();
    }

}
