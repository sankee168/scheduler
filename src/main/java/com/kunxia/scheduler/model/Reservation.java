package com.kunxia.scheduler.model;

public class Reservation {

	private int id;
	private String checkin;
	private String checkout;
	private String guestName;
	private String guestPhone;
	private String guestEmail;
	private String quartzRef;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getGuestPhone() {
		return guestPhone;
	}
	public void setGuestPhone(String guestPhone) {
		this.guestPhone = guestPhone;
	}
	public String getGuestEmail() {
		return guestEmail;
	}
	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}
	public String getQuartzRef() {
		return quartzRef;
	}
	public void setQuartzRef(String quartzRef) {
		this.quartzRef = quartzRef;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((checkin == null) ? 0 : checkin.hashCode());
		result = prime * result
				+ ((checkout == null) ? 0 : checkout.hashCode());
		result = prime * result
				+ ((guestEmail == null) ? 0 : guestEmail.hashCode());
		result = prime * result
				+ ((guestName == null) ? 0 : guestName.hashCode());
		result = prime * result
				+ ((guestPhone == null) ? 0 : guestPhone.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((quartzRef == null) ? 0 : quartzRef.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		if (checkin == null) {
			if (other.checkin != null)
				return false;
		} else if (!checkin.equals(other.checkin))
			return false;
		if (checkout == null) {
			if (other.checkout != null)
				return false;
		} else if (!checkout.equals(other.checkout))
			return false;
		if (guestEmail == null) {
			if (other.guestEmail != null)
				return false;
		} else if (!guestEmail.equals(other.guestEmail))
			return false;
		if (guestName == null) {
			if (other.guestName != null)
				return false;
		} else if (!guestName.equals(other.guestName))
			return false;
		if (guestPhone == null) {
			if (other.guestPhone != null)
				return false;
		} else if (!guestPhone.equals(other.guestPhone))
			return false;
		if (id != other.id)
			return false;
		if (quartzRef == null) {
			if (other.quartzRef != null)
				return false;
		} else if (!quartzRef.equals(other.quartzRef))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", checkin=" + checkin + ", checkout="
				+ checkout + ", guestName=" + guestName + ", guestPhone="
				+ guestPhone + ", guestEmail=" + guestEmail + ", quartzRef="
				+ quartzRef + "]";
	}
	
}
	