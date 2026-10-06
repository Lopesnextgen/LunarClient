package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import lombok.Generated;

public abstract class EventFog extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final EventFog.FogKind field1;

   @Generated
   public EventFog.FogKind method1() {
      return this.field1;
   }

   @Generated
   public EventFog(EventFog.FogKind type21) {
      this.field1 = type21;
   }

   @VersionGate(max = 25)
   public static class FogRender extends EventFog {
      private final float field2;
      private final boolean field3;
      private final EventFog.FogField field4;
      private final float field5;

      public FogRender(EventFog.FogKind type21, float value2, boolean flag3, EventFog.FogField fogField, float value5) {
         super(type21);
         this.field2 = value2;
         this.field3 = flag3;
         this.field4 = fogField;
         this.field5 = value5;
      }

      public void method1(float value1) {
         switch (this.field4) {
            case START:
               Bridge.method42().method68(value1);
               break;
            case END:
               Bridge.method42().method69(value1);
               break;
            case DENSITY:
               if (Ref.MC_VERSION <= 7) {
                  Bridge.method42().method63(value1);
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
      public EventFog.FogField method4() {
         return this.field4;
      }

      @Generated
      public float method5() {
         return this.field5;
      }
   }

   public static class EventFogColor extends EventFog {
      private final double field2;
      private float red;
      private float green;
      private float blue;

      public EventFogColor(EventFog.FogKind type21, double value2, float value4, float value5, float value) {
         super(type21);
         this.field2 = value2;
         this.red = value4;
         this.green = value5;
         this.blue = value;
      }

      public void method1(float value1, float value2, float value) {
         this.red = value1;
         this.green = value2;
         this.blue = value;
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

   public static class EventFogRange extends EventFog {
      private final float field2;
      private final boolean field3;
      private float field4;
      private float field5;

      public EventFogRange(EventFog.FogKind type21, float value2, boolean flag3, float value4, float value5) {
         super(type21);
         this.field2 = value2;
         this.field3 = flag3;
         this.field4 = value4;
         this.field5 = value5;
      }

      public void method1(float value1, float value2) {
         this.field4 = value1;
         this.field5 = value2;
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

   public enum FogField {
      DENSITY,
      START,
      END;

      @Generated
      FogField() {
      }
   }

   public enum FogKind {
      LAVA,
      WATER,
      POWDER_SNOW,
      RENDER_DISTANCE,
      BLINDNESS,
      DARKNESS,
      DIMENSION,
      BOSS,
      ATMOSPHERIC;

      @Generated
      FogKind() {
      }
   }
}
