package com.natwest.reportgenerator.utiltest;

import com.natwest.reportgenerator.model.InputRecord;
import com.natwest.reportgenerator.model.ReferenceRecord;
import com.natwest.reportgenerator.util.CsvFileParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class CsvFileParserTest {

    @Autowired
    private CsvFileParser csvFileParser;

    @Test
    public void testParseInputFile() throws IOException {
        List<InputRecord> records = csvFileParser.parseInputFile("src/test/resources/input.csv");
        assertFalse(records.isEmpty());
        assertEquals("Happy", records.get(0).getField1());
    }

    @Test
    public void testParseReferenceFile() throws IOException {
        Map<String, ReferenceRecord> referenceData = csvFileParser.parseReferenceFile("src/test/resources/reference.csv");
        assertFalse(referenceData.isEmpty());
        ReferenceRecord referenceRecord = new ReferenceRecord("refer1", "refer1data", "refer2", "refer2data", "refer3data", "101.78");
        assertTrue(referenceData.containsValue(referenceRecord));
    }
}
