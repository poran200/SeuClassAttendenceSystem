package com.seu.edu.bd.cas;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class SampleTest {

    @Test
    void fillerTest() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);

        long count = list.stream().filter(integer -> integer == 2).count();
        assertEquals(count,2L);
    }
}
