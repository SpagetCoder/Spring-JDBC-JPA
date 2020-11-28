package com.example.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.example.demo.entity.EmployeeJPA;
import com.example.demo.entity.TeamJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamDaoJPA {

	@Autowired
	EntityManagerFactory emf;

	public TeamJPA getOne(int id)
	{
		EntityManager em =emf.createEntityManager();
		TeamJPA teamJPA = em.find(TeamJPA.class,id);
		em.close();
		return teamJPA;
	}
	
	public long getCount()
	{
		return (long) emf.createEntityManager().createQuery("SELECT COUNT(T) FROM TeamJPA T").getSingleResult();
	}

	public List<TeamJPA> getTeamByName(String name)
	{
		TypedQuery<TeamJPA> query = emf.createEntityManager().createQuery("SELECT t FROM TeamJPA t WHERE t.teamName=:name",TeamJPA.class);
		return query.setParameter("name",name).getResultList();
	}

	public List<TeamJPA> getTeamFromTheSameInstitute(int id)
	{
		TypedQuery<TeamJPA> query = emf.createEntityManager().createQuery("SELECT t FROM TeamJPA t WHERE t.instituteJPA.instituteId = :id",TeamJPA.class);
		return query.setParameter("id",id).getResultList();
	}

	public void create(TeamJPA team)
	{
		EntityManager em = emf.createEntityManager();
		try
		{
			em.getTransaction().begin();
			em.persist(team);
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
		EntityManager em = emf.createEntityManager();
		try
		{
			em.getTransaction().begin();
			em.remove(em.find(TeamJPA.class,id));
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

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
}
