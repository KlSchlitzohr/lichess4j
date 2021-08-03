package de.lichess.games.export;

import lombok.Getter;

@Getter
public class Black { 
    
    private User user;
    
    private int rating;
    private int ratingDiff;

    private boolean provisional;
}