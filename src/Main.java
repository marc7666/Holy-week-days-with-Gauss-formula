public class Main {
    public static void main(String[] args) {
        GaussFormula gaussFormula = new GaussFormula(2024);
        String dates = gaussFormula.HolyWeekDates();
        System.out.println(dates);
    }
}