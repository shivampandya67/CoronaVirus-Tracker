package com.webapplication.coronavirustracker.models;

public class LocationStats {
    
    private String state;
    private String country;
    private int latestTotalCases;
    private int diffFromPreviousDay;

    /**
     * @return String return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return String return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return int return the latestTotalCases
     */
    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    /**
     * @param latestTotalCases the latestTotalCases to set
     */
    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    /**
     * @return int return the diffFromPreviousDay
     */
    public int getDiffFromPreviousDay() {
        return diffFromPreviousDay;
    }

    /**
     * @param diffFromPreviousDay the diffFromPreviousDay to set
     */
    public void setDiffFromPreviousDay(int diffFromPreviousDay) {
        this.diffFromPreviousDay = diffFromPreviousDay;
    }

    @Override
    public String toString() {
        return "LocationStats [country=" + country + ", state=" + state  +  ", latestTotalCases=" + latestTotalCases
                + "]";
    }


    

}
