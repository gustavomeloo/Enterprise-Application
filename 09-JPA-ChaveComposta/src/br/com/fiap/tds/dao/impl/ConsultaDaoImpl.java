package br.com.fiap.tds.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.tds.dao.ConsultaDao;
import br.com.fiap.tds.ex.entity.Consulta;
import br.com.fiap.tds.ex.entity.ConsultaPK;

public class ConsultaDaoImpl extends GenericDaoImpl<Consulta, ConsultaPK> implements ConsultaDao {

	public ConsultaDaoImpl(EntityManager em) {
		super(em);
	}

}
