package requestTo;

import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class CurrencyRate {

    @Id
    @Column(name = "exchangeDate", columnDefinition = "DATE")
    @SerializedName("exchangedate")
    private LocalDate exchangeDate;

    @SerializedName("rate")
    private Double rate;

    public CurrencyRate() {
    }

    public LocalDate getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(LocalDate exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public Double getRate() {
        return rate;
    }
    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "{Date: " + exchangeDate +
                ", Rate: " + rate +
                '}';
    }
}
