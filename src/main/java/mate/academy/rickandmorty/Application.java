package mate.academy.rickandmorty;

import java.util.List;
import mate.academy.rickandmorty.client.RickAndMortyApiClient;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    @Autowired
    private RickAndMortyApiClient rickAndMortyApiClient;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private CharacterMapper mapper;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            List<ExternalCharacterDto> responseCharacters =
                    rickAndMortyApiClient.fetchAllCharacters();
            responseCharacters.stream()
                    .map(dto -> mapper.toModel(dto))
                    .forEach(character -> characterService.save(character));
        };
    }
}
