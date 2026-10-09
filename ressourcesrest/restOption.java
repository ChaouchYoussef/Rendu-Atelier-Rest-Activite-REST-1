package ressourcesrest;

import entities.Option;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("options")
public class restOption {

   public static OptionBusiness optB= new OptionBusiness();

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getAllOptions(@QueryParam("domaine") String domaine) {

      List<Option> l;

      if (domaine == null || domaine.isEmpty()) {
         l = optB.getListeOptions();
      } else {
         l = optB.getOptionsByDomaine(domaine);
      }
      return Response.ok(l).build();
   }

   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response addOption(Option option) {

      boolean added = optB.addOption(option);
      if (added) {
         return Response.status(Response.Status.CREATED).entity(option).build();
      }
      return Response.status(Response.Status.BAD_REQUEST).build();
   }

   @DELETE
   @Path("{code}")
   public Response deleteOption(@PathParam("code") int code) {
      if (optB.deleteOption(code)) {
         return Response.status(204).build();
      }
      return Response.status(Response.Status.BAD_REQUEST).build();
   }

   @PUT
   @Path("{code}")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response updateOption(@PathParam("code") int code, Option option) {
      if (optB.updateOption(code, option)) {
         return Response.status(200).build();
      }
      return Response.status(Response.Status.NOT_FOUND).build();
   }

   @GET
   @Path("{code}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getOption(@PathParam("code") int code) {
      Option opt = optB.getOptionByCode(code);
      if (opt == null) {
         return Response.status(Response.Status.NOT_FOUND).build();
      }
      return Response.status(Response.Status.OK).entity(opt).build();
   }

}
