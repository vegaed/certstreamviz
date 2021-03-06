package certstreamviz.models;

import java.util.Objects;

/***
 * Immutable object representing latitude and longitude
 */
public class Coordinate {

    private double latitude, longitude;

    private static final double LATITUDE_MAX = 90;
    private static final double LATITUDE_MIN = -LATITUDE_MAX;
    private static final double LONGITUDE_MAX = 180;
    private static final double LONGITUDE_MIN = -LONGITUDE_MAX;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Coordinate() {
    }

    public Coordinate(double latitude, double longitude) throws IllegalArgumentException {
        this.latitude = validateLatitude(latitude);
        this.longitude = validateLongitude(longitude);
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    private double validateLongitude(double longitude) {
        if (Double.isNaN(longitude) || longitude < LONGITUDE_MIN || longitude > LONGITUDE_MAX) {
            throw new IllegalArgumentException("invalid longitude: " + longitude);
        }
        return longitude;
    }

    private double validateLatitude(double latitude) {
        if (Double.isNaN(latitude) || latitude < LATITUDE_MIN || latitude > LATITUDE_MAX) {
            throw new IllegalArgumentException("invalid latitude: " + latitude);
        }
        return latitude;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this)
            return true;
        if (!(o instanceof Coordinate)) {
            return false;
        }
        Coordinate coordinate = (Coordinate) o;
        return Objects.equals(latitude, coordinate.getLatitude())
                && Objects.equals(longitude, coordinate.getLongitude());
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}