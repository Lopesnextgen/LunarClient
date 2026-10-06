package com.moonsworth.lunar.client.cosmetics.gecko;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.render.turbo.TurboPathFollower;
import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;

public class TeleportToOwnerTask extends AbstractTimedTask {
   @JsonProperty("min_radius")
   private int field1 = 2;
   @JsonProperty("max_radius")
   private int field2 = 4;
   @JsonProperty("duration")
   private int duration = 10;

   @Override
   public boolean method2(EmoteDefinition var1, PathFilter var2) {
      TurboPathFollower var3 = var2.method5();
      var3.method25();
      Itemcounter6 var4 = var1.method29().bridge$getWorld();
      return var4 == null ? false : var1.method25(var2, var4, var1.method29(), this.field1, this.field2, 1);
   }

   @Override
   public int getDuration() {
      return this.duration;
   }

   @Override
   public String toString() {
      return "TeleportToOwnerTask{min_radius=" + this.field1 + ",max_radius=" + this.field2 + ",duration=" + this.duration + "}";
   }
}
