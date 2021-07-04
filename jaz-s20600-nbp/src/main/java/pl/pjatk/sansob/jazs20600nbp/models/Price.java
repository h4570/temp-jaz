package pl.pjatk.sansob.jazs20600nbp.models;

import io.swagger.annotations.ApiModelProperty;
import pl.pjatk.sansob.jazs20600nbp.enums.NBPType;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Entity()
@Table(name = "prices")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private NBPType type;
    private Date fromDate;
    private Date toDate;
    private float price;
    private Date addedAtDate;
    private LocalTime addedAtTime;

    public Price() {
    }

    public Price(Long id, NBPType type, Date fromDate, Date toDate, float price, Date addedAtDate, LocalTime addedAtTime) {
        this.id = id;
        this.type = type;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.price = price;
        this.addedAtDate = addedAtDate;
        this.addedAtTime = addedAtTime;
    }

    // ---

    @ApiModelProperty(notes = "Id of the NBP price")
    public Long getId() {
        return id;
    }

    @ApiModelProperty(notes = "NBP price type")
    public NBPType getType() {
        return type;
    }

    @ApiModelProperty(notes = "NBP price \"from\" date")
    public Date getFromDate() {
        return fromDate;
    }

    @ApiModelProperty(notes = "NBP price \"to\" date")
    public Date getToDate() {
        return toDate;
    }

    @ApiModelProperty(notes = "NBP average price")
    public float getPrice() {
        return price;
    }

    @ApiModelProperty(notes = "Date of entry")
    public Date getAddedAtDate() {
        return addedAtDate;
    }

    @ApiModelProperty(notes = "Time of entry")
    public LocalTime getAddedAtTime() {
        return addedAtTime;
    }

    // ---

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(NBPType type) {
        this.type = type;
    }

    public void setFromDate(Date from) {
        this.fromDate = from;
    }

    public void setToDate(Date to) {
        this.toDate = to;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAddedAtDate(Date addedAtDate) {
        this.addedAtDate = addedAtDate;
    }

    public void setAddedAtTime(LocalTime addedAtTime) {
        this.addedAtTime = addedAtTime;
    }
}
