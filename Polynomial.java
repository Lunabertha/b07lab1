import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Polynomial {

	// fields
	// double[] nonzerocoef;
	double[] nonzerocoef;
	int[] exponents;

	// methods - constructor?
	public Polynomial() {
		// super();
		this.nonzerocoef = new double[] { 0.0 };
		this.exponents = new int[] { 0 };

	}

	// lab2 constructors;
	// 4d connected to 4e.
	public Polynomial(File poly_file) {
		// initialize polynomial
		// read file
		// assign them values
		// [coef1]x[exp1][+coef2]x[exp2][+coef3]x[exp3]
		Scanner readd = new Scanner(poly_file);
		if (readd.hasNext()) {
			String readpoly = readd.next();
			parsePolynomial(readpoly);
		}
		readd.close();

	}

	private void parsePolynomial(String polyfromfile) {
		String[] parts = polyfromfile.split("\\+");
		// init coef
		// init exp
		double[] coefArray = new double[0];
		int[] expArray = new int[0];
		for (String partString : parts) {
			String[] divideStrings = partString.split("x");
			double parsedcoef = Double.parseDouble(divideStrings[0]);
			int exponent;
			if (divideStrings.length > 1) {
				exponent = Integer.parseInt(parts[1]);
			} else {
				exponent = 0;
			}
		}
		// add exponent and coefffff; to the array.
		coefArray = append(coefArray, parsedcoef);
		expArray = append(expArray, exponent);
	}

	public Polynomial(double[] a, int[] b) {
		int newlength = a.length;
		this.nonzerocoef = new double[a.length];
		for (int i = 0; i < newlength; i++) {
			this.nonzerocoef[i] = a[i];
		}

		int newexp = b.length;
		this.exponents = new int[b.length];
		for (int i = 0; i < newexp; i++) {
			this.exponents[i] = b[i];
		}
	}

	// public Polynomial add(Polynomial newcoef) {
	// int deg = Math.max(this.nonzerocoef.length, newcoef.nonzerocoef.length);
	// double[] newpoly = new double[deg];
	// int[] newexp = new int[deg];
	// for (int i = 0; i < deg; i++) {
	// // somehow add the nonzerocoefs together based on deg
	// double coeff_1 = 0.0;
	// double coeff_2 = 0.0;
	// int polylength = this.nonzerocoef.length;
	// int otherlength = newcoef.nonzerocoef.length;
	// if (i < newcoef.nonzerocoef.length) {
	// coeff_2 = newcoef.nonzerocoef[i];
	// }
	// if (i < polylength) {
	// coeff_1 = this.nonzerocoef[i];
	// }
	// newpoly[i] = coeff_2 + coeff_1;
	// }
	// return new Polynomial(newpoly);
	// }

	public double evaluate(double valx) {
		double result = 0.0;
		double deg = 1.0;

		for (int i = 0; i < nonzerocoef.length; i++) {
			result += nonzerocoef[i] * deg;
			deg = deg * valx;

		}
		return result;

	}

	public boolean hasRoot(double valx) {
		double evaluated = evaluate(valx);
		return evaluated == 0.0;

	}

	public Polynomial multiply(Polynomial poly2) {
		// calling object * argument, no redundant exp.
		int k = 0;
		int poly2length = poly2.nonzerocoef.length * this.nonzerocoef.length;
		int[] poly2_exp = new int[poly2length];
		double[] poly2_coef = new double[poly2length];

		// for loop i, j
		// i for coef
		// j for exp
		// multiply in each
		for (int i = 0; i < this.nonzerocoef.length; i++) {
			for (int j = 0; j < poly2.nonzerocoef.length; j++) {
				poly2_exp[k] = poly2.exponents[j] + this.exponents[j];
				poly2_coef[k] = poly2.nonzerocoef[i] * this.nonzerocoef[i];
				k = k + 1;

			}
		}

		return new Polynomial(poly2_coef, poly2_exp);

	}

	// 4e
	public void saveToFile(String filename) {
		FileWriter myfilewriter = new FileWriter(filename);
		StringBuilder mynewpoly = new StringBuilder();

		for (int i = 0; i < nonzerocoef.length; i++) {
			if (nonzerocoef[i] > 0 && i > 0) {
				mynewpoly.append("+");
			} else if (nonzerocoef[i] < 0) {
				mynewpoly.append("-");
			}
			mynewpoly.append(nonzerocoef[i]);
			if (exponents[i] > 0) {
				mynewpoly.append("x^").append(exponents[i]);
			}
		}
		myfilewriter.write(mynewpoly.toString());
		myfilewriter.close();

	}
}