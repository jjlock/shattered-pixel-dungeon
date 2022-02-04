package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Shopkeeper;
import com.shatteredpixel.shatteredpixeldungeon.items.Amulet;
import com.shatteredpixel.shatteredpixeldungeon.items.Ankh;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClothArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.HuntressArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CapeOfThorns;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.PotionBandolier;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.ArcaneBomb;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Firebomb;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Berry;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Blandfruit;
import com.shatteredpixel.shatteredpixeldungeon.items.journal.AlchemyPage;
import com.shatteredpixel.shatteredpixeldungeon.items.journal.GuidePage;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.CrystalKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.GoldenKey;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.AlchemicalCatalyst;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfExperience;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.BlizzardBrew;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfAquaticRejuvenation;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfCleansing;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.CeremonialCandle;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.CorpseDust;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfAccuracy;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfElements;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfAntiMagic;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Alchemize;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.AquaBlast;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfAggression;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfAugmentation;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfCorrosion;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.SpiritBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.AssassinsBlade;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.FishingSpear;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts.AdrenalineDart;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShopkeeperTest {

    @BeforeClass
    public static void setUp() {
        // set testing flag to prevent errors with loading sprites
        // no need to load sprites when testing
        SPDTest.testing = true;
        // the shopkeeper starts appearing at depth 6
        Dungeon.depth = 6;
    }

    @Test
    public void testSellPriceArmor() {
        // regular armor
        Item ca = new ClothArmor();
        // class armor
        Item ha = new HuntressArmor();

        assertEquals(200, Shopkeeper.sellPrice(ca));
        assertEquals(0, Shopkeeper.sellPrice(ha));
    }

    @Test
    public void testSellPriceArtifact() {
        Item atk = new AlchemistsToolkit();
        Item cot = new CapeOfThorns();

        assertEquals(1000, Shopkeeper.sellPrice(atk));
        assertEquals(1000, Shopkeeper.sellPrice(cot));
    }

    @Test
    public void testSellPriceBags() {
        Item mh = new MagicalHolster();
        Item pb = new PotionBandolier();

        assertEquals(600, Shopkeeper.sellPrice(mh));
        assertEquals(400, Shopkeeper.sellPrice(pb));
    }

    @Test
    public void testSellPriceBombs() {
        Item ab = new ArcaneBomb();
        Item fb = new Firebomb();

        assertEquals(700, Shopkeeper.sellPrice(ab));
        assertEquals(500, Shopkeeper.sellPrice(fb));
    }

    @Test
    public void testSellPriceFood() {
        Item berry = new Berry();
        Item bf = new Blandfruit();

        assertEquals(50, Shopkeeper.sellPrice(berry));
        assertEquals(200, Shopkeeper.sellPrice(bf));
    }

    @Test
    public void testSellPriceJournals() {
        Item ap = new AlchemyPage();
        Item gp = new GuidePage();

        assertEquals(0, Shopkeeper.sellPrice(ap));
        assertEquals(0, Shopkeeper.sellPrice(gp));
    }

    @Test
    public void testSellPriceKeys() {
        Item ck = new CrystalKey();
        Item gk = new GoldenKey();

        assertEquals(0, Shopkeeper.sellPrice(ck));
        assertEquals(0, Shopkeeper.sellPrice(gk));
    }

    @Test
    public void testSellPricePotions() {
        // regular
        Item ac = new AlchemicalCatalyst();
        Item poe = new PotionOfExperience();
        // brew
        Item bb = new BlizzardBrew();
        // elixir
        Item eoar = new ElixirOfAquaticRejuvenation();
        // exotic
        Item poc = new PotionOfCleansing();

        assertEquals(400, Shopkeeper.sellPrice(ac));
        assertEquals(300, Shopkeeper.sellPrice(poe));
        assertEquals(700, Shopkeeper.sellPrice(bb));
        assertEquals(800, Shopkeeper.sellPrice(eoar));
        assertEquals(500, Shopkeeper.sellPrice(poc));
    }

    @Test
    public void testSellPriceQuestItems() {
        Item cc = new CeremonialCandle();
        Item cd = new CorpseDust();

        assertEquals(0, Shopkeeper.sellPrice(cc));
        assertEquals(0, Shopkeeper.sellPrice(cd));
    }

    @Test
    public void testSellPriceRings() {
        Item roa = new RingOfAccuracy();
        Item roe = new RingOfElements();

        assertEquals(750, Shopkeeper.sellPrice(roa));
        assertEquals(750, Shopkeeper.sellPrice(roe));
    }

    @Test
    public void testSellPriceScrolls() {
        // regular
        Item soi = new ScrollOfIdentify();
        Item sol = new ScrollOfLullaby();
        // exotic
        ScrollOfAntiMagic soam = new ScrollOfAntiMagic();

        assertEquals(300, Shopkeeper.sellPrice(soi));
        assertEquals(300, Shopkeeper.sellPrice(sol));
        assertEquals(600, Shopkeeper.sellPrice(soam));
    }

    @Test
    public void testSellPriceSpells() {
        Item alchemize = new Alchemize();
        Item ab = new AquaBlast();

        assertEquals(50, Shopkeeper.sellPrice(alchemize));
        assertEquals(80, Shopkeeper.sellPrice(ab));
    }

    @Test
    public void testSellPriceStones() {
        Item stoneOfAggression = new StoneOfAggression();
        Item stoneOfAugmentation = new StoneOfAugmentation();

        assertEquals(150, Shopkeeper.sellPrice(stoneOfAggression));
        assertEquals(300, Shopkeeper.sellPrice(stoneOfAugmentation));
    }

    @Test
    public void testSellPriceWands() {
        Item wobw = new WandOfBlastWave();
        Item woc = new WandOfCorrosion();

        assertEquals(750, Shopkeeper.sellPrice(wobw));
        assertEquals(750, Shopkeeper.sellPrice(woc));
    }

    @Test
    public void testSellPriceWeapons() {
        // regular
        Item sb = new SpiritBow();
        // melee
        Item ab = new AssassinsBlade();
        // missile
        Item fs = new FishingSpear();
        Item ad = new AdrenalineDart(); // dart

        assertEquals(0, Shopkeeper.sellPrice(sb));
        assertEquals(800, Shopkeeper.sellPrice(ab));
        assertEquals(120, Shopkeeper.sellPrice(fs));
        assertEquals(80, Shopkeeper.sellPrice(ad));
    }

    @Test
    public void testSellPriceUniqueItems() {
        Item amulet = new Amulet();
        Item ankh = new Ankh();

        assertEquals(0, Shopkeeper.sellPrice(amulet));
        assertEquals(500, Shopkeeper.sellPrice(ankh));
    }
}
