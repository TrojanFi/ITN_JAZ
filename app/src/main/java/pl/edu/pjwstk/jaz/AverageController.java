package pl.edu.pjwstk.jaz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


@RestController
public class AverageController {

    @GetMapping("average") //value = "numbers", required = false
    public AverageResult getAverage(@RequestParam(value = "numbers", required = false) String numbers) {
        if ( numbers == null ) return new AverageResult("Please put parameters.");
        String[] query = numbers.split(",");
        double[] queryInt = new double[query.length];
        double sum = 0;
        for(int i = 0; i < query.length; i++){
            queryInt[i] = Integer.parseInt(query[i]);
            sum += queryInt[i];
        }

       /* Locale locale  = new Locale("en", "UK");
        String pattern = "0.#";
        DecimalFormat decimalFormat = (DecimalFormat)
                NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);*/
        // DecimalFormat format = new DecimalFormat("0.#");
        // return new AverageResult(String.valueOf(sum));
        return new AverageResult("Average equals: " + (new BigDecimal(sum/queryInt.length).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros()));

    }
}
