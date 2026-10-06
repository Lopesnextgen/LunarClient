package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms_7;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler24;
import com.moonsworth.lunar.client.guiRewindhandlers.Annotation3;
import com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public abstract class Rewindhandlers3 extends Highlight implements Nameplate2 {
   private final Holograms_7 field1;
   private final Vector3iBridge field2;

   @Generated
   public Rewindhandlers3(Holograms_7 var1, Vector3iBridge var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Generated
   public Holograms_7 method1() {
      return this.field1;
   }

   @Generated
   public Vector3iBridge method2() {
      return this.field2;
   }

   @Annotation3(GuiRewindhandlersHandler24.class)
   public static class Data extends Rewindhandlers3 {
      private final Rewindhandlers3.Data.Type field3;

      public Data(Holograms_7 var1, Vector3iBridge var2, Rewindhandlers3.Data.Type type) {
         super(var1, var2);
         this.field3 = type;
      }

      @Generated
      public Rewindhandlers3.Data.Type method3() {
         return this.field3;
      }

      public enum Type {
         SUPERBOOM,
         LEVER,
         REDSTONE_KEY_PICKUP,
         REDSTONE_KEY_PLACED,
         LOCKED_CHEST,
         ROOM_COMPLETED;
      }
   }

   @Annotation3(GuiRewindhandlersHandler24.class)
   public static class Data2 extends Rewindhandlers3 {
      public Data2(Holograms_7 var1, Vector3iBridge var2) {
         super(var1, var2);
      }
   }
}
