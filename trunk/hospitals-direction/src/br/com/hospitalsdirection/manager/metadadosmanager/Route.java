package br.com.hospitalsdirection.manager.metadadosmanager;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;

public class Route {
    private String name;
    private final List<LatLng> points;
    private List<Segmento> segments;
    private String copyright;
    private String warning;
    private String country;
    private int length;
    private String polyline;
    
    private String strPartida;
    private String strDestino;
    private LatLng pontoPartida;
    private LatLng pontoDestino;

    public Route() {
            points = new ArrayList<LatLng>();
            segments = new ArrayList<Segmento>();
    }

    public String getStrPartida() {
		return strPartida;
	}

	public void setStrPartida(String strPartida) {
		this.strPartida = strPartida;
	}

	public String getStrDestino() {
		return strDestino;
	}

	public void setStrDestino(String strDestino) {
		this.strDestino = strDestino;
	}

	public void addPoint(final LatLng p) {
            points.add(p);
    }

    public void addPoints(final List<LatLng> points) {
            this.points.addAll(points);
    }

    public List<LatLng> getPoints() {
            return points;
    }

    public void addSegment(final Segmento s) {
            segments.add(s);
    }

    public List<Segmento> getSegments() {
            return segments;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
            this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
            return name;
    }

    /**
     * @param copyright the copyright to set
     */
    public void setCopyright(String copyright) {
            this.copyright = copyright;
    }

    /**
     * @return the copyright
     */
    public String getCopyright() {
            return copyright;
    }

    /**
     * @param warning the warning to set
     */
    public void setWarning(String warning) {
            this.warning = warning;
    }

    /**
     * @return the warning
     */
    public String getWarning() {
            return warning;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
            this.country = country;
    }

    /**
     * @return the country
     */
    public String getCountry() {
            return country;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
            this.length = length;
    }

    /**
     * @return the length
     */
    public int getLength() {
            return length;
    }


    /**
     * @param polyline the polyline to set
     */
    public void setPolyline(String polyline) {
            this.polyline = polyline;
    }

    /**
     * @return the polyline
     */
    public String getPolyline() {
            return polyline;
    }

	public LatLng getPontoPartida() {
		return pontoPartida;
	}

	public void setPontoPartida(LatLng pontoPartida) {
		this.pontoPartida = pontoPartida;
	}

	public LatLng getPontoDestino() {
		return pontoDestino;
	}

	public void setPontoDestino(LatLng pontoDestino) {
		this.pontoDestino = pontoDestino;
	}

}
