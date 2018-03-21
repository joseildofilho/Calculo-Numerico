package calcnumerico.ph.maquinabinaria.Helpers;


public class BinConverter {

    public static String converter(double value) {
        if (value > 0) {
            int valueInt = (int) value;
            double valueFloat = value - valueInt;
            return converterInt(valueInt) + "." + converterFloat(valueFloat);
        }
        if (value < 0) {
            int valueInt = (int) (value * -1);
            double valueFloat = (value * -1) - valueInt;
            return "-" + converterInt(valueInt) + "." + converterFloat(valueFloat);
        }
        return "0";
    }

    private static String converterFloat(double value) {
        StringBuilder stringBuilder = new StringBuilder();
        if (value == 0) {
            stringBuilder.append("0");
            return stringBuilder.toString();
        }
        converterFloatAux(stringBuilder, value);
        return stringBuilder.toString();

    }

    private static void converterFloatAux(StringBuilder stringBuilder, double value) {
        if (value == 0)
            return;
        else if (value * 2 < 1) {
            stringBuilder.append("0");
            converterFloatAux(stringBuilder, value * 2);
        } else if (value * 2 >= 1) {
            stringBuilder.append("1");
            converterFloatAux(stringBuilder, (value * 2) - 1);
        }
    }

    private static String converterInt(int value) {
        StringBuilder stringBuilder = new StringBuilder();
        if (value == 0) {
            stringBuilder.append("0");
            return stringBuilder.toString();
        }
        converterIntAux(stringBuilder, value);
        return stringBuilder.reverse().toString();

    }

    private static void converterIntAux(StringBuilder stringBuilder, int value) {
        if (value == 0)
            return;
        else if (value % 2 == 0)
            stringBuilder.append("0");
        else if (value % 2 == 1)
            stringBuilder.append("1");
        converterIntAux(stringBuilder, value / 2);
    }
}
