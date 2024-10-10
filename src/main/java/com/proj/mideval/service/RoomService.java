package com.proj.mideval.service;

import com.proj.mideval.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to get all Room records
    public List<Room> getAllRooms() {
        String sql = "SELECT * FROM Room";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToRoom(rs));
    }

    // Method to get a specific Room entry by roomID
    public Optional<Room> getRoomById(int roomID) {
        String sql = "SELECT * FROM Room WHERE RoomID = ?";
        return jdbcTemplate.query(sql, new Object[]{roomID}, (rs, rowNum) -> mapRowToRoom(rs)).stream().findFirst();
    }

    // Method to create a new Room record
    public int createRoom(Room room) {
        String sql = "INSERT INTO Room (RoomID, RoomType, Cost, PatientID) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, room.getRoomID(), room.getRoomType(), room.getCost(), room.getPatientID());
    }

    // Method to update an existing Room record
    public int updateRoom(int roomID, Room room) {
        String sql = "UPDATE Room SET RoomType = ?, Cost = ?, PatientID = ? WHERE RoomID = ?";
        return jdbcTemplate.update(sql, room.getRoomType(), room.getCost(), room.getPatientID(), roomID);
    }

    // Method to delete a Room record
    public int deleteRoom(int roomID) {
        String sql = "DELETE FROM Room WHERE RoomID = ?";
        return jdbcTemplate.update(sql, roomID);
    }

    // Method to map a ResultSet row to a Room object
    private Room mapRowToRoom(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setRoomID(rs.getInt("RoomID"));
        room.setRoomType(rs.getString("RoomType"));
        room.setCost(rs.getInt("Cost"));
        room.setPatientID(rs.getInt("PatientID"));
        return room;
    }
}
