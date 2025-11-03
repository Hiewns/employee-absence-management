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
                         SELECT [rid]
                               ,[created_by]
                               ,[created_time]
                               ,[from]
                               ,[to]
                               ,[reason]
                               ,[status]
                           FROM [dbo].[RequestForLeave]""";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Request rq = new Request();
                rq.setId(rs.getInt("rid"));
                rq.setCreatedby(rs.getInt("created_by"));
                rq.setCreatedtime(rs.getTimestamp("created_time"));
                rq.setFrom(rs.getDate("from"));
                rq.setTo(rs.getDate("to"));
                rq.setReason(rs.getString("reason"));
                rq.setStatus(rs.getInt("status"));
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Request model) {
        String sql = "INSERT INTO RequestForLeave([created_by], [created_time], [from], [to], reason, status) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Request model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
