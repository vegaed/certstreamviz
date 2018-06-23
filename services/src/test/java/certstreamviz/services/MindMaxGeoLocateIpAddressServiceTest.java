package certstreamviz.services;

import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;

import certstreamviz.models.Coordinate;

public class MindMaxGeoLocateIpAddressServiceTest {

    private MindMaxGeoLocateIpAddressService service;

    @Before
    public void setUp() throws IOException {
        service = new MindMaxGeoLocateIpAddressService();
    }

    @Test
    public void testValidHost() {
        Optional<Coordinate> coordinate = service.geolocateIpAddress("1.1.1.1");
        assertThat(coordinate, is(Optional.of(new Coordinate(-37.700000, 145.183300))));
    }

    @Test
    public void testValidHostByName() {
        Optional<Coordinate> coordinate = service.geolocateIpAddress("www.google.com");
        assertThat(coordinate.isPresent(), is(true));
    }

    @Test
    public void testValidHostByNameWithoutSubDomain() {
        Optional<Coordinate> coordinate = service.geolocateIpAddress("google.com");
        assertThat(coordinate.isPresent(), is(true));
    }

    @Test
    public void testUnknownHost() {
        Optional<Coordinate> coordinate = service.geolocateIpAddress("bad host");
        assertThat(coordinate, is(Optional.empty()));

    }

}