package server;

import java.util.ArrayList;
import java.util.List;

public class ListDataSource implements DataSource {
    private List<UserData> users;

    public ListDataSource(){
        users = new ArrayList<>();
        users.add(new UserData("qwe", "qwe", "qwe"));
        users.add(new UserData("asd", "asd", "asd"));
        users.add(new UserData("zxc", "zxc", "zxc"));
        for (int i = 1; i <= 10; i++) {
            users.add(new UserData("login" + i, "pass" + i, "nick" + i));
        }
    }

    @Override
    public List<UserData> getUsersData() {
        return users;
    }

    @Override
    public int setUserData(UserData userdata) {
        users.add(userdata);
        return 0;
    }
}
