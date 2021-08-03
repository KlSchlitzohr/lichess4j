package de.lichess.relations;

import de.lichess.LichessClient;
import de.lichess.Manager;
import de.lichess.utils.HttpsRequestBuilder;
import de.lichess.utils.HttpsRequestType;
import lombok.SneakyThrows;

public class RelationsManager extends Manager {

    public RelationsManager(LichessClient lichessClient) {
        super(lichessClient,"user/");
    }

    @SneakyThrows
    public FollowUser[] getFollowedByUser(String username) {
        return getLichessClient().getObjectMapper().readValue("[" + new HttpsRequestBuilder
                (getUrl() + username + "/following", HttpsRequestType.GET, getLichessClient()).getResponse().replace("}{","},{") + "]", FollowUser[].class);
    }

    @SneakyThrows
    public FollowUser getFollowAUser(String username) {
        return getLichessClient().getObjectMapper().readValue("[" + new HttpsRequestBuilder
                (getUrl() + username + "/followers", HttpsRequestType.GET, getLichessClient()).getResponse().replace("}{","},{") + "]", FollowUser.class);
    }

}
