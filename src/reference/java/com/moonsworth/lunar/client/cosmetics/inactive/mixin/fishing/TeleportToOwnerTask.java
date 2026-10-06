package com.moonsworth.lunar.client.cosmetics.inactive.mixin.fishing;

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

   public TeleportToOwnerTask() {
   }

   @Override
   public boolean method2(EmoteDefinition inactive31, PathFilter holograms3handler2) {
      TurboPathFollower holograms2_43 = holograms3handler2.method5();
      holograms2_43.method25();
      Itemcounter6 itemcounter64 = inactive31.method29().bridge$getWorld();
      return itemcounter64 == null ? false : inactive31.method25(holograms3handler2, itemcounter64, inactive31.method29(), this.field1, this.field2, 1);
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
