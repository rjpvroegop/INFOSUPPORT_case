package courseinfo;

import controller.CourseInfoStringController;
import controller.DateController;
import courseinfo.CourseInfoDb;
import model.CourseInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.ArrayList;

@Path("/courseinfo")
public class CourseInfoService {

    public DateController dateController = new DateController();
    public CourseInfoController db = new CourseInfoController();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCourseInfo(){
        try {
            ArrayList<CourseInfo> list = db.getCourseInfo();
            return header(Response.ok(list));
        } catch(Exception exception){
            return header(Response.status(412).entity("{\"error\":\"" + exception.getMessage() + "\"}"));
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCourseInfo(
        @PathParam("id") int id
    ){
        try {
            CourseInfo info = db.getCourseInfo(id);
            return header(Response.ok(info));
        } catch(Exception exception){
            return header(Response.status(412).entity("{\"error\":\"" + exception.getMessage() + "\"}"));
        }
    }

    @GET
    @Path("week/{week}/{year}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCourses(
        @PathParam("week") int week,
        @PathParam("year") int year
    ){
        return getCoursesByWeekAndYear(week, year);
    }

    @GET
    @Path("week/{week}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCourses(
        @PathParam("week") int week
    ){
        return getCoursesByWeekAndYear(week, LocalDate.now().getYear());
    }

    private Response getCoursesByWeekAndYear(int week, int year){
        try {
            LocalDate startDate = dateController.getWeekOfYearDate(week, year);
            ArrayList<CourseInfo> courses = db.getCourseInfo(startDate);
            return header(Response.ok(courses));
        } catch(Exception exception){
            return header(Response.status(412).entity("{\"error\":\"" + exception.getMessage() + "\"}"));
        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.TEXT_PLAIN})
    public Response addCourseInfo(String courseInfo){
        try {
            CourseInfoStringController cisc = new CourseInfoStringController();
            db.saveCourseInfo(cisc.toArrayList(courseInfo));
            return header(Response.ok("{\"message\":\"ok\"}"));
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
            .header("Access-Control-Allow-Methods", "GET, POST").build();
    }
}
