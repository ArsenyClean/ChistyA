package ru.job4j.servic;

import org.junit.Test;
import ru.job4j.cup.TraidSystem;

import java.io.IOException;

public class CupTest {

    @Test
    public void test() throws IOException {
        TraidSystem traidSystem = new TraidSystem();
        traidSystem.add(12,"fdsf",1,21,4);
        traidSystem.add(11,"fdsf",1,21,4);
        traidSystem.add(14,"fdsf",1,21,4);
        traidSystem.add(15,"fdsf",1,21,4);
        traidSystem.add(17,"fdsf",1,21,4);
        traidSystem.add(18,"fdsf",1,21,4);
        traidSystem.add(1,"fdsf",-1,22,3);
        traidSystem.add(2,"fdsf",-1,20,7);
        traidSystem.add(3,"fdsf",-1,22,3);
        traidSystem.add(4,"fdsf",-1,22,5);
        traidSystem.add(15,"qe",1,21,4);
        traidSystem.add(17,"qe",1,21,4);
        traidSystem.add(18,"qe",1,21,4);
        traidSystem.add(1,"qe",-1,22,3);
        traidSystem.add(2,"qe",-1,20,7);
        System.out.println(traidSystem.toString());
        System.out.println(traidSystem.delete(14));
        System.out.println(traidSystem.toString());
    }
}