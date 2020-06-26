package com.example.covidtracker;

public class Count {

    private String total_cases, total_recovered, total_unresolved, total_deaths,
            total_new_cases_today, total_new_deaths_today, total_active_cases, total_serious_cases;

    public Count() {
    }

    public Count(String total_cases, String total_recovered, String total_unresolved, String total_deaths, String total_new_cases_today, String total_new_deaths_today, String total_active_cases, String total_serious_cases) {
        this.total_cases = total_cases;
        this.total_recovered = total_recovered;
        this.total_unresolved = total_unresolved;
        this.total_deaths = total_deaths;
        this.total_new_cases_today = total_new_cases_today;
        this.total_new_deaths_today = total_new_deaths_today;
        this.total_active_cases = total_active_cases;
        this.total_serious_cases = total_serious_cases;
    }

    public String getTotal_cases() {
        return total_cases;
    }

    public void setTotal_cases(String total_cases) {
        this.total_cases = total_cases;
    }

    public String getTotal_recovered() {
        return total_recovered;
    }

    public void setTotal_recovered(String total_recovered) {
        this.total_recovered = total_recovered;
    }

    public String getTotal_unresolved() {
        return total_unresolved;
    }

    public void setTotal_unresolved(String total_unresolved) {
        this.total_unresolved = total_unresolved;
    }

    public String getTotal_deaths() {
        return total_deaths;
    }

    public void setTotal_deaths(String total_deaths) {
        this.total_deaths = total_deaths;
    }

    public String getTotal_new_cases_today() {
        return total_new_cases_today;
    }

    public void setTotal_new_cases_today(String total_new_cases_today) {
        this.total_new_cases_today = total_new_cases_today;
    }

    public String getTotal_new_deaths_today() {
        return total_new_deaths_today;
    }

    public void setTotal_new_deaths_today(String total_new_deaths_today) {
        this.total_new_deaths_today = total_new_deaths_today;
    }

    public String getTotal_active_cases() {
        return total_active_cases;
    }

    public void setTotal_active_cases(String total_active_cases) {
        this.total_active_cases = total_active_cases;
    }

    public String getTotal_serious_cases() {
        return total_serious_cases;
    }

    public void setTotal_serious_cases(String total_serious_cases) {
        this.total_serious_cases = total_serious_cases;
    }
}
