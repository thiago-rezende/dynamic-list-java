package horus;

import org.testng.annotations.*;
import static org.testng.Assert.*;

public class DynamicListTest
{
    @Test public void push_back_validation()
    {
        DynamicList<Integer> list = new DynamicList<>();

        list.push_back(100);
        assertEquals(list.get(0), (Integer) 100);

        list.push_back(50);
        assertEquals(list.get(1), (Integer) 50);
    }

    @Test public void push_front_validation()
    {
        DynamicList<Integer> list = new DynamicList<>();

        list.push_front(0);
        assertEquals(list.get(0), (Integer) 0);

        list.push_front(10);
        assertEquals(list.get(0), (Integer) 10);
    }

    @Test public void add_validation()
    {
        DynamicList<Integer> list = new DynamicList<>();

        list.add(500, 2);
        assertEquals(list.get(0), (Integer) 500);

        list.add(50, 1);
        assertEquals(list.get(1), (Integer) 50);
    }

    @Test public void unsafe_add_validation()
    {
        DynamicList<Integer> list = new DynamicList<>();

        list.unsafe_add(500, 2);
        assertEquals(list.get(2), (Integer) 500);

        list.unsafe_add(50, 1);
        assertEquals(list.get(1), (Integer) 50);
    }

    @Test public void pop_back_validation()
    {
        DynamicList<Integer> list = new DynamicList<>();

        list.push_back(100);
        assertEquals(list.get(0), (Integer) 100);

        list.push_front(10);
        assertEquals(list.get(0), (Integer) 10);

        list.pop_back();
        assertNull(list.unsafe_get(list.size()));
    }

    @Test public void pop_front_validation()
    {
        DynamicList<Integer> list = new DynamicList<>();

        list.push_back(100);
        assertEquals(list.get(0), (Integer) 100);

        list.push_front(10);
        assertEquals(list.get(0), (Integer) 10);

        list.pop_front();
        assertEquals(list.get(0), (Integer) 100);
    }

    @Test public void size_validation()
    {
        DynamicList<Integer> list = new DynamicList<>();
        assertEquals(list.size(), 0);    

        list.push_back(10);
        assertEquals(list.get(0), (Integer) 10);

        assertEquals(list.size(), 1);

        list.remove(0);
        assertEquals(list.size(), 0);
    }

    @Test public void remove_validation()
    {
        DynamicList<Integer> list = new DynamicList<>();
        list.push_back(500);
        list.push_back(50);
        list.remove(0);
        assertEquals(list.get(0), (Integer) 50);
        assertNull(list.unsafe_get(list.size()));
    }

    @Test public void toString_validation()
    {
        DynamicList<Integer> list = new DynamicList<>();
        list.push_back(500);
        list.push_back(50);

        String expected = "{500, 50}";
        assertEquals(list.toString(), expected);
    }

    @Test public void toStringWithNullPositions_validation()
    {
        DynamicList<Integer> list = new DynamicList<>();
        list.unsafe_add(500, 2);
        list.unsafe_add(50, 5);

        String expected = "{null, null, 500, null, null, 50}";
        assertEquals(list.toStringWithNullPositions(), expected);
    }
}
