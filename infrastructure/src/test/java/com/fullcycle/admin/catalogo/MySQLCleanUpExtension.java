package com.fullcycle.admin.catalogo;

import com.fullcycle.admin.catalogo.castmember.persistence.CastMemberRepository;
import com.fullcycle.admin.catalogo.category.persistence.CategoryRepository;
import com.fullcycle.admin.catalogo.genre.persistence.GenreRepository;
import com.fullcycle.admin.catalogo.video.persistence.VideoRepository;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.List;

public class MySQLCleanUpExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(final ExtensionContext context) {
        final var appContext = SpringExtension.getApplicationContext(context);

        cleanUp(List.of(
                appContext.getBean(VideoRepository.class),
                appContext.getBean(CastMemberRepository.class),
                appContext.getBean(GenreRepository.class),
                appContext.getBean(CategoryRepository.class)
        ));
    }

    private void cleanUp(final Collection<CrudRepository> repositories) {
        repositories.forEach(CrudRepository::deleteAll);
    }
}