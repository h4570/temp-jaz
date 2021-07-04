package pl.pjatk.sansob.jazs20600nbp.dtos;
import java.util.Date;

public class NBPResponseDto {
    private Date data;
    private float cena;

    public Date getData() {
        return data;
    }

    public float getCena() {
        return cena;
    }

    // ---

    public void setData(Date data) {
        this.data = data;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

}
