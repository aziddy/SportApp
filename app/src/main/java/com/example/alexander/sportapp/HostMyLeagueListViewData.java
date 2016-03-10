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

    String RankSystem;
    String Sport;
    String Leaguetype;
    String Private;
    String HostUserName;



    HostMyLeagueListViewData(String[] values) {

        this.LeagueName = values[0];
        this.LeagueColor = values[1];

        this.TeamColor1 = values[2];
        this.TeamColor2 = values[3];

        this.TeamName1 = values[4];
        this.TeamName2 = values[5];

        this.MatchTime = values[6];
        this.MatchDate = values[7];

        this.RankSystem = values[8];
        this.Sport = values[9];
        this.Leaguetype = values[10];
        this.Private = values[11];
        this.HostUserName = values[12];

    }


    HostMyLeagueListViewData(String LeagueName, String LeagueColor, String TeamColor1, String TeamColor2, String TeamName1, String TeamName2, String MatchTime, String MatchDate, String RankSystem, String Sport, String Leaguetype, String Private, String HostUserName) {

        this.LeagueName = LeagueName;
        this.LeagueColor = LeagueColor;

        this.TeamColor1 = TeamColor1;
        this.TeamColor2 = TeamColor2;

        this.TeamName1 = TeamName1;
        this.TeamName2 = TeamName2;

        this.MatchTime = MatchTime;
        this.MatchDate = MatchDate;

        this.RankSystem = RankSystem;
        this.Sport = Sport;
        this.Leaguetype = Leaguetype;
        this.Private = Private;
        this.HostUserName = HostUserName;

    }




    public void addLater(String LeagueName, String LeagueColor, String TeamColor1, String TeamColor2, String TeamName1, String TeamName2, String MatchTime, String MatchDate, String RankSystem, String Sport, String Leaguetype, String Private, String HostUserName) {

        this.LeagueName = LeagueName;
        this.LeagueColor = LeagueColor;

        this.TeamColor1 = TeamColor1;
        this.TeamColor2 = TeamColor2;

        this.TeamName1 = TeamName1;
        this.TeamName2 = TeamName2;

        this.MatchTime = MatchTime;
        this.MatchDate = MatchDate;

        this.RankSystem = RankSystem;
        this.Sport = Sport;
        this.Leaguetype = Leaguetype;
        this.Private = Private;
        this.HostUserName = HostUserName;

    }



}
