package com.synnex.adms.central.console.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InteractiveConsoleDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	
	public List queryNodeList() {
		
		List reList = new ArrayList();//回傳的List
		StringBuffer sql = new StringBuffer();
		List inList = new ArrayList();//初始的List
		
		sql.append(" select nod ");
		sql.append(" from Node nod ");
		sql.append(" where 1 = 1 ");
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql.toString());
		reList = query.list();
		return reList;
		
	}
	

}
