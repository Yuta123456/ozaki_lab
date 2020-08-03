
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

public class p4_1 {
    public static void main(String args[]){
        Calendar day = Calendar.getInstance();
        ArrayList <Date> calendar_list = new ArrayList<>();
        int year = day.get(Calendar.YEAR);
        day.add(Calendar.DAY_OF_MONTH, 8 - day.get(Calendar.DAY_OF_MONTH));
        while (year == day.get(Calendar.YEAR)){
            calendar_list.add(day.getTime());
            day.add(Calendar.DAY_OF_MONTH, 7);
        }
        SimpleDateFormat calendarFormat = new SimpleDateFormat("yyyy年MM月dd日");
        System.out.println("count of sunday : " + calendar_list.size());
        for(Date d : calendar_list){
            System.out.println(calendarFormat.format(d.getTime()));
        }
    }
}
