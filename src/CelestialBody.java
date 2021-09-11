

/**
 * Celestial Body class for NBody
 * Modified from original Planet class
 * used at Princeton and Berkeley
 * @author ola
 *
 * If you add code here, add yourself as @author below
 * @author soa
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		// TODO: complete constructor
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;

	}

	/**
	 *
	 * @return
	 */
	public double getX() {
		// TODO: complete method
		// return 0.0;
		return myXPos;
	}

	/**
	 *
	 * @return
	 */
	public double getY() {
		// TODO: complete method
		// return 0.0;
		return myYPos;
	}

	/**
	 * Accessor for the x-velocity
	 * @return the value of this object's x-velocity
	 */
	public double getXVel() {
		// TODO: complete method
		// return 0.0;
		return myXVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		// TODO: complete method
		// return 0.0;
		return myYVel;
	}

	/**
	 *
	 * @return
	 */
	public double getMass() {
		// TODO: complete method
		// return 5.0;
		return myMass;
	}

	/**
	 *
	 * @return
	 */
	public String getName() {
		// TODO: complete method
		// return "cow-planet";
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		// TODO: complete method
		double x_dist = Math.abs(this.getX() - b.getX());
		double y_dist = Math.abs(this.getY() - b.getY());

		double dist = Math.sqrt(Math.pow(x_dist, 2) + Math.pow(y_dist,2));

		//return 0.0;
		return dist;
	}

	public double calcForceExertedBy(CelestialBody b) {
		// TODO: complete method
		// where the gravitational constant is 6.67408 × 10-11
		double G = 6.67 * Math.pow(10, -11);

		// force exerted equals = (G * M * m) / r^2
		double F = (G * this.myMass * b.getMass()) / Math.pow(calcDistance(b), 2);

		//return 0.0;
		return F;
	}

	public double calcForceExertedByX(CelestialBody b) {
		// TODO: complete method
		double F = this.calcForceExertedBy(b);
		double x_delt = b.getX() - this.getX();
		double y_delt = b.getY() - this.getY();
		double r = Math.sqrt(Math.pow(x_delt, 2) + Math.pow(y_delt,2));

		// force_x = F∆X / distance
		double F_x = F * x_delt / r;

		//return 0.0;
		return F_x;
	}

	public double calcForceExertedByY(CelestialBody b) {
		// TODO: complete method
		double F = this.calcForceExertedBy(b);
		double x_delt = b.getX() - this.getX();		// final - initial
		double y_delt = b.getY() - this.getY();
		double r = Math.sqrt(Math.pow(x_delt, 2) + Math.pow(y_delt,2));

		// force_x = F∆X / distance
		double F_y = F * y_delt / r;

		//return 0.0;
		return F_y;
	}

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		// TODO: complete method
		double sum = 0.0;

		// calculate all F_x
		for(CelestialBody b : bodies){
			double F = this.calcForceExertedBy(b);
			double x_delt = b.getX() - this.getX();
			double y_delt = b.getY() - this.getY();
			double r = Math.sqrt(Math.pow(x_delt, 2) + Math.pow(y_delt,2));

			double F_x = F * x_delt / r;

			sum += F_x;
		}

		return sum;
	}

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double sum = 0.0;

		for(CelestialBody b : bodies){
			double F = this.calcForceExertedBy(b);
			double x_delt = b.getX() - this.getX();
			double y_delt = b.getY() - this.getY();
			double r = Math.sqrt(Math.pow(x_delt, 2) + Math.pow(y_delt,2));

			double F_y = F * y_delt / r;

			sum += F_y;
		}

		return sum;
	}

	public void update(double deltaT, 
			           double xforce, double yforce) {
		// TODO: complete method
		// find x_f, y_f after applied force and change in time
		// x_f = x_0 + v_0x + 0.5t^2 * a_x
		// y_f = y_0 + v_0y + 0.5t^2 * a_y
		double a_x = (xforce / this.getMass());
		double a_y = (yforce / this.getMass());


		// find nv_x and nv_y after applied force and change in time
		// nv_x = vx_0 + a_x * t
		// nv_y = vy_0 + a_y * t
		double nv_x = this.getXVel() + a_x * deltaT;
		double nv_y = this.getYVel() + a_y * deltaT;

		// use nv_x and nv_y (velocity after applied force) to fin final position
		double x_f = this.getX() + (nv_x * deltaT);
		double y_f = this.getY() + (nv_y * deltaT);

		// update instance variables
		myXPos = x_f;
		myYPos = y_f;
		myXVel = nv_x;
		myYVel = nv_y;
	}

	/**
	 * Draws this planet's image at its current position
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
