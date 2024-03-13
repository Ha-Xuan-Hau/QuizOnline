package Controller;


import Entity.Student;
import Entity.Teacher;
import Entity.User;
import Model.DAOStudent;
import Model.DAOTeacher;
import Model.DAOUser;
import Utils.EncryptionUtils;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;

/**
 *
 * @author Lautom
 */
@WebServlet(name = "QuestionsServlet", urlPatterns = {"/questions"})
public class QuestionsServlet extends HttpServlet {

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        Part filePart = request.getPart("file");
        String fileName = getFileName(filePart);

        InputStream fileContent = filePart.getInputStream();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(fileContent);
       
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
              
                Cell cell1 = row.getCell(0);
                Cell cell2 = row.getCell(1);
           
                String value1 = cell1.getStringCellValue();
                String value2 = cell2.getStringCellValue();
        
                System.out.println("Value1: " + value1);
                System.out.println("Value2: " + value2);
            }
            response.getWriter().println("Dữ liệu từ hai cột đầu tiên đã được đọc thành công.");
        } catch (IOException e) {
            response.getWriter().println("Lỗi khi đọc file Excel: " + e.getMessage());
        } finally {
            if (fileContent != null) {
                fileContent.close();
            }
        }
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
