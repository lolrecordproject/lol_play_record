package project_team.lol_play_record.domain;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class MatchGame {
    //puuid로 매칭중인 게임 정보 가져오기
    //게임 정보
    public int gameId;
    public List<Participant> participants;
    public String gameMode;
    public String gameType;
    public int gameQueueConfigId;
    public Map<String, String> observers;
    public String platformId;
    public Map<String, String> bannedChampions;
    public Long gameStartTime;
    public Long gameLength;

    @Getter
    @Setter
    public static class Participant {
        //puuid로 매칭중인 게임 정보 가져오기
        public boolean bot;
        public long championId;
        public long teamId;
        public long spell1Id;
        public long spell2Id;
        public long profileIconId;
        public String summonerName;
        public String summonerId;
        public String puuid;
    }

}
