package calcnumerico.ph.maquinabinaria.Helpers;


import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Bissection {

    public static double calculate(String func, double a, double b, double tol, int N) throws NoRootException {
        Expression e = new ExpressionBuilder(func).variable("x").build();
        e.setVariable("x", a);
        double fa = e.evaluate();
        e.setVariable("x", b);
        double fb = e.evaluate();
        if(fa*fb >= 0){
            throw new NoRootException("Não há raizes entre a e b");
        }

        boolean done = false;
        double xm = (a+b)/2;

        int i = 1;

        while (Math.abs(a-b) > tol && (!done || N != 0)){
            e.setVariable("x", xm);
            double fxm = e.evaluate();
            if(fa*fxm < 0){
                b = xm;
                fb = fxm;
                xm = (a+b)/2;
            }else if(fxm*fb < 0){
                a = xm;
                fa = fxm;
                xm = (a+b)/2;
            }else{
                done = true;
            }

            N--;
            i++;
        }

        return xm;
    }
}
class NoRootException extends Exception{
    NoRootException(String message){
        super(message);
    }
}
