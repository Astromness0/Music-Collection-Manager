package model;

import java.util.UUID;

public class Artist {
    private String id;
    private String name;
    private String country;

    public Artist(String name, String country) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.name = name;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return name + " (" + country + ")";
    }
}