package com.moonsworth.lunar.client.framework.feature.worldeditcui;

import java.util.ArrayList;
import java.util.List;

public final class Worldeditcui2Base23 extends Worldeditcui2Base2 {
   private List<int[]> field3 = new ArrayList<>();

   public Worldeditcui2Base23 method1(int[] var1) {
      this.field3.add(var1);
      return this;
   }

   public List<int[]> method3() {
      return this.field3;
   }
}
