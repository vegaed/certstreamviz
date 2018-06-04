package certstreamviz.services;

import java.util.Optional;

import certstreamviz.models.Coordinate;

interface IGeolocateIpAddressService {

    /***
     * Get the {@link Coordinate} of a host
     */
    public Optional<Coordinate> geolocateIpAddress(String host);

}