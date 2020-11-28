package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class EmployeesDaoJPA
{
    @Autowired
    EntityManagerFactory emf;

    public EmployeeJPA getOne(int id)
    {
        EntityManager entityManager = emf.createEntityManager();
        EmployeeJPA employeeJPA = entityManager.find(EmployeeJPA.class,id);
        entityManager.close();
        return employeeJPA;
    }

    public long getCount()
    {
        return (long) emf.createEntityManager().createQuery("SELECT COUNT(e) FROM EmployeeJPA e").getSingleResult();
    }

    public List<EmployeeJPA> getEveryoneFromTeamId(int id)
    {
        TypedQuery<EmployeeJPA> query = emf.createEntityManager().createQuery("SELECT e FROM EmployeeJPA e WHERE e.teamJPA.teamId=:id", EmployeeJPA.class);
        return query.setParameter("id",id).getResultList();
    }

    public int getFemaleCount()
    {
        TypedQuery<EmployeeJPA> query = emf.createEntityManager().createQuery("SELECT e FROM EmployeeJPA e where e.gender = 'F'",EmployeeJPA.class);
        return query.getResultList().size();
    }

    public void create(EmployeeJPA employeeJpa)
    {
        EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();
            em.persist(employeeJpa);
            em.getTransaction().commit();

    }

    public  void delete(int id)
    {
        EntityManager entityManager = emf.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(EmployeeJPA.class,id));
            entityManager.getTransaction().commit();
        }
        catch(Exception ex)
        {
            try {entityManager.getTransaction().rollback(); } catch (Exception e) { }
        }
        finally
        {
            entityManager.close();
        }
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
}
