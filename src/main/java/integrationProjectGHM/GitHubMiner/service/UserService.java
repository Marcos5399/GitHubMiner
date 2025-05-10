package integrationProjectGHM.GitHubMiner.service;

import integrationProjectGHM.GitHubMiner.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static integrationProjectGHM.GitHubMiner.util.RestTemplateHelper.getResponseEntity;
import static integrationProjectGHM.GitHubMiner.util.RestTemplateHelper.getResponseEntityById;

@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;
    // Recomendable la creación de un constructor
    @Autowired
    public UserService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Value("${github.baseuri}")
    private String baseUri;

    // Añadimos la posibilidad de poder usar tokens para aumentar el límite de peticiones
    @Value("${github.token}")
    private String token;


    // A partir de aquí comenzamos a definir métodos para cada operación que queramos hacer con la API de GitHub para los Users
    //   https://api.github.com/users
    // Esto lista a todos los usuarios de GitHub, solo nos interesa para comprobar que nuestra clase Pojo User funciona
    public ResponseEntity<User[]> getAllUsers (){
        String uri ="https://api.github.com/users";
        return getResponseEntity(restTemplate, uri, token, User[].class);
    }
    // https://api.github.com/users/{username}
    public ResponseEntity<User> getUserByName (String username){
        String uri = "Https://api.github.com/users/" + username;
        return getResponseEntityById(restTemplate, uri, token, User.class);
    }

}
