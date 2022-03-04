package com.shatteredpixel.shatteredpixeldungeon;

import com.watabou.utils.Bundle;

import org.junit.Test;
import org.mockito.ArgumentMatchers;

import static org.mockito.Mockito.*;

public class StatisticsTest {

    @Test
    public void saveAndRestoreStatisticsTest() {
        Bundle bundle = mock(Bundle.class);
        // the keys in the bundle where the values are stored under
        String[] keys = {
                "score",
                "maxDepth",
                "enemiesSlain",
                "foodEaten",
                "potionsCooked",
                "priranhas",
                "ankhsUsed",
                "upgradesUsed",
                "sneakAttacks",
                "thrownAssists",
                "spawnersAlive",
                "duration",
                "qualifiedForNoKilling",
                "amuletObtained"
        };

        // simulate saving the statistics
        Statistics.storeInBundle(bundle);

        // verify put() was called with every key only once
        for (String key : keys) {
            if (key.equals("duration")) {
                verify(bundle, times(1)).put(ArgumentMatchers.eq(key), ArgumentMatchers.anyFloat());
            } else if (key.equals("qualifiedForNoKilling") || key.equals("amuletObtained")) {
                verify(bundle, times(1)).put(ArgumentMatchers.eq(key), ArgumentMatchers.anyBoolean());
            } else {
                verify(bundle, times(1)).put(ArgumentMatchers.eq(key), ArgumentMatchers.anyInt());
            }
        }

        // simulate loading the statistics
        Statistics.restoreFromBundle(bundle);

        // verify getX() methods were called with the appropriate key only once
        for (String key : keys) {
            if (key.equals("duration")) {
                verify(bundle, times(1)).getFloat(ArgumentMatchers.eq(key));
            } else if (key.equals("qualifiedForNoKilling") || key.equals("amuletObtained")) {
                verify(bundle, times(1)).getBoolean(ArgumentMatchers.eq(key));
            } else {
                verify(bundle, times(1)).getInt(ArgumentMatchers.eq(key));
            }
        }
    }
}
