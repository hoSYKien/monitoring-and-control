package com.example.hosykien211604449;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;

import com.example.apphiep.Energy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseHelper {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public DatabaseHelper(Context context) {
        // context nếu cần
    }

    /**
     * Thiết lập kết nối đến SQL Server
     */
    public static Connection connect() {
        Connection conn = null;
        String ip = "171.244.38.118";
        String port = "1433";
        String dbName = "iot2024";
        String username = "NCKH_2025";
        String password = "12345678";
        String instance = "SQLEXPRESS";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String url = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";instance=" + instance + ";databaseName=" + dbName
                    + ";user=" + username + ";password=" + password + ";";
            conn = DriverManager.getConnection(url);
            Log.i("DBConnection", "Connected successfully");
        } catch (ClassNotFoundException | SQLException e) {
            Log.e("DBConnection", "Connection error: " + e.getMessage());
        }
        return conn;
    }

    public interface Callback<T> { void onResult(T data); }

    public void setEnergyMeasurements(Energy energy, Callback<Energy> callback) {
        executorService.execute(() -> {
            try (Connection conn = connect();
                 PreparedStatement ps = conn.prepareStatement("INSERT INTO EnergyMeasurements (Voltage, Currents, Powers, Energy, Frequency, Device) VALUES (?, ?, ?, ?, ?, ?)")) {

                // Set các giá trị vào PreparedStatement từ đối tượng Energy
                ps.setDouble(1, energy.getVoltage());
                ps.setDouble(2, energy.getCurrents());
                ps.setDouble(3, energy.getPowers());
                ps.setDouble(4, energy.getEnergy());
                ps.setDouble(5, energy.getFrequency());
                ps.setString(6, energy.getDevice());

                // Thực thi câu lệnh chèn
                int affectedRows = ps.executeUpdate();

                if (affectedRows > 0) {
                    // Chèn thành công, trả lại đối tượng Energy
                    mainHandler.post(() -> callback.onResult(energy));
                } else {
                    // Nếu không có bản ghi nào được chèn, có thể trả về null hoặc thông báo lỗi tùy thuộc yêu cầu
                    mainHandler.post(() -> callback.onResult(null));
                }
            } catch (Exception e) {
                Log.e("DBHelper", "Error inserting energy measurement: " + e.getMessage());
                // Trả về null nếu có lỗi
                mainHandler.post(() -> callback.onResult(null));
            }
        });
    }



}
