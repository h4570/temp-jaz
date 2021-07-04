package pl.pjatk.sansob.jazs20600nbp.services.internal;

import org.springframework.stereotype.Service;
import pl.pjatk.sansob.jazs20600nbp.exceptions.PriceNotFoundException;
import pl.pjatk.sansob.jazs20600nbp.models.Price;
import pl.pjatk.sansob.jazs20600nbp.repositories.internal.PriceRepository;

import java.util.List;

@Service
public class PriceService {

    private final PriceRepository repo;

    public PriceService(PriceRepository repo) {
        this.repo = repo;
    }

    public List<Price> getAll() {
        return this.repo.findAll();
    }

    public Price getById(Long id) throws PriceNotFoundException {
        var opt = this.repo.findById(id);
        if (!opt.isPresent())
            throw new PriceNotFoundException(String.format("Price with id=%d was not found!", id));
        return opt.get();
    }

    public Price add(Price payload) {
        payload.setId(0L);
        return this.repo.save(payload);
    }

    public Price update(Price payload) throws PriceNotFoundException {
        var db = getById(payload.getId());
        db.setType(payload.getType());
        db.setFromDate(payload.getFromDate());
        db.setPrice(payload.getPrice());
        db.setToDate(payload.getToDate());
        this.repo.save(db);
        return db;
    }

    public void delete(Long id) throws PriceNotFoundException {
        var db = getById(id);
        this.repo.deleteById(id);
    }

}
