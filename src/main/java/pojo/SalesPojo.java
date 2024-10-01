package pojo;

import java.util.Date;

public class SalesPojo {
    private int saleId;         // Unique ID for each sale
    private int carId;          // ID of the car being sold
    private int customerId;     // ID of the customer buying the car
    private Date saleDate;      // Date of the sale
    private float saleAmount;   // Total sale amount

    // Default constructor
    public SalesPojo() {
    }

    // Parameterized constructor
    public SalesPojo(int saleId, int carId, int customerId, Date saleDate, float saleAmount) {
        this.saleId = saleId;
        this.carId = carId;
        this.customerId = customerId;
        this.saleDate = saleDate;
        this.saleAmount = saleAmount;
    }

    // Getters and Setters
    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public float getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(float saleAmount) {
        this.saleAmount = saleAmount;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", carId=" + carId +
                ", customerId=" + customerId +
                ", saleDate=" + saleDate +
                ", saleAmount=" + saleAmount +
                '}';
    }
}
