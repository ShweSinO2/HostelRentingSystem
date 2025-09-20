package HostelRentingSystem;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.lang.String.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checking {
	public static boolean IsValidName(String str)
	{
	    if(IsNull(str)||str.startsWith(""))
	        return false;
	    if(!IsLetter(str))
	        return false;
	    return true;
	}
	
	public static boolean IsNull(String str)
	{
	    if(str.trim().equals("")||str.trim().equals(null))
	        return true;
	    else
	        return false;
	}
	
	public static boolean IsPhoneNo(String str) {
		if(str.length() <= 12) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean IsPassNo(String str) {	
	    String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(str);
	    boolean matchFound = matcher.find();
		
		if(str.length() >= 8 && matchFound) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean IsNrc(String str) {
		if(str.length() == 6) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean IsLetter(String str) {
		boolean bool = false;
		for(int i=0;i<str.length();i++) {
			if(Character.isLetter(str.charAt(i))) {
				bool = true;
			} else {
				bool = false;
			}
		}
		return bool;
	}
	
	public static boolean IsAllDigit(String str) {
		boolean bool = false;
		for(int i=0;i<str.length();i++) {
			if(Character.isDigit(str.charAt(i))) {
				bool = true;
			} else {
				bool = false;
			}
		}
		return bool;
	}
	
	public static boolean IsValidPrice(String str) {
		if(str.length() < 7) {
			return true;
		} else {
			return false;
		}
	}
}

