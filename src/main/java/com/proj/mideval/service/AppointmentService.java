package com.proj.mideval.service;

import com.proj.mideval.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    // Create an appointment request (patient requests an appointment)
    public int createAppointmentRequest(int patientID, int doctorID) {
        String sql = "INSERT INTO Appointments (patientID, doctorID, time, status) VALUES (?, ?, NULL, 0)";
        return jdbcTemplate.update(sql, patientID, doctorID);
    }

    // Grant an appointment (doctor assigns a time and updates the status)
    public int grantAppointment(int appointmentID, Date appointmentTime) {
        String sql = "UPDATE Appointments SET time = ?, status = 1 WHERE appointmentID = ?";
        return jdbcTemplate.update(sql, new Timestamp(appointmentTime.getTime()), appointmentID);
    }

    public int updateAppointmentStatus(int appointmentID, int status) {
        String sql = "UPDATE Appointments SET status = ? WHERE appointmentID = ?";
        return jdbcTemplate.update(sql, status, appointmentID);
    }

    private Appointment mapRowToAppointment(ResultSet rs) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentID(rs.getInt("appointmentID"));
        appointment.setPatientID(rs.getInt("patientID"));
        appointment.setDoctorID(rs.getInt("doctorID"));
        appointment.setTime(rs.getTimestamp("time"));
        appointment.setStatus(rs.getInt("status"));
        return appointment;
    }
}
