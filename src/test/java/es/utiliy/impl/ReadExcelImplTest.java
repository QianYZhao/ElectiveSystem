package es.utiliy.impl;

import es.utiliy.ReadExcel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadExcelImplTest {
    ReadExcel readExcel= new ReadExcelImpl();

    @Test
    void read() {
        readExcel.Read("data/grade.xlsx");
    }
}