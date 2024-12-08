package mate.academy.rickandmorty.client;

import java.util.List;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;

public interface RickAndMortyApiClient {
    List<ExternalCharacterDto> fetchAllCharacters();
}
