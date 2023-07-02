package impl.functions;

import java.util.List;

@FunctionalInterface
public interface ListToIntFunction<T> {

    int apply(List<T> elements);
}
