package calcnumerico.ph.maquinabinaria.Helpers;

import net.objecthunter.exp4j.Expression;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        if(Math.abs(exp.setVariable("x",x).evaluate()) - this.diff > 0)
            return true;
        return false;
    }

    public boolean compare(Exception exp, int a, int b) throws NotImplementedException {
        throw new NotImplementedException();
    }
}
