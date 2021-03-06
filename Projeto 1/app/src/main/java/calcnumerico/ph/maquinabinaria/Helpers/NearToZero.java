package calcnumerico.ph.maquinabinaria.Helpers;

import net.objecthunter.exp4j.Expression;

import kotlin.NotImplementedError;

public class NearToZero implements StopCriteria {
    private double diff;
    public NearToZero(double diff) {
        this.diff = diff;
    }
    /**
     * while the difference is smaller than 0, then true, else false
     * means that, the y of expression it's bigger than the setted value of @diff
     * */
    public boolean compare(Expression exp, double x) {
        return Math.abs(exp.setVariable("x", x).evaluate()) - this.diff > 0;
    }

    public boolean compare(Exception exp, int a, int b) throws NotImplementedError {
        throw new NotImplementedError();
    }
}
