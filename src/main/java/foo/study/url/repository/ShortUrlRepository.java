package foo.study.url.repository;

import foo.study.url.domain.Entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

}
