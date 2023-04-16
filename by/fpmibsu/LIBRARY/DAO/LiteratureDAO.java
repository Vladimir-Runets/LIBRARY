package by.fpmibsu.LIBRARY.DAO;

import java.sql.*;
import by.fpmibsu.LIBRARY.entity.Literature;
import by.fpmibsu.LIBRARY.entity.Reviews;
import by.fpmibsu.LIBRARY.entity.User;
import by.fpmibsu.LIBRARY.exception.DAOException;
public class LiteratureDAO extends AbstractDAO<Literature, Integer> {

    @Override
    protected String getCreateQuery(Literature literature) {
        return "INSERT INTO literature (title, author_id, review_id, text, amount_of_reads, adding_time) VALUES (?,?,?,?,?,?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE literature SET title = ?, author_id = ?, review_id = ?, text = ?, amount_of_reads = ?, adding_time = ? WHERE literature_id = ?;";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT * FROM literature WHERE literature_id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM literature WHERE literature_id = ?";
    }

    @Override
    protected String getCountRowsQuery() {
        return "SELECT count(*) FROM literature;";
    }

    @Override
    protected void setIdStatement(PreparedStatement statement, Integer literatureID) throws DAOException {
        try {
            statement.setInt(1, literatureID);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    protected void setObjectStatement(PreparedStatement statement, Literature literature) throws DAOException {
        try {
            if (literature.getLiteratureID() != 0) {
                statement.setInt(1, literature.getLiteratureID());
                statement.setString(2, literature.getTitle());
                statement.setInt(3, literature.getAuthorLiteratureID());
                statement.setInt(4, literature.getReviewLiteratureID());
                statement.setString(5, literature.getText());
                statement.setInt(6, literature.getAmountOfReads());
                statement.setString(7, literature.getAddingTime());
            } else {
                statement.setString(1, literature.getTitle());
                statement.setInt(2, literature.getAuthorLiteratureID());
                statement.setInt(3, literature.getReviewLiteratureID());
                statement.setString(4, literature.getText());
                statement.setInt(5, literature.getAmountOfReads());
                statement.setString(6, literature.getAddingTime());
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    protected Literature readObject(ResultSet resultSet) throws DAOException {
        Literature literature = new Literature();
        try {
            literature.setLiteratureID(resultSet.getInt("literature_id"));
            literature.setTitle(resultSet.getString("literature_title"));
            literature.setAuthorLiteratureID(resultSet.getInt("author_of_literature_ID"));
            literature.setReviewLiteratureID(resultSet.getInt("author_of_review_ID"));
            literature.setText(resultSet.getString("text_of_literature"));
            literature.setAmountOfReads(resultSet.getInt("amount_of_reads_of_literature"));
            literature.setAddingTime(resultSet.getString("literature_adding_time"));
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return literature;
    }

    @Override
    public void delete(Integer literatureId) throws DAOException {
        Literature literature = read(literatureId);
        new UserLiteratureDAO().delete(literature);
        super.delete(literatureId);
    }

    public Literature getRandomLiterature() throws DAOException {
        Literature literature = new Literature();

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM literature LIMIT 1;";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                literature = readObject(resultSet);
            } else {
                throw new DAOException("No literature entry was found!");
            }

        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
        return literature;
    }
}
