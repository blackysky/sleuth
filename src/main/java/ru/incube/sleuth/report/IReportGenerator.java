package ru.incube.sleuth.report;

import ru.incube.sleuth.enums.DataModel;

public interface IReportGenerator {
    void generateReport(DataModel model);
}
