package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;

public class PathWorldView {
   private final Itemcounter6 field1;
   private final Horsestats20Extension2 field2;
   private final Vector3iBridge.Extension field3;

   public PathWorldView(Itemcounter6 var1, Horsestats20Extension2 var2) {
      this(var1, var2, Bridge.method8().method9(0, 0, 0));
   }

   public PathWorldView(Itemcounter6 var1, Horsestats20Extension2 var2, Vector3iBridge.Extension extension) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = extension;
   }

   public Bridge2_17 method1(Vector3iBridge var1) {
      return this.field1.method2(var1);
   }

   public Itemcounter6 method2() {
      return this.field1;
   }

   public Horsestats20Extension2 method3() {
      return this.field2;
   }

   public Vector3iBridge.Extension method4() {
      return this.field3;
   }
}
