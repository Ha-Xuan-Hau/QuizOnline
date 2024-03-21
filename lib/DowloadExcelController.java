/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.questionExam;
import Entity.questionExamAnswer;
import Entity.Exam;
import Entity.User;
import Model.DAOExam;
import Model.DAOQuestionExam;
import Model.DAOQuestionExamAnswer;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ACER
 */
@WebServlet(name = "DowloadExcelController", urlPatterns = {"/ExcelURL"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 20,
        maxFileSize = 1024 * 1024 * 100,
        maxRequestSize = 1024 * 1024 * 500
)
public class DowloadExcelController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("go");
        if (service==null){
            service = "showImport";
        }
        if (service.equals("showImport")){
            request.getRequestDispatcher("/exam/import.html").forward(request, response);
        }
        if (service.equals("downLoadFile")) {
            String exampleFilePath = "/ExampleFormQuiz.xlsx"; // Đường dẫn đến tệp ExampleFormQuiz.xlsx trong thư mục web
            try ( InputStream inputStream = getServletContext().getResourceAsStream(exampleFilePath)) {
                if (inputStream == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }

                Workbook workbook;
                if (exampleFilePath.endsWith(".xls")) {
                    workbook = new HSSFWorkbook(inputStream); // Đối với tệp Excel định dạng .xls (Excel 97-2003)
                } else if (exampleFilePath.endsWith(".xlsx")) {
                    workbook = new XSSFWorkbook(inputStream); // Đối với tệp Excel định dạng .xlsx (Excel 2007+)
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    return;
                }

                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=ExampleFormQuiz.xlsx");

                try ( OutputStream outputStream = response.getOutputStream()) {
                    workbook.write(outputStream);
                }

                workbook.close();
            } catch (IOException ex) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                ex.printStackTrace();
            }
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String fileName = getFileName(filePart);

        InputStream fileContent = filePart.getInputStream();
        try {
            Workbook workbook;
            if (fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(fileContent);
            } else if (fileName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fileContent);
            } else {
                throw new IOException("Unsupported file format");
            }
            //dao
            DAOExam dao = new DAOExam();
            DAOQuestionExam questionDAO = new DAOQuestionExam();
            DAOQuestionExamAnswer answerDAO = new DAOQuestionExamAnswer();

            JsonArray dataArray = new JsonArray();
            Sheet sheet = workbook.getSheetAt(0);

            // Đọc tiêu đề bài thi và thời gian làm bài từ dòng đầu tiên và dòng thứ hai
            String examTitle = getValueFromCell(sheet.getRow(0).getCell(0));
            String examTime = getValueFromCell(sheet.getRow(1).getCell(0));
            String examScore = getValueFromCell(sheet.getRow(1).getCell(1));
            String takingTime = getValueFromCell(sheet.getRow(1).getCell(2));

            String examTitleValue = examTitle.split(":")[1].trim();
            int examTimeValue = Integer.parseInt(examTime.split(":")[1].trim());
            double examScoreValue = Integer.parseInt(examScore.split(":")[1].trim());
            int takingTimeValue = Integer.parseInt(takingTime.split(":")[1].trim());

            // Bắt đầu đọc từ dòng thứ 7
            for (int i = 6; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String question = getValueFromCell(row.getCell(0));
                JsonArray answersArray = new JsonArray();
                for (int j = 1; j <= 4; j++) { // Đọc các đáp án từ cột B đến E
                    String answer = getValueFromCell(row.getCell(j));
                    if (!answer.equals("")) {
                        answersArray.add(new JsonPrimitive(answer));
                    }
                }
                String correctAnswer = getValueFromCell(row.getCell(5)); // Đọc chuỗi đáp án đúng từ cột F
                JsonObject questionObject = new JsonObject();
                questionObject.add("question", new JsonPrimitive(question));
                questionObject.add("answers", answersArray);

                String[] correctAnswerIndices = correctAnswer.split(",");
                JsonArray correctAnswerArray = new JsonArray();
                for (String index : correctAnswerIndices) {
                    correctAnswerArray.add(Integer.parseInt(index.trim()));
                }
                questionObject.add("correctAnswer", correctAnswerArray);
                dataArray.add(questionObject);
            }

            System.out.println("title: " + examTitleValue + " time: " + examTimeValue + "Score: " + examScoreValue + " taking time: " + takingTimeValue);
            for (int i = 0; i < dataArray.size(); i++) {
                JsonObject questionObject = dataArray.get(i).getAsJsonObject();

                String question = questionObject.get("question").getAsString();
                System.out.println("Question: " + question);

                JsonArray answersArray = questionObject.get("answers").getAsJsonArray();
                System.out.println("Answers:");
                for (int j = 0; j < answersArray.size(); j++) {
                    String answer = answersArray.get(j).getAsString();
                    System.out.println("- " + answer);
                }

                JsonArray correctAnswerArray = questionObject.get("correctAnswer").getAsJsonArray();
                System.out.println("Correct Answers:");
                for (int k = 0; k < correctAnswerArray.size(); k++) {
                    int correctAnswerIndex = correctAnswerArray.get(k).getAsInt();
                    System.out.println("- " + correctAnswerIndex);
                }

                System.out.println("--------------------");
            }

            User user = (User) request.getSession().getAttribute("acc");
            int teacherId = user.getAccountId();
            //int classId = (int) request.getSession().getAttribute("ClassId");
            int classId = 6;

            try {
                dao.createDefaultExam(classId, teacherId);
                Exam newExam = dao.getNewExam();
                Exam exam = new Exam(newExam.getExamId(), examTitleValue, examScoreValue, examTimeValue * 60, takingTimeValue);
                dao.importExam(exam);
                if (dataArray != null) {
                    double scoreQuestion = examScoreValue / dataArray.size();

                    for (int i = 0; i < dataArray.size(); i++) {
                        JsonObject questionObject = dataArray.get(i).getAsJsonObject();

                        String question = questionObject.get("question").getAsString();
                        questionExam ques = new questionExam(newExam.getExamId(), question, scoreQuestion);
                        questionDAO.insertQuetionExam(ques);
                        int questionExamId = questionDAO.getLast();

                        JsonArray answersArray = questionObject.get("answers").getAsJsonArray();
                        JsonArray correctAnswerArray = questionObject.get("correctAnswer").getAsJsonArray();
                        for (int j = 0; j < answersArray.size(); j++) {
                            String answer = answersArray.get(j).getAsString();
                            float percentage = 0.0f;
                            if (correctAnswerArray.size() > 0) {
                                percentage = answersArray.size() > 0 ? 100.0f / correctAnswerArray.size() : 0.0f;
                            }
                            
                            boolean correct = getCorret(correctAnswerArray, j + 1);
                            float percent = correct ? percentage : 0.0f;
                            questionExamAnswer ExamAnswer = new questionExamAnswer(questionExamId, answer, correct, percent);
                            answerDAO.insertQuetionExamAnswer(ExamAnswer);
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DowloadExcelController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        } catch (IOException e) {
            response.getWriter().println("Lỗi khi đọc file Excel: " + e.getMessage());
        } finally {
            if (fileContent != null) {
                fileContent.close();
            }
        }
        response.sendRedirect("exam/import.html");

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String getValueFromCell(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case STRING:
                return cell.getStringCellValue();
            default:
                return "";
        }
    }

    private boolean getCorret(JsonArray correctAnswerArray, int j) {
        for (int i = 0; i < correctAnswerArray.size(); i++) {
            int correctAnswerIndex = correctAnswerArray.get(i).getAsInt();
            if (j == correctAnswerIndex) {
                return true;
            }
        }
        return false;
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
