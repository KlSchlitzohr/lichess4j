package de.lichess;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.lichess.account.AccountManager;
import de.lichess.games.GamesManager;
import de.lichess.relations.RelationsManager;
import de.lichess.users.UsersManager;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class LichessClient {

    private final String baseUrl = "https://lichess.org/api/";
    private final String token;
    private final ObjectMapper objectMapper;
    private final TypeReference<HashMap<String, String>> typeReference;

    private AccountManager accountManager;
    private UsersManager usersManager;
    private RelationsManager relationsManager;
    private GamesManager gamesManager;

    public LichessClient(String token) {
        this.token = token;
        this.objectMapper = new ObjectMapper();
        this.typeReference = new TypeReference<HashMap<String, String>>() {};
        createManagers();
    }

    private void createManagers() {
        this.accountManager = new AccountManager(this);
        this.usersManager = new UsersManager(this);
        this.relationsManager = new RelationsManager(this);
        this.gamesManager = new GamesManager(this);
    }

}
