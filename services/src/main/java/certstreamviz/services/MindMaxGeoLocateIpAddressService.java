package certstreamviz.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Optional;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import certstreamviz.models.Coordinate;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;

/***
 * This product includes GeoLite2 data created by MaxMind, available from
 * <a href="http://www.maxmind.com">http://www.maxmind.com</a>.
 */
@Service
public class MindMaxGeoLocateIpAddressService implements IGeolocateIpAddressService {

    private static final Logger logger = LoggerFactory.getLogger(MindMaxGeoLocateIpAddressService.class);

    private Counter successCounter = Metrics.counter("geolocate.success");
    private Counter failureCounter = Metrics.counter("geolocate.failure");

    DatabaseReader cityDbReader;

    MindMaxGeoLocateIpAddressService() throws IOException {

        // A InputStream pointing to GooIP2 or GeoLite2 database
        InputStream cityDb = MindMaxGeoLocateIpAddressService.class
                .getResourceAsStream("/GeoList2-city/GeoLite2-City.mmdb");

        // This creates the DatabaseReader object. Reuse
        // the object across lookups. The object is thread-safe.
        // Using this cache, lookup performance is significantly improved at the cost of
        // a small (~2MB) memory overhead.

        cityDbReader = new DatabaseReader.Builder(cityDb).build();
    }

    // TODO: Possibly hand wildcards etc domain names
    public Optional<Coordinate> geolocateIpAddress(String host) {
        try {
            InetAddress ipAddress = InetAddress.getByName(host);
            // Do the lookup
            CityResponse response = cityDbReader.city(ipAddress);
            Location location = response.getLocation();
            successCounter.increment();
            return Optional.of(new Coordinate(location.getLatitude(), location.getLongitude()));
        } catch (Exception ex) {
            logger.debug("Could not lookup host to get coordinate", ex);
        }

        failureCounter.increment();
        return Optional.empty();
    }

}