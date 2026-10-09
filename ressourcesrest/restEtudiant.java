package ressourcesrest;

import entities.Etudiant;
import entities.EtudiantList;
import entities.Option;
import metiers.EtudiantBusiness;
import metiers.OptionBusiness;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("etudiant")
public class restEtudiant {
    public static EtudiantBusiness eb = new EtudiantBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AddEtudiant(Etudiant etudiant)
    {
        if (eb.addEtudiant(etudiant)){
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiant(@PathParam("id") String id ){
        if (eb.getEtudiantByIdentifiant(id) != null){
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteEt(@PathParam("code") String id){
        if(eb.deleteEtudiant(id)){
            return Response.status(204).build();
        }else{
            return Response.status(404).build();
        }
    }
    @PUT
    @Path("{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOp(@PathParam("code") String id,Etudiant e ){
        if(eb.updateEtudiant(id,e)){
            return Response.status(200).build();
        }else{
            return Response.status(404).build();
        }
    }
    @GET
    @Path("/option/{codeOption}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiantsByOption(@PathParam("codeOption") int codeOption) {

        Option option = new Option();

        option.setCodeOption(codeOption);

        List<Etudiant> etudiants = eb.getEtudiantsByOption(option);

        return Response.ok(etudiants).build();
    }

}
