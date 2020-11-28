package com.example.demo;

import com.example.demo.entity.InstituteJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class InstituteDaoJPA
{
    @Autowired
    EntityManagerFactory emf;

    public InstituteJPA getOne(int id)
    {
        EntityManager em = emf.createEntityManager();
        InstituteJPA institute = em.find(InstituteJPA.class,id);
        em.close();
        return institute;
    }

    public long getCount()
    {
        return (long) emf.createEntityManager().createQuery("SELECT COUNT(i) FROM InstituteJPA i").getSingleResult();
    }

    public List<InstituteJPA> getByInstituteName(String name)
    {
        TypedQuery<InstituteJPA> query = emf.createEntityManager().createQuery("SELECT i FROM InstituteJPA i where i.instituteName=:name",InstituteJPA.class);
        return query.setParameter("name",name).getResultList();
    }

    public List<InstituteJPA> getEverything()
    {
        TypedQuery<InstituteJPA> query = emf.createEntityManager().createQuery("SELECT i FROM InstituteJPA i",InstituteJPA.class);
        return query.getResultList();
    }

    public void create(InstituteJPA institute)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(institute);
            em.getTransaction().commit();
        }
        catch(Exception ex)
        {
            try
            {
                em.getTransaction().rollback();
            }
            catch (Exception e) { }
        }
        finally
        {
            em.close();
        }
    }

    public  void delete(int id){
        EntityManager entityManager = emf.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(InstituteJPA.class,id));
            entityManager.getTransaction().commit();
        }catch(Exception ex){
            try {entityManager.getTransaction().rollback(); } catch (Exception e) { }
        } finally {
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
