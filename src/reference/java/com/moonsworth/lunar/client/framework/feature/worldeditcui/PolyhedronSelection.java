package com.moonsworth.lunar.client.framework.feature.worldeditcui;

import java.util.ArrayList;
import java.util.List;

public final class PolyhedronSelection extends VersionedSelection {
   private List<int[]> field3 = new ArrayList<>();

   public PolyhedronSelection() {
   }

   public PolyhedronSelection method1(int[] items1) {
      this.field3.add(items1);
      return this;
   }

   public List<int[]> method3() {
      return this.field3;
   }
}
