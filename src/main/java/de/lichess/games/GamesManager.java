package de.lichess.games;

import de.lichess.LichessClient;
import de.lichess.Manager;
import de.lichess.games.export.Export;
import de.lichess.utils.HashMapBuilder;
import de.lichess.utils.HttpsRequestBuilder;
import de.lichess.utils.HttpsRequestType;
import lombok.SneakyThrows;

public class GamesManager extends Manager {

    public GamesManager(LichessClient lichessClient) {
        super(lichessClient, "export");
    }

    @SneakyThrows
    public Export getExportByGameID(String gameId, ExportGameParameters parameters) {
        return getLichessClient().getObjectMapper().readValue(new HttpsRequestBuilder
                (getUrl() + "/" + gameId, HttpsRequestType.GET, getLichessClient()).setPostParams(parameters.getHaspMap())
                .setHeaderParameters(new HashMapBuilder<String, String>().put("Accept","application/json").build())
                .getResponse(), Export.class);
    }

    @SneakyThrows
    public String getPGNExportByGameID(String gameId, ExportGameParameters parameters) {
        return new HttpsRequestBuilder
                (getUrl() + "/" + gameId, HttpsRequestType.GET, getLichessClient()).setPostParams(parameters.getHaspMap())
                .getResponse();
    }

    @SneakyThrows
    public Export getExportOfOngoingGame(String username, ExportGameParameters parameters) {
        return getLichessClient().getObjectMapper().readValue(new HttpsRequestBuilder
                (getLichessClient().getBaseUrl() + "user/" + username + "/current-game"
                        , HttpsRequestType.GET, getLichessClient()).setPostParams(parameters.getHaspMap())
                .setHeaderParameters(new HashMapBuilder<String, String>().put("Accept","application/json").build()).
                        getResponse(), Export.class);
    }

    @SneakyThrows
    public String getPGNExportOfOngoingGame(String username, ExportGameParameters parameters) {
        return new HttpsRequestBuilder
                (getLichessClient().getBaseUrl() + "/user/" + username + "/current-game"
                        , HttpsRequestType.GET, getLichessClient()).setPostParams(parameters.getHaspMap())
                .getResponse();
    }
}
