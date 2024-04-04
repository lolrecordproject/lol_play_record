package project_team.lol_play_record.dto;

import lombok.Data;

@Data
public class MatchGameDto {
    private int teamId;
    private int spell1Id;
    private int spell2Id;
    private int championId;
    private int profileIconId;
    private String summonerName;
    private String roitId;
}
