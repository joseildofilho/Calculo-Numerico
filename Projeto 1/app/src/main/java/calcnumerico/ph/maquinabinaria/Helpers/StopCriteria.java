import net.objecthunter.exp4j.Expression;

public interface StopCriteria {
    boolean compare(Expression exp, double x);
    boolean compare(Exception exp, int a, int b);
}
