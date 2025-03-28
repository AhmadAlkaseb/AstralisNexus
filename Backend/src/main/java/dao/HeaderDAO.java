package dao;

import jakarta.persistence.*;
import persistence.model.Header;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HeaderDAO implements IDAO<Header> {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private static String timestamp = dateFormat.format(new Date());

    private static HeaderDAO instance;
    private static EntityManagerFactory emf;

    public static HeaderDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HeaderDAO();
        }
        return instance;
    }

    @Override
    public List<Header> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Header> query = em.createQuery("SELECT h FROM Header h", Header.class);
            return query.getResultList();
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(timestamp + ": " + "No headers found.");
        }
    }

    @Override
    public Header getById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Header> query = em.createQuery("SELECT h FROM Header h WHERE h.id = :id", Header.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(timestamp + ": " + "No headers found with the following id: " + id);
        }
    }

    @Override
    public Header create(Header header) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(header);
            em.getTransaction().commit();
            return header;
        } catch (PersistenceException e) {
            throw new PersistenceException(timestamp + ": " + "Error persisting header.");
        }
    }

    @Override
    public Header update(Header header) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(header);
            em.getTransaction().commit();
            return header;
        } catch (PersistenceException e) {
            throw new PersistenceException(timestamp + ": " + "Error updating header.");
        }
    }

    @Override
    public Header delete(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Header header = em.find(Header.class, id);
            if (header != null) {
                em.remove(header);
                em.getTransaction().commit();
            }
            return header;
        } catch (PersistenceException e) {
            throw new PersistenceException(timestamp + ": " + "Error deleting account.");
        }
    }
}
