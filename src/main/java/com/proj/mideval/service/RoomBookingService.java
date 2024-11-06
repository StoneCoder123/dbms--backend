package com.proj.mideval.service;

import com.proj.mideval.model.RoomBooking;
import com.proj.mideval.model.RoomBookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Service
public class RoomBookingService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BillService billService;

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

    public int createRoomBooking(RoomBookingRequest roomBookingRequest) {
        // First, create a bill with type "RoomBooking"
        int billID = billService.createBill(roomBookingRequest.getPatientID(), roomBookingRequest.getTotalCost(), "RoomBooking");

        // SQL query to insert a new RoomBooking record
        String sql = "INSERT INTO RoomBooking (RoomID, PatientID, BookFrom, BookTill, NumDays, BillID) VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, roomBookingRequest.getRoomID());
            ps.setInt(2, roomBookingRequest.getPatientID());
            ps.setDate(3, new java.sql.Date(roomBookingRequest.getBookFrom().getTime()));
            ps.setDate(4, new java.sql.Date(roomBookingRequest.getBookTill().getTime()));
            ps.setInt(5, roomBookingRequest.getNumDays());
            ps.setInt(6, billID);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue(); // Return the generated RoomBooking ID
    }


    public int updateRoomBooking(int roomBookingID, RoomBookingRequest roomBookingRequest) {
        String fetchBillIdSql = "SELECT BillID FROM RoomBooking WHERE RoomBookingID = ?";
        Integer billID = jdbcTemplate.queryForObject(fetchBillIdSql, Integer.class, roomBookingID);

        // Check if a BillID was found
        if (billID == null) {
            System.out.println("No BillID found for RoomBookingID: " + roomBookingID);
            return 0; // or throw an exception depending on your use case
        }
        // Update room booking details
        String sql = "UPDATE RoomBooking SET RoomID = ?, PatientID = ?, BookFrom = ?, BookTill = ?, NumDays = ? WHERE RoomBookingID = ?";
        int rowsAffected = jdbcTemplate.update(sql, roomBookingRequest.getRoomID(), roomBookingRequest.getPatientID(),
                new java.sql.Date(roomBookingRequest.getBookFrom().getTime()),
                new java.sql.Date(roomBookingRequest.getBookTill().getTime()),
                roomBookingRequest.getNumDays(), roomBookingID);

        // After updating the room booking, update the bill
        if (rowsAffected > 0) {
            // You can calculate the new cost based on the updated booking details.
            int newCost = roomBookingRequest.getTotalCost(); // Assuming `roomCost` is in the request.
            billService.updateBill(billID, newCost); // Update the bill with the new cost
            billService.updateBillStatus(billID,0);
        }

        return rowsAffected;
    }


    public boolean deleteRoomBooking(int id) {
        String sql = "DELETE FROM RoomBooking WHERE RoomBookingID = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
