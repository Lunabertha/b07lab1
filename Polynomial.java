
public class Polynomial {

	// fields
	double[] coefficient;

	// methods - constructor?
	public Polynomial() {
		this.coefficient = new double[] { 0.0 };
	}

	public Polynomial(double[] a) {
		int newlength = a.length;
		this.coefficient = new double[a.length];
		for (int i = 0; i < newlength; i++) {
			this.coefficient[i] = a[i];
		}
	}

	public Polynomial add(Polynomial newcoef) {
		int deg = Math.max(this.coefficient.length, newcoef.coefficient.length);
		double[] newpoly = new double[deg];
		for (int i = 0; i < deg; i++) {
			// somehow add the coefficients together based on deg
			double coeff_1 = 0.0;
			double coeff_2 = 0.0;
			int polylength = this.coefficient.length;
			int otherlength = newcoef.coefficient.length;
			if (i < newcoef.coefficient.length) {
				coeff_2 = newcoef.coefficient[i];
			}
			if (i < polylength) {
				coeff_1 = this.coefficient[i];
			}
			newpoly[i] = coeff_2 + coeff_1;
		}
		return new Polynomial(newpoly);
	}

	public double evaluate(double valx) {
		double result = 0.0;
		double deg = 1.0;

		for (int i = 0; i < coefficient.length; i++) {
			result += coefficient[i] * deg;
			deg = deg * valx;

		}
		return result;

	}

	public boolean hasRoot(double valx) {
		double evaluated = evaluate(valx);
		return evaluated == 0.0;

	}
}