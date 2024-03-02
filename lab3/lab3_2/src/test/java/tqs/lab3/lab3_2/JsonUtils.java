package tqs.lab3.lab3_2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/*
 * Sourced from: https://gitlab.com/ico_gl/ua_tqs_gs20/-/blob/master/gs-employee-mngr/src/test/java/tqsdemo/employeemngr/employee/JsonUtils.java
 * Original author: ico@ua.pt
 */

class JsonUtils {
    static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}

