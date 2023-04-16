import java.sql.*;
import java.util.*;

import by.fpmibsu.LIBRARY.DAO.*;
import by.fpmibsu.LIBRARY.entity.*;
import by.fpmibsu.LIBRARY.exception.DAOException;;

public class Runner {

    public void addUser(User user) throws DAOException {
        new UserDAO().create(user);
    }

    public void deleteUser(int userID) throws DAOException {
        new UserDAO().delete(userID);
    }

    public void addComplaint(Complaints complaint) throws DAOException {
        new ComplaintsDAO().create(complaint);
    }

    public void deleteComplaint(int complaintID) throws DAOException {
        new ComplaintsDAO().delete(complaintID);
    }

    public void addLiterature(Literature literature) throws DAOException {
        new LiteratureDAO().create(literature);
    }

    public void deleteLiterature(int userID) throws DAOException {
        new LiteratureDAO().delete(userID);
    }

    public void addReview(Reviews review) throws DAOException {
        new ReviewsDAO().create(review);
    }

    public void deleteReview(int reviewID) throws DAOException {
        new ReviewsDAO().delete(reviewID);
    }

//    public void addStudentToCourse(Student student, Course course) throws DAOException, SQLException {
//        StudentCourseDAO studentCourseDAO = new StudentCourseDAO();
//        StudentCourse studentCourse = new StudentCourse(student.getStudentId(), course.getId());
//
//        studentCourseDAO.create(studentCourse);
//    }
//
//    public void removeStudentFromCourse(Student student, Course course) throws Exception {
//        StudentCourseDAO studentCourseDAO = new StudentCourseDAO();
//        StudentCourse studentCourse = studentCourseDAO.read(student.getStudentId(), course.getId());
//
//        studentCourseDAO.delete(studentCourse.getStudentCourseId());
//    }
}
