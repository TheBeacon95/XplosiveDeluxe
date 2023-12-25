//package common;
//
//import java.lang.constant.Constable;
//import java.lang.constant.ConstantDesc;
//import java.lang.invoke.MethodHandles;
//import java.util.Optional;
//
//public final class Vector implements Comparable<Vector>, Constable {
//	public int x;
//	public int y;
//	
//	public static Vector zero = new Vector(0, 0);
//	public static Vector up = new Vector(0, -1);
//	public static Vector right = new Vector(1, 0);
//	public static Vector down = new Vector(0, 1);
//	public static Vector left = new Vector(-1, 0);
//	
//	public Vector() {
//		x = 0;
//		y = 0;
//	}
//	
//	public Vector(int x, int y) {
//		this.x = x;
//		this.y = y;
//	}
//	
//	public Vector(Vector vector) {
//		x = vector.x;
//		y = vector.y;
//	}
//	
//	public static Vector add(Vector a, Vector b) {
//		return new Vector(a.x + b.x, a.y + b.y);
//	}
//	
//	public static Vector subtract(Vector a, Vector b) {
//		return new Vector(a.x - b.x, a.y - b.y);
//	}
//	
//	public static Vector scale(Vector vector, int scaleFactor) {
//		return new Vector(vector.x * scaleFactor, vector.y * scaleFactor);
//	}
//	
//	public void scale(int scaleFactor) {
//		x *= scaleFactor;
//		y *= scaleFactor;
//	}
//
//    @Override
//    public int compareTo(Vector o) {
//        return (x==o.x && y==o.y) ? 0 : -1;
//    }
//
//    @Override
//    public Optional<? extends ConstantDesc> describeConstable() {
//        return null; // Find out how to do this.
//    }
//}
