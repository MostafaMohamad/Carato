package project.aut.carato;

import project.aut.carato.activities.Reservation;

public class ReservedCar {
    Car car;
    Reservation reservation;

    public ReservedCar(Car car, Reservation reservation) {
        this.car = car;
        this.reservation = reservation;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
