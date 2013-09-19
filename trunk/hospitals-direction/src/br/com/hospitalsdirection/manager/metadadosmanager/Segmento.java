package br.com.hospitalsdirection.manager.metadadosmanager;

import com.google.android.gms.maps.model.LatLng;

public class Segmento {
    
	private LatLng start;
    private String instruction;
    private int length;
    private double distance;


    public Segmento() {
    }


    public void setInstruction(final String turn) {
            this.instruction = turn;
    }

    public String getInstruction() {
            return instruction;
    }

    public void setPoint(final LatLng point) {
            start = point;
    }

    public LatLng startPoint() {
            return start;
    }

    public Segmento copy() {
            final Segmento copy = new Segmento();
            copy.start = start;
            copy.instruction = instruction;
            copy.length = length;
            copy.distance = distance;
            return copy;
    }

    public void setLength(final int length) {
            this.length = length;
    }

    public int getLength() {
            return length;
    }

    public void setDistance(double distance) {
            this.distance = distance;
    }

    public double getDistance() {
            return distance;
    }

}
