import by.fpmibsu.LIBRARY.entity.Complaints;
import by.fpmibsu.LIBRARY.entity.Literature;
import by.fpmibsu.LIBRARY.entity.Reviews;
import by.fpmibsu.LIBRARY.entity.User;
import by.fpmibsu.LIBRARY.exception.DAOException;

public class Main {
    public static void main(String[] args) throws DAOException {
        Runner runner = new Runner();
        //runner.addUser(new User("kobalt", "password", "kobalt23@gmial.com"));
        //runner.deleteUser(1);
        //runner.addComplaint(new Complaints(3, "текст жалобы"));
        //runner.addReview(new Reviews("хороший отзыв", 3));
        runner.addLiterature(new Literature("название", 3 , 1, "текст литературы", 0, "15.04.2023"));
    }
}