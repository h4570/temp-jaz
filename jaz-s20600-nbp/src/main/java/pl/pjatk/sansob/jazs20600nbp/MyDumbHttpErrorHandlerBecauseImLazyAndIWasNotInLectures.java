package pl.pjatk.sansob.jazs20600nbp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.pjatk.sansob.jazs20600nbp.exceptions.NBPHttp400Exception;
import pl.pjatk.sansob.jazs20600nbp.exceptions.NBPHttp404Exception;
import pl.pjatk.sansob.jazs20600nbp.exceptions.NBPHttpUnknownException;
import pl.pjatk.sansob.jazs20600nbp.exceptions.NBPNoDataException;

import java.text.ParseException;

/** pls dont kill me */
public class MyDumbHttpErrorHandlerBecauseImLazyAndIWasNotInLectures {

    public static ResponseEntity HandleNBPExceptions(Exception e) {
        if (e instanceof NBPHttp400Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NBP returned bad request - internal app error");
        } else if (e instanceof NBPHttp404Exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NBP returned no data");
        } else if (e instanceof NBPHttpUnknownException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown NBP error");
        } else if (e instanceof NBPNoDataException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("NBP was ok, but returned no data");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown error");
    }

    public static ResponseEntity HandleParseException(ParseException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provided dates are wrong");
    }

}
