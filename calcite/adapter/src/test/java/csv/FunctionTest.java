package csv;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

interface Function<R> {

}

interface Function1<T0, R> extends Function<R> {

    Function1<Object, Object> IDENTITY = v0 -> v0;

    R apply(T0 a0);
}



public class FunctionTest {

    @Test
    public void testFunction() {
        List<Function1<String, Number>> function = getFunction();

        for (Function1<String, Number> udf : function) {
            Number a = udf.apply("你好");
            System.out.println(a);
        }
    }

    List<Function1<String, Number>> getFunction() {
        return Collections.singletonList(a0 -> {
            byte[] bytes = a0.getBytes(StandardCharsets.UTF_8);
            int number = 0;
            for (byte aByte : bytes) {
                number += aByte;
            }
            return number;
        });
    }
}
