package certstreamviz.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CoordinateTests {

    @Test(expected = IllegalArgumentException.class)
    public void validateLatitudeHigherThanMax() {
        new Coordinate(91, 0);
    }

    @Test
    public void validateLatitude() {
        Coordinate coordinate = new Coordinate(45.0, 0);
        assertEquals("Validate Latitude", 45.0, coordinate.getLatitude(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateLatitudeLowerThanMin() {
        new Coordinate(-91, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateLongitudeHigherThanMax() {
        new Coordinate(0, 181);
    }

    @Test
    public void validateLongitude() {
        Coordinate coordinate = new Coordinate(0, 91);
        assertEquals("Validate Longitude", 91, coordinate.getLongitude(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateLongitudeLowerThanMax() {
        new Coordinate(0, -181);
    }
}