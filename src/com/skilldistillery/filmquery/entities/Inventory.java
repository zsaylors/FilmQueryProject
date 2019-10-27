package com.skilldistillery.filmquery.entities;

public class Inventory {
	//F I E L D S
	private int id;
	private int film_id;
	private int store_id;
	private String mediaCondition;
	private String lastUpdate;
	private Store storeLocation;
	
	//C O N S T R U C T O R S
	public Inventory() {}
	
	public Inventory(int id, int film_id, int store_id, String mediaCondition, String lastUpdate,
			Store storeLocation) {
		super();
		this.id = id;
		this.film_id = film_id;
		this.store_id = store_id;
		this.mediaCondition = mediaCondition;
		this.lastUpdate = lastUpdate;
		this.storeLocation = storeLocation;
	}
	
	//M E T H O D S
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFilm_id() {
		return film_id;
	}
	public void setFilm_id(int film_id) {
		this.film_id = film_id;
	}
	public int getStore_id() {
		return store_id;
	}
	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}
	public String getMediaCondition() {
		return mediaCondition;
	}
	public void setMediaCondition(String mediaCondition) {
		this.mediaCondition = mediaCondition;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public Store getStoreLocation() {
		return storeLocation;
	}
	public void setStoreLocation(Store storeLocation) {
		this.storeLocation = storeLocation;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Inventory [id=");
		builder.append(id);
		builder.append(", film_id=");
		builder.append(film_id);
		builder.append(", store_id=");
		builder.append(store_id);
		builder.append(", mediaCondition=");
		builder.append(mediaCondition);
		builder.append(", lastUpdate=");
		builder.append(lastUpdate);
		builder.append(", StoreLocation=");
		builder.append(storeLocation);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((storeLocation == null) ? 0 : storeLocation.hashCode());
		result = prime * result + film_id;
		result = prime * result + id;
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		result = prime * result + ((mediaCondition == null) ? 0 : mediaCondition.hashCode());
		result = prime * result + store_id;
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
		Inventory other = (Inventory) obj;
		if (storeLocation == null) {
			if (other.storeLocation != null)
				return false;
		} else if (!storeLocation.equals(other.storeLocation))
			return false;
		if (film_id != other.film_id)
			return false;
		if (id != other.id)
			return false;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
		if (mediaCondition == null) {
			if (other.mediaCondition != null)
				return false;
		} else if (!mediaCondition.equals(other.mediaCondition))
			return false;
		if (store_id != other.store_id)
			return false;
		return true;
	}
}