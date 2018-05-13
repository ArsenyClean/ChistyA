package music_application.model;

public class Address {

    private   String country;
    private   String city;
    private   String street;
    private   String house;
    private   String flat;

    public Address(  String country,   String city,   String street,
                     String house,   String flat) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getFlat() {
        return flat;
    }

    @Override
    public String toString() {
        return String.format("country: %s, city: %s, street: %s, house: %s, flat: %s",
                this.country, this.city, this.street, this.house, this.flat);
    }
}

