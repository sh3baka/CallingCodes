package com.task.calling_codes;

import com.task.calling_codes.service.WikiTable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class WikiTableTest {

    private WikiTable wikiTable;

    @Before
    public void setUp() {
        wikiTable = new WikiTable();
    }

    @Test
    public void testWikitableParsed() {
        assertNotNull(wikiTable.getMapOfCodes());
    }

    @Test
    public void testWikitableHasAllElements() {
        assertEquals(250, wikiTable.getMapOfCodes().size());
    }
}