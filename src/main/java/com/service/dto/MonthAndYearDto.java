package com.service.dto;

public class MonthAndYearDto {
    private String month;
    private String year;
    private int number;

    public MonthAndYearDto(String month, String year, int number) {
        this.month = month;
        this.year = year;
        this.number = number;
    }

    public MonthAndYearDto() {
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
