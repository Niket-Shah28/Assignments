package com.aurionpro.model;

public class Player {
	private String mark;
	private final int index;

	public Player(String mark, int index) {
		this.mark = mark;
		this.index = index;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public int getIndex() {
		return index;
	}
}
