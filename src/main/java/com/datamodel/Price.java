package com.datamodel;

import com.service.dto.BestDealDto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getTheBestDeals",
                query = "SELECT p1.price + p2.price as priceSum," +
                        "p1.date as dateFrom," +
                        "p2.date as dateTo," +
                        "p1.departure.name as departure," +
                        "p1.destination.name as destination" +
                        " FROM Price p1 " +
                        "join Price p2  on p1.destination.id = p2.departure.id " +
                        "where p1.departure.id=4  and p1.destination.id = 6 " +
                        "and datediff(p2.date, p1.date) between 4 and 8 " +
                        "order by p1.price + p2.price")
})

    @SqlResultSetMapping(
                        name = "mappingBestDealDto",
                        classes = {
                                @ConstructorResult(
                                        targetClass = BestDealDto.class,
                                        columns = {
                                                @ColumnResult(name = "priceSum",type = Long.class),
                                                @ColumnResult(name = "dateFrom", type = Date.class),
                                                @ColumnResult(name = "dateTo", type = Date.class),
                                                @ColumnResult(name = "departure", type = String.class),
                                                @ColumnResult(name = "destination", type = String.class)

                                        }
                                )
                        }
                )
@Entity
@Table(name = "wizz_prices")
public class Price {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "departureId")
    private WizzAirport departure;

    @ManyToOne
    @JoinColumn(name = "destinationId")
    private WizzAirport destination;

    @Column
    private BigDecimal price;

    @Column
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WizzAirport getDeparture() {
        return departure;
    }

    public void setDeparture(WizzAirport departure) {
        this.departure = departure;
    }

    public WizzAirport getDestination() {
        return destination;
    }

    public void setDestination(WizzAirport destination) {
        this.destination = destination;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
