package tqs.lab2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Optional;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TqsClientTest 
{
    @Mock 
    ISimpleHttpClient httpClient;

    @InjectMocks // SuT
    AddressResolverService addrRes;

    @Test
    public void findAddr() throws IOException, URISyntaxException, ParseException, org.json.simple.parser.ParseException{

        // geo coords
        final double x = 30.333472;
        final double y = -81.470448;

        final double x_bad = 1e10;
        final double y_bad = 1e10;

        // Setup Mock

        // Well-structured case
        when(httpClient.doHttpGet(AddressResolverService.prepareUriForRemoteEndpoint(x, y)))
        .thenReturn("{\"info\":{\"statuscode\":0,\"copyright\":{\"text\":\"© 2024 MapQuest, Inc.\",\"imageUrl\":\"http://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"© 2024 MapQuest, Inc.\"},\"messages\":[]},\"options\":{\"maxResults\":1,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{\"latLng\":{\"lat\":30.333472,\"lng\":-81.470448}},\"locations\":[{\"street\":\"802 Arkenstone Dr\",\"adminArea6\":\"East Arlington\",\"adminArea6Type\":\"Neighborhood\",\"adminArea5\":\"Jacksonville\",\"adminArea5Type\":\"City\",\"adminArea4\":\"Duval\",\"adminArea4Type\":\"County\",\"adminArea3\":\"FL\",\"adminArea3Type\":\"State\",\"adminArea1\":\"US\",\"adminArea1Type\":\"Country\",\"postalCode\":\"32225\",\"geocodeQualityCode\":\"L1AAA\",\"geocodeQuality\":\"ADDRESS\",\"dragPoint\":false,\"sideOfStreet\":\"L\",\"linkId\":\"0\",\"unknownInput\":\"\",\"type\":\"s\",\"latLng\":{\"lat\":30.33353,\"lng\":-81.47004},\"displayLatLng\":{\"lat\":30.33356,\"lng\":-81.47019},\"mapUrl\":\"\"}]}]}");

        // Bad coordinates
        when(httpClient.doHttpGet(AddressResolverService.prepareUriForRemoteEndpoint(x_bad, y_bad)))
        .thenReturn("{\"info\":{\"statuscode\":400,\"copyright\":{\"text\":\"© 2024 MapQuest, Inc.\",\"imageUrl\":\"http://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"© 2024 MapQuest, Inc.\"},\"messages\":[\"Illegal argument from request: Invalid LatLng specified.\"]},\"options\":{\"maxResults\":1,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{},\"locations\":[]}]}");

        // Test successful case
        Optional<Address> addr = addrRes.findAddressForLocation(x, y);

        assertTrue(addr.isPresent());
        assertEquals(addr.get(), new Address("802 Arkenstone Dr", "Jacksonville", "32225", ""));

        verify(httpClient, atLeastOnce()).doHttpGet(AddressResolverService.prepareUriForRemoteEndpoint(x, y));

        // Test bad coordinates case

        addr = addrRes.findAddressForLocation(x_bad, y_bad);

        assertTrue(addr.isEmpty());

        verify(httpClient, atLeastOnce()).doHttpGet(AddressResolverService.prepareUriForRemoteEndpoint(x_bad, y_bad));
    } 
}
