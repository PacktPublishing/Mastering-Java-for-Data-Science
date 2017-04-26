package chapter07;

import java.util.Arrays;

import org.apache.commons.lang3.Validate;

import smile.validation.AUC;

public class Metrics {

    public static double auc(double[] actual, double[] predicted) {
        Validate.isTrue(actual.length == predicted.length, "the lengths don't match");
        int[] truth = Arrays.stream(actual).mapToInt(i -> (int) i).toArray();
        double result = AUC.measure(truth, predicted);
        if (result < 0.5) {
            return 1 - result;
        } else {
            return result;
        }
    }

}
