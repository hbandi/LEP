package com.hb;

public class Customer {

    private String customerName;
    private int customerId;
    private int pruchaseAmount;
    private String city;
    private int age;

    public int getAge() {
        return age;
    }

    public Customer(String customerName, int customerId, int pruchaseAmount, String city, int age) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.pruchaseAmount = pruchaseAmount;
        this.city = city;
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Customer(String customerName, int customerId, int pruchaseAmount, String city) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.pruchaseAmount = pruchaseAmount;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", customerId=" + customerId +
                ", pruchaseAmount=" + pruchaseAmount +
                ", city='" + city + '\'' +
                '}';
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPruchaseAmount() {
        return pruchaseAmount;
    }

    public void setPruchaseAmount(int pruchaseAmount) {
        this.pruchaseAmount = pruchaseAmount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
