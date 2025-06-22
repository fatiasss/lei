package aula05;
import java.util.ArrayList;

import myutils.DateYMD;

import java.time.LocalDate;
public class Calendar {
    private int year;
    private int firstweekday;
    private ArrayList<ArrayList<ArrayList<Integer>>> CalendarArray = new ArrayList<>();

    public Calendar(int year, int firstweekday){
        this.year = year;
        this.firstweekday = firstweekday;
        this.CalendarArray = new ArrayList<>();
        generateCalendar();

    }
    public void generateCalendar(){
        int currentWeekday = firstweekday;
        int currentmonth = 1;
        ArrayList<ArrayList<Integer>> monthDays = new ArrayList<>();
        ArrayList<Integer> weekDays = new ArrayList<>();
        for(currentmonth = 1; currentmonth < 13; currentmonth++){
            monthDays = new ArrayList<>();
            weekDays = new ArrayList<>();
            for(int i = 1; i<firstweekday; i++){
                weekDays.add(0);
            }
            for(int day = 1; day<= DateYMD.monthDays(currentmonth, year); day++){
                    weekDays.add(day);
                    currentWeekday = (currentWeekday % 7) + 1;
                    if (currentWeekday==1 || day==DateYMD.monthDays(currentmonth, year)){
                        monthDays.add(new ArrayList<>(weekDays));
                        weekDays = new ArrayList<Integer>();
                    }

            }
            CalendarArray.add(monthDays);
            firstweekday=currentWeekday;
        }
    }
    public void printCalendar(){
        String[] weekdays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for(int month = 0; month < 12; month++){   
            System.out.println("\nMonth:" + months[month]);
            System.out.println();
            for (String weekday : weekdays) {
                System.out.printf("%4s", weekday);
            }
            System.out.println();
            ArrayList<ArrayList<Integer>> monthDays = CalendarArray.get(month);
            for (ArrayList<Integer> week : monthDays) {
                for (Integer day : week) {
                    if (day == 0) {
                        System.out.printf("%4s", " ");
                    } else {
                        System.out.printf("%4d", day);
                    }
                }
                System.out.println();
            }
        }


    }
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(java.time.Year.now().getValue(), 01, 01);
        int dayOfWeek = localDate.getDayOfWeek().getValue();
        if(dayOfWeek==7){dayOfWeek=0;}
        System.out.print(localDate);
        Calendar cal = new Calendar(java.time.Year.now().getValue(), dayOfWeek+1);
        cal.printCalendar();
    }
    
}
