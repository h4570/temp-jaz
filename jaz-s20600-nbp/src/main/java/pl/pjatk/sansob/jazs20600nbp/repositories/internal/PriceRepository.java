package pl.pjatk.sansob.jazs20600nbp.repositories.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pjatk.sansob.jazs20600nbp.models.Price;

import java.util.List;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Override
    List<Price> findAll();

    @Override
    Optional<Price> findById(Long id);

    @Override
    <S extends Price> S save(S s);

    @Override
    void delete(Price movie);

}
