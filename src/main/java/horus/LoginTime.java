package horus;

public class LoginTime
{
    int m_login;
    int m_logout;
    int m_time;

    public LoginTime(int login, int logout)
    {
        m_login = login;
        m_logout = logout;
        calcUsageTime();
    }

    public int get_loginTime() { return m_login; }
    public int get_logoutTime() { return m_logout; }
    public int get_time() { return m_time; }

    public void set_loginTime(int nLogin)
    {
        m_login = nLogin;
        calcUsageTime();
    }

    public void set_logoutTime(int nLogout)
    {
        m_logout = nLogout;
        calcUsageTime();
    }

    private void calcUsageTime() { m_time = m_logout - m_login; }

    @Override
    public String toString()
    {
        return "{in:" + m_login +", out:" + m_logout + ", usage:" + m_time +"}";
    }
}
