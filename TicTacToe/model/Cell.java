package com.aurionpro.model;

public class Cell {
	private String mark = "";
	private Boolean isMarked = false;
	
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		if (!this.isMarked) {
            this.mark = mark;
            this.isMarked = true;;
        } else {
            throw new IllegalStateException("Cell is already marked and cannot be changed.");
        }
	}
	public Boolean getMarkStatus() {
		return isMarked;
	}
}
