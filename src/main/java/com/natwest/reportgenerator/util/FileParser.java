package com.natwest.reportgenerator.util;

import com.natwest.reportgenerator.model.InputRecord;
import com.natwest.reportgenerator.model.OutputRecord;
import com.natwest.reportgenerator.model.ReferenceRecord;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileParser {
    List<InputRecord> parseInputFile(String filePath) throws IOException;
    Map<String, ReferenceRecord> parseReferenceFile(String filePath) throws IOException;
    void writeOutputFile(String filePath, List<OutputRecord> outputRecords) throws IOException;
}
