package server;

import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService {

    private List<UserData> users;
    private ListDataSource datasource;
    //private SQLiteDataSource datasource;

    public SimpleAuthService() {
        datasource=new ListDataSource();
       // datasource = new SQLiteDataSource();
        refreshData();
    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        for (UserData user : users) {
            if (user.login.equals(login) && user.password.equals(password)) {
                return user.nickname;
            }
        }
        return null;
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        for (UserData user : users) {
            if (user.login.equals(login) || user.nickname.equals(nickname)) {
                return false;
            }
        }

        datasource.putUserData(new UserData(login, password, nickname));
        refreshData();
        return true;
    }

    @Override
    public void changeNickName(String login, String nickname) {
        datasource.changeUserNick(login, nickname);
        refreshData();
    }

    @Override
    public void refreshData() {
        users = datasource.getUsersData();
    }
}
