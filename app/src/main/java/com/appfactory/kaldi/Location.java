package com.appfactory.kaldi;

public class Location
{
    public String street, city, state, zip;
    public double longitude, latitude;
    public Location(String street, String city, String state, String zip)
    {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
}