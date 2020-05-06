package cz.mendelu.pef.ei.pjj.projekt.hra.domain.Timer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Timer {
    private Calendar cas;
    private Calendar druhycas;

    private static Timer ourInstance = new Timer();

    public static Timer getInstance() {
        return ourInstance;
    }

    private Timer() {
        cas = Calendar.getInstance();
        DateFormat formatData = new SimpleDateFormat("d.MMMM yyyy H:mm");
        System.out.println(formatData.format(cas.getTime()));
    }

    public void rozdil() {
        druhycas = Calendar.getInstance();
        druhycas.setTimeInMillis(1000);
        System.out.println(druhycas.compareTo(cas));
        System.out.println(druhycas.getTimeInMillis());
    }

}
