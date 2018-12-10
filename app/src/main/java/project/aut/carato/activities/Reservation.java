package project.aut.carato.activities;

public class Reservation {
    int reservation_id;
    int c_id;
    int u_id;
    String from;
    String to;

    public Reservation(int reservation_id, int c_id, int u_id, String from, String to) {
        this.reservation_id = reservation_id;
        this.c_id = c_id;
        this.u_id = u_id;
        this.from = from;
        this.to = to;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
