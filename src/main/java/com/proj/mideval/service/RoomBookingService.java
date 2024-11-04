package com.proj.mideval.service;

import com.proj.mideval.model.RoomBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class RoomBookingService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<RoomBooking> roomBookingRowMapper = new RowMapper<RoomBooking>() {
        @Override
        public RoomBooking mapRow(ResultSet rs, int rowNum) throws SQLException {
            RoomBooking roomBooking = new RoomBooking();
            roomBooking.setRoomBookingID(rs.getInt("RoomBookingID"));
            roomBooking.setRoomID(rs.getInt("RoomID"));
            roomBooking.setPatientID(rs.getInt("PatientID"));
            roomBooking.setBookFrom(rs.getDate("BookFrom"));
            roomBooking.setBookTill(rs.getDate("BookTill"));
            roomBooking.setNumDays(rs.getInt("NumDays"));
            return roomBooking;
        }
    };

    public List<RoomBooking> getAllRoomBookings() {
        String sql = "SELECT * FROM RoomBooking";
        return jdbcTemplate.query(sql, roomBookingRowMapper);
    }

    public Optional<RoomBooking> getRoomBookingById(int id) {
        String sql = "SELECT * FROM RoomBooking WHERE RoomBookingID = ?";
        return jdbcTemplate.query(sql, roomBookingRowMapper, id).stream().findFirst();
    }

    public List<RoomBooking> getRoomBookingByPatientID(int patientID) {
        String sql = "SELECT * FROM RoomBooking WHERE PatientID = ?";
        return jdbcTemplate.query(sql, roomBookingRowMapper, patientID);
    }

    public RoomBooking createRoomBooking(RoomBooking roomBooking) {
        String sql = "INSERT INTO RoomBooking (RoomID, PatientID,  BookFrom, BookTill, NumDays) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,roomBooking.getRoomID(), roomBooking.getPatientID(),
                roomBooking.getBookFrom(), roomBooking.getBookTill(), roomBooking.getNumDays());
        return roomBooking;
    }

    public Optional<RoomBooking> updateRoomBooking(int id, RoomBooking roomBookingDetails) {
        String sql = "UPDATE RoomBooking SET RoomID = ?, PatientID = ?, BookFrom = ?, BookTill = ?, NumDays = ? WHERE RoomBookingID = ?";
        int result = jdbcTemplate.update(sql, roomBookingDetails.getRoomID(), roomBookingDetails.getPatientID(),
                roomBookingDetails.getBookFrom(), roomBookingDetails.getBookTill(), roomBookingDetails.getNumDays(),id);
        return result > 0 ? Optional.of(roomBookingDetails) : Optional.empty();
    }

    public boolean deleteRoomBooking(int id) {
        String sql = "DELETE FROM RoomBooking WHERE RoomBookingID = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
