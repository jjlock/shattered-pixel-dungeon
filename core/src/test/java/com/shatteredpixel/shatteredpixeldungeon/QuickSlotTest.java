package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClothArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.ArcaneBomb;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Berry;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Blandfruit;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.CrystalKey;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfExperience;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Alchemize;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.BattleAxe;

import com.watabou.utils.Bundle;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuickSlotTest {

    @BeforeClass
    public static void setUp() {
        // set testing flag to prevent errors with loading sprites
        // no need to load sprites when testing
        SPDTest.testing = true;
        // initialize the player character
        Dungeon.hero = new Hero();
    }

    @Before
    public void resetQuickSlot() {
        Dungeon.quickslot.reset();
    }

    @Test
    public void dropItemWhenQuickSlotIsEmptyPath() {
        Dungeon.quickslot.clearItem(null);
        for (int i = 0; i < QuickSlot.SIZE; i++) {
            assertNull(Dungeon.quickslot.getItem(i));
        }
    }

    @Test
    public void assignNonStackable_AssignNonStackable_Drop_Path() {
        Item at = new AlchemistsToolkit(); // non-stackable item
        Item wobw = new WandOfBlastWave(); // non-stackable item

        // assign non-stackable item to quick slot
        Dungeon.quickslot.setSlot(1, at);
        assertSame(at, Dungeon.quickslot.getItem(1));

        // reassign a new non-stackable item to quick slot
        Dungeon.quickslot.setSlot(1, wobw);
        assertSame(wobw, Dungeon.quickslot.getItem(1));

        // drop item
        wobw.detachAll(Dungeon.hero.belongings.backpack);
        assertNull(Dungeon.quickslot.getItem(1));
    }

    @Test
    public void assignStackable_AssignNonStackable_AssignStackable_AssignStackable_Drop_Path() {
        Item ab = new ArcaneBomb(); // stackable item
        Item ba = new BattleAxe(); // non-stackable item
        Item berry = new Berry(); // stackable item
        Item ck = new CrystalKey(); // stackable item

        // assign stackable item to quick slot
        Dungeon.quickslot.setSlot(1, ab);
        assertSame(ab, Dungeon.quickslot.getItem(1));

        // reassign non-stackable item to quick slot
        Dungeon.quickslot.setSlot(1, ba);
        assertSame(ba, Dungeon.quickslot.getItem(1));

        // reassign stackable item to quick slot
        Dungeon.quickslot.setSlot(1, berry);
        assertSame(berry, Dungeon.quickslot.getItem(1));

        // reassign stackable item to quick slot
        Dungeon.quickslot.setSlot(1, ck);
        assertSame(ck, Dungeon.quickslot.getItem(1));

        // drop item
        ck.detachAll(Dungeon.hero.belongings.backpack);
        assertNull(Dungeon.quickslot.getItem(1));
    }

    @Test
    public void assignStackable_UseItemNo_UseItemYes_AssignStackable_Drop_Path() {
        Item poe1 = new PotionOfExperience(); // stackable item
        // create a stack of two
        poe1.quantity(2);
        Item poe2 = new PotionOfExperience(); // stackable item

        // assign stackable item to quick slot
        Dungeon.quickslot.setSlot(1, poe1);
        assertSame(poe1, Dungeon.quickslot.getItem(1));

        // use one item from the stack
        poe1.detach(Dungeon.hero.belongings.backpack);
        assertSame(poe1, Dungeon.quickslot.getItem(1));
        assertEquals(1, poe1.quantity());

        // use one item from the stack
        // the stack should now be empty and a placeholder should be present in the slot
        poe1.detach(Dungeon.hero.belongings.backpack);
        assertTrue(Dungeon.quickslot.isPlaceholder(1));

        // reassign new stackable item to slot with placeholder
        Dungeon.quickslot.replacePlaceholder(poe2);
        assertSame(poe2, Dungeon.quickslot.getItem(1));

        // drop item
        poe2.detachAll(Dungeon.hero.belongings.backpack);
        assertNull(Dungeon.quickslot.getItem(1));
    }

    @Test
    public void assignStackable_UseItemYes_AssignNonStackable_Drop_Path() {
        Item soi = new ScrollOfIdentify(); // stackable item
        Item ca = new ClothArmor(); // non-stackable item

        // assign stackable item to quick slot
        Dungeon.quickslot.setSlot(1, soi);
        assertSame(soi, Dungeon.quickslot.getItem(1));

        // use one item from the stack
        // the stack should now be empty and a placeholder should be present in the slot
        soi.detach(Dungeon.hero.belongings.backpack);
        assertTrue(Dungeon.quickslot.isPlaceholder(1));

        // reassign non-stackable item to slot with placeholder
        Dungeon.quickslot.setSlot(1, ca);
        assertSame(ca, Dungeon.quickslot.getItem(1));

        // drop item
        ca.detachAll(Dungeon.hero.belongings.backpack);
        assertNull(Dungeon.quickslot.getItem(1));
    }

    @Test
    public void assignStackable_UseItemYes_Drop_Path() {
        Item alchemize = new Alchemize(); // stackable item

        // assign stackable item to quick slot
        Dungeon.quickslot.setSlot(1, alchemize);
        assertSame(alchemize, Dungeon.quickslot.getItem(1));

        // use one item from the stack
        // the stack should now be empty and a placeholder should be present in the slot
        alchemize.detach(Dungeon.hero.belongings.backpack);
        assertTrue(Dungeon.quickslot.isPlaceholder(1));

        // drop item
        Item placeholder = Dungeon.quickslot.getItem(1);
        placeholder.detachAll(Dungeon.hero.belongings.backpack);
        assertNull(Dungeon.quickslot.getItem(1));
    }

    @Test
    public void quickSlotDoesNotContainPlaceholder() {
        Item berry = new Berry();
        Dungeon.quickslot.setSlot(1, berry);
        assertTrue(Dungeon.quickslot.isNonePlaceholder(1));
    }

    @Test
    public void quickSlotSaveAndRestorePlaceholders() {
        Item ab = new ArcaneBomb();
        Item bf = new Blandfruit();
        Item ck = new CrystalKey();
        Item poe = new PotionOfExperience();
        Item[] qs = new Item[]{ab, bf, ck, poe};

        // place all items into quick slots
        Dungeon.quickslot.setSlot(0, ab);
        Dungeon.quickslot.setSlot(1, bf);
        Dungeon.quickslot.setSlot(2, ck);
        Dungeon.quickslot.setSlot(3, poe);

        // convert all the items to placeholders
        for (int i = 0; i < QuickSlot.SIZE; i++) {
            Dungeon.quickslot.convertToPlaceholder(Dungeon.quickslot.getItem(i));
        }

        Bundle bundle = new Bundle();
        // store the placeholders
        Dungeon.quickslot.storePlaceholders(bundle);
        // clear the quick slot array
        Dungeon.quickslot.reset();
        // restore the placeholders
        Dungeon.quickslot.restorePlaceholders(bundle);

        // check whether the placeholders are the same objects as before
        // and if they are in the correct positions
        for (int i = 0; i < QuickSlot.SIZE; i++) {
            assertTrue(Dungeon.quickslot.isPlaceholder(i));
        }
        assertTrue(Dungeon.quickslot.getItem(0) instanceof ArcaneBomb);
        assertTrue(Dungeon.quickslot.getItem(1) instanceof Blandfruit);
        assertTrue(Dungeon.quickslot.getItem(2) instanceof CrystalKey);
        assertTrue(Dungeon.quickslot.getItem(3) instanceof PotionOfExperience);
    }
}
