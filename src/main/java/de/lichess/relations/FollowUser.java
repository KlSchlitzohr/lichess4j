package de.lichess.relations;

import de.lichess.account.account.PlayTime;
import de.lichess.users.team.Perfs;
import lombok.Getter;

@Getter
public class FollowUser {

    private Perfs perfs;
    private Profile profile;
    private PlayTime playTime;

    private String id;
    private String username;
    private String language;
    private String title;
    private String url;

    private long createdAt;
    private long seenAt;

    private boolean patron;
    private boolean online;
}
