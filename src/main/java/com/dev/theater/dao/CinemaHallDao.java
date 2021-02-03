package com.dev.theater.dao;

import com.dev.theater.model.CinemaHall;
import java.util.List;

public interface CinemaHallDao extends Dao<CinemaHall> {
    List<CinemaHall> getAll();
}
