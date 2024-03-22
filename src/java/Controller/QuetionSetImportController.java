/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.questionExam;
import Entity.questionExamAnswer;
import Entity.Exam;
import Entity.NormalQuestion;
import Entity.NormalQuestionAnswer;
import Entity.QuestionSet;
import Entity.User;
import Model.DAONormalQuestion;
import Model.DAONormalQuestionAnswer;
import Model.DAOQuestionExam;
import Model.DAOQuestionExamAnswer;
import Model.DAOQuestionSet;
import Model.DAOSubject;
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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ACER
 */
@WebServlet(name = "QuetionSetImportController", urlPatterns = {"/QuetionSetImportURL"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 20,
        maxFileSize = 1024 * 1024 * 100,
        maxRequestSize = 1024 * 1024 * 500
)
public class QuetionSetImportController extends HttpServlet {

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
        if (service == null) {
            service = "showImport";
        }
        if (service.equals("showImport")) {
            //dao
            DAOQuestionSet dao = new DAOQuestionSet();
            DAOSubject subjectDAO = new DAOSubject();
            //set data
            HashMap<Integer, String> subjectMap = subjectDAO.SubjectMap();
            request.setAttribute("subjectMap", subjectMap);
            request.getRequestDispatcher("/Question/import.jsp").forward(request, response);
        }
        if (service.equals("downLoadFile")) {
            String exampleFilePath = "/ExampleFormQuetionSet.xlsx"; // Đường dẫn đến tệp ExampleFormQuiz.xlsx trong thư mục web
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
                response.setHeader("Content-Disposition", "attachment; filename=ExampleFormQuetionSet.xlsx");

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
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));

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
            DAOQuestionSet dao = new DAOQuestionSet();
            DAONormalQuestion questionDao = new DAONormalQuestion();
            DAONormalQuestionAnswer answerDao = new DAONormalQuestionAnswer();

            JsonArray dataArray = new JsonArray();
            Sheet sheet = workbook.getSheetAt(0);

            // Đọc tiêu đề bài thi từ dòng đầu tiên
            String quizTitle = getValueFromCell(sheet.getRow(0).getCell(0));

            String quizTitleValue = quizTitle.split(":")[1].trim();

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

//            System.out.println("title: " + quizTitleValue);
//            for (int i = 0; i < dataArray.size(); i++) {
//                JsonObject questionObject = dataArray.get(i).getAsJsonObject();
//
//                String question = questionObject.get("question").getAsString();
//                System.out.println("Question: " + question);
//
//                JsonArray answersArray = questionObject.get("answers").getAsJsonArray();
//                System.out.println("Answers:");
//                for (int j = 0; j < answersArray.size(); j++) {
//                    String answer = answersArray.get(j).getAsString();
//                    System.out.println("- " + answer);
//                }
//
//                JsonArray correctAnswerArray = questionObject.get("correctAnswer").getAsJsonArray();
//                System.out.println("Correct Answers:");
//                for (int k = 0; k < correctAnswerArray.size(); k++) {
//                    int correctAnswerIndex = correctAnswerArray.get(k).getAsInt();
//                    System.out.println("- " + correctAnswerIndex);
//                }
//
//                System.out.println("--------------------");
//            }
            //lấy dữ liệu tạo queston set
            User user = (User) request.getSession().getAttribute("acc");
            int userId = user.getAccountId();
            
            QuestionSet set = new QuestionSet(quizTitleValue, userId, subjectId, 0);
            dao.insertQuestionSet(set);
            QuestionSet newset = dao.getNewest();
            if (dataArray != null) {
                
                for (int i = 0; i < dataArray.size(); i++) {
                    JsonObject questionObject = dataArray.get(i).getAsJsonObject();
                    
                    String question = questionObject.get("question").getAsString();
                    NormalQuestion ques = new NormalQuestion(question, newset.getSetId());
                    questionDao.insert(ques);
                    int questionSetId = questionDao.getLast();
                    
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
                        NormalQuestionAnswer setAnswer = new NormalQuestionAnswer(questionSetId, answer, correct, percent);
                        answerDao.insert(setAnswer);
                    }
                }
            }

        } catch (IOException e) {
            request.setAttribute("status", "fail");
            request.getRequestDispatcher("Question/import.jsp").forward(request, response);
        } finally {
            if (fileContent != null) {
                fileContent.close();
            }
        }
        response.sendRedirect("QuestionSetURL");

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
