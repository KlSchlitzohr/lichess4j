package de.lichess.games.export;

import lombok.Getter;

import java.time.Clock;

@Getter
public class Export {

    private Opening opening;
    private Players players;
    private Clock clock;
    
    private String id;
    private String variant;
    private String speed;
    private String perf;
    private String status;
    private String moves;
    private String winner;

    private long lastMoveAt;
    private long createdAt;

    private boolean rated;
}
