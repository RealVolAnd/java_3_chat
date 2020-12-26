package server;

import java.util.ArrayList;
import java.util.List;

public interface DataSource {
    public List<UserData> getUsersData();
    public int setUserData(UserData userdata);
}
