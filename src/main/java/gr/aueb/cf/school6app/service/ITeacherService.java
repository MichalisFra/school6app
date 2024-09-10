package gr.aueb.cf.school6app.service;

import gr.aueb.cf.school6app.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.school6app.dto.FiltersDTO;
import gr.aueb.cf.school6app.dto.TeacherInsertDTO;
import gr.aueb.cf.school6app.dto.TeacherUpdateDTO;
import gr.aueb.cf.school6app.model.Teacher;
import gr.aueb.cf.school6app.service.exceptions.TeacherNotFoundException;

import java.util.List;

public interface ITeacherService {
    Teacher insertTeacher(TeacherInsertDTO dto) throws TeacherDAOException;
    Teacher updateTeacher(TeacherUpdateDTO dto) throws TeacherNotFoundException,
            TeacherDAOException;
    void deleteTeacher(Integer id) throws TeacherNotFoundException, TeacherDAOException;
    Teacher getTeacherById(Integer id) throws TeacherNotFoundException, TeacherDAOException;
    List<Teacher> getFilteredTeachers(FiltersDTO filters) throws TeacherDAOException;
}
