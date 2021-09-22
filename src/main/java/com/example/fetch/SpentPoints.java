package com.example.fetch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SpentPoints {
	
	//@JsonProperty(value="points")
	private int points;
	
	@JsonCreator
	public SpentPoints(@JsonProperty("points") int points) {
		this.points = points;
	}
	public int getPts() {return points;}
	public void setPts(int points) {this.points = points;}
}
