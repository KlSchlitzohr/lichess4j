package de.lichess.games;

import de.lichess.utils.HashMapBuilder;

import java.util.HashMap;

public class ExportGameParameters {

    private boolean moves = true;
    private boolean pgnInJson = false;
    private boolean tags = true;
    private boolean clocks = true;
    private boolean evals = true;
    private boolean opening = true;
    private boolean literate = false;
    private String players;
    
    public ExportGameParameters() {
        
    }

    public HashMap<String, String> getHaspMap() {
        HashMapBuilder hashMap = new HashMapBuilder<String, String>()
                .put("moves",String.valueOf(moves))
                .put("pgnInJson",String.valueOf(pgnInJson))
                .put("tags",String.valueOf(tags))
                .put("clocks",String.valueOf(clocks))
                .put("evals",String.valueOf(evals))
                .put("opening",String.valueOf(opening))
                .put("literate",String.valueOf(literate));
        if (players != null) {
            hashMap.put("players",players);
        }
        return hashMap.build();
    }

    public ExportGameParameters setMoves(boolean moves) {
        this.moves = moves;
        return this;
    }

    public ExportGameParameters setPgnInJson(boolean pgnInJson) {
        this.pgnInJson = pgnInJson;
        return this;
    }

    public ExportGameParameters setTags(boolean tags) {
        this.tags = tags;
        return this;
    }

    public ExportGameParameters setClocks(boolean clocks) {
        this.clocks = clocks;
        return this;
    }

    public ExportGameParameters setEvals(boolean evals) {
        this.evals = evals;
        return this;
    }

    public ExportGameParameters setOpening(boolean opening) {
        this.opening = opening;
        return this;
    }

    public ExportGameParameters setLiterate(boolean literate) {
        this.literate = literate;
        return this;
    }

    public ExportGameParameters setPlayers(String players) {
        this.players = players;
        return this;
    }
}
