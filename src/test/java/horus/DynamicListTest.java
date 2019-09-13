package horus;

import org.testng.annotations.*;
import static org.testng.Assert.*;

public class DynamicListTest
{
    @Test public void size_validation()
    {
        DynamicList<Integer> list = new DynamicList<>();

        list.push_back(10);
        assertEquals(list.get(0), (Integer) 10);

        list.push_back(100);
        assertEquals(list.get(1), (Integer) 100);

        list.push_front(0);
        assertEquals(list.get(0), (Integer) 0);

        list.add(500, 2);
        assertEquals(list.get(2), (Integer) 500);
    }
}
