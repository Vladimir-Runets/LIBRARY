package by.fpmibsu.LIBRARY.DAO;

import java.sql.*;

import by.fpmibsu.LIBRARY.entity.Complaints;
import by.fpmibsu.LIBRARY.entity.Reviews;
import by.fpmibsu.LIBRARY.entity.User;
import by.fpmibsu.LIBRARY.exception.DAOException;
public class ReviewsDAO extends AbstractDAO<Reviews, Integer> {

    @Override
    protected String getCreateQuery(Reviews literature) {
        return "INSERT INTO reviews (reactions, author_of_review_id) VALUES (?,?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE reviews SET reactions = ?, author_of_review_id = ? WHERE review_id = ?;";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT * FROM reviews WHERE review_id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM reviews WHERE review_id = ?";
    }

    @Override
    protected String getCountRowsQuery() {
        return "SELECT count(*) FROM reviews;";
    }

    @Override
    protected void setIdStatement(PreparedStatement statement, Integer reviewID) throws DAOException {
        try {
            statement.setInt(1, reviewID);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    protected void setObjectStatement(PreparedStatement statement, Reviews review) throws DAOException {
        try {
            if (review.getReviewID() != 0) {
                statement.setInt(1, review.getReviewID());
                statement.setString(2, review.getReactions());
                statement.setInt(3, review.getAuthorOfReviewID());
            } else {
                statement.setString(1, review.getReactions());
                statement.setInt(2, review.getAuthorOfReviewID());
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    protected Reviews readObject(ResultSet resultSet) throws DAOException {
        Reviews review = new Reviews();
        try {
            review.setReviewID(resultSet.getInt("review_id"));
            review.setReactions(resultSet.getString("review_reactions"));
            review.setAuthorOfReviewID(resultSet.getInt("author_of_review_id"));
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return review;
    }

    @Override
    public void delete(Integer reviewId) throws DAOException {
        Reviews review = read(reviewId);
        String query = "DELETE FROM complaints WHERE review_id = " + review.getReviewID() + ";";
        executeQuery(query);
        super.delete(reviewId);
    }

    private void executeQuery(String query) throws DAOException {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {
             statement.execute(query);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    public Reviews getRandomReview() throws DAOException {
        Reviews review = new Reviews();

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM reviews LIMIT 1;";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                review = readObject(resultSet);
            } else {
                throw new DAOException("No review entry was found!");
            }

        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
        return review;
    }
}
