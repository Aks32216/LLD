package Behavioural_Design_Pattern.Template_Design_Pattern;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

class ReportData {
    public List<String> getHeaders() {
        return Arrays.asList("ID", "Name", "Value");
    }

    public List<Map<String, Object>> getRows() {
        return Arrays.asList(
                Map.of("ID", 1, "Name", "Item A", "Value", 100.0),
                Map.of("ID", 2, "Name", "Item B", "Value", 150.5),
                Map.of("ID", 3, "Name", "Item C", "Value", 75.25)
        );
    }
}

abstract class AbstractReportExporter {
    // Template method - defines the algorithm skeleton
    public final void exportReport(ReportData data, String filePath) {
        prepareData(data);
        openFile(filePath);
        writeHeader(data);
        writeDataRows(data);
        writeFooter(data);    // Hook - optional override
        closeFile(filePath);
        System.out.println("Export complete: " + filePath);
    }

    // Concrete method - shared across all subclasses
    protected void prepareData(ReportData data) {
        System.out.println("Preparing report data...");
    }

    // Concrete method - shared across all subclasses
    protected void openFile(String filePath) {
        System.out.println("Opening file: " + filePath);
    }

    // Abstract method - each subclass MUST implement
    protected abstract void writeHeader(ReportData data);

    // Abstract method - each subclass MUST implement
    protected abstract void writeDataRows(ReportData data);

    // Hook method - optional override with sensible default
    protected void writeFooter(ReportData data) {
        // Default: no footer. Subclasses can override if needed.
    }

    // Concrete method - shared across all subclasses
    protected void closeFile(String filePath) {
        System.out.println("Closing file: " + filePath);
    }
}

class CsvReportExporter extends AbstractReportExporter {
    @Override
    protected void writeHeader(ReportData data) {
        System.out.println("CSV: " + String.join(",", data.getHeaders()));
    }

    @Override
    protected void writeDataRows(ReportData data) {
        for (Map<String, Object> row : data.getRows()) {
            StringBuilder sb = new StringBuilder();
            for (String header : data.getHeaders()) {
                if (sb.length() > 0) sb.append(",");
                sb.append(row.get(header));
            }
            System.out.println("CSV: " + sb);
        }
    }
}

class PdfReportExporter extends AbstractReportExporter {
    @Override
    protected void writeHeader(ReportData data) {
        System.out.println("PDF: | " + String.join(" | ", data.getHeaders()) + " |");
        System.out.println("PDF: " + "-".repeat(40));
    }

    @Override
    protected void writeDataRows(ReportData data) {
        for (Map<String, Object> row : data.getRows()) {
            StringBuilder sb = new StringBuilder("PDF: | ");
            for (String header : data.getHeaders()) {
                sb.append(row.get(header)).append(" | ");
            }
            System.out.println(sb);
        }
    }

    @Override
    protected void writeFooter(ReportData data) {
        System.out.println("PDF: --- Page 1 of 1 ---");
    }
}

public class Data_Exporter {
    public static void main(String[] args) {
        ReportData data = new ReportData();

        AbstractReportExporter csvExporter = new CsvReportExporter();
        csvExporter.exportReport(data, "sales_report.csv");

        System.out.println();

        AbstractReportExporter pdfExporter = new PdfReportExporter();
        pdfExporter.exportReport(data, "sales_report.pdf");
    }
}
