package controller;

import model.CourseInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * Created by rjpvr on 10-10-2016.
 */
public class CourseInfoStringController {
    public ArrayList<CourseInfo> toArrayList(String courseInfo){
        ArrayList<CourseInfo> list = new ArrayList<>();

        String seperated = courseInfo
            .replace("\r\n", ";")
            .replace("\r", ";")
            .replace("\n", ";");

        /*
            the input string is seperated by ;
            the different object strings are seperated by ;;
            so from every string in array splitted by ;; we create one object
         */

        Arrays.asList(seperated.split(";;")).forEach(infoItem -> {
            list.add(toCourseInfo(infoItem));
        });

        return list;
    }

    public CourseInfo toCourseInfo(String infoItem){
        CourseInfo.CourseInfoBuilder ci = new CourseInfo().builder();

        Arrays.asList(infoItem.split(";")).forEach(infoVar -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            formatter = formatter.withLocale(Locale.GERMAN);

            String[] details = infoVar.split(":");

            switch(details[0]){
                case "Titel":
                    ci.titel(details[1].trim());
                    break;
                case "Cursuscode":
                    ci.cursuscode(details[1].trim());
                    break;
                case "Startdatum":
                    ci.startdatum(LocalDate.parse(details[1].trim(), formatter));
                    break;
                case "Duur":
                    ci.duur(details[1].trim());
                    break;
            }
        });

        return ci.build();
    }
}
