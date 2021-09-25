package core;

import core.filter.Gaus;
import core.filter.Gaus5;
import core.filter.Identisch;
import core.filter.LaplaceMax;
import core.filter.LaplaceMedium;
import core.filter.LaplaceMin;
import core.filter.MedianMatrix;
import core.filter.Relief;
import core.filter.Scharr;
import core.filter.Schärfung;
import core.filter.Sobel;

public class FilterLib {

	public static final Filter GAUS = new Gaus();
	public static final Filter GAUS_5 = new Gaus5();
	public static final Filter IDENTISCH = new Identisch();
	public static final Filter LAPLACE_MIN = new LaplaceMin();
	public static final Filter LAPLACE_MEDIUM = new LaplaceMedium();
	public static final Filter LAPLACE_MAX = new LaplaceMax();
	public static final Filter MEDIAN = new MedianMatrix();
	public static final Filter RELIEF = new Relief();
	public static final Filter SCHÄRFUNG = new Schärfung();
	public static final Filter SCHARR = new Scharr();
	public static final Filter SOBEL = new Sobel();

}
