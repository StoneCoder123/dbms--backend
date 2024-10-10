package com.proj.mideval.controller;

import com.proj.mideval.model.Room;
import com.proj.mideval.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{roomID}")
    public ResponseEntity<Room> getRoomById(@PathVariable int roomID) {
        Optional<Room> room = roomService.getRoomById(roomID);
        return room.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        int result = roomService.createRoom(room);
        if (result > 0) {
            return ResponseEntity.status(201).body(room);  // 201 Created
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{roomID}")
    public ResponseEntity<Room> updateRoom(@PathVariable int roomID, @RequestBody Room room) {
        int result = roomService.updateRoom(roomID, room);
        if (result > 0) {
            return ResponseEntity.ok(room);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{roomID}")
    public ResponseEntity<Void> deleteRoom(@PathVariable int roomID) {
        int result = roomService.deleteRoom(roomID);
        if (result > 0) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }
}
