package integrationProjectGHM.GitHubMiner.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateHelper {
    // Definimos una función que utilizaremos mucho en los servicios para obtener el ResponseEntity de las funciones del tipo "getAll"
    public static <E> ResponseEntity<E[]> getResponseEntity(RestTemplate restTemplate, String uri, String token, Class<E[]> clase) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<E[]> request = new HttpEntity<>(null, headers); // "null" es el cuerpo de la solicitud, que no se necesita en este caso
        return restTemplate.exchange(uri, HttpMethod.GET, request, clase);
    }

    // Definimos una función que utilizaremos mucho en los servicios para obtener el ResponseEntity de las funciones del tipo "getById"
    public static <E> ResponseEntity<E> getResponseEntityById(RestTemplate restTemplate, String uri, String token, Class<E> clase) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<E> request = new HttpEntity<>(null, headers); // "null" es el cuerpo de la solicitud, que no se necesita en este caso
        return restTemplate.exchange(uri, HttpMethod.GET, request, clase);
    }

}
