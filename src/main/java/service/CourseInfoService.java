package service;

import database.CourseInfoDb;
import model.CourseInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/courseinfo")
public class CourseInfoService {

    public CourseInfoDb db = new CourseInfoDb();

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

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addCourseInfo(CourseInfo courseInfo){
        try {
            db.saveCourseInfo(courseInfo);
            return header(Response.ok("{\"message\":\"ok\"}"));
        } catch(Exception exception){
            return header(Response.status(412).entity("{\"error\":\"" + exception.getMessage() + "\"}"));
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCourseInfo(CourseInfo courseInfo){
        try {
            db.updateCourseInfo(courseInfo);
            return header(Response.ok("{\"message\":\"ok\"}"));
        } catch(Exception exception){
            return header(Response.status(412).entity("{\"error\":\"" + exception.getMessage() + "\"}"));
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeCourseInfo(CourseInfo courseInfo){
        try {
            db.removeCourseInfo(courseInfo);
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
