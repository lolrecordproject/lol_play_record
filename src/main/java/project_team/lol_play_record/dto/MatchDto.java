package project_team.lol_play_record.dto;

import lombok.Data;

import java.util.List;

@Data
public class MatchDto {
    //매치id로 게임 정보 가져오기
    public MetaDto metaDto;
    public InfoDto info;

    @Data
    public static class MetaDto{
        public String dataVersion;
        public String matchId;
        public List participants;

    }

    @Data
    public static class InfoDto{
        public long gameId;
        public String gameMode;
        public String gameName;
        public List<ParticipantDto> participants;
        public List teams;

    }


    @Data
    public static class ParticipantDto{
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

    @Data
    public static class TeamDto{
        public List bans;
        public int teamId;
        public boolean win;
    }

}
