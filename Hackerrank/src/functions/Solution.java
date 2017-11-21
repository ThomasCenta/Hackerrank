package functions;
import java.util.Scanner;

public class Solution {

	
	
	public static int modMultiply(int i, int j, long mod){
        long i2 = i;
        long j2 = j;
        return (int)((i2*j2) % mod);
    }
	
	public static int modDivide(int base, int divideBy, int mod) {
		assert divideBy != 0;
		if(divideBy > 0) {
			return modMultiply(base, modularInverse(divideBy, mod), mod);
		}else {
			return modMultiply(base, -1*modularInverse(-1*divideBy, mod), mod);
		}
	}
	
	public static int clockSubtract(int subtractFrom, int subtractThis, int mod) {
		long i2 = subtractFrom;
		long j2 = subtractThis;
		return (int)((((i2-j2) % mod)+mod) % mod);
	}
	
	public static void shiftLeft(int[] arr, int shiftLeftBy) {
		for(int i = 0; i < arr.length-shiftLeftBy; i += 1) {
			arr[i] = arr[i+shiftLeftBy];
		}
	}
	
	public static int modAdd(int i, int j, int mod) {
		long i2 = i;
		long j2 = j;
		return (int)((i2+j2) % mod);
	}
	
	public static int modSubtract(int subtractFrom, int subtractThis, int mod) {
		long i2 = subtractFrom;
		long j2 = subtractThis;
		return (int)((i2-j2) % mod);
	}
	
	public static int powMod(long n, int pow, int mod){
		assert pow >= 0;

		if(pow == 0){
			return 1;
		}if(pow == 1){
			return (int)(n%mod);
		}
		int half = powMod(n,pow/2, mod);
		int halfSquared = modMultiply(half, half, mod);
		if(pow % 2 == 1){
			return modMultiply(halfSquared, (int)(n%mod), mod);
		}else{
			return halfSquared;
		}
	}
	
	/*
	 * returns -1 if no modular inverse
	 * @requires n > 0
	 */
	public static int modularInverse(int n, int mod) {
		assert n > 0;

		n = n % mod;
		if(n == 1) {return 1;}
		int[] pSeries = {0,1,0};
		int[] quotients = {0,0,0};
		int[] remainders = new int[3];
		quotients[0] = mod/n;
		remainders[0] = mod%n;
		if(remainders[0] == 0) {return -1;}
		quotients[1] = n/remainders[0];
		remainders[1] = n%remainders[0];
		pSeries[2] = clockSubtract(pSeries[0],modMultiply(pSeries[1],quotients[0], mod), mod);
		if(remainders[1] == 0) {return pSeries[2];}
		remainders[2] = remainders[0]%remainders[1];
		quotients[2] = remainders[0]/remainders[1];
		while(remainders[2] != 0) {
			shiftLeft(remainders,1);
			shiftLeft(quotients,1);
			shiftLeft(pSeries,1);
			remainders[2] = remainders[0]%remainders[1];
			quotients[2] = remainders[0]/remainders[1];
			pSeries[2] = clockSubtract(pSeries[0],modMultiply(pSeries[1],quotients[0], mod), mod);
		}
		if(remainders[1] != 1) {return -1;}
		return clockSubtract(pSeries[1],modMultiply(pSeries[2],quotients[1], mod), mod);
	}
	
	public static int powMod(int n, int pow, int mod){
		assert pow >= 0;

		if(pow == 0){
			return 1;
		}if(pow == 1){
			return n;
		}
		int half = powMod(n,pow/2, mod);
		int halfSquared = modMultiply(half, half, mod);
		if(pow % 2 == 1){
			return modMultiply(halfSquared, n%mod, mod);
		}else{
			return halfSquared;
		}
	}
	
	public static int binomialCoefficientMod(int n, int k, int mod) {
		assert k <= n;
		assert n > 0;
		if(k == 0)
			return 1;
		if(k > n/2)
			return binomialCoefficientMod(n,n-k, mod);
		int next = binomialCoefficientMod(n-1,k-1, mod);
		int next2 = modDivide(next, k, mod);
		return modMultiply(n, next2 , mod);
	}
	
	private static int bernoulliMod(int n, int mod) {
		if(n % 2 == 1 && n > 10) {
			return 0;
		}
		int result = 0;
		for (int k = 0; k <= n; k++) {
			int jSum = 0;
			int bInt = 1;
			for (int j = 0; j <= k; j++) {
				int jPowN = powMod(j, n, mod);
				if (j % 2 == 0) {
					jSum = modAdd(jSum, modMultiply(jPowN,bInt, mod), mod);
				} else {
					jSum = modSubtract(jSum, modMultiply(jPowN,bInt, mod), mod);
				}
				bInt = modDivide(modMultiply(bInt,modSubtract(k,j,mod),mod),modAdd(j,1,mod),mod);
			}
			result = modAdd(result, modDivide(jSum, modAdd(k,1,mod), mod), mod);
		}
		return result;
	}
	
	public static int kroneckerDelta(int i, int j) {
		if(i != j) {return 0;}
		return 1;
	}
	
	private static int[] bernoullis;
	
	private static int[] getBernoullis(int mod) {
		int[] toReturn = {1,-500000005,-166666668,0,633333339,0,-23809524,0,633333339,0,469696974,0,160805862,0,833333342,0,574509802,0,607769484,0,269696443,0,-992747440,0,-839280727,0,834758858,0,88793724,0,375425307,0,458194177,0,-552027477,0,-159994174,0,147911937,0,906304171,0,-164899154,0,-209565619,0,18465409,0,219852826,0,-270569791,0,549724558,0,185958893,0,966180022,0,875441660,0,-836008987,0,122478099,0,356692668,0,-147027509,0,843757650,0,263617252,0,612272568,0,-16863406,0,460777770,0,440931647,0,1931634,0,-462155639,0,-670730988,0,-335839789,0,301809573,0,147947308,0,209437816,0,487053062,0,-96487078,0,38883016,0,399980359,0,832893605,0,137659912,0,32046654,0,96201969,0,-727617616,0,-15190287,0,567516997,0,277826241,0,129450247,0,-140780828,0,495505064,0,8707610,0,-850008165,0,197841389,0,-584162108,0,-547443454,0,255969333,0,-412730482,0,263414781,0,-167295383,0,646359153,0,886506266,0,-514265694,0,-229215849,0,-770710137,0,-215663274,0,410524300,0,-414919795,0,141553402,0,425223057,0,237545850,0,63589729,0,33858933,0,325120530,0,395472691,0,-38887517,0,920184514,0,418233316,0,-671688984,0,88615861,0,-181835722,0,-416452965,0,-119739442,0,227383121,0,652754015,0,195906609,0,143074934,0,826044587,0,387905283,0,-395288194,0,-957978551,0,18853119,0,-738060082,0,570870708,0,-63572132,0,22903034,0,332187245,0,-825385636,0,11038394,0,363254813,0,525874981,0,658368378,0,396296094,0,783547564,0,-979144039,0,-437632207,0,949243780,0,211426253,0,-201683683,0,2189158,0,549188555,0,65168462,0,-441311496,0,614620286,0,597722193,0,-439505698,0,-326853641,0,-948748935,0,481381363,0,-298778215,0,-389094269,0,538953990,0,735130715,0,122194196,0,-159368848,0,-684284367,0,476095583,0,89037894,0,197626168,0,379851231,0,352939984,0,1603881,0,-504313441,0,452061366,0,-977208455,0,-532915244,0,112929067,0,-973660141,0,-358175995,0,151097054,0,27572593,0,128712039,0,336837738,0,136194315,0,827051682,0,-266956969,0,940867749,0,601253373,0,77767278,0,414299962,0,-67962234,0,738226723,0,239077453,0,393853968,0,-277062311,0,91409035,0,-454654961,0,-885358653,0,148877819,0,-737682644,0,250818962,0,-78136436,0,594138284,0,216245249,0,268198006,0,644235673,0,45020701,0,15197600,0,-67742349,0,233031406,0,-106393879,0,102728268,0,-155126602,0,-597838007,0,-181210581,0,-970684036,0,246877693,0,786369190,0,190539628,0,-349629527,0,336782181,0,307008561,0,-817284702,0,32544774,0,-800833465,0,-63682638,0,472944157,0,-3586310,0,308540076,0,724760508,0,278710238,0,-271450366,0,-104812460,0,-402981267,0,-108667857,0,-786685734,0,232858337,0,-230401879,0,60714296,0,-486178264,0,-665587391,0,355998556,0,-844484827,0,272958997,0,91080539,0,289948741,0,18208039,0,228396089,0,685375239,0,-67986438,0,354972820,0,142772925,0,327077220,0,-852873115,0,545255853,0,326621095,0,150434414,0,-958517998,0,669751880,0,-482040786,0,39900214,0,101166409,0,-409309391,0,455010719,0,605684678,0,317918217,0,-788859443,0,435382484,0,-598451615,0,-401849750,0,526705503,0,-785791706,0,-26019211,0,259608707,0,-348116713,0,-359068320,0,-290329276,0,845424175,0,-89357156,0,-882112938,0,-59460902,0,-552818506,0,-224148556,0,798595153,0,646557270,0,-401628359,0,182121197,0,-783344856,0,233674960,0,348110444,0,969342628,0,941227791,0,-72708192,0,548282982,0,35494838,0,193345727,0,-365916483,0,848413421,0,-307736568,0,-858960946,0,420990465,0,-900031051,0,-114572907,0,-25347513,0,710104495,0,758887858,0,657847149,0,-94463090,0,138925594,0,-38634538,0,792067907,0,-169825414,0,-112494661,0,-143346604,0,66520656,0,-535563198,0,-68047069,0,183318987,0,89044938,0,-529801484,0,100406774,0,2440362,0,314128474,0,443702464,0,253825927,0,-541698372,0,517281429,0,-783482900,0,115148439,0,-647211801,0,524532489,0,731457312,0,867734240,0,702581780,0,384432913,0,-1981601,0,-200332906,0,-233316978,0,255467277,0,-230320080,0,-553200238,0,506785884,0,90367510,0,-189539226,0,390906268,0,739257138,0,173415006,0,188140175,0,-459938421,0,-432729723,0,424512513,0,502150529,0,213408829,0,413161595,0,-38439221,0,-489505037,0,-161683927,0,879558681,0,830705431,0,591747767,0,237101735,0,140378135,0,852001724,0,331668928,0,-291747768,0,-931337548,0,182555116,0,893538359,0,-63850570,0,-48805550,0,-513693495,0,-728349198,0,-300785910,0,765695912,0,19806886,0,-794132774,0,580303862,0,31389524,0,219423408,0,665425392,0,799852916,0,-255564128,0,-237131396,0,-689534832,0,-473976163,0,658432441,0,742149662,0,110910902,0,-741249598,0,-590837201,0,-22952213,0,43894115,0,480488643,0,-731534854,0,100559602,0,-132480782,0,633379197,0,98138438,0,916305323,0,103527234,0,695952670,0,51702511,0,697247040,0,269625520,0,-487515420,0,-40228152,0,139799501,0,-817181165,0,-639119117,0,-323398634,0,571409550,0,423527592,0,-1062559,0,-88117651,0,-642627578,0,-430173126,0,-72237197,0,-210510988,0,-58203433,0,176041796,0,-927445753,0,301167323,0,13654611,0,452813689,0,-948496339,0,762831889,0,585781233,0,121672429,0,305331100,0,-32710333,0,154103581,0,-742321811,0,977205795,0,-19448911,0,223350161,0,-105031891,0,-82092954,0,808738276,0,937225992,0,-531650758,0,-59907326,0,30717348,0,-287864146,0,-387785638,0,-799513922,0,-63593117,0,100110001,0,427337831,0,945651565,0,236598252,0,690549125,0,-605262048,0,-223293680,0,299478746,0,-341470173,0,307757330,0,-433252346,0,710125911,0,-172869351,0,9983955,0,323916365,0,607915625,0,-314094541,0,-27998969,0,682676454,0,-689188271,0,-225077252,0,644321926,0,512199688,0,643906074,0,46604419,0,-142108815,0,529476487,0,909815091,0,734569750,0,169167645,0,574820238,0,181574243,0,395929881,0,329346463,0,236503503,0,-518934047,0,660068394,0,39488967,0,767760914,0,-153296228,0,131607844,0,-968055579,0,57414656,0,103890805,0,262746099,0,414410559,0,887714003,0,-681991456,0,264525958,0,313716146,0,417633607,0,298841184,0,-226956121,0,111856336,0,-136761109,0,-93177084,0,-303076013,0,-885503635,0,434214180,0,200788426,0,-571289317,0,644886298,0,520452372,0,337637872,0,-886554021,0,647989717,0,17926007,0,-249084488,0,-852425978,0,620849157,0,433572160,0,270718319,0,-595865552,0,242824034,0,212675460,0,812599908,0,693194593,0,370922888,0,886952955,0,-299137498,0,-285316552,0,266781052,0,254450987,0,104223891,0,518390975,0,-652855531,0,82841519,0,745926148,0};
		return toReturn;
	}
	
	private static void setup(int mod) {
		bernoullis = getBernoullis(mod);
	}
	
	public static int brutePowerSumMod(int begin, long n, int p, int mod) {
		int sum = 0;
		for(long i = begin; i <= n; i += 1) {
			sum = modAdd(sum, powMod(i, p, mod), mod);
		}
		return sum;
	}
	
	/*
	 * @requires p > 0 && n > 0 && mod > 0
	 * Returns the sum(1^p+2^p+...n^p) mod mod
	 */
	public static int faulhaberMod(long n, int p, int mod) {
		assert n > 0;
		assert p > 0;

		int sum = 0;
		for(int k = 1; k <= p+1; k += 1) {
			int summand = powMod(-1, kroneckerDelta(k, p), mod);
			summand = modMultiply(summand, binomialCoefficientMod(p+1, k, mod), mod);
			//int bernoulli = bernoulliMod(p+1-k, mod);
			int bernoulli = bernoullis[p+1-k];
			summand = modMultiply(summand, bernoulli, mod);
			//System.out.println((p+1-k)+" bernoulli: "+bernoulli);
			//System.out.println("array bernoulli: "+bernoullis[p+1-k]);
			summand = modMultiply(summand, powMod(n, k, mod), mod);
			sum = modAdd(summand, sum, mod);
		}
		return modDivide(sum,(p+1), mod);
	}
	
	private static int highwayConstruction(long n, int k) {
		if(n == 1) {return 0;}
		return faulhaberMod(n-1, k, 1000000009)-1;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            long n = in.nextLong();
            int k = in.nextInt();
            setup(1000000009);
            int result = highwayConstruction(n, k);
            System.out.println(result);
            //System.out.println("expected: "+brutePowerSumMod(2, n-1, k, 1000000009));
        }
        in.close();
	}

	
}
