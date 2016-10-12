package course;

import controller.DateController;
import course.CourseController;
import course.CourseDb;
import model.Course;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by rjpvr on 12-10-2016.
 */

@Path("/quotation")
public class QuotationService {
    public CourseController courseController = new CourseController();
    private DateController dateController = new DateController();

    @GET
    @Path("{week}/{year}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCourses(
        @PathParam("week") int week,
        @PathParam("year") int year
    ){
        return getCoursesByWeekAndYear(week, year);
    }

    @GET
    @Path("{week}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCourses(
        @PathParam("week") int week
    ){
        return getCoursesByWeekAndYear(week, LocalDate.now().getYear());
    }

    private Response getCoursesByWeekAndYear(int week, int year){
        try {
            LocalDate startDate = dateController.getWeekOfYearDate(week, year);
            ArrayList<Course> courses = courseController.getCourses(startDate);
            return header(Response.ok(courses));
        } catch(Exception exception){
            return header(Response.status(412).entity("{\"error\":\"" + exception.getMessage() + "\"}"));
        }
    }

    @OPTIONS
    public Response optionHeader(){
        return header(Response.ok());
    }

    private Response header(Response.ResponseBuilder responseBuilder){
        return responseBuilder
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, Session")
            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
    }
}
