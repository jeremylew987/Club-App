package com.example.isugroups;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void TestSetGetClubName(){
        GlobalVars.setCurClubName("Chess");
        assertEquals(GlobalVars.getCurClubName(), "Chess");
    }
    public void TestSetGetUserID(){
        GlobalVars.setCurUserID("19191991");
        assertEquals(GlobalVars.getCurUserID(), "19191991");
    }

}