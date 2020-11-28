package com.example.demo;

import com.example.demo.entity.MajorJPA;
import com.example.demo.entity.TeamJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class MajorDaoJPA
{
    @Autowired
    EntityManagerFactory emf;

    public MajorJPA getOne(int id)
    {
        EntityManager em =emf.createEntityManager();
        MajorJPA majorJPA = em.find(MajorJPA.class,id);
        em.close();
        return majorJPA;
    }

    public long getCount()
    {
        return (long) emf.createEntityManager().createQuery("SELECT COUNT(m) FROM MajorJPA m").getSingleResult();
    }

    public List<MajorJPA> getMajorsFromTheSameInstitute(int id)
    {
        TypedQuery<MajorJPA> query = emf.createEntityManager().createQuery("SELECT m FROM MajorJPA m WHERE m.instituteJPA.instituteId = :id",MajorJPA.class);
        return query.setParameter("id",id).getResultList();
    }

    public List<MajorJPA> getMajorByName(String name)
    {
        TypedQuery<MajorJPA> query = emf.createEntityManager().createQuery("SELECT m FROM MajorJPA m WHERE m.majorName = :name",MajorJPA.class);
        return query.setParameter("name",name).getResultList();
    }

    public void create(MajorJPA majorJPA)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(majorJPA);
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

    public  void delete(int id) {
        EntityManager entityManager = emf.createEntityManager();
        try
        {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(MajorJPA.class, id));
            entityManager.getTransaction().commit();
        }
        catch (Exception ex) {
            try
            {
                entityManager.getTransaction().rollback();
            }
            catch (Exception e) { }
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
