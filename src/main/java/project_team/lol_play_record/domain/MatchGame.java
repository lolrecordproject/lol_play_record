package project_team.lol_play_record.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchGame {

    private int teamId;
    private int spell1Id;
    private int spell2Id;
    private int championId;
    private int profileIconId;
    private String summonerName;
    private String roitId;

}
