package com.natwest.reportgenerator.controller;

import com.natwest.reportgenerator.util.ReportGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api_v1/report")
@Slf4j
public class ReportController {
    private final ReportGenerator reportGenerator;

    public ReportController(ReportGenerator reportGenerator){
        this.reportGenerator = reportGenerator;
    }

    @PostMapping("/generate")
    public String generateReport(){
        log.info("Started report generation");
        reportGenerator.triggerReportGeneration();
        return "Report generated Successfully!!!";

    }
}
