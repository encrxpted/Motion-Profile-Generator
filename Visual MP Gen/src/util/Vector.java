package util;

public class Vector {
	
	public double x;
	public double y;
	public double mag;
	public double angle;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
		this.angle = Math.atan(y/x);
		this.mag = Math.sqrt(x*x + y*y);
	}
	
	public Vector(double magnitude, double angle, boolean isComponents) {
		this.angle = angle;
		this.mag = magnitude;
		x = mag * Math.cos(angle);
		y = mag * Math.sin(angle);
	}
	
	public double getAngle() {
		return angle;
	}
	
	public double getMagnitude() {
		return mag;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

}
