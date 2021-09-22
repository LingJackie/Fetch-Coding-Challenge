package com.example.fetch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SpendResponse {
	private String payer;
	private int points;


	public SpendResponse(String payer, int points) {
		this.payer = payer;
		this.points = points;
	}

	public String getPayer() {
		return payer;
	}
	public int getPoints() {
		return points;
	}
	public void addPoints(int pts) {
		points += pts;
	}

}
