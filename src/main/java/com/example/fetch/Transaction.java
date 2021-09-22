package com.example.fetch;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction implements Comparable<Transaction>{

	private String payer;
	private int points;
	private String timestamp;

	public Transaction(String payer, int points, String timestamp) {
		this.payer = payer;
		this.points = points;
		this.timestamp = timestamp;
	}

	public String getPayer() {
		return payer;
	}
	public int getPoints() {
		return points;
	}

	public String getTimestamp() {
		return timestamp;
	}
	

	//sorts by date descending
	@Override
	public int compareTo(Transaction o) {
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		try {
            return (f.parse(timestamp)).compareTo(f.parse(o.getTimestamp()));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
	}
}

