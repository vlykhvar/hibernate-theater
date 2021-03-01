package com.dev.football.dao.impl;

import com.dev.football.dao.GameSessionDao;
import com.dev.football.exception.CrudException;
import com.dev.football.model.GameSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GameSessionDaoImpl extends DaoImpl<GameSession> implements GameSessionDao {
    @Autowired
    public GameSessionDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<GameSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from game_session "
                    + "where id = :movieId and DATE_FORMAT(showTime,'%Y-%m-%d') "
                    + "= :date", GameSession.class)
                    .setParameter("movieId", movieId)
                    .setParameter("date", date.format(DateTimeFormatter.ISO_DATE))
                    .getResultList();
        } catch (Exception e) {
            throw new CrudException("Can't get session on id " + movieId + " in " + date);
        }
    }

    public GameSession update(GameSession gameSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(gameSession);
            transaction.commit();
            return gameSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CrudException("Can't update moviesession: "
                    + gameSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            GameSession gameSession = session.load(GameSession.class, id);
            session.remove(gameSession);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CrudException("Can't delete moviesession by id: "
                    + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<GameSession> getById(Long movieSessionId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from MovieSession"
                    + "where id = :movieSessionId", GameSession.class)
                    .setParameter("movieSessionId", movieSessionId)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new CrudException("Can't get movie session on id " + movieSessionId);
        }
    }
}