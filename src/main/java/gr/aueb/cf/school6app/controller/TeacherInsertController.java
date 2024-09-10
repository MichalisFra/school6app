package gr.aueb.cf.school6app.controller;

import gr.aueb.cf.school6app.dao.ITeacherDAO;
import gr.aueb.cf.school6app.dao.TeacherDAOImpl;
import gr.aueb.cf.school6app.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.school6app.dto.TeacherInsertDTO;
import gr.aueb.cf.school6app.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.school6app.model.Teacher;
import gr.aueb.cf.school6app.service.ITeacherService;
import gr.aueb.cf.school6app.service.TeacherServiceImpl;
import gr.aueb.cf.school6app.validator.TeacherValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/teachers/insert")
public class TeacherInsertController extends HttpServlet  {

    ITeacherDAO teacherDAO = new TeacherDAOImpl();
    ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/teacher-insert.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TeacherInsertDTO insertDTO;
        Map<String, String> errors;

        String firstname = request.getParameter("firstname").trim();
        String lastname = request.getParameter("lastname").trim();
        String firstnameMessage;
        String lastnameMessage;
        Teacher teacher;
        String errorMessage;

        insertDTO = new TeacherInsertDTO(firstname,lastname);

        errors = TeacherValidator.validate(insertDTO);

        if (!errors.isEmpty()) {
            firstnameMessage = errors.getOrDefault("firstname", "");
            lastnameMessage = errors.getOrDefault("lastname", "");

            request.setAttribute("firstnameMessage", firstnameMessage);
            request.setAttribute("lastnameMessage", lastnameMessage);
            request.setAttribute("insertDTO", insertDTO);
            request.getRequestDispatcher("/WEB-INF/jsp/teacher-insert.jsp").forward(request,response);
            return;

        }

        // Call the service
        try {
            teacher = teacherService.insertTeacher(insertDTO);
            TeacherReadOnlyDTO readOnlyDTO =  mapToReadOnlyDTO(teacher);
            request.setAttribute("teacherInfo", readOnlyDTO);
            request.getRequestDispatcher("/WEB-INF/jsp/teacher-inserted.jsp").forward(request,response);

        } catch (TeacherDAOException e) {
            errorMessage = e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/jsp/teacher-insert.jsp").forward(request,response);
        }
    }


    private TeacherReadOnlyDTO mapToReadOnlyDTO(Teacher teacher) {
        return new TeacherReadOnlyDTO(teacher.getId(), teacher.getFirstname(), teacher.getLastname());
    }
}
