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
	    //if(str.trim().equals("")||str.trim().equals(null))
		if(str.trim().equals("")||str.trim().isEmpty())
	        return true;
	    else
	        return false;
	}
	
	public static boolean IsPhoneNo(String str) {
		if(str.length() >= 6 && str.length() <= 11 ) {
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
		
		if(matchFound) {
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
	
//	public static boolean IsLetter(String str) {
//		boolean bool = false;
//		for(int i=0;i<str.length();i++) {
//			if(Character.isLetter(str.charAt(i))) {
//				bool = true;
//			} else {
//				bool = false;
//			}
//		}
//		return bool;
//	}
	
	public static boolean IsLetter(String str) {
	    if (str == null || str.isEmpty()) {
	        return false; // null / ဗလာ string ဆိုရင် false
	    }

	    for (int i = 0; i < str.length(); i++) {
	        if (!Character.isLetter(str.charAt(i))) {
	            return false; // letter မဟုတ်တာ တစ်လုံးခုရှိရင် false
	        }
	    }

	    return true; // အကုန် letter ဖြစ်မှသာ true
	}

	
//	public static boolean IsAllDigit(String str) {
//		boolean bool = false;
//		for(int i=0;i<str.length();i++) {
//			if(Character.isDigit(str.charAt(i))) {
//				bool = true;
//			} else {
//				bool = false;
//			}
//		}
//		return bool;
//	}
//	
	
	public static boolean IsAllDigit(String str) {
	    // null / ဗလာ string ဆိုရင် false
	    if (str == null || str.isEmpty()) {
	        return false;
	    }

	    // စာလုံးတိုင်း digit ဖြစ်မဖြစ် စစ်မယ်
	    for (int i = 0; i < str.length(); i++) {
	        if (!Character.isDigit(str.charAt(i))) {
	            return false; // digit မဟုတ်တာတစ်လုံးခု တွေ့တာနဲ့ false
	        }
	    }

	    return true; // အကုန် digit ဖြစ်မှသာ true
	}

	public static boolean IsValidPrice(String str) {
		if(str.length() < 7) {
			return true;
		} else {
			return false;
		}
	}
}

