package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Get random \"Rick and Morty\" character")
    @GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
    public InternalCharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Get \"Rick and Morty\" characters list by name like with pagination")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InternalCharacterDto> getCharactersWithNameLike(
            @RequestParam("namePart") String namePart,
            @ParameterObject() Pageable pageable) {
        return characterService.getAllCharactersWithNameLike(namePart, pageable);
    }
}
