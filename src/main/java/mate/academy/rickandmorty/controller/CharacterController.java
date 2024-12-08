package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    public InternalCharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping
    public List<InternalCharacterDto> getCharactersWithNameLike(
            @RequestParam("partName") String partName, Pageable pageable) {
        return characterService.getAllCharactersWithNameLike(partName, pageable);
    }
}
