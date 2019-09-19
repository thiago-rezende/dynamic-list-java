package horus;

public class Pair<_fType, _sType> {

    private _fType m_first;
    private _sType m_second;

    public Pair(_fType first, _sType second)
    {
        m_first = first;
        m_second = second;
    }

    public _fType first() { return m_first; }
    public _sType second() { return m_second; }

    @Override
    public String toString()
    {
        return "{" + m_first.toString() + ", " + m_second.toString() + "}";
    }
}
