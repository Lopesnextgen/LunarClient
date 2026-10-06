package com.moonsworth.lunar.client.render.particle;

import lombok.Generated;

public enum Interpolation {
   LINEAR("linear") {
      @Override
      public float interpolate(float value1, float value2, float value3) {
         return Interpolations.lerp(value1, value2, value3);
      }
   },
   QUAD_IN("quad_in") {
      @Override
      public float interpolate(float value1, float value2, float value3) {
         return value1 + (value2 - value1) * value3 * value3;
      }
   },
   QUAD_OUT("quad_out") {
      @Override
      public float interpolate(float value1, float value2, float value3) {
         return value1 - (value2 - value1) * value3 * (value3 - 2.0F);
      }
   },
   QUAD_INOUT("quad_inout") {
      @Override
      public float interpolate(float value1, float value2, float value3) {
         value3 *= 2.0F;
         if (value3 < 1.0F) {
            return value1 + (value2 - value1) / 2.0F * value3 * value3;
         }

         value3--;
         return value1 - (value2 - value1) / 2.0F * (value3 * (value3 - 2.0F) - 1.0F);
      }
   },
   CUBIC_IN("cubic_in") {
      @Override
      public float interpolate(float value1, float value2, float value3) {
         return value1 + (value2 - value1) * value3 * value3 * value3;
      }
   },
   CUBIC_OUT("cubic_out") {
      @Override
      public float interpolate(float value1, float value2, float value3) {
         value3--;
         return value1 + (value2 - value1) * (value3 * value3 * value3 + 1.0F);
      }
   },
   CUBIC_INOUT("cubic_inout") {
      @Override
      public float interpolate(float value1, float value2, float value3) {
         value3 *= 2.0F;
         if (value3 < 1.0F) {
            return value1 + (value2 - value1) / 2.0F * value3 * value3 * value3;
         }

         value3 -= 2.0F;
         return value1 + (value2 - value1) / 2.0F * (value3 * value3 * value3 + 2.0F);
      }
   },
   EXP_IN("exp_in") {
      @Override
      public float interpolate(float value1, float value2, float value3) {
         return value1 + (value2 - value1) * (float)Math.pow(2.0, 10.0F * (value3 - 1.0F));
      }
   },
   EXP_OUT("exp_out") {
      @Override
      public float interpolate(float value1, float value2, float value3) {
         return value1 + (value2 - value1) * (float)(-Math.pow(2.0, -10.0F * value3) + 1.0);
      }
   },
   EXP_INOUT("exp_inout") {
      @Override
      public float interpolate(float value1, float value2, float value3) {
         if (value3 == 0.0F) {
            return value1;
         }

         if (value3 == 1.0F) {
            return value2;
         }

         value3 *= 2.0F;
         if (value3 < 1.0F) {
            return value1 + (value2 - value1) / 2.0F * (float)Math.pow(2.0, 10.0F * (value3 - 1.0F));
         }

         value3--;
         return value1 + (value2 - value1) / 2.0F * (float)(-Math.pow(2.0, -10.0F * value3) + 2.0);
      }
   };

   public final String key;

   public abstract float interpolate(float value1, float value2, float value3);

   public String getName() {
      return "mclib.interpolations." + this.key;
   }

   @Generated
   Interpolation(String text) {
      this.key = text;
   }
}
