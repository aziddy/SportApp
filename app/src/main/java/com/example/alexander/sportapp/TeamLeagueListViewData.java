package com.example.alexander.sportapp;

/**
 * Created by alexa on 3/29/2016.
 */
public class TeamLeagueListViewData {

    String TeamName;
    String TeamColor;
    String CurrentLeague;
    String Wins;
    String Losses;
    String Players;
    String Rank;
    String Creator;


    TeamLeagueListViewData(String TeamName, String TeamColor, String CurrentLeague, String Wins, String Losses, String Players, String Rank, String Creator){

        this.TeamName = TeamName;
        this.TeamColor = TeamColor;
        this.CurrentLeague = CurrentLeague;
        this.Wins = Wins;
        this.Losses = Losses;
        this.Players = Players;
        this.Rank = Rank;
        this.Creator = Creator;

    }

}
