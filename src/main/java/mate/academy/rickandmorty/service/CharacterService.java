package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    Character save(Character character);

    InternalCharacterDto getRandomCharacter();

    List<InternalCharacterDto> getAllCharactersWithNameLike(String namePart, Pageable pageable);
}
