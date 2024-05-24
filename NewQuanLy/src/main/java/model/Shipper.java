/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author V
 */
public class Shipper {
    private int id;
    private String name;
    private Date dob;
    private String gender;
    private int phoneNumber;
    private String distinct;
    private String province;
    private String ward;
    private String licensePlate;
    private String status;
    private double rating;
    private boolean isDeleted;
    private int warningCount;
    private String updated;
    private LocalDateTime created;
    private LocalDateTime lastAssignedTime;
    
    public Shipper()
    {
        
    }

    public Shipper(int id, String name, Date date, String gender, int phoneNumber, String distinct, String province, String ward, String licensePlate, String status, double rating, boolean isDeleted, int warningCount, String updated, LocalDateTime created, LocalDateTime lastAssignedTime) {
        this.id = id;
        this.name = name;
        this.dob = date;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.distinct = distinct;
        this.province = province;
        this.ward = ward;
        this.licensePlate = licensePlate;
        this.status = status;
        this.rating = rating;
        this.isDeleted = isDeleted;
        this.warningCount = warningCount;
        this.updated = updated;
        this.created = created;
        this.lastAssignedTime = lastAssignedTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date date) {
        this.dob = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDistinct() {
        return distinct;
    }

    public void setDistinct(String distinct) {
        this.distinct = distinct;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getWarningCount() {
        return warningCount;
    }

    public void setWarningCount(int warningCount) {
        this.warningCount = warningCount;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastAssignedTime() {
        return lastAssignedTime;
    }

    public void setLastAssignedTime(LocalDateTime lastAssignedTime) {
        this.lastAssignedTime = lastAssignedTime;
    }

    
    
    @Override
    public String toString() {
       return this.id+" "+this.name+" "+this.dob+" "+this.gender+" "+this.phoneNumber+" "+this.distinct+" "+this.province+" "+this.ward+" "+this.licensePlate+" "+this.status+" "+this.rating+" "+this.isDeleted+" "+this.warningCount+" "+this.updated+" "+this.created+" "+this.lastAssignedTime;
    }   
}
