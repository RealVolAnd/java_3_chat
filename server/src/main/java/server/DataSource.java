package server;

import java.util.ArrayList;
import java.util.List;

public interface DataSource {
    public List<UserData> getUsersData();

    public int putUserData(UserData userdata);

    public int changeUserNick(String login, String newNick);
}
