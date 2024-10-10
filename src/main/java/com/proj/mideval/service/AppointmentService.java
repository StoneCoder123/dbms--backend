package com.proj.mideval.service;

import com.proj.mideval.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to get all appointments
    public List<Appointment> getAllAppointments() {
        String sql = "SELECT * FROM Appointment";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToAppointment(rs));
    }

    // Method to get an appointment by ID
    public Optional<Appointment> getAppointmentById(int appointmentID) {
        String sql = "SELECT * FROM Appointment WHERE AppointmentID = ?";
        return jdbcTemplate.query(sql, new Object[]{appointmentID}, (rs, rowNum) -> mapRowToAppointment(rs)).stream().findFirst();
    }

    // Method to create a new appointment
    public int createAppointment(Appointment appointment) {
        String sql = "INSERT INTO Appointment (PatientID, DoctorID, Date, Time) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, appointment.getPatientID(), appointment.getDoctorID(),
                new java.sql.Date(appointment.getDate().getTime()), appointment.getTime());
    }

    // Method to update an existing appointment
    public int updateAppointment(int appointmentID, Appointment appointment) {
        String sql = "UPDATE Appointment SET PatientID = ?, DoctorID = ?, Date = ?, Time = ? WHERE AppointmentID = ?";
        return jdbcTemplate.update(sql, appointment.getPatientID(), appointment.getDoctorID(),
                new java.sql.Date(appointment.getDate().getTime()), appointment.getTime(), appointmentID);
    }

    // Method to delete an appointment
    public int deleteAppointment(int appointmentID) {
        String sql = "DELETE FROM Appointment WHERE AppointmentID = ?";
        return jdbcTemplate.update(sql, appointmentID);
    }

    // Method to map a ResultSet row to an Appointment object
    private Appointment mapRowToAppointment(ResultSet rs) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentID(rs.getInt("AppointmentID"));
        appointment.setPatientID(rs.getInt("PatientID"));
        appointment.setDoctorID(rs.getInt("DoctorID"));
        appointment.setDate(rs.getDate("Date")); // Ensure the SQL type matches
        appointment.setTime(rs.getString("Time"));
        return appointment;
    }
}
