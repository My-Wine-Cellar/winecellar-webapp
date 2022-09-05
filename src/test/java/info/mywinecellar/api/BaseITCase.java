package info.mywinecellar.api;

import info.mywinecellar.json.MyWineCellar;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class BaseITCase {

    private final RestTemplate client = new RestTemplate();
    protected final ObjectMapper objectMapper = new ObjectMapper();
    private MyWineCellar myWineCellar;

    protected MyWineCellar apiRequest(String path, String body, HttpMethod httpMethod) {
        return setupResponseObject(responseEntity(path, body, httpMethod));
    }

    protected MyWineCellar apiImageRequest(String path) {
        return setupResponseObject(setupImage(path));
    }

    protected String jsonBody() {
        JSONObject json = new JSONObject();
        try {
            json.put("description", "edited description");
            json.put("weblink", "edited weblink");
        } catch (JSONException ignore) {
        }
        return json.toString();
    }

    private MyWineCellar setupResponseObject(ResponseEntity<String> json) {
        try {
            myWineCellar = objectMapper.readValue(json.getBody(), MyWineCellar.class);
        } catch (JsonProcessingException ignored) {
        }
        return myWineCellar;
    }

    private ResponseEntity<String> responseEntity(String path, String body, HttpMethod httpMethod) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
        return client.exchange("http://localhost:8080/api/v1/" + path,
                httpMethod, httpEntity, String.class);
    }

    private ResponseEntity<String> setupImage(String path) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        var content = new FileSystemResource("src/test/resources/opus_one.jpg");

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", content);

        HttpEntity<MultiValueMap<String, Object>> httpEntity =
                new HttpEntity<>(body, headers);
        return client.exchange("http://localhost:8080/api/v1" + path, HttpMethod.PUT, httpEntity, String.class);
    }

}
