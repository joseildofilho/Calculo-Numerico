package calcnumerico.ph.maquinabinaria.Helpers;

public class Simulator {
    private long B,t, under, over;
    public final long size;
    public Simulator(long b, long t, long under, long over) {
        this.B = b;
        this.t = t;
        this.under = under;
        this.over = over;

        size = (long) (2 * (b - 1) * Math.pow(b,(t-1)) * (over - under + 1) + 1);
    }

    public String sum(double x, double y) {
        double X = format(x);
        double Y = format(y);
        return String.valueOf(format(X+Y));
    }

    public String sub(double x, double y) {
        return sum(x,-y);
    }
    /*
    * Return the number formated to the system
    * */
    public double format(double x) throws ArithmeticException {
        String y = Double.toString(x > 0? x: -x);
        if(x == 0.0) return 0.0;
        int index = y.indexOf(".");

        if((x > 1 || x < -1)) {
            if(index >= over) throw new ArithmeticException("your value is too big" + x);
            String aux = y.substring(0, index);
            String aux2 = y.substring(index, (int) (t + 1));
            System.out.println(aux.length());
            if(x > 0) return Double.parseDouble(aux.concat(aux2));
            else return Double.parseDouble("-" + aux.concat(aux2));
        } else {
            int indexZeros = 0;
            for(char a: y.substring(index).toCharArray())
                if(a == '0') indexZeros ++;
                else break;
            if(-indexZeros < under) throw new ArithmeticException("your value is too small" + x
            );
            if(x > 0) return Double.parseDouble("0.".concat(y.substring(index + 1, (int) (index + this.t + 1))));
            else return Double.parseDouble("-0.".concat(y.substring(index + 1, (int) (index + this.t + 1))));

        }
    }
    public static void main(String args []) {
        Simulator sim = new Simulator(10,3,-5,5);
        System.out.println(sim.sum(0.403,7.65));
        System.out.println(sim.sub(1.39,0.987));
    }
}
