package com.moonsworth.lunar.client.framework.feature.potioneffects;

import com.google.common.collect.ImmutableMap.Builder;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Map;

@VersionGate(max = 5)
public class Potioneffects {
   private static final Map<Integer, Integer> field1 = new Builder()
      .put(1, -11141121)
      .put(2, -10851199)
      .put(3, -2506685)
      .put(4, -11910633)
      .put(5, -7134173)
      .put(6, -515037)
      .put(7, -5675670)
      .put(8, -1)
      .put(9, -11199158)
      .put(10, -3318613)
      .put(11, -7387667)
      .put(12, -1795526)
      .put(13, -13741415)
      .put(14, -8420462)
      .put(15, -14737629)
      .put(16, -14737503)
      .put(17, -10979757)
      .put(18, -12038840)
      .put(19, -11627727)
      .put(20, -13293017)
      .put(21, -492253)
      .put(22, -14331227)
      .put(23, -515037)
      .put(24, -7036831)
      .put(25, -3211265)
      .put(26, -10895098)
      .put(27, -4152243)
      .build();

   private Potioneffects() {
   }

   public static Integer get(int index0) {
      return field1.get(index0);
   }
}
