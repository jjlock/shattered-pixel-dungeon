package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Bat;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.levels.SewerLevel;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.BArray;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuickSlotButtonTest {

    @BeforeClass
    public static void setUp() {
        // set testing flag to prevent errors with loading sprites
        // no need to load sprites when testing
        SPDTest.testing = true;
        // initialize the player character
        Dungeon.hero = new Hero();
    }

    @Before
    public void reset() {
        Dungeon.hero = new Hero();
        Dungeon.level = new SewerLevel();
        System.out.println("before");
    }

    @Test
    public void autoAimTest() {
        Char bat = new Bat();
        Item item = new Item();
        int[] distanceMap = {1, 1, 1, 1};

        // set up level
        int width = 10;
        int height = 10;
        int length = width * height;
        Dungeon.level.setSize(width, height);
        // make every tile passable
        Dungeon.level.passable = BArray.not(new boolean[length], null);

        // set positions of hero and mob
        Dungeon.hero.pos = 25;
        bat.pos = 28;

        assertEquals(28, QuickSlotButton.autoAim(bat, item, distanceMap));

        // change the position of the mob to be outside the range of the hero based on the distance map
        bat.pos = 30;
        assertEquals(-1, QuickSlotButton.autoAim(bat, item, distanceMap));
    }
}
