package cz.maxikek.netitletovatelne;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("games")

public class GameResource {

    @Inject
    private GameManager manager;

    @GET
    public Response getAll() {
        return Response.ok(manager.getGameList()).build();
    }

    @GET
    @Path("{id}")
    public Response getGame(@PathParam("id") int id) {
        return  Response.ok(manager.getGame(id)).build();
    }

    @POST
    public Response createGame(GameDetail gameDetail){
        if(!manager.create(gameDetail))
            return Response.status(400).build();

        return Response.ok(gameDetail).build();
    }

    @PUT
    @Path("{id}")
    public Response editGame(@PathParam("id") int id, GameDetail gameDetail) {
        if(!manager.editGame(id, gameDetail)) {
            return Response.ok().build();
        }
        else return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    public Response eraseGame(@PathParam("id") int id) {
        if(manager.removeGame(id)){
            return Response.ok("Game removed").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
