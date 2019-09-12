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
    private final static Logger LOGGER = Logger.getLogger(DynamicList.class.getName());

    /**
     * Data Array
     */
    private Object[] m_data;

    /**
     * Number of Objects initialized from Data Array
     */
    private int m_size;

    /**
     * Constructs a DynamicList
     */
    public DynamicList()
    {
        m_data = new Object[10];
        m_size = 0;
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
     * Add a new item in a position of the list
     * @param value Value to be added in the list
     * @param index Position of the new item
     */
    public void add(_Type value, int index)
    {
        if(index >= m_size || index < 0)
            throw new IllegalArgumentException("Out of Bounds!");

        this.check_size();

        for(int i = m_size; i > index; i--)
            m_data[i] = m_data[i - 1];
        
        m_data[index] = value;
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
     * Add a new item to the bottom of the list
     * @param value Value to be added in the list
     */
    public void push_back(_Type value)
    {
        this.check_size();
        
        m_data[m_size++] = value;
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

    /**
     * Return a String with the list content
     * @return A formatted string of list content
     */
    @Override
    public String toString()
    {
        String out = new String("{");
        
        for(int i = 0; i < m_size; i++)
            if(m_data[i] != null)
                if(i == m_size - 1)
                    out += m_data[i].toString() + "}";
                else
                    out += m_data[i].toString() + ", ";

        return out;
    }
}
