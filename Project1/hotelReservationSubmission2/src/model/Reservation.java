package model;

import java.util.Date;
import java.util.Objects;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;
    private int hashVariable;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.hashVariable = Objects.hash(customer, room, checkInDate, checkOutDate);
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.hashVariable = Objects.hash(customer, room, checkInDate, checkOutDate);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setRoom(IRoom room) {
        this.room = room;
        this.hashVariable = Objects.hash(customer, room, checkInDate, checkOutDate);
    }

    public IRoom getRoom() {
        return room;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
        this.hashVariable = Objects.hash(customer, room, checkInDate, checkOutDate);
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
        this.hashVariable = Objects.hash(customer, room, checkInDate, checkOutDate);
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Reservation)) return false;
        if (obj == this)  return true;
        return (this.hashCode() == ((Reservation) obj).hashCode());
    }

    @Override
    public int hashCode() {
        return this.hashVariable;
    }

    @Override
    public String toString() {
        return "\r\n**** Reservation ****" +
               "\r\n" + customer +
               "\r\n" + room +
               "\r\n\r\ncheckInDate: " + checkInDate +
               "\r\ncheckOutDate: " + checkOutDate +
               "\r\n*********************";
    }
}
