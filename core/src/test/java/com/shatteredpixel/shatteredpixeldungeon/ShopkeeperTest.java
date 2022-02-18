package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Shopkeeper;
import com.shatteredpixel.shatteredpixeldungeon.items.Amulet;
import com.shatteredpixel.shatteredpixeldungeon.items.Ankh;
import com.shatteredpixel.shatteredpixeldungeon.items.BrokenSeal;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.*;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Affection;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
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
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfAccuracy;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfElements;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfAntiMagic;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Alchemize;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.AquaBlast;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfAggression;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfAugmentation;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfCorrosion;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.SpiritBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Blazing;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.AssassinsBlade;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.FishingSpear;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts.AdrenalineDart;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ShopkeeperTest {

    @BeforeClass
    public static void setUp() {
        // set testing flag to prevent errors with loading sprites
        // no need to load sprites when testing
        SPDTest.testing = true;
        // the shopkeeper starts appearing at depth 6
        Dungeon.depth = 6;
        // initialize the player character
        Dungeon.hero = new Hero();
    }

    @Test
    public void armorSellPrice() {
        // regular armor
        Item ca = new ClothArmor();
        // class armor
        Item ha = new HuntressArmor();

        assertEquals(200, Shopkeeper.sellPrice(ca));
        assertEquals(0, Shopkeeper.sellPrice(ha));
    }

    @Test
    public void artifactsSellPrice() {
        Item atk = new AlchemistsToolkit();
        Item cot = new CapeOfThorns();

        assertEquals(1000, Shopkeeper.sellPrice(atk));
        assertEquals(1000, Shopkeeper.sellPrice(cot));
    }

    @Test
    public void bagsSellPrice() {
        Item mh = new MagicalHolster();
        Item pb = new PotionBandolier();

        assertEquals(600, Shopkeeper.sellPrice(mh));
        assertEquals(400, Shopkeeper.sellPrice(pb));
    }

    @Test
    public void bombsSellPrice() {
        Item ab = new ArcaneBomb();
        Item fb = new Firebomb();

        assertEquals(700, Shopkeeper.sellPrice(ab));
        assertEquals(500, Shopkeeper.sellPrice(fb));
    }

    @Test
    public void foodSellPrice() {
        Item berry = new Berry();
        Item bf = new Blandfruit();

        assertEquals(50, Shopkeeper.sellPrice(berry));
        assertEquals(200, Shopkeeper.sellPrice(bf));
    }

    @Test
    public void journalsSellPrice() {
        Item ap = new AlchemyPage();
        Item gp = new GuidePage();

        assertEquals(0, Shopkeeper.sellPrice(ap));
        assertEquals(0, Shopkeeper.sellPrice(gp));
    }

    @Test
    public void keysSellPrice() {
        Item ck = new CrystalKey();
        Item gk = new GoldenKey();

        assertEquals(0, Shopkeeper.sellPrice(ck));
        assertEquals(0, Shopkeeper.sellPrice(gk));
    }

    @Test
    public void potionsSellPrice() {
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
    public void questItemsSellPrice() {
        Item cc = new CeremonialCandle();
        Item cd = new CorpseDust();

        assertEquals(0, Shopkeeper.sellPrice(cc));
        assertEquals(0, Shopkeeper.sellPrice(cd));
    }

    @Test
    public void ringsSellPrice() {
        Item roa = new RingOfAccuracy();
        Item roe = new RingOfElements();

        assertEquals(750, Shopkeeper.sellPrice(roa));
        assertEquals(750, Shopkeeper.sellPrice(roe));
    }

    @Test
    public void scrollsSellPrice() {
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
    public void spellsSellPrice() {
        Item alchemize = new Alchemize();
        Item ab = new AquaBlast();

        assertEquals(50, Shopkeeper.sellPrice(alchemize));
        assertEquals(80, Shopkeeper.sellPrice(ab));
    }

    @Test
    public void stonesSellPrice() {
        Item stoneOfAggression = new StoneOfAggression();
        Item stoneOfAugmentation = new StoneOfAugmentation();

        assertEquals(150, Shopkeeper.sellPrice(stoneOfAggression));
        assertEquals(300, Shopkeeper.sellPrice(stoneOfAugmentation));
    }

    @Test
    public void wandsSellPrice() {
        Item wobw = new WandOfBlastWave();
        Item woc = new WandOfCorrosion();

        assertEquals(750, Shopkeeper.sellPrice(wobw));
        assertEquals(750, Shopkeeper.sellPrice(woc));
    }

    @Test
    public void weaponsSellPrice() {
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
    public void uniqueItemsSellPrice() {
        Item amulet = new Amulet();
        Item ankh = new Ankh();

        assertEquals(0, Shopkeeper.sellPrice(amulet));
        assertEquals(500, Shopkeeper.sellPrice(ankh));
    }

    @Test
    public void armorSellPriceWithAugmentations() {
        // test armor with seal
        Armor wa = new WarriorArmor();
        wa.affixSeal(new BrokenSeal());
        assertNotNull(wa.checkSeal());
        assertEquals(0, Shopkeeper.sellPrice(wa));

        // test armor with "good" glyph and level > 0
        Armor la1 = new LeatherArmor();
        la1.level(1);
        la1.levelKnown = true;
        la1.glyph = new Affection();
        assertEquals(1200, Shopkeeper.sellPrice(la1));

        // test armor with a curse
        Armor la2 = new LeatherArmor();
        la2.cursed = true;
        la2.cursedKnown = true;
        assertEquals(200, Shopkeeper.sellPrice(la2));
    }

    @Test
    public void artifactsSellPriceWithAugmentations() {
        // test artifact with level > 0 and a curse
        Artifact at = new AlchemistsToolkit();
        at.level(1);
        at.cursed = true;
        at.cursedKnown = true;
        assertEquals(500, Shopkeeper.sellPrice(at));
    }

    @Test
    public void ringsSellPriceWithAugmentations() {
        // test ring with a curse
        Ring roa1 = new RingOfAccuracy();
        roa1.cursed = true;
        roa1.cursedKnown = true;
        assertEquals(370, Shopkeeper.sellPrice(roa1));

        // test ring with level known and level > 0
        Ring roa2 = new RingOfAccuracy();
        roa2.level(1);
        roa2.levelKnown = true;
        assertEquals(1500, Shopkeeper.sellPrice(roa2));

        // test ring with level known and level < 0
        Ring roa3 = new RingOfAccuracy();
        roa3.level(-2);
        roa3.levelKnown = true;
        assertEquals(250, Shopkeeper.sellPrice(roa3));
    }

    @Test
    public void wandsSellPriceWithAugmentations() {
        // test wand with a curse
        Wand wobw1 = new WandOfBlastWave();
        wobw1.cursed = true;
        wobw1.cursedKnown = true;
        assertEquals(370, Shopkeeper.sellPrice(wobw1));

        // test ring with level known and level > 0
        Wand wobw2 = new WandOfBlastWave();
        wobw2.level(1);
        wobw2.levelKnown = true;
        assertEquals(1500, Shopkeeper.sellPrice(wobw2));

        // test ring with level known and level < 0
        Wand wobw3 = new WandOfBlastWave();
        wobw3.level(-2);
        wobw3.levelKnown = true;
        assertEquals(250, Shopkeeper.sellPrice(wobw3));
    }

    @Test
    public void meleeWeaponsSellPriceWithAugmentations() {
        // test melee weapon with an enchantment and level > 0
        MeleeWeapon ab1 = new AssassinsBlade();
        ab1.enchantment = new Blazing();
        ab1.level(1);
        ab1.levelKnown = true;
        assertEquals(2400, Shopkeeper.sellPrice(ab1));

        // test melee weapon with a curse
        MeleeWeapon ab2 = new AssassinsBlade();
        ab2.cursed = true;
        ab2.cursedKnown = true;
        ab2.level(1);
        ab2.levelKnown = true;
        assertEquals(800, Shopkeeper.sellPrice(ab2));
    }
}
