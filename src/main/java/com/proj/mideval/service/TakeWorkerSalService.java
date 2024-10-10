package com.proj.mideval.service;

import com.proj.mideval.model.TakeWorkerSal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class TakeWorkerSalService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Retrieve all TakeWorkerSal records
    public List<TakeWorkerSal> getAllTakeWorkerSal() {
        String sql = "SELECT * FROM TakeWorkerSal";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToTakeWorkerSal(rs));
    }

    // Retrieve a TakeWorkerSal record by workerID and date
    public Optional<TakeWorkerSal> getTakeWorkerSalByWorkerIdAndDate(String workerID, java.sql.Date date) {
        String sql = "SELECT * FROM TakeWorkerSal WHERE workerID = ? AND date = ?";
        return jdbcTemplate.query(sql, new Object[]{workerID, date}, (rs, rowNum) -> mapRowToTakeWorkerSal(rs)).stream().findFirst();
    }

    // Create a new TakeWorkerSal record
    public int createTakeWorkerSal(TakeWorkerSal takeWorkerSal) {
        String sql = "INSERT INTO TakeWorkerSal (workerID, date, time, bonus) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, takeWorkerSal.getWorkerID(), takeWorkerSal.getDate(), takeWorkerSal.getTime(), takeWorkerSal.getBonus());
    }

    // Delete a TakeWorkerSal record by workerID and date
    public int deleteTakeWorkerSal(String workerID, java.sql.Date date) {
        String sql = "DELETE FROM TakeWorkerSal WHERE workerID = ? AND date = ?";
        return jdbcTemplate.update(sql, workerID, date);
    }

    // Map a ResultSet row to a TakeWorkerSal object
    private TakeWorkerSal mapRowToTakeWorkerSal(ResultSet rs) throws SQLException {
        TakeWorkerSal takeWorkerSal = new TakeWorkerSal();
        takeWorkerSal.setWorkerID(rs.getString("workerID"));
        takeWorkerSal.setDate(rs.getDate("date"));
        takeWorkerSal.setTime(rs.getTime("time"));
        takeWorkerSal.setBonus(rs.getInt("bonus"));
        return takeWorkerSal;
    }
}
