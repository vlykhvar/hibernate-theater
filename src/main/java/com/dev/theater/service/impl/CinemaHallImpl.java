package com.dev.theater.service.impl;

import com.dev.theater.dao.CinemaHallDao;
import com.dev.theater.model.CinemaHall;
import com.dev.theater.service.CinemaHallService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaHallImpl implements CinemaHallService {

    private CinemaHallDao cinemaHallDao;

    @Autowired
    CinemaHallImpl(CinemaHallDao cinemaHallDao) {
        this.cinemaHallDao = cinemaHallDao;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
