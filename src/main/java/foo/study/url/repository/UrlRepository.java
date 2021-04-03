package foo.study.url.repository;

import foo.study.url.domain.Entity.Url;
import foo.study.url.domain.ShortenUrl;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface UrlRepository extends JpaRepository<Url, Long>{
    @Query("select su.url from ShortUrl su join su.url where su.shortenUrl=:shortenUrl")
    Optional<Url> findOriginalUrlByShortenUrl(@Param("shortenUrl") String shortenUrl);

    Optional<Url> findByOriginalUrl(String originalUrl);
}
