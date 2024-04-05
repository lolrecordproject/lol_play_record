package project_team.lol_play_record.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project_team.lol_play_record.dto.MatchDto;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Match {
    //매치id로 게임 정보 가져오기
    public MetaDto metaDto;
    public InfoDto info;

    @AllArgsConstructor
    @Getter
    @Setter
    @NoArgsConstructor
    public static class MetaDto{
        public String dataVersion;
        public String matchId;
        public List participants;

    }

    @AllArgsConstructor
    @Getter
    @Setter
    @NoArgsConstructor
    public static class InfoDto{
        public long gameId;
        public String gameMode;
        public String gameName;
        public List<Participant> participants;
        public List<TeamDto> teams;

    }

    @AllArgsConstructor
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Participant{
        public int teamId;
        public int championId;
        public String championName;
        public int kills;
        public int assists;
        public int deaths;
        public int item0;
        public int item1;
        public int item2;
        public int item3;
        public int item4;
        public int item5;
        public int item6;
        public String individualPosition;
        public String puuid;
        public String summonerName;
        public String riotIdName;
        public String teamPosition;
        public boolean win;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    @NoArgsConstructor
    public static class TeamDto{
        public List bans;
        public int teamId;
        public boolean win;
    }
}
