package com.moonsworth.lunar.client.cosmetics.inactive.mixin.fishing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;
import com.moonsworth.lunar.client.cosmetics.gecko.AttachedBone;

public class AttachToOwnerTask implements InactiveTask {
   @JsonProperty("attached_bone")
   private AttachedBone field1 = AttachedBone.SHOULDER;

   public AttachToOwnerTask() {
   }

   @Override
   public boolean method1(EmoteDefinition inactive31, PathFilter holograms3handler2) {
      return true;
   }

   @Override
   public boolean method2(EmoteDefinition inactive31, PathFilter holograms3handler2) {
      return false;
   }

   @Override
   public void method3(EmoteDefinition inactive31, PathFilter holograms3handler2) {
   }

   @Override
   public void method6(EmoteDefinition inactive31, PathFilter holograms3handler2) {
   }

   @Override
   public String toString() {
      return "AttachToOwnerTask{bone=" + this.field1 + "}";
   }
}
