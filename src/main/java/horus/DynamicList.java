package horus;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple DynamicList
 * @author Thiago Rezende
 */
public class DynamicList<_Type> {

    /**
     * DynamycList Logger
     */
    protected final static Logger LOGGER = Logger.getLogger(DynamicList.class.getName());

    /**
     * Data Array
     */
    protected Object[] m_data;

    /**
     * Number of Objects initialized from Data Array
     */
    protected int m_size;

    /**
     * Constructs a DynamicList
     */
    public DynamicList()
    {
        m_data = new Object[10];
        m_size = 0;
        LOGGER.setLevel(Level.SEVERE);
    }

    /**
     * Get the size of the Data Array
     * @return Size of the Data Array
     */
    public int size() { return m_size; }

    public boolean empty()
    {
        if(m_size == 0)
            return true;

        return false;
    }

    /**
     * Expands list capacity
     */
    private void expand()
    {
        Object[] tmp_arr = new Object[m_data.length + 20];

        for(int i = 0; i < m_size; i++)
            tmp_arr[i] = m_data[i];

        m_data = tmp_arr;
        LOGGER.log(Level.INFO, "List expanded to {0} positions", m_data.length);
    }

    /**
     * Check the Data Array capacity and call the expand function if necessary
     */
    private void check_size()
    {
        if(m_size >= m_data.length)
            this.expand();
    }

    /**
     * Add a new item in a position of the list (ordering)
     * @param value Value to be added in the list
     * @param index Position of the new item
     */
    public void add(_Type value, int index)
    {
        if(index >= m_data.length || index < 0)
            throw new IllegalArgumentException("Out of Bounds!");

        this.check_size();

        if(index >= m_size)
        {
            m_data[m_size] = value;
            m_size++;
            LOGGER.log(Level.INFO, "Added to position {0}", m_size - 1);
        }
        else
        {
            for(int i = m_size; i > index; i--)
                m_data[i] = m_data[i - 1];

            m_data[index] = value;
            m_size += index - m_size + 1;
            LOGGER.log(Level.INFO, "Added to position {0}", index);
        }
    }

    /**
     * Add a new item in a position of the list (no ordering)
     * @param value Value to be added in the list
     * @param index Position of the new item
     */
    public void unsafe_add(_Type value, int index)
    {
        if(index >= m_data.length || index < 0)
            throw new IllegalArgumentException("Out of Bounds!");

        this.check_size();

        for(int i = m_size; i > index; i--)
            m_data[i] = m_data[i - 1];

        m_data[index] = value;

	    if (index >= m_size)
            m_size += index - m_size + 1;
        else
            m_size++;

        LOGGER.log(Level.INFO, "Added to position {0}", index);
    }

    /**
     * Get an item from the Data Array
     * @param index Position of the new item
     * @return An item from the Data Array
     */
    public _Type get(int index)
    {
        if(index >= m_size || index < 0)
            throw new IllegalArgumentException("Out of Bounds!");

		if(m_data[index] == null)
			throw new IllegalArgumentException("Uninitialized position!");

        return (_Type) m_data[index];
    }

    /**
     * Get an item from the Data Array (no size handling)
     * @param index Position of the new item
     * @return An item from the Data Array
     */
    public _Type unsafe_get(int index)
    {
        if(index >= m_data.length || index < 0)
            throw new IllegalArgumentException("Out of Bounds!");

        return (_Type) m_data[index];
    }

    /**
     * Add a new item to the bottom of the list
     * @param value Value to be added in the list
     */
    public void push_back(_Type value)
    {
        this.check_size();

        m_data[m_size++] = value;
        LOGGER.log(Level.INFO, "Added to position {0}", (m_size - 1));
    }

    public void push_back(DynamicList<_Type> list)
    {
        this.check_size();

        LOGGER.log(Level.INFO, "Added to position {0}", (m_size - 1));
    }

    /**
     * Add a new item to the top of the list
     * @param value Value to be added in the list
     */
    public void push_front(_Type value)
    {
        this.check_size();

        for(int i = m_size; i > 0; i--)
            m_data[i] = m_data[i - 1];

        m_data[0] = value;
        m_size++;
        LOGGER.log(Level.INFO, "Added to position 0");
    }

    public void pop_back()
    {
        if(m_size <= 0)
            return;

        m_data[m_size - 1] = null;
        m_size--;
    }

    public void pop_front()
    {
        if(m_size <= 0)
            return;

        for(int i = 0; i < m_size; i++)
            m_data[i] = m_data[i + 1];

        m_size--;
    }

    /**
     * Removes an object from the list
     * @param index Index of the object to be removed
     */
    public void remove(int index)
    {
        if(index >= m_size || index < 0)
            throw new IllegalArgumentException("Out of Bounds!");

        for(int i = index; i < m_size; i++)
            m_data[i] = m_data[i + 1];

        m_size--;

        LOGGER.log(Level.INFO, "Removed from position {0}", index);
    }

    public void unsafe_remove(int index)
    {
        if(index >= m_data.length || index < 0)
            throw new IllegalArgumentException("Out of Bounds!");

        m_data[index] = null;
        m_size--;
    }

    /**
     * Reorder the Data Array removing the nullpointer between the values
     */
    public void sort()
    {
        Object[] tmp_array = new Object[m_data.length];

        int pos = 0;

        for(int i = 0; i < m_data.length; i++)
            if(m_data[i] != null)
            {
                tmp_array[pos] = m_data[i];
                pos++;
            }

        m_data = tmp_array;
        m_size = pos;
    }

    public Pair<Boolean, Integer> find(_Type value)
    {
        for(int i = 0; i < m_size; i++)
            if(m_data[i].equals(value))
                return new Pair<>(true, i);

        return new Pair<>(false, -1);
    }

    /**
     * Return a String with the list content
     * @return A formatted string of list content
     */
    @Override
    public String toString()
    {
        String out = new String("{\"objects\":[");

        if(empty())
        {
            out += "]}";
            return out;
        }

        for(int i = 0; i < m_size; i++)
            if(m_data[i] != null)
                if(i == m_size - 1)
                    out += m_data[i].toString() + "]}";
                else
                    out += m_data[i].toString() + ", ";

        return out;
    }

    /**
     * Return a String with the list content (no ordering)
     * @return A formatted string of list content
     */
    public String toStringWithNullPositions()
    {
        String out = new String("{\"objects\":[");

        for(int i = 0; i < m_size; i++)
            if(m_data[i] != null)
                if(i == m_size - 1)
                    out += m_data[i].toString() + "]}";
                else
                    out += m_data[i].toString() + ", ";
            else
                if(i == m_size - 1)
                    out += "null]}";
                else
                    out += "null, ";
        return out;
    }
}
