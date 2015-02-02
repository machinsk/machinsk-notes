package com.example.machinsk_notes;

import java.util.Calendar;
import java.util.Comparator;

//https://community.oracle.com/thread/1155768 Feb 1 2015

public class DateSort implements Comparator {

	@Override
	public int compare(Object date1, Object date2) {
		return ((Calendar) date1).compareTo((Calendar) date2);
	}
	
}
