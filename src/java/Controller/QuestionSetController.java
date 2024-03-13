/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.NormalQuestion;
import Entity.NormalQuestionAnswer;
import Entity.QuestionSet;
import Model.DAONormalQuestion;
import Model.DAONormalQuestionAnswer;
import Model.DAOQuestionSet;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
import java.io.BufferedReader;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author admin
 */
@WebServlet(name = "QuestionSetController", urlPatterns = {"/QuestionSetURL", "/uploadFile", "/getExampleFile"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 20,  
    maxFileSize = 1024 * 1024 * 100,       
    maxRequestSize = 1024 * 1024 * 500   
)
public class QuestionSetController extends HttpServlet {

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

        response.setContentType("text/html;charset=UTF-8");
        DAOQuestionSet dao = new DAOQuestionSet();
        DAONormalQuestion questionDAO = new DAONormalQuestion();
        DAONormalQuestionAnswer answerDAO = new DAONormalQuestionAnswer();
        String service = request.getParameter("go");
        if (service == null) {
            service = "listAllSets";
        }
        if (service.equals("listAllSets")) {
            ArrayList<QuestionSet> allQuesSet = dao.getData("select * from QuestionSet");
            request.setAttribute("data", allQuesSet);
            request.getRequestDispatcher("Question/displayAllQuesSet.jsp").forward(request, response);
        }
        if (service.equals("setDetails")) {
            int setId = Integer.parseInt(request.getParameter("SetId"));

            ArrayList<NormalQuestion> Ques = dao.getQues(setId);

            ArrayList<ArrayList<NormalQuestionAnswer>> QuesAnswers = dao.getAnswer(setId);

            request.setAttribute("question", Ques);
            request.setAttribute("content", QuesAnswers);
            request.getRequestDispatcher("Question/setDetail.jsp").forward(request, response);
        }
        if (service.equals("addNewSet")) {
            QuestionSet set = new QuestionSet("New Set", 1, 1, 0);
            dao.insertQuestionSet(set);
            QuestionSet setObj = dao.getNewest();
            //    request.getRequestDispatcher("editSet.jsp").forward(request, response);
            response.sendRedirect("EditQuestionSetURL?setId=" + setObj.getSetId());
        }
        if (service.equals("addNewSetDetails")) {
            String action = request.getParameter("submit");
            if (action != null) {
                int setId = Integer.parseInt(request.getParameter("setId"));
                String title = request.getParameter("title");
                int subjectId = Integer.parseInt(request.getParameter("subjectId"));

                QuestionSet set = new QuestionSet(setId, title, 1, subjectId, 0);
                dao.updateQuestionSet(set);
                // lay cau hoi trong set
                int index1 = questionDAO.getTotalQuestionInSet(setId);
                for (int i = 0; i < index1; i++) {
                    String question = request.getParameter("QuestionDetail" + i);
                    int questionId = Integer.parseInt(request.getParameter("QuestionId" + i));

                    NormalQuestion ques = new NormalQuestion(questionId, question, setId);
                    questionDAO.update(ques);

                    int index2 = answerDAO.getTotalAnswerOfQuestion(questionId);
                    int correctCount = 0;
                    for (int j = 0; j < index2; j++) {
                        String isCorrect = request.getParameter("IsTrueAnswer" + i + "-" + j);
                        if ("true".equals(isCorrect)) {
                            correctCount++;
                        }
                    }
                    float percentage = correctCount > 0 ? 100.0f / correctCount : 0.0f;

                    for (int j = 0; j < index2; j++) {
                        String answer = request.getParameter("AnswerDetail" + i + "-" + j);
                        String isCorrect = request.getParameter("IsTrueAnswer" + i + "-" + j);
                        String answerId = request.getParameter("AnswerId" + i + "-" + j);

                        boolean correct = "true".equals(isCorrect);
                        int id = Integer.parseInt(answerId);

                        float percent = correct ? percentage : 0.0f;

                        NormalQuestionAnswer quesAnswer = new NormalQuestionAnswer(id, questionId, answer, correct, percent);
                        answerDAO.update(quesAnswer);
                    }
                }
                response.sendRedirect("QuestionSetURL");
            }
        }
        if (service.equals("deleteSet")) {
            int setId = Integer.parseInt(request.getParameter("setId"));
            dao.deleteQuestionSet(setId);
            response.sendRedirect("QuestionSetURL");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getServletPath().equals("/QuestionSetURL")) {
            processRequest(request, response);
        } else {
            serveExampleFile(request, response);
        }

    }

    private void serveExampleFile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String exampleFilePath = "./ExampleFile/example.xls"; 
        try (InputStream inputStream = getServletContext().getResourceAsStream(exampleFilePath)) {
            if (inputStream == null) {
                
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            
            response.setContentType("application/octet-stream");
            
            response.setHeader("Content-Disposition", "attachment; filename=example_file_name");

            
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }
        } catch (IOException ex) {
            
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getServletPath().equals("/uploadFile")) {
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

                JsonArray dataArray = new JsonArray();
                Sheet sheet = workbook.getSheetAt(0);
                for (Row row : sheet) {
                    Cell cell1 = row.getCell(0);
                    Cell cell2 = row.getCell(1);

                    String question = getValueFromCell(cell1);
                    String answer = getValueFromCell(cell2);

                    JsonObject dataObject = new JsonObject();
                    dataObject.add("question", new JsonPrimitive(question));
                    dataObject.add("answer", new JsonPrimitive(answer));
                    dataArray.add(dataObject);
                }

                Gson gson = new Gson();
                String json = gson.toJson(dataArray);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            } catch (IOException e) {
                response.getWriter().println("Lỗi khi đọc file Excel: " + e.getMessage());
            } finally {
                if (fileContent != null) {
                    fileContent.close();
                }
            }
        } else {

            BufferedReader reader = request.getReader();
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();

            Gson gson = new Gson();
            JsonArray dataArray = gson.fromJson(jsonBuilder.toString(), JsonArray.class);
            DAONormalQuestion dAONormalQuestion = new DAONormalQuestion();

            for (JsonElement element : dataArray) {
                JsonObject obj = element.getAsJsonObject();
                String question = obj.get("question").getAsString();
                String answer = obj.get("answer").getAsString();
                dAONormalQuestion.insertMultipleQuestions(question, answer);
            }
        }

    }

    private String getValueFromCell(Cell cell) {
        if (cell == null) {
            return "";
        }
        return switch (cell.getCellType()) {
            case NUMERIC ->
                String.valueOf(cell.getNumericCellValue());
            case STRING ->
                cell.getStringCellValue();
            default ->
                "";
        };
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
