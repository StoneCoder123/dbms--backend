package com.proj.mideval.controller;

import com.proj.mideval.model.RoomBooking;
import com.proj.mideval.model.RoomBookingRequest;
import com.proj.mideval.service.RoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roomBookings")
public class RoomBookingController {

    @Autowired
    private RoomBookingService roomBookingService;


    @GetMapping
    public ResponseEntity<List<RoomBooking>> getAllRoomBookings() {
        List<RoomBooking> roomBookings = roomBookingService.getAllRoomBookings();
        return new ResponseEntity<>(roomBookings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomBooking> getRoomBookingById(@PathVariable int id) {
        Optional<RoomBooking> roomBooking = roomBookingService.getRoomBookingById(id);
        return roomBooking.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/patient/{id}")
    public ResponseEntity<List<RoomBooking>> getRoomBookingByPatientID(@PathVariable int id) {
        List<RoomBooking> roomBookings = roomBookingService.getRoomBookingByPatientID(id);
        return new ResponseEntity<>(roomBookings, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> createRoomBooking(@RequestBody RoomBookingRequest roomBookingRequest) {
        int roomBookingID = roomBookingService.createRoomBooking(roomBookingRequest);
        return ResponseEntity.ok(roomBookingID);
    }


    @PutMapping("/{roomBookingID}")
    public ResponseEntity<Void> updateRoomBooking(
            @PathVariable int roomBookingID,
            @RequestBody RoomBookingRequest roomBookingRequest) {

        int rowsAffected = roomBookingService.updateRoomBooking(roomBookingID, roomBookingRequest);
        return rowsAffected > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomBooking(@PathVariable int id) {
        boolean isDeleted = roomBookingService.deleteRoomBooking(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
