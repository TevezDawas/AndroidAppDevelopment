package com.example.a1001_first;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.Queue;

public class ConnectionPool {
    private static final int MAX_POOL_SIZE = 10; // Maximum number of connections
    private static ConnectionPool instance;
    private final Queue<SQLiteDatabase> connections = new LinkedList<>();
    private final SQLiteOpenHelper dbHelper;

    private ConnectionPool(SQLiteOpenHelper dbHelper) {
        this.dbHelper = dbHelper;
        initializePool();
    }

    public static synchronized ConnectionPool getInstance(SQLiteOpenHelper dbHelper) {
        if (instance == null) {
            instance = new ConnectionPool(dbHelper);
        }
        return instance;
    }

    private void initializePool() {
        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            SQLiteDatabase connection = dbHelper.getReadableDatabase(); // Create a new database connection
            connections.add(connection);
        }
    }

    public synchronized SQLiteDatabase getConnection() {
        while (connections.isEmpty()) {
            try {
                wait(); // Wait for a connection to be returned
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        SQLiteDatabase connection = connections.poll();
        return connection;
    }

    public synchronized void restoreConnection(SQLiteDatabase connection) {
        connections.add(connection);
        notify(); // Notify a waiting thread that a connection is available
    }

    public synchronized void closeAllConnections() {
        for (SQLiteDatabase connection : connections) {
            connection.close();
        }
        connections.clear();
    }


}
