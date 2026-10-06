package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import lombok.Generated;

public abstract class EventFogSetup extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final EventFogSetup.FogSource field1;

   @Generated
   public EventFogSetup.FogSource method1() {
      return this.field1;
   }

   @Generated
   public EventFogSetup(EventFogSetup.FogSource var1) {
      this.field1 = var1;
   }

   @Annotation2(max = 25)
   public static class Data extends EventFogSetup {
      private final float field2;
      private final boolean field3;
      private final EventFogSetup.Type field4;
      private final float field5;

      public Data(EventFogSetup.FogSource var1, float var2, boolean var3, EventFogSetup.Type var4, float var5) {
         super(var1);
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = var5;
      }

      public void method1(float var1) {
         switch (this.field4) {
            case START:
               Bridge.method42().method68(var1);
               break;
            case END:
               Bridge.method42().method69(var1);
               break;
            case DENSITY:
               if (ThreadModuleDump63.MC_VERSION <= 7) {
                  Bridge.method42().method63(var1);
               }
         }

         this.setCancelled(true);
      }

      @Generated
      public float method2() {
         return this.field2;
      }

      @Generated
      public boolean method3() {
         return this.field3;
      }

      @Generated
      public EventFogSetup.Type method4() {
         return this.field4;
      }

      @Generated
      public float method5() {
         return this.field5;
      }
   }

   public static class EventFogTint extends EventFogSetup {
      private final double field2;
      private float red;
      private float green;
      private float blue;

      public EventFogTint(EventFogSetup.FogSource var1, double var2, float var4, float var5, float value) {
         super(var1);
         this.field2 = var2;
         this.red = var4;
         this.green = var5;
         this.blue = value;
      }

      public void method1(float var1, float var2, float var3) {
         this.red = var1;
         this.green = var2;
         this.blue = var3;
      }

      @Generated
      public double method2() {
         return this.field2;
      }

      @Generated
      public float method3() {
         return this.red;
      }

      @Generated
      public float method4() {
         return this.green;
      }

      @Generated
      public float method5() {
         return this.blue;
      }
   }

   public static class EventFogDistance extends EventFogSetup {
      private final float field2;
      private final boolean field3;
      private float field4;
      private float field5;

      public EventFogDistance(EventFogSetup.FogSource var1, float var2, boolean var3, float var4, float var5) {
         super(var1);
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = var5;
      }

      public void method1(float var1, float var2) {
         this.field4 = var1;
         this.field5 = var2;
      }

      @Generated
      public float method2() {
         return this.field2;
      }

      @Generated
      public boolean method3() {
         return this.field3;
      }

      @Generated
      public float method4() {
         return this.field4;
      }

      @Generated
      public float method5() {
         return this.field5;
      }
   }

   public enum Type {
      DENSITY,
      START,
      END;
   }

   public enum FogSource {
      LAVA,
      WATER,
      POWDER_SNOW,
      RENDER_DISTANCE,
      BLINDNESS,
      DARKNESS,
      DIMENSION,
      BOSS,
      ATMOSPHERIC;
   }
}
