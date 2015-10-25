package com.kunxia.scheduler.dao;

import java.util.List;

import com.kunxia.scheduler.model.Reservation;

public interface ReservationDAO {
	public List<Reservation> findAllReservations();
	public Reservation updateReservation(int id, String ref);
}
