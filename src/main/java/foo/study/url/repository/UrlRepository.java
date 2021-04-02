package foo.study.url.repository;

import foo.study.url.domain.ShortenUrl;
import foo.study.url.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UrlRepository extends JpaRepository<Url, Long>{
    @Query("select u.originalUrl from url u where ")
    String findOriginalUrlByShortenUrl(String shortenUrl);
}
