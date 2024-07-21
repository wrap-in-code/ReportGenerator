package com.natwest.reportgenerator.servicetest;

import com.natwest.reportgenerator.model.InputRecord;
import com.natwest.reportgenerator.model.ReferenceRecord;
import com.natwest.reportgenerator.service.ReportService;
import com.natwest.reportgenerator.util.CsvFileParser;
import com.natwest.reportgenerator.util.FileParser;
import com.natwest.reportgenerator.util.FileParserFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import static org.mockito.Mockito.*;
import java.io.IOException;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    @Mock
    private FileParserFactory fileParserFactory;

    @Mock
    private KieContainer kieContainer;

    @Mock
    private KieSession kieSession;

    @InjectMocks
    private ReportService reportService;

    @Test
    public void testGenerateReport() throws IOException {
        // Mock input and reference data
        FileParser mockInputParser = mock(CsvFileParser.class);
        FileParser mockReferenceParser = mock(CsvFileParser.class);

        List<InputRecord> inputRecords = new ArrayList<>();
        InputRecord inputRecord = new InputRecord("value1", "value2", "value3", "value4", "5.0", "key1", "key2");
        inputRecords.add(inputRecord);

        Map<String, ReferenceRecord> referenceData = new HashMap<>();
        ReferenceRecord refRecord = new ReferenceRecord("key1", "refvalue1", "key2", "refvalue2", "refvalue3", "10.0");
        referenceData.put("key1", refRecord);

        when(fileParserFactory.getFileParser("input.csv")).thenReturn(mockInputParser);
        when(fileParserFactory.getFileParser("reference.csv")).thenReturn(mockReferenceParser);
        when(mockInputParser.parseInputFile("input.csv")).thenReturn(inputRecords);
        when(mockReferenceParser.parseReferenceFile("reference.csv")).thenReturn(referenceData);
        when(kieContainer.newKieSession()).thenReturn(kieSession);

        reportService.generateReport("input.csv", "reference.csv", "output.csv");

        // Verify interactions
        verify(mockInputParser, times(1)).parseInputFile("input.csv");
        verify(mockReferenceParser, times(1)).parseReferenceFile("reference.csv");
        verify(kieSession, times(1)).fireAllRules();
        verify(kieSession, times(1)).dispose();
    }
}
