package HostelRentingSystem;
import java.time.LocalDate;
import java.util.*;

public class MyDate {
	
	public String getStartDate(LocalDate date) {
		LocalDate ld1 = date.plusDays(7);
		System.out.println("Local Date => "+ld1);
		return ld1.toString();
	}

	public String getEndDate(String startDate) {
		LocalDate ld2 = LocalDate.parse(startDate);
		System.out.println("Local Date => "+ld2.plusMonths(1));
		return ld2.plusMonths(1).toString();
	}
}
