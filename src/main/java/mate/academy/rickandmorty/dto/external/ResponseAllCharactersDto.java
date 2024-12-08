package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record ResponseAllCharactersDto(List<ExternalCharacterDto> results) {
}
