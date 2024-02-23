package tqs.lab2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Optional;

import org.junit.jupiter.api.*;

public class AddressResolverIT
{
    ISimpleHttpClient httpClient;

    AddressResolverService addrRes;

    @BeforeEach
    public void setup(){
        httpClient = new TqsBasicHttpClient();
        addrRes = new AddressResolverService(httpClient);
    }

    @Test
    public void findAddr() throws IOException, URISyntaxException, ParseException, org.json.simple.parser.ParseException{

        // geo coords
        final double x = 30.333472;
        final double y = -81.470448;

        final double x_bad = 1e10;
        final double y_bad = 1e10;

        // Test successful case
        Optional<Address> addr = addrRes.findAddressForLocation(x, y);

        assertTrue(addr.isPresent());
        assertEquals(addr.get(), new Address("802 Arkenstone Dr", "Jacksonville", "32225", ""));

        // Test bad coordinates case

        addr = addrRes.findAddressForLocation(x_bad, y_bad);

        assertTrue(addr.isEmpty());
    } 
}
