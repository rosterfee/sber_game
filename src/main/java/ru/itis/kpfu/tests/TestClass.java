package ru.itis.kpfu.tests;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.itis.kpfu.models.Player;

public class TestClass {

    private Player player;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @BeforeClass
    public void setUp() {
        player = new Player();
    }

    @Test
    public void assertException() {
        exceptionRule.expect(IllegalArgumentException.class);
        player.go("прямо");
    }

}
