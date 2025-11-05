/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Request;
import model.iam.User;

/**
 *
 * @author ultsu
 */
public class RequestDBContext extends DBContext<Request> {

    @Override
    public ArrayList<Request> list() {
        ArrayList<Request> requests = new ArrayList<>();
        try {
            String sql = """
                         SELECT r.[rid]
                                ,r.[created_by]
                                ,r.[created_time]
                                ,r.[from]
                                ,r.[to]
                                ,r.[reason]
                                ,r.[status]
                                ,r.[reviewed_by]
                                ,r.[reviewed_time]
                                ,creator.displayname AS creator_name
                                ,reviewer.displayname AS reviewer_name
                           FROM [dbo].[RequestForLeave] r
                         LEFT JOIN [User] creator ON r.created_by = creator.uid
                         LEFT JOIN [User] reviewer ON r.reviewed_by = reviewer.uid""";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Request rq = new Request();
                rq.setId(rs.getInt("rid"));
                rq.setCreatedby(rs.getInt("created_by"));
                rq.setCreatedtime(rs.getTimestamp("created_time"));
                rq.setFrom(rs.getDate("from"));
                rq.setTo(rs.getDate("to"));
                rq.setReason(rs.getString("reason"));
                rq.setStatus(rs.getInt("status"));
                rq.setReviewedby(rs.getInt("reviewed_by"));
                rq.setReviewedtime(rs.getTimestamp("reviewed_time"));

                User creator = new User();
                creator.setDisplayname(rs.getString("creator_name"));
                rq.setCreator(creator);

                if (rs.getString("reviewer_name") != null) {
                    User reviewer = new User();
                    reviewer.setDisplayname(rs.getString("reviewer_name"));
                    rq.setReviewer(reviewer);
                }

                requests.add(rq);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return requests;
    }

    @Override
    public Request get(int id) {
        Request rq = null;
        try {
            String sql = "SELECT rid, created_by, created_time, [from], [to], reason, status, reviewed_by, reviewed_time FROM RequestForLeave WHERE rid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                rq = new Request();
                rq.setId(rs.getInt("rid"));
                rq.setCreatedby(rs.getInt("created_by"));
                rq.setCreatedtime(rs.getTimestamp("created_time"));
                rq.setFrom(rs.getDate("from"));
                rq.setTo(rs.getDate("to"));
                rq.setReason(rs.getString("reason"));
                rq.setStatus(rs.getInt("status"));
                rq.setReviewedby(rs.getInt("reviewed_by"));
                rq.setReviewedtime(rs.getTimestamp("reviewed_time"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return rq;
    }

    @Override
    public void insert(Request model) {
        String sql = "INSERT INTO RequestForLeave([created_by], [created_time], [from], [to], reason, status) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stm = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setInt(1, model.getCreatedby());
            stm.setTimestamp(2, model.getCreatedtime());
            stm.setDate(3, model.getFrom());
            stm.setDate(4, model.getTo());
            stm.setString(5, model.getReason());
            stm.setInt(6, model.getStatus());
            stm.executeUpdate();

            // Retrieve the auto-generated ID
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                model.setId(generatedId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void update(Request model) {
        String sql = "UPDATE RequestForLeave SET status = ?, reviewed_by = ?, reviewed_time = ? WHERE rid = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getStatus());
            
            if (model.getReviewedby() == null || model.getReviewedby() == 0) {
                stm.setNull(2, java.sql.Types.INTEGER);
            } else {
                stm.setInt(2, model.getReviewedby());
            }
            System.out.println("ReviewedBy = " + model.getReviewedby());

            
            stm.setTimestamp(3, model.getReviewedtime());
            stm.setInt(4, model.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE,
                    "Error updating request ID: " + model.getId(), ex);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void delete(Request model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
