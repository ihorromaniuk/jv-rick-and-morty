package mate.academy.rickandmorty.client.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.RickAndMortyApiClient;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.external.ResponseAllCharactersDto;
import mate.academy.rickandmorty.exception.HttpException;
import mate.academy.rickandmorty.exception.MappingException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class RickAndMortyApiClientImpl implements RickAndMortyApiClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api";
    private static final String CHARACTER_URI = "/character";
    private static final String PAGE_REQUEST_PARAM = "?page=";
    private static final int FIRST_PAGE = 1;

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    public List<ExternalCharacterDto> fetchAllCharacters() {
        int page = FIRST_PAGE;
        int status;
        List<ExternalCharacterDto> responseCharacterDtoList = new ArrayList<>();
        boolean isListFinished = false;

        do {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(BASE_URL
                            + CHARACTER_URI
                            + PAGE_REQUEST_PARAM
                            + page))
                    .build();
            HttpResponse<String> response = null;
            try {
                response = httpClient
                        .send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                throw new HttpException("Can't fetch Rick and Morty characters info", e);
            }
            status = response.statusCode();

            if (status == HttpStatus.OK.value() && StringUtils.hasText(response.body())) {
                try {
                    ResponseAllCharactersDto charactersDto = objectMapper
                            .readValue(response.body(), new TypeReference<>() {});
                    responseCharacterDtoList.addAll(charactersDto.results());
                } catch (JsonProcessingException e) {
                    throw new MappingException("Can't map response body to "
                            + "ResponseAllCharactersDto object. Body: " + response.body(), e);
                }
            } else {
                isListFinished = true;
            }
            page++;
        } while (status == HttpStatus.OK.value() && !isListFinished);
        
        return responseCharacterDtoList;
    }
}
