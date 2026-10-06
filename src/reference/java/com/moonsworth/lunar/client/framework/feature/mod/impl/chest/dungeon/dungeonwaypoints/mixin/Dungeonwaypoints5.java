package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin;

public class Dungeonwaypoints5 {
   private final String field1;
   private final Dungeonwaypoints4 field2;
   private final Gui2Extension field3;

   public Dungeonwaypoints5(String var1, Dungeonwaypoints4 dungeonwaypoints4, Gui2Extension gui2) {
      this.field1 = var1;
      this.field2 = dungeonwaypoints4;
      this.field3 = gui2;
   }

   public Dungeonwaypoints5 method1(Dungeonwaypoints4 var1) {
      return new Dungeonwaypoints5(this.field1, var1, this.field3);
   }

   public Dungeonwaypoints5 method2(Gui2Extension var1) {
      return new Dungeonwaypoints5(this.field1, this.field2, var1);
   }

   public String name() {
      return this.field1;
   }

   public Dungeonwaypoints4 method3() {
      return this.field2;
   }

   public Gui2Extension method4() {
      return this.field3;
   }
}
