package com.task.callingCodes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class WikiTableTest {

    private final WikiTable wikiTable = new WikiTable();

    @Test
    public void testWikitableParsed() {
        assertNotNull(wikiTable.getMapOfCodes());
    }

    @Test
    public void testWikitableHasAllElements() {
        assertEquals(250, wikiTable.getMapOfCodes().size());
    }
}