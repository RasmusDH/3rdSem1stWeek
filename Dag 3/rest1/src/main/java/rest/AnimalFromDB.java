/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Animal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Rasmus
 */
@Path("animals_db")
public class AnimalFromDB {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalFromDB
     */
    public AnimalFromDB() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalFromDB
     * @return an instance of java.lang.String
     */
    @Path("animals")
@GET
@Produces(MediaType.APPLICATION_JSON)
public String getAnimals() {
  EntityManager em = emf.createEntityManager();
  try{
      TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
      List<Animal> animals = query.getResultList();
      return new Gson().toJson(animals);
   } finally {
          em.close();
   }
}


    /**
     * PUT method for updating or creating an instance of AnimalFromDB
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
