package com.project.DigitalAssetLendingSystem.dto;

public class AssetRequest {

    private String name;
    private String type;
    private int totalLicenses;
    private int availableLicenses;
    private String department;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotalLicenses() {
        return totalLicenses;
    }

    public void setTotalLicenses(int totalLicenses) {
        this.totalLicenses = totalLicenses;
    }

    public int getAvailableLicenses() {
        return availableLicenses;
    }

    public void setAvailableLicenses(int availableLicenses) {
        this.availableLicenses = availableLicenses;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}