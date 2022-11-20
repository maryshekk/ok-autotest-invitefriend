package ValueObject;

public class UserBot {
    private String login;
    private String password;

    public UserBot(String login, String password)
    {
        this.login = login;
        this.password = password;
    }

    public String getLogin()
    {
        return this.login;
    }

    public String getPassword()
    {
        return this.password;
    }

    public UserBot setLogin(String login)
    {
        this.login = login;
        return this;
    }

    public UserBot setPassword(String password)
    {
        this.password = password;
        return this;
    }

}
