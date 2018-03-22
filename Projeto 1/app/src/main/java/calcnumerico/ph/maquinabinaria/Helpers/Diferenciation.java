package calcnumerico.ph.maquinabinaria.Helpers;


import net.objecthunter.exp4j.Expression;

import static java.lang.Math.nextAfter;

public class Diferenciation {
    private Expression exp = null;
    public Diferenciation(Expression f) {
        this.exp = f;
    }
    public double differenciate(double x) {
        double f1 = exp.setVariable("x",nextAfter(x,Double.POSITIVE_INFINITY)).evaluate();
        double f2 = exp.setVariable("x",x).evaluate();
        return (f1 - f2) / (nextAfter(x,Double.POSITIVE_INFINITY) - x);

    }
}
