package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates;

import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator;

public class Coordinates4 {
   private final Fishing2Iterator field1;
   private final Fishing2Loader<?, ?> field2;
   private final int field3;

   public Coordinates4(Fishing2Iterator iterator, Fishing2Loader<?, ?> fishing2Loader, int value) {
      this.field1 = iterator;
      this.field2 = fishing2Loader;
      this.field3 = value;
   }

   public Fishing2Iterator method1() {
      return this.field1;
   }

   public Fishing2Loader<?, ?> method2() {
      return this.field2;
   }

   public int method3() {
      return this.field3;
   }
}
