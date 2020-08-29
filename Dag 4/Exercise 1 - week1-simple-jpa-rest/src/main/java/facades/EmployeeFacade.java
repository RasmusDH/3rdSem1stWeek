/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rasmus
 */
public class EmployeeFacade {
     private static EntityManagerFactory emf;
    private static EmployeeFacade instance;
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");      
        EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
        Employee e1 = facade.createEmployee("Lund", "Haraldvej 22", 15000);
        Employee e2 = facade.createEmployee("kurt", "kurtvej 38", 20000);
        //Find employee by ID
        System.out.println("Employee1: "+facade.getEmployeesById(e1.getId()).getName());
        System.out.println("Employee2: "+facade.getEmployeesById(e2.getId()).getName());
        //Find all employees
        System.out.println("Number of employees: "+facade.getAllEmployees().size());
        //Find employee by name
        //System.out.println("Employee1: "+facade.getEmployeesByName("Lund").getAddress());
        //Find employee with highest salary
        System.out.println("Employee with highest salary: "+facade.getEmployeesWithHighestSalary().getName());

    }
    
    
    private EmployeeFacade() {}

    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }
    
    public Employee getEmployeesById(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Employee employee = em.find(Employee.class,id);
            return employee;
        }finally {
            em.close();
        }
    }
    public Employee getEmployeesByName(String name){
         EntityManager em = emf.createEntityManager();
        try{
            Employee employee = em.find(Employee.class,name);
            return employee;
        }finally {
            em.close();
        }
    }
    public List<Employee> getAllEmployees(){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query = 
                       em.createQuery("Select employee from Employee employee",Employee.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
    public Employee getEmployeesWithHighestSalary() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query =
                        em.createQuery("Select employee FROM Employee employee WHERE employee.salary IN (Select MAX(employee.salary) FROM Employee employee)",Employee.class);
            return query.getSingleResult();
        }finally {
            em.close();
        }
        
    }
    public Employee createEmployee(String name, String address, int salary){
        Employee employee = new Employee(name, address, salary);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        }finally {
            em.close();
        }
    }
}
