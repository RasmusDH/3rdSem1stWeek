/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.RenameMe;
import facades.EmployeeFacade;
import facades.FacadeExample;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Rasmus
 */
@Path("employee")
public class EmployeeResource {
    //NOTE: Change Persistence unit name according to your setup
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    EmployeeFacade facade =  EmployeeFacade.getEmployeeFacade(emf);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
      EntityManager em = emf.createEntityManager();
    try{
      TypedQuery<EmployeeDTO> query = em.createQuery("SELECT employee FROM Employee employee", EmployeeDTO.class);
      List<EmployeeDTO> employees = query.getResultList();
      return new Gson().toJson(employees);
    } finally {
          em.close();
    }
    }
    
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeById(@PathParam("id") int id) {
        EmployeeDTO employee =  new EmployeeDTO(facade.getEmployeesById(id));
        return new Gson().toJson(employee);
    }
    @Path("highestpaid")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getHighestPaid() {
        EmployeeDTO employee = new EmployeeDTO(facade.getEmployeesWithHighestSalary());
        return new Gson().toJson(employee);
    }
    @Path("name/{name}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeByName(@PathParam("name") String name) {
        EmployeeDTO employee = new EmployeeDTO(facade.getEmployeesByName(name));
        return new Gson().toJson(employee);
    }
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(RenameMe entity) {
        throw new UnsupportedOperationException();
    }
    
    
    /*@PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(RenameMe entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }*/
}
