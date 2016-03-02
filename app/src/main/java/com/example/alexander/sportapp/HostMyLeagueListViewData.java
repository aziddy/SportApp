package com.example.alexander.sportapp;

import java.util.ArrayList;

/**
 * Created by Alex on 3/1/2016.
 */
public class HostMyLeagueListViewData  {

    String LeagueName;
    String LeagueColor;

    String TeamColor1;
    String TeamColor2;

    String TeamName1;
    String TeamName2;

    String MatchTime;
    String MatchDate;



    HostMyLeagueListViewData(String LeagueName, String LeagueColor, String TeamColor1, String TeamColor2, String TeamName1, String TeamName2, String MatchTime, String MatchDate) {

        this.LeagueName = LeagueName;
        this.LeagueColor = LeagueColor;

        this.TeamColor1 = TeamColor1;
        this.TeamColor2 = TeamColor2;

        this.TeamName1 = TeamName1;
        this.TeamName2 = TeamName2;

        this.MatchTime = MatchTime;
        this.MatchDate = MatchDate;


    }


}
