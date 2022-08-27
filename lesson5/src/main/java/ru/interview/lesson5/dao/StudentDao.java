package ru.interview.lesson5.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;

import java.util.List;

public class StudentDao {
    private Session currentSession;
    private Transaction currentTransaction;
    private static final SessionFactory sessionFactory = buildSessionFactory();

    public Session openCurrentSession() {
        currentSession = sessionFactory.openSession();
        return currentSession;
    }
    public Session openCurrentSessionWithTransaction() {
        currentSession = sessionFactory.openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
    public void closeCurrentSession() {
        currentSession.close();
    }
    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }
    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            return configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(
                    "There was an error building the factory");
        }
    }
    public Session getCurrentSession() {
        return currentSession;
    }
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
    public void persist(Student entity) {
        getCurrentSession().save(entity);
    }
    public void update(Student entity) {
        getCurrentSession().update(entity);
    }
    public Student findById(String id) {
        return getCurrentSession().get(Student.class, id);
    }
    public void delete(Student entity) {
        getCurrentSession().delete(entity);
    }
    @SuppressWarnings("unchecked")
    public List<Student> findAll() {
        return getCurrentSession().createQuery("from Student ").list();
    }
    public void deleteAll() {
        List<Student> entityList = findAll();
        for (Student entity : entityList) {
            delete(entity);
        }
    }
}