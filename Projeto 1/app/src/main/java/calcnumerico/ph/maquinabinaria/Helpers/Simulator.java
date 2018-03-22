package calcnumerico.ph.maquinabinaria.Helpers;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.pow;

public class Simulator {
    private int base;
    private int precision;
    private int lower;
    private int upper;
    private double epslon;

    public double getMax() {
        return max;
    }

    private double max;

    public Simulator(int base, int precision, int lower, int upper) {
        this.base = base;
        this.precision = precision;
        this.lower = lower;
        this.upper = upper;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0.");
        for(int i = 0; i < precision - 1; i++){
            stringBuilder.append("0");
        }
        stringBuilder.append("1");
        this.epslon = Double.parseDouble(stringBuilder.toString()) * pow(base, lower);

        stringBuilder = new StringBuilder();
        for(int i = 0; i < precision; i++){
            stringBuilder.append("9");
        }
        this.max = Double.parseDouble(stringBuilder.toString()) * pow(base, upper);
    }

    public String operations(String exp) throws ArithmeticException {
        Expression e = new ExpressionBuilder(exp).build();
        return normalize(e.evaluate());
    }

    public String normalize(double value) throws ArithmeticException {
        boolean flag = false;
        if (value < 0) {
            flag = true;
            value = Math.abs(value);
        }
        int exp = 0;
        if (value < 1) {
            if (Math.abs(value) < Math.abs(epslon)) {
                throw new ArithmeticException("Value " + value + " too low for precision " + precision);
            } else {
                while (value < 0.1) {
                    exp--;
                    value *= base;
                }
                if (exp < lower)
                    throw new ArithmeticException("Value " + value + " too low for lower exp " + lower);
            }
        } else {
            if (Math.abs(value) > Math.abs(max)) {
                throw new ArithmeticException("Value " + value + " too high for precision " + precision);
            } else {
                while (value >= 1) {
                    exp++;
                    value /= base;
                }
                if (exp > upper)
                    throw new ArithmeticException("Value " + value + " high for upper exp " + upper);
            }
        }

        value = Math.round(value * pow(base, upper-lower+1)) / pow(base, upper-lower+1);

        if(String.valueOf(value).length() > precision + 2)
            throw new ArithmeticException("Value " + value + " can't be represented with precison " + precision);

        if(flag)
            return "-" + value + "*" + base + "e" + exp;
        else
            return value + "*" + base + "e" + exp;

    }
    public double normalizeDouble(double value) throws ArithmeticException {
        boolean flag = false;
        if (value < 0) {
            flag = true;
            value = Math.abs(value);
        }
        int exp = 0;
        if (value < 1) {
            if (Math.abs(value) < Math.abs(epslon)) {
                throw new ArithmeticException("Value " + value + " too low for precision " + precision);
            } else {
                while (value < 0.1) {
                    exp--;
                    value *= base;
                }
                if (exp < lower)
                    throw new ArithmeticException("Value " + value + " too low for lower exp " + lower);
            }
        } else {
            if (Math.abs(value) > Math.abs(max)) {
                throw new ArithmeticException("Value " + value + " too high for precision " + precision);
            } else {
                while (value >= 1) {
                    exp++;
                    value /= base;
                }
                if (exp > upper)
                    throw new ArithmeticException("Value " + value + " high for upper exp " + upper);
            }
        }

        value = Math.round(value * pow(base, upper-lower+1)) / pow(base, upper-lower+1);

        if(String.valueOf(value).length() > precision + 2)
            throw new ArithmeticException("Value " + value + " can't be represented with precison " + precision);

        if(flag)
            return (value * pow(base, exp)) * -1;
        else
            return (value * pow(base, exp));

    }

    public String[] getAllValues() {
        ArrayList<String> strings = new ArrayList<>();
        long max = base - 1;
        for (int i = 0; i < precision - 1; i++) {
            max *= base;
        }
        max *= 2 * (upper - lower + 1);

        StringBuilder stringBuilder = new StringBuilder();
        double scale_factor = 1 * pow(base, lower);
        double value = -0.1 * pow(base, lower);
        double epslon_base = -1 * epslon;
        String normalized = "";
        for (int i = 0; i < max; i++) {
            if(value/scale_factor == -1){
                scale_factor *= base;
                epslon_base *= base;
            }
            if(scale_factor == pow(base, (upper-lower) + 1)){
                break;
            }
            try {
                normalized = normalize(value);
                strings.add(normalized);
            }catch (ArithmeticException e){
                break;
            }
            value += epslon_base;
            value = Math.round(value * pow(base, (upper-lower)+1)) / pow(base, (upper-lower)+1);
        }

        Collections.reverse(strings);
        strings.add("0");
        value = 0.1 * pow(base, lower);
        epslon_base = epslon;
        scale_factor = 1 * pow(base, lower);
        normalized = "";
        for (int i = 0; i < max; i++) {
            if(value/scale_factor == 1){
                scale_factor *= base;
                epslon_base *= base;
            }
            if(scale_factor == pow(base, (upper-lower) + 1))
                break;
            try {
                normalized = normalize(value);
                strings.add(normalized);
            }catch (ArithmeticException e){
                break;
            }
            value += epslon_base;
            value = Math.round(value * pow(base, upper-lower+1)) / pow(base, upper-lower+1);
        }
        return strings.toArray(new String[strings.size()]);
    }
    public ArrayList<Double> getAllValuesDouble() {
        ArrayList<Double> values = new ArrayList<>();
        long max = base - 1;
        for (int i = 0; i < precision - 1; i++) {
            max *= base;
        }
        max *= 2 * (upper - lower + 1);

        StringBuilder stringBuilder = new StringBuilder();
        double scale_factor = 1 * pow(base, lower);
        double value = -0.1 * pow(base, lower);
        double epslon_base = -1 * epslon;
        for (int i = 0; i < max; i++) {
            if(value/scale_factor == -1){
                scale_factor *= base;
                epslon_base *= base;
            }
            if(scale_factor == pow(base, (upper-lower) + 1)){
                break;
            }
            try {
                values.add(normalizeDouble(value));
            }catch (ArithmeticException e){
                break;
            }
            value += epslon_base;
            value = Math.round(value * pow(base, (upper-lower)+1)) / pow(base, (upper-lower)+1);
        }

        Collections.reverse(values);
        value = 0.1 * pow(base, lower);
        epslon_base = epslon;
        scale_factor = 1 * pow(base, lower);
        for (int i = 0; i < max; i++) {
            if(value/scale_factor == 1){
                scale_factor *= base;
                epslon_base *= base;
            }
            if(scale_factor == pow(base, (upper-lower) + 1))
                break;
            try {
                values.add(normalizeDouble(value));
            }catch (ArithmeticException e){
                break;
            }
            value += epslon_base;
            value = Math.round(value * pow(base, upper-lower+1)) / pow(base, upper-lower+1);
        }
        return values;
    }
}
