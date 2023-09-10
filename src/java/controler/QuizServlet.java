/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler;

import dal.ProductsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Quiz;

/**
 *
 * @author dangn
 */
public class QuizServlet extends HttpServlet {

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
        String answer = request.getParameter("answer");
        String correctAnswer = request.getParameter("correctAnswer");

        if (answer != null && correctAnswer != null) {
            // Check if the submitted answer is correct
            boolean isCorrect = answer.equals(correctAnswer);

            // Get the current score and increment it if the answer is correct
            HttpSession session = request.getSession();
            int score = (int) session.getAttribute("score");
            if (isCorrect) {
                score++;
            }

            // Get the list of quiz questions from the session
            List<Quiz> quizData = (List<Quiz>) session.getAttribute("quizData");
            if (quizData == null || quizData.isEmpty()) {
                // No more quiz data available
                // Redirect to the result page
                response.sendRedirect("result");
                return;
            }

            // Remove the current question from the list and update the session attribute
            quizData.remove(0);
            session.setAttribute("quizData", quizData);

            // Update the score and currentQuestionNumber in the session
            session.setAttribute("score", score);
            session.setAttribute("currentQuestionNumber", quizData.isEmpty() ? 0 : 1);

            // Redirect to the same page to load the next question
            response.sendRedirect("QuizServlet");
        } else {
            // If the form is not submitted, load the quiz question as before
            ProductsDAO quizDAO = new ProductsDAO();
            List<Quiz> quizData = quizDAO.getAll();

            if (quizData.isEmpty()) {
                // No more quiz data available
                // Redirect to the result page
                response.sendRedirect("result");
            } else {
                Quiz currentQuizData = quizData.get(0);

                // Set the quizData and currentQuizData attributes in the session
                HttpSession session = request.getSession();
                session.setAttribute("quizData", quizData);
                session.setAttribute("currentQuizData", currentQuizData);

                // Reset the score and currentQuestionNumber in the session
                session.setAttribute("score", 0);
                session.setAttribute("currentQuestionNumber", 1);

                request.getRequestDispatcher("show.jsp").forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Initialize the quiz by setting initial values for the score and question number
        request.getSession().setAttribute("score", 0);
        request.getSession().setAttribute("currentQuestionNumber", 0);

        // Redirect to the doGet method to start the quiz
        doGet(request, response);
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

}
