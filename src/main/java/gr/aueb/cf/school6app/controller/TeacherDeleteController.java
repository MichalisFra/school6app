package gr.aueb.cf.school6app.controller;

import gr.aueb.cf.school6app.dao.ITeacherDAO;
import gr.aueb.cf.school6app.dao.TeacherDAOImpl;
import gr.aueb.cf.school6app.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.school6app.service.ITeacherService;
import gr.aueb.cf.school6app.service.TeacherServiceImpl;
import gr.aueb.cf.school6app.service.exceptions.TeacherNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/teachers/delete")
public class TeacherDeleteController extends HttpServlet {

    ITeacherDAO teacherDAO = new TeacherDAOImpl();
    ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);
    String message = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            teacherService.deleteTeacher(id);
            request.setAttribute("id", id);
            request.getRequestDispatcher("/WEB-INF/jsp/teacher-deleted.jsp").forward(request,response);

        } catch (TeacherDAOException | TeacherNotFoundException e) {
            message = e.getMessage();
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/jsp/teachers.jsp").forward(request,response);
        }
    }
}
