package service;

import database.CourseDb;
import model.Course;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by rjpvr on 11-10-2016.
 */

@Path("/registration")
public class CourseService {


    public CourseDb db = new CourseDb();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCourses(){
        try {
            ArrayList<Course> list = db.getCourses();
            return header(Response.ok(list));
        } catch(Exception exception){
            return header(Response.status(412).entity("{\"error\":\"" + exception.getMessage() + "\"}"));
        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addCourse(Course course){
        try {
            db.saveCourse(course);
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
            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
    }
}
