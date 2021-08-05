package ReadAndWriteInputData;



import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFromExcel {

    public String readExcel(String filePath,String fileName,String sheetName,int iRow, int iCol) throws IOException{

    //Create an object of File class to open xlsx file
    String returnContent = "";
    File file =    new File(filePath+"\\"+fileName);

    //Create an object of FileInputStream class to read excel file

    FileInputStream inputStream = new FileInputStream(file);

    Workbook guru99Workbook = null;

    //Find the file extension by splitting file name in substring  and getting only extension name

    String fileExtensionName = fileName.substring(fileName.indexOf("."));

    //Check condition if the file is xlsx file

    if(fileExtensionName.equals(".xlsx")){

    //If it is xlsx file then create object of XSSFWorkbook class

    guru99Workbook = new XSSFWorkbook(inputStream);

    }

    //Check condition if the file is xls file

    else if(fileExtensionName.equals(".xls")){

        //If it is xls file then create object of HSSFWorkbook class

        guru99Workbook = new HSSFWorkbook(inputStream);

    }

    //Read sheet inside the workbook by its name

    Sheet xlSheet = guru99Workbook.getSheet(sheetName);

    //Find number of rows in excel file

    int rowCount = xlSheet.getLastRowNum()-xlSheet.getFirstRowNum();

    //Create a loop over all the rows of excel file to read it

    for (int i = 0; i < rowCount+1; i++) {

        Row row = xlSheet.getRow(i);

        //Create a loop to print cell values in a row

        for (int j = 0; j < row.getLastCellNum(); j++) {

            //Print Excel data in console
        	if(i==iRow && j==iCol) {
            System.out.print(row.getCell(j).getStringCellValue());
        	returnContent =  row.getCell(j).getStringCellValue();
        	break;
        	}
        }
        if(!returnContent.equals("")) {break;}
        System.out.println();
    } 
    
    return returnContent;

    }  

    //Main function is calling readExcel function to read data from excel file
/*
    public static void main(String...strings) throws IOException{

    //Create an object of ReadGuru99ExcelFile class

    ReadGuru99ExcelFile objExcelFile = new ReadGuru99ExcelFile();

    //Prepare the path of excel file

    String filePath = System.getProperty("user.dir")+"\\src\\excelExportAndFileIO";

    //Call read file method of the class to read data

    objExcelFile.readExcel(filePath,"ExportExcel.xlsx","ExcelGuru99Demo");

    }
*/
}
