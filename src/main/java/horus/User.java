package horus;

public class User
{
    private String m_name;
    private DynamicList<LoginTime> m_loginList;

    public User(String name)
    {
        m_name = name;
        m_loginList = new DynamicList<LoginTime>();
    }

    public User()
    {
        m_name = "UNKNOWN";
        m_loginList = new DynamicList<LoginTime>();
    }

    public String get_name() { return m_name; }
    public void set_name(String nName) { m_name = nName; }
    public DynamicList<LoginTime> get_loginList() { return m_loginList; }

    public void add_login(LoginTime login)
    {
        m_loginList.push_back(login);
    }

    @Override
    public String toString()
    {
        String out = "{user:" + m_name + ", logins{";

        for(int i = 0; i < m_loginList.size(); i++)
            if(i == m_loginList.size() - 1)
                out += m_loginList.get(i).toString() + "}";
            else
                out += m_loginList.get(i).toString() + ", ";

        return out;
    }

}
