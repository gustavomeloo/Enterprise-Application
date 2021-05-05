package br.com.fiap.tds.dao.impl;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import br.com.fiap.tds.dao.GenericDao;
import br.com.fiap.tds.exception.CommitException;
import br.com.fiap.tds.exception.EntityNotFoundException;

public abstract class GenericDaoImpl<E,K> implements GenericDao<E, K> {

	private EntityManager em;
	
	private Class <E> clazz; //Atributo que armazena o .class da Entidade
	
	@SuppressWarnings("all")
	public GenericDaoImpl(EntityManager em) {
		this.em = em;
		this.clazz = (Class<E>) ( (ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Override
	public void create(E entidade) {
		em.persist(entidade);
		
	}

	@Override
	public E search(K id) throws EntityNotFoundException {
		E entity = em.find(clazz, id);
		if (entity == null) {
			throw new EntityNotFoundException();
		}
		return entity;
	}

	@Override
	public void update(E entidade) {
		em.merge(entidade);
		
	}

	@Override
	public void delete(K id) throws EntityNotFoundException {
		E entity = search(id);
		em.remove(entity);
		
	}

	@Override
	public void commit() throws CommitException {
		try {
			em.getTransaction().begin();
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			throw new CommitException();
		}
		
	}

}