package com.natwest.reportgenerator.util;

import com.natwest.reportgenerator.model.InputRecord;
import com.natwest.reportgenerator.model.OutputRecord;
import com.natwest.reportgenerator.model.ReferenceRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CsvFileParser implements FileParser {

    @Override
    public List<InputRecord> parseInputFile(String filePath) throws IOException {
        List<InputRecord> inputRecords = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            for (CSVRecord record : csvParser) {
                InputRecord inputRecord = new InputRecord();
                inputRecord.setField1(record.get("field1"));
                inputRecord.setField2(record.get("field2"));
                inputRecord.setField3(record.get("field3"));
                inputRecord.setField4(record.get("field4"));
                inputRecord.setField5(record.get("field5"));
                inputRecord.setRefkey1(record.get("refkey1"));
                inputRecord.setRefkey2(record.get("refkey2"));
                inputRecords.add(inputRecord);
            }
        }
        return inputRecords;
    }

    @Override
    public Map<String, ReferenceRecord> parseReferenceFile(String filePath) throws IOException {
        Map<String, ReferenceRecord> referenceData = new HashMap<>();
        try (FileReader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            for (CSVRecord record : csvParser) {
                ReferenceRecord refRecord = new ReferenceRecord();
                refRecord.setRefkey1(record.get("refkey1"));
                refRecord.setRefdata1(record.get("refdata1"));
                refRecord.setRefkey2(record.get("refkey2"));
                refRecord.setRefdata2(record.get("refdata2"));
                refRecord.setRefdata3(record.get("refdata3"));
                refRecord.setRefdata4(record.get("refdata4"));
                referenceData.put(refRecord.getRefkey1() + refRecord.getRefkey2(), refRecord);
            }
        }
        return referenceData;
    }

    @Override
    public void writeOutputFile(String filePath, List<OutputRecord> outputRecords) throws IOException {
        try (FileWriter writer = new FileWriter(filePath);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("outfield1", "outfield2", "outfield3", "outfield4", "outfield5"))) {

            for (OutputRecord outputRecord : outputRecords) {
                csvPrinter.printRecord(
                        outputRecord.getOutfield1(),
                        outputRecord.getOutfield2(),
                        outputRecord.getOutfield3(),
                        outputRecord.getOutfield4(),
                        outputRecord.getOutfield5()
                );
            }
        }
    }
}
