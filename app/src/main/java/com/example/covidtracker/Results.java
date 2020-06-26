package com.example.covidtracker;

import java.util.List;

public class Results {

    private List<Count> results;
    private List<Count> countrydata;

    public Results(List<Count> results, List<Count> countrydata) {
        this.results = results;
        this.countrydata = countrydata;
    }

    public List<Count> getResults() {
        return results;
    }

    public void setResults(List<Count> results) {
        this.results = results;
    }

    public List<Count> getCountrydata() {
        return countrydata;
    }

    public void setCountrydata(List<Count> countrydata) {
        this.countrydata = countrydata;
    }
}
