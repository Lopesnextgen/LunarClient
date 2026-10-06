package com.moonsworth.lunar.client.cosmetics.gecko;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;
import com.moonsworth.lunar.client.cosmetics.gecko.AttachedBone;

public class AttachToOwnerTask implements InactiveTask {
   @JsonProperty("attached_bone")
   private AttachedBone field1 = AttachedBone.SHOULDER;

   @Override
   public boolean method1(EmoteDefinition var1, PathFilter var2) {
      return true;
   }

   @Override
   public boolean method2(EmoteDefinition var1, PathFilter var2) {
      return false;
   }

   @Override
   public void method3(EmoteDefinition var1, PathFilter var2) {
   }

   @Override
   public void method6(EmoteDefinition var1, PathFilter var2) {
   }

   @Override
   public String toString() {
      return "AttachToOwnerTask{bone=" + this.field1 + "}";
   }
}
