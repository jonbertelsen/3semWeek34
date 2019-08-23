/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author jobe
 */
public class CarDTO {
        private String make;
    private double price;
    private int year;

    public CarDTO(String make, double price, int year) {
        this.make = make;
        this.price = price;
        this.year = year;
    }
    public CarDTO(){} //Required for the automatic demo
    public void setMake(String make) {
        this.make = make;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getMake() {
        return make;
    }
    public double getPrice() {
        return price;
    }
    public int getYear() {
        return year;
    }
    @Override
    public String toString() {
        return "CarDTO{" + "make=" + make + ", price=" + price + ", year=" + year + '}';
    }   

    
}
