package core.filter;

import core.filter.filterTypes.Filter;
import core.filter.filters.GameOfLife;
import core.filter.filters.Gaus;
import core.filter.filters.Gaus5;
import core.filter.filters.Identisch;
import core.filter.filters.LaplaceMax;
import core.filter.filters.LaplaceMedium;
import core.filter.filters.LaplaceMin;
import core.filter.filters.MedianMatrix;
import core.filter.filters.Prewitt;
import core.filter.filters.Relief;
import core.filter.filters.Scharr;
import core.filter.filters.Schaerfung;
import core.filter.filters.Sobel;
import core.filter.filters.TheVoid;

public class FilterLib {

	public static final Filter GAUS = new Gaus();
	public static final Filter GAUS_5 = new Gaus5();
	public static final Filter IDENTISCH = new Identisch();
	public static final Filter LAPLACE_MIN = new LaplaceMin();
	public static final Filter LAPLACE_MEDIUM = new LaplaceMedium();
	public static final Filter LAPLACE_MAX = new LaplaceMax();
	public static final Filter MEDIAN = new MedianMatrix();
	public static final Filter RELIEF = new Relief();
	public static final Filter SCHAERFUNG = new Schaerfung();
	public static final Filter SCHARR = new Scharr();
	public static final Filter SOBEL = new Sobel();
	public static final Filter PREWITT = new Prewitt();
	public static final Filter GAME_OF_LIVE = new GameOfLife();
	public static final Filter THE_VOID = new TheVoid();
}
