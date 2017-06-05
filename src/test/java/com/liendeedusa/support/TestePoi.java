package com.liendeedusa.support;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.net.URL;
import java.util.Iterator;

/**
 * Created by hermeswaldemarin on 02/06/17.
 */
public class TestePoi {

    @Test
    public void teste() throws Exception{

        HSSFWorkbook workbook = new HSSFWorkbook(new URL("http://co.madison.tn.us/DocumentCenter/View/858").openStream());

        Sheet sheet = workbook.getSheetAt(2);
        Iterator<Row> iterator = sheet.iterator();

        JSONArray array = new JSONArray();

        while (iterator.hasNext()) {

            Row currentRow = iterator.next();

            if(currentRow.getFirstCellNum() != 0 || currentRow.getCell(0).getCellTypeEnum() != CellType.NUMERIC){
                continue;
            }

            JSONObject object = new JSONObject();

            Cell parcelCell = currentRow.getCell(2);
            Cell addressCell = currentRow.getCell(4);
            Cell typeCell = currentRow.getCell(5);
            Cell priceCell = currentRow.getCell(9);

            object.put("parcelId", parcelCell.getStringCellValue());
            object.put("address", addressCell.getStringCellValue());
            object.put("type", typeCell.getStringCellValue());
            object.put("price", priceCell.getNumericCellValue());

            array.put(object);


        }

        System.out.println(array.toString());
    }

}
