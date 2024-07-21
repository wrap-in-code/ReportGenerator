package com.natwest.reportgenerator.utiltest;

import com.natwest.reportgenerator.util.CsvFileParser;
import com.natwest.reportgenerator.util.FileParser;
import com.natwest.reportgenerator.util.FileParserFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FileParserFactoryTest {

    @Autowired
    private FileParserFactory fileParserFactory;

    @Test
    public void testGetFileParserCsv() {
        FileParser parser = fileParserFactory.getFileParser("input.csv");
        assertTrue(parser instanceof CsvFileParser);
    }
}
