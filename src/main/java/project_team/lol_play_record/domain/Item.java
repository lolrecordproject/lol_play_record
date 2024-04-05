package project_team.lol_play_record.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "item") //디비에 저장할 컬렉션 이름 지정하기
public class Item {

//    private Long id;
//    private String name;
    private String accountId;
    private int profileIconId;
    private long revisionDate;
    private String name;
    private String id;
    private String puuid;
    private long summonerLevel;
}
