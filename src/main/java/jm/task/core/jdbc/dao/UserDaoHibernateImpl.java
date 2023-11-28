package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getInstance().getSessionFactory();
    private static final String CREATE_USERS_TABLE_SQL = """
            create table if not exists users(
            id bigint primary key auto_increment,
            name varchar(30),
            last_name varchar(30),
            age int
            )
            """;
    private static final String DROP_USERS_TABLE_SQL = """
            drop table if exists users;
            """;
    private static final String CLEAN_USERS_TABLE_SQL = """
            TRUNCATE TABLE Users;
            """;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(CREATE_USERS_TABLE_SQL).executeUpdate();
            transaction.commit();
        } catch (PersistenceException e){
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(DROP_USERS_TABLE_SQL).executeUpdate();
            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(new User(name, lastName, age));
            tx.commit();
        } catch (PersistenceException e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (null != user) {
                session.delete(user);
            }
            transaction.commit();
        } catch (PersistenceException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<User> userList = session.createQuery("from User").list();
            transaction.commit();
            return userList;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(CLEAN_USERS_TABLE_SQL)
                    .executeUpdate();
            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }
}
