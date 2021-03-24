package foo.study.url.repository;

import foo.study.url.utils.RandomUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UrlRepositoryImpl implements UrlRepository{

    private static final int RANGE_URL = 5; //생성할 URL의 길이
    private final ConcurrentHashMap<String, String> urlLists;
    private String shortenUrl = "";
    private boolean includesUrl = true;

    @Override
    public String save(String url) {
        if(!urlLists.containsKey(url)){ //URL 매핑 되지 않았다면

            makeRandUrlAndValidation();

            urlLists.put(url, shortenUrl);
            shortenUrl = "";
            includesUrl = true;
            return urlLists.get(url);
        }
        return urlLists.get(url); //기존에 존재하는 key에 대한 value 반환
    }

    public List<String> findAll(){
        return new ArrayList<>(urlLists.values());
    }

    private void makeRandUrlAndValidation(){
        while(includesUrl){ // URL이 중복일 경우 랜덤 URL을 계속해서 생성
            shortenUrl = RandomUtils.generateUrl(RANGE_URL); // 랜덤 단축 URL 생성
            if(isDuplication(shortenUrl)){ // 생성한 단축 URL이 중복되었다면 while문 탈출 X
                continue;
            }
            includesUrl = false; //중복된 URL이 없다면 while문 탈출
        }
    }

    private boolean isDuplication(String shortenUrl){
        for(String value : urlLists.values()){ // url 매핑된 전체 문자열을 가져와서
            if(value.equals(shortenUrl)){ // 만약 기존 단축 url 존재한다면
                return true;
            }
        }
        return false;
    }

}
