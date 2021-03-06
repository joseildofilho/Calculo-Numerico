package calcnumerico.ph.maquinabinaria.Helpers;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import kotlin.NotImplementedError;


/**
*   This class it's a Newton-Raphson numerical method, the now and only function
* it's make a calculation of one number.
*
* @version 0.0.000001
* @author Joseildo Mariano S Filho
* */
public class NewtonRaphson implements NumericMethod  {

    private String expression;
    private Expression expressionObj;
    private double firstX = 0;
    private int iterations;
    private StopCriteria stopCriteria;
    private Diferenciation d;
    private double lastResult = Double.NaN;
    /**
    * @param expression gets a expression in string and parse to a respective object to treat the string.
    * @param firstX it's the first 'kick' of value to the method starts.
    * @param iterations number of iterations, the only stop criteria treat inside of this class
    * @param stopCriteria the interfaces that would used as stop criteria to the methods, futher number of @iterations
    * */
    public NewtonRaphson(String expression, double firstX, int iterations, StopCriteria stopCriteria) {
        this.expression = expression;
        this.firstX = firstX;
        this.iterations = iterations;
        this.stopCriteria = stopCriteria;
        this.expressionObj = new ExpressionBuilder(this.expression).variable("x").build();
        this.d = new Diferenciation(this.expressionObj);
    }
    /**
     * Executes the methods, sounds like good not have any argument, because the parameters of contructor sounds enough
     * */
    public double run() {
        double x = firstX, aux;
        for(int i = 0; i < iterations; i++) {
            aux = calculateNext(x);
            if(!stopCriteria.compare(expressionObj, aux)) break;
            x = aux;
        }
        lastResult = x;
        return x;
    }

    private double calculateNext(double x) {
       return x - (expressionObj.setVariable("x", x).evaluate() / d.differenciate(x));
    }

    @Override
    public int hashCode() {
       throw new NotImplementedError();
    }

    @Override
    public boolean equals(Object obj) {
        throw new NotImplementedError();
    }

    @Override
    public String toString() {
        throw new NotImplementedError();
    }

}
