package com.kunxia.scheduler.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.kunxia.scheduler.model.Reservation;

public class ReservationRowMapper implements RowMapper<Reservation> {

	public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
		Reservation reservation = new Reservation();
		reservation.setId(rs.getInt("ID"));
		reservation.setGuestName(rs.getString("GUEST_NAME"));
		reservation.setGuestPhone(rs.getString("GUEST_PHONE"));
		reservation.setGuestEmail(rs.getString("GUEST_EMAIL"));
		reservation.setCheckin(rs.getString("CHECKIN_DATE"));	
		reservation.setCheckout(rs.getString("CHECKOUT_DATE"));
		reservation.setQuartzRef(rs.getString("QUARTZ_REF"));
		return reservation;
	}

}
