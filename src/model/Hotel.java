package model;

public class Hotel {
    private int id;
    private String name;
    private String location;
    private String amenities;

    public Hotel(int id, String name, String location, String amenities) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.amenities = amenities;
    }

    public Hotel(String name, String location, String amenities) {
        this.name = name;
        this.location = location;
        this.amenities = amenities;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getAmenities() { return amenities; }
    public void setAmenities(String amenities) { this.amenities = amenities; }
}
