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
    String whatIsWrongWithTheFormat = "";

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
            try {
                list.add(toCourseInfo(infoItem));
            } catch(Exception e){
                e.printStackTrace();
                throw e;
            }
        });

        return list;
    }

    public CourseInfo toCourseInfo(String infoItem){
        if(!isValidFormat(infoItem)){
            throw new IllegalArgumentException("Wrong format in importing data: " + whatIsWrongWithTheFormat);
        }

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

    public boolean isValidFormat(String item){
        boolean isCorrect = true;

        String[] rows = item.split(";");
        if(rows.length != 4){
            whatIsWrongWithTheFormat += "The number of rows is incorrect: " + rows.length + "\r\n";
            isCorrect = false;
        } else {

            String titel = rows[0].split(":")[0];
            String code = rows[1].split(":")[0];
            String duur = rows[2].split(":")[0];
            String start = rows[3].split(":")[0];

            if (!titel.equals("Titel")) {
                whatIsWrongWithTheFormat += "The keyword Titel was not the first argument in the import item\n";
                isCorrect = false;
            }

            if (!code.equals("Cursuscode")) {
                whatIsWrongWithTheFormat += "The keyword Cursuscode was not the second argument in the import item\n";
                isCorrect = false;
            }

            if (!duur.equals("Duur")) {
                whatIsWrongWithTheFormat += "The keyword Duur was not the third argument in the import item\n";
                isCorrect = false;
            }

            if (!start.equals("Startdatum")) {
                whatIsWrongWithTheFormat += "The keyword Startdatum was not the fourth argument in the import item\n";
                isCorrect = false;
            }
        }


        return isCorrect;
    }
}
