public class GaussFormula {
    private int year;
    private int easter;

    private StackImpl<Integer> stack;

    String holyMondayMonth;
    String holyTuesdayMonth;
    String holyWednesdayMonth;
    String holyThursdayMonth;
    String holyFridayMonth;
    String holySaturdayMonth;

    private String easterMonth;

    public GaussFormula(int year) {
        this.year = year;
        this.easter = 0;
        this.easterMonth = "";
        this.stack = new StackImpl<>();
    }

    private void caulculateYearPositionInA19YearMoonCycle() {
        int calc = year % 19;
        stack.push(calc);
    }

    private void calculateIfIsALeapYear() {
        int calc = year % 4;
        stack.push(calc);
    }

    private void calculateYearPositionInThe7DayWeekCycle() {
        int calc = year % 7;
        stack.push(calc);
    }

    private void calculateMoonPositionInA19YearMoonCycle(int yearPosition) {
        int calc = (19 * yearPosition + 24) % 30;
        stack.push(calc);
    }

    private void calculateResurrectionSunday(int isLeapYear, int weekCycle, int moonPosition) {
        int calc = (2 * isLeapYear + 4 * weekCycle + 6 * moonPosition + 5) % 7;
        stack.push(calc);
    }

    private String convertResurrectionSundayToDate(int moonPosition, int resurrectionSunday) {
        easter = 22 + moonPosition + resurrectionSunday;
        if (easter > 31) {
            easter = easter - 31;
            easterMonth = "April";
            stack.push(easter);
            return "The resurrection sunday is the " + easter + " of April";
        } else {
            easterMonth = "March";
            stack.push(easter);
            return "The resurrection sunday is the " + easter + " of March";
        }
    }

    public String HolyWeekDates() {
        calculateIfIsALeapYear(); //b
        calculateYearPositionInThe7DayWeekCycle(); //c
        caulculateYearPositionInA19YearMoonCycle(); //a
        int a = stack.top();
        stack.pop();
        calculateMoonPositionInA19YearMoonCycle(a); //d
        int d = stack.top();
        stack.pop();
        int c = stack.top();
        stack.pop();
        int b = stack.top();
        stack.pop();
        calculateResurrectionSunday(b, c, d); //e
        int e = stack.top();
        stack.pop();
        String aux = convertResurrectionSundayToDate(d, e); //date
        int palmSundayDay = (easter - 7);
        while (palmSundayDay < 0) {
            palmSundayDay += 31;
        }
        String palmSundayMonth = "";
        palmSundayMonth = getPalDayMonth();
        if (palmSundayMonth.equals("March")) {
            holyMondayMonth = ((palmSundayDay + 1) > 31) ? "April" : "March";
            holyTuesdayMonth = ((palmSundayDay + 2) > 31) ? "April" : "March";
            holyWednesdayMonth = ((palmSundayDay + 3) > 31) ? "April" : "March";
            holyThursdayMonth = ((palmSundayDay + 4) > 31) ? "April" : "March";
            holyFridayMonth = ((palmSundayDay + 5) > 31) ? "April" : "March";
            holySaturdayMonth = ((palmSundayDay + 6) > 31) ? "April" : "March";
        } else {
            holyMondayMonth = holyTuesdayMonth = holyWednesdayMonth = holyThursdayMonth = holyFridayMonth = holySaturdayMonth = "April";
        }
        String dates = "Holy week dates for year " + year + "\n" + "Palm Sunday: " + palmSundayDay + " " + palmSundayMonth + "\n" +
                "Holy Monday: " + normalize(palmSundayDay, 1) + " " + holyMondayMonth + "\n" +
                "Holy Tuesday: " + normalize(palmSundayDay, 2) + " " + holyTuesdayMonth + "\n" +
                "Holy Wednesday: " + normalize(palmSundayDay, 3) + " " + holyWednesdayMonth + "\n" +
                "Holy Thursday: " + normalize(palmSundayDay, 4) + " " + holyThursdayMonth + "\n" +
                "Holy Friday: " + normalize(palmSundayDay, 5) + " " + holyFridayMonth + "\n" +
                "Holy Saturday: " + normalize(palmSundayDay, 6) + " " + holySaturdayMonth + "\n" +
                "Easter: " + easter + " " + easterMonth;
        return dates;
    }

    private int normalize(int palmSunday, int holyDay) {
        if (((palmSunday + holyDay) % 31) == 0) {
            return 31;
        } else {
            return (palmSunday + holyDay) % 31;
        }
    }

    private String getPalDayMonth() {
        String palmSundayMonth;
        if (easter - 7 < 1 && easterMonth.equals("April")) {
            palmSundayMonth = "March";
        } else {
            palmSundayMonth = "April";
        }
        return palmSundayMonth;
    }
}
