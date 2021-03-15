package foo.study.url.repository;

import java.util.List;

public interface UrlRepository{
    String save(String url);
    List<String> findAll();

}
