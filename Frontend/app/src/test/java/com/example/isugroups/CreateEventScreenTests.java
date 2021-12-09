package com.example.isugroups;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateEventScreenTests {

    private CreateEventScreen screen = new CreateEventScreen();

    @Test
    public void testDateValidation()
    {
        assert(screen.validDate("24/04/2021"));
        assert(!screen.validDate("0424/2021"));
        assert(!screen.validDate("04324/2021"));
        assert(!screen.validDate("07/13/2021"));
        assert(!screen.validDate("32/04/2021"));
        assert(!screen.validDate("0/03/2021"));
    }
}
