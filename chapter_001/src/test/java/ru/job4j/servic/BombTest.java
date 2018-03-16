package ru.job4j.servic;

import org.junit.Test;
import ru.job4j.currency.thread.bomber.BomberMan;

public class BombTest {
@Test
    public void testOf() {
        BomberMan bomberMan = new BomberMan(7, 12, true);
        bomberMan.starter(20, 2);
    }
}
