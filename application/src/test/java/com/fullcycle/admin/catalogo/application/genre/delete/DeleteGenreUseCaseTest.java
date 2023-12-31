package com.fullcycle.admin.catalogo.application.genre.delete;

import com.fullcycle.admin.catalogo.application.UseCaseTest;
import com.fullcycle.admin.catalogo.application.genre.delete.DefaultDeleteGenreUseCase;
import com.fullcycle.admin.catalogo.domain.genre.Genre;
import com.fullcycle.admin.catalogo.domain.genre.GenreGateway;
import com.fullcycle.admin.catalogo.domain.genre.GenreID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.*;

public class DeleteGenreUseCaseTest extends UseCaseTest {


    @InjectMocks
    private DefaultDeleteGenreUseCase useCase;

    @Mock
    private GenreGateway genreGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(genreGateway);
    }

    @Test
    public void givenAValidGenreId_whenCallsDelete_shouldDeleteGenre() {
        //given
        final var aGenre = Genre.newGenre("Ação", true);
        final var expectedId = aGenre.getId();

        doNothing()
                .when(genreGateway).deleteById(any());

        //when
        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));
        verify(genreGateway, times(1)).deleteById(expectedId);
    }

    @Test
    public void givenAnInvalidGenreId_whenCallsDelete_shouldBeOk() {
        //given
        final var expectedId = GenreID.from("123");

        doNothing()
                .when(genreGateway).deleteById(any());

        //when
        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));
        verify(genreGateway, times(1)).deleteById(expectedId);
    }

    @Test
    public void givenAValidGenreId_whenCallsDeleteAndGatewayThrowsUnexpectedError_shouldReceiveException() {
        //given
        final var aGenre = Genre.newGenre("Ação", true);
        final var expectedId = GenreID.from("123");

        doThrow(new IllegalStateException("Gateway error"))
                .when(genreGateway).deleteById(any());

        //when
        Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(expectedId.getValue()));
        verify(genreGateway, times(1)).deleteById(expectedId);
    }

}
