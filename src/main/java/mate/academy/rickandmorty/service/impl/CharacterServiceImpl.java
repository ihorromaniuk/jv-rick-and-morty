package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository repository;
    private final CharacterMapper characterMapper;
    private final Random random = new Random();

    @Override
    public Character save(Character character) {
        return repository.save(character);
    }

    @Override
    public InternalCharacterDto getRandomCharacter() {
        long index = random.nextLong(repository.count()) + 1;
        return characterMapper.toDto(repository.findById(index).orElseThrow());
    }

    @Override
    public List<InternalCharacterDto> getAllCharactersWithNameLike(String namePart,
                                                                   Pageable pageable) {
        return repository.getAllByNameLikeIgnoreCase("%" + namePart + "%", pageable)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
