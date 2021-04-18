package foo.study.url.repository;

import foo.study.url.domain.Entity.ShortUrl;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

    Optional<ShortUrl> findShortUrlByShortenUrl(String shortenUrl);

    boolean existsByShortenUrl(String shortedUrl);

}
