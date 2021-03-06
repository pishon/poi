package pl.gihon.fdd.poi.model;

import java.util.ArrayList;
import java.util.List;

public class Area {

	private long nr;
	private String name;
	private List<LocatedPlace> places = new ArrayList<>();
	private Polygon polygon;

	public Area(long nr, String name) {
		super();
		this.nr = nr;
		this.name = name;
	}

	public Area(Polygon polygon) {
		this.name = polygon.getName();
		this.polygon = polygon;
	}

	public Area() {
		super();
	}

	public int getPlaceCount() {
		return places.size();
	}

	public long getNr() {
		return nr;
	}

	public String getName() {
		return name;
	}

	public void setNr(long nr) {
		this.nr = nr;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlaces(List<LocatedPlace> places) {
		this.places = places;
	}

	public List<LocatedPlace> getPlaces() {
		return places;
	}

	public void addPlace(LocatedPlace place) {
		places.add(place);

	}

	public boolean hasPolygon() {
		return polygon != null;
	}

	public boolean contains(LocatedPlace locatedPlace) {
		return polygon.contains(locatedPlace.getLongitude(), locatedPlace.getLatitude());
	}


}
