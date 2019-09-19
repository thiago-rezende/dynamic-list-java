package horus;

public class User
{
    private String m_name;
    private DynamicList<LoginTime> m_loginList;
    private int m_usage;

    public User(String name)
    {
        if(name.equals(""))
            m_name = "UNKNOWN";
        else
            m_name = name;

        m_usage = 0;
        m_loginList = new DynamicList<LoginTime>();
    }

    public User()
    {
        m_name = "UNKNOWN";
        m_usage = 0;
        m_loginList = new DynamicList<LoginTime>();
    }

    public String get_name() { return m_name; }
    public int get_usage() {return m_usage; }
    public void set_name(String nName) { m_name = nName; }
    public DynamicList<LoginTime> get_loginList() { return m_loginList; }

    public void add_login(LoginTime login)
    {
        m_usage += login.get_time();
        m_loginList.push_back(login);
    }

    @Override
    public boolean equals(Object other)
    {
        User user = (User) other;

        if(m_name.equals(user.get_name()))
            return true;

        return false;
    }

    @Override
    public String toString()
    {
        String out = "{\"user\":\"" + m_name + "\", \"usage\":\"" + m_usage + "\", \"logins\":[";

        if(m_loginList.empty()) {
            out += "]}";
            return out;
        }

        for(int i = 0; i < m_loginList.size(); i++)
            if(i == m_loginList.size() - 1)
                out += m_loginList.get(i).toString() + "]}";
            else
                out += m_loginList.get(i).toString() + ", ";

        return out;
    }

}
