package pl.js.efecteback.exceptions;

import java.sql.Timestamp;

public final class ErrorResponse {
    private Timestamp timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorResponse(Timestamp timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    @Override
    public String toString(){
        return timestamp.toString() + ": " + status + " " + error.toString() + ", path: " + path + "  " + message;
    }
}
