package com.kunxia.scheduler.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.kunxia.scheduler.model.Reservation;

@Component
public class ReservationDAOImpl implements ReservationDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger logger = Logger
			.getLogger(ReservationDAOImpl.class);



	public List<Reservation> findAllReservations() {
		String sql = "SELECT * FROM RESERVATIONS";
		List<Reservation> reservations = jdbcTemplate.query(sql,
				new ReservationRowMapper());
		return reservations;
	}



	public Reservation updateReservation(int id, String ref) {
		String updateSql = "UPDATE RESERVATIONS SET QUARTZ_REF = ? WHERE ID = ?";
		jdbcTemplate.update(updateSql, ref, id);
		String selectSql="SELECT * FROM RESERVATIONS WHERE ID = ?";
		Reservation reservation = jdbcTemplate.queryForObject(selectSql, new Object[] { id }, new ReservationRowMapper());
		logger.info("Updated reservation :"+reservation+" with Quartz registration ref: "+ref);
		return reservation;
	}
	
	

}
