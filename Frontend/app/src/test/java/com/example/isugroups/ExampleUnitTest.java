package com.example.isugroups;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
    @Test
    public void TestSetGetUserID(){
        GlobalVars.setCurUserID("19191991");
        assertEquals(GlobalVars.getCurUserID(), "19191991");
    }
    @Test
    public void TestSetGetPassword(){
        GlobalVars.setUserPassphrase("19191991");
        assertEquals(GlobalVars.getUserPassphrase(), "19191991");
    }
    @Test
    public void TestSetGetPage(){
        GlobalVars.setCurPage(1);
        assertEquals(GlobalVars.getCurPage(), 1);
    }
    @Test
    public void TestSetGetEnvent(){
        GlobalVars.setCurEventTitle("Swim meet");
        assertEquals(GlobalVars.getCurEventTitle(), "Swim meet");
    }
    @Test
    public void TestSetGetClubs(){
        String Clubs[] = {"Swim","Running"};
        GlobalVars.setClubs(Clubs);
        assertEquals(GlobalVars.getClubs().contains("Swim")&&GlobalVars.getClubs().contains("Running"), true);
    }

}