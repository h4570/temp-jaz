package pl.pjatk.sansob.jazs20600nbp.services.external;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.pjatk.sansob.jazs20600nbp.dtos.NBPResponseDto;
import pl.pjatk.sansob.jazs20600nbp.exceptions.NBPHttp400Exception;
import pl.pjatk.sansob.jazs20600nbp.exceptions.NBPHttp404Exception;
import pl.pjatk.sansob.jazs20600nbp.exceptions.NBPHttpUnknownException;
import pl.pjatk.sansob.jazs20600nbp.exceptions.NBPNoDataException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class NBPService {

    private final RestTemplate restTemplate;

    public NBPService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Get gold prices from NBP
     *
     * @param from Date from
     * @param to   Date to
     * @return List of prices
     */
    public List<NBPResponseDto> getGoldPrices(Date from, Date to) throws NBPHttp404Exception, NBPHttp400Exception, NBPHttpUnknownException {
        var pattern = "yyyy-MM-dd";
        var formatter = new SimpleDateFormat(pattern);
        var fromFormatted = formatter.format(from);
        var toFormatted = formatter.format(to);
        String url = "http://api.nbp.pl/api/cenyzlota/" + fromFormatted + "/" + toFormatted + "?format=json";
        try {
            var body = restTemplate.getForEntity(url, NBPResponseDto[].class);
            return Arrays.asList(body.getBody());
        } catch (final HttpClientErrorException e) {
            var statusCode = e.getStatusCode();
            if (statusCode.value() == 404)
                throw new NBPHttp404Exception("No data for given dates");
           else if (statusCode.value() == 400)
                throw new NBPHttp400Exception("Bad parameters - internal app err");
           else
                throw new NBPHttpUnknownException("Unknown NBP error");
        }
    }

}
