package by.fpmibsu.LIBRARY.DAO;

import java.sql.*;
import by.fpmibsu.LIBRARY.entity.Complaints;
import by.fpmibsu.LIBRARY.entity.Literature;
import by.fpmibsu.LIBRARY.entity.User;
import by.fpmibsu.LIBRARY.exception.DAOException;
public class ComplaintsDAO extends AbstractDAO<Complaints, Integer> {

    @Override
    protected String getCreateQuery(Complaints Complaints) {
        return "INSERT INTO complaints (author_id, text) VALUES (?,?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE complaints SET author_id = ?, text = ? WHERE complaint_id = ?;";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT * FROM complaints WHERE complaint_id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM complaints WHERE complaint_id = ?";
    }

    @Override
    protected String getCountRowsQuery() {
        return "SELECT count(*) FROM complaints;";
    }

    @Override
    protected void setIdStatement(PreparedStatement statement, Integer complaintID) throws DAOException {
        try {
            statement.setInt(1, complaintID);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    protected void setObjectStatement(PreparedStatement statement, Complaints complaints) throws DAOException {
        try {
            if (complaints.getComplaintID() != 0) {
                statement.setInt(1, complaints.getComplaintID());
                statement.setInt(2, complaints.getAuthorOfComplaintID());
                statement.setString(3,complaints.getTextOfComplaint());
            } else {
                statement.setInt(1, complaints.getAuthorOfComplaintID());
                statement.setString(2,complaints.getTextOfComplaint());
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    protected Complaints readObject(ResultSet resultSet) throws DAOException {
        Complaints complaint = new Complaints();
        try {
            complaint.setComplaintID(resultSet.getInt("complaint_id"));
            complaint.setAuthorOfComplaintID(resultSet.getInt("author_of_complaintID"));
            complaint.setTextOfComplaint(resultSet.getString("text_of_complaint"));
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return complaint;
    }

    @Override
    public void delete(Integer complaintId) throws DAOException {
        Complaints complaint = read(complaintId);
        String query = "DELETE FROM complaints WHERE complaint_id = " + complaint.getComplaintID() + ";";
        executeQuery(query);
        super.delete(complaintId);
    }

    private void executeQuery(String query) throws DAOException {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {
             statement.execute(query);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    public Complaints getRandomComplaint() throws DAOException {
        Complaints complaint = new Complaints();

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM complaints LIMIT 1;";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                complaint = readObject(resultSet);
            } else {
                throw new DAOException("No complaint entry was found!");
            }

        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
        return complaint;
    }
}
