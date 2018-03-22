package calcnumerico.ph.maquinabinaria.Helpers;

import net.objecthunter.exp4j.Expression;

import kotlin.NotImplementedError;

public interface StopCriteria {
    boolean compare(Expression exp, double x);
    boolean compare(Exception exp, int a, int b) throws NotImplementedError;
}
