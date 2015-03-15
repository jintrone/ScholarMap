package csst15

import grails.transaction.Transactional
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import javax.servlet.http.HttpServletRequest

@Transactional
class ExcelService {
    def userService

    def readExcelData(HttpServletRequest request, String field) {
        def fieldsMap = [:]
        def reqFile = request.getFile("${field}")
        Workbook workbook = new XSSFWorkbook(reqFile?.inputStream)

        Sheet sheet = workbook.getSheetAt(0)
        Iterator<Row> rowIterator = sheet.iterator()

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next()
            Iterator<Cell> cellIterator = row.iterator()
            def fieldsList = []
            def invalid = false
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next()
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        if (cell.getStringCellValue().trim() == "username" || cell.getStringCellValue().trim() == '') {
                            invalid = true
                        } else {
                            fieldsList.add(cell.getStringCellValue())
                        }
                        break
                    case Cell.CELL_TYPE_NUMERIC:
                        fieldsList.add(cell.getNumericCellValue())
                        break
                }

                if (invalid)
                    break
            }
            if (!invalid)
                fieldsMap.put(row.rowNum, fieldsList)
        }

        return userService.createUser(fieldsMap)
    }
}
