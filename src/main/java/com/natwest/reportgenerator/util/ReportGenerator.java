package com.natwest.reportgenerator.util;

import com.natwest.reportgenerator.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReportGenerator {

    @Value("${file.path.input}")
    private String inputFile;

    @Value("${file.path.reference}")
    private String referenceFile;

    @Value("${file.path.output}")
    private String outputFile;

    ReportService reportService;

    private final Object lock = new Object();
    private boolean isGeneratingReport = false;

    public ReportGenerator(ReportService reportService) {
        this.reportService = reportService;
    }


    @Scheduled(cron = "${report.scheduler.cron}")
    public void generateReportScheduled() {
        synchronized (lock) {
            if (!isGeneratingReport) {
                try {
                    isGeneratingReport = true;
                    log.info("Started report generation");
                    log.info("Scheduler running to generate report!!!");
                    reportService.generateReport(inputFile,referenceFile,outputFile);
                } finally {
                    isGeneratingReport = false;
                }
            }
        }
    }

    public void triggerReportGeneration() {
        synchronized (lock) {
            if (!isGeneratingReport) {
                try {
                    isGeneratingReport = true;
                    log.info("API triggered to generate report!!!");
                    reportService.generateReport(inputFile,referenceFile,outputFile);
                } finally {
                    isGeneratingReport = false;
                }
            }
        }
    }

}
