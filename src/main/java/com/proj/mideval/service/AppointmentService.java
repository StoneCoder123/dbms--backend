package com.proj.mideval.service;

import com.proj.mideval.model.Appointment;
import com.proj.mideval.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BillService billService;

    public List<Appointment> getPreviousAppointmentsForPatient(int patientID) {
        String sql = "SELECT * FROM Appointments WHERE patientID = ? AND status = 1 AND time < ?";
        return jdbcTemplate.query(sql, new Object[]{patientID, new Timestamp(new Date().getTime())},
                (rs, rowNum) -> mapRowToAppointment(rs));
    }

    public List<Appointment> getUpcomingAppointmentsForPatient(int patientID) {
        String sql = "SELECT * FROM Appointments WHERE patientID = ? AND status = 1 AND time >= ?";
        return jdbcTemplate.query(sql, new Object[]{patientID, new Timestamp(new Date().getTime())},
                (rs, rowNum) -> mapRowToAppointment(rs));
    }

    public List<Appointment> getRequestedAppointmentsForPatient(int patientID) {
        String sql = "SELECT * FROM Appointments WHERE patientID = ? AND status = 0";
        return jdbcTemplate.query(sql, new Object[]{patientID},
                (rs, rowNum) -> mapRowToAppointment(rs));
    }

    public List<Appointment> getPreviousAppointmentsForDoctor(int doctorID) {
        String sql = "SELECT * FROM Appointments WHERE doctorID = ? AND status = 1 AND time < ?";
        return jdbcTemplate.query(sql, new Object[]{doctorID, new Timestamp(new Date().getTime())},
                (rs, rowNum) -> mapRowToAppointment(rs));
    }

    public List<Appointment> getUpcomingAppointmentsForDoctor(int doctorID) {
        String sql = "SELECT * FROM Appointments WHERE doctorID = ? AND status = 1 AND time >= ?";
        return jdbcTemplate.query(sql, new Object[]{doctorID, new Timestamp(new Date().getTime())},
                (rs, rowNum) -> mapRowToAppointment(rs));
    }

    public List<Appointment> getRequestedAppointmentsForDoctor(int doctorID) {
        String sql = "SELECT * FROM Appointments WHERE doctorID = ? AND status = 0";
        return jdbcTemplate.query(sql, new Object[]{doctorID},
                (rs, rowNum) -> mapRowToAppointment(rs));
    }

    // 1) Create an appointment request
    public int createAppointmentRequest(int patientID, int doctorID) {
        String sql = "INSERT INTO Appointments (patientID, doctorID, time, status) VALUES (?, ?, NULL, 0)";
        return jdbcTemplate.update(sql, patientID, doctorID);
    }

    // 2) Grant an appointment request, set status to 1, and create a new bill
    public int grantAppointmentRequest(int appointmentID, Date appointmentTime, int patientID, int totalCost, String type) {
        // Update the appointment status and time

        String sql = "UPDATE Appointments SET time = ?, status = 1 WHERE appointmentID = ?";
        int rowsUpdated = jdbcTemplate.update(sql, new Timestamp(appointmentTime.getTime()), appointmentID);
//        System.out.println(rowsUpdated);
        if (rowsUpdated > 0) {
            // Create a new bill using BillService
            int billID = billService.createBill(patientID, totalCost, type);
//            System.out.println(billID);
            // Link the bill to the appointment
            String updateBillSql = "UPDATE Appointments SET billID = ? WHERE appointmentID = ?";
            jdbcTemplate.update(updateBillSql, billID, appointmentID);
        }

        return rowsUpdated;
    }

    // 3) Update the time of an appointment
    public int updateAppointmentTime(int appointmentID, Date newTime) {
        // Check if the new time is in the future
        if (newTime.before(new Date())) {
            return 0; // Indicate failure because the new time is in the past
        }

        String sql = "UPDATE Appointments SET time = ? WHERE appointmentID = ?";
        return jdbcTemplate.update(sql, new Timestamp(newTime.getTime()), appointmentID);
    }


    public int deleteAppointment(int appointmentID) {
        // Check if there is an associated bill
        String queryBillSql = "SELECT billID FROM Appointments WHERE appointmentID = ?";
        Integer billID = jdbcTemplate.queryForObject(queryBillSql, new Object[]{appointmentID}, Integer.class);

        System.out.println(billID);



        // Delete the appointment
        String sql = "DELETE FROM Appointments WHERE appointmentID = ?";
        int res=jdbcTemplate.update(sql, appointmentID);
        if (billID != null) {
            // Delete the bill using the deleteBillByID controller in BillService
            billService.deleteBill(billID);
        }
        return res;
    }



    // 5) Update the prescription
    public int updateAppointmentPrescription(int appointmentID, String prescription) {
        String sql = "UPDATE Appointments SET prescription = ? WHERE appointmentID = ?";
        return jdbcTemplate.update(sql, prescription, appointmentID);
    }

    private Appointment mapRowToAppointment(ResultSet rs) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentID(rs.getInt("appointmentID"));
        appointment.setPatientID(rs.getInt("patientID"));
        appointment.setDoctorID(rs.getInt("doctorID"));
        appointment.setTime(rs.getTimestamp("time"));
        appointment.setStatus(rs.getInt("status"));
        appointment.setBillID(rs.getInt("billID"));
        appointment.setPrescription(rs.getString("prescription"));
        return appointment;
    }


    public List<Appointment> getUpcomingAppointmentsForDoctorWithBillIdStatus1(int doctorID) {
        String sql = "SELECT a.* FROM Appointments a " +
                "JOIN Bill b ON a.billID = b.BillID " +
                "WHERE a.doctorID = ? AND b.status = 1 AND a.time >= ?";
        return jdbcTemplate.query(sql, new Object[]{doctorID, new Timestamp(new Date().getTime())},
                (rs, rowNum) -> mapRowToAppointment(rs));
    }


}






