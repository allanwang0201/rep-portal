package au.com.jaycar.warehouse.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import au.com.jaycar.warehouse.dao.BaseDao;



public abstract class BaseDaoImpl<T> implements BaseDao<T>{

	@Resource(name = "sqlSession")
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}
	
	private String ns;		//命名空间
	public String getNs() {
		return ns;
	}
	public void setNs(String ns) {
		this.ns = ns;
	}

	public List<T> find(Map map) {
		List<T> oList = this.getSqlSession().selectList(ns + ".find", map);
		return oList;
	}
	public T get(Serializable id) {
		return this.getSqlSession().selectOne(ns + ".get", id);
	}

	public void insert(T entity) {
		this.getSqlSession().insert(ns + ".insert", entity);
	}

	public void update(T entity) {
		this.getSqlSession().update(ns + ".update", entity);
	}

	public void deleteById(Serializable id) {
		this.getSqlSession().delete(ns + ".deleteById", id);
	}

	public void delete(Serializable[] ids) {
		this.getSqlSession().delete(ns + ".delete", ids);
	}
}
