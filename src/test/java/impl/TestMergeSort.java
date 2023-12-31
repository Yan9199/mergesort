package impl;

import impl.functions.ConstantListToIntFunction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMergeSort {

    static final Comparator<Integer> CMP = Comparator.naturalOrder();

    @Test
    void testMergeSort() {
        List<Integer> toSort = new ArrayList<>(List.of(
                2, 3, 8, 9,
                1, 2, 4, 5,
                0, 1, 6, 9,
                7, 5, 8, 1)
        );

        List<Integer> expected = toSort.stream().sorted(CMP).toList();

        ConstantListToIntFunction<Integer> c = new ConstantListToIntFunction<>(4);

        MergeSort<Integer> m = new MergeSort<>(c, CMP);

        m.sort(toSort);

        assertEquals(expected, toSort);
    }

}
