package com.datamodel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wizz_departures_destinations")
public class WizzDeparturesDestinations {

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


}
