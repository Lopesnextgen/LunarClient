package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge5_13;
import net.minecraft.client.shader.ShaderUniform;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ShaderUniform.class)
public abstract class ShaderUniformMixin implements Bridge5_13 {
   public ShaderUniformMixin() {
   }

   @Shadow
   public abstract void set(float value1);

   @Shadow
   public abstract void set(float value1, float value2);

   @Shadow
   public abstract void set(float value1, float value2, float value3);

   @Shadow
   public abstract void set(float[] items1);

   public void bridge$set(float value1) {
      this.set(value1);
   }

   public void bridge$set(float value1, float value2) {
      this.set(value1, value2);
   }

   public void bridge$set(float value1, float value2, float value3) {
      this.set(value1, value2, value3);
   }

   public void bridge$set(Float[] items1) {
      float[] items2 = new float[items1.length];

      for (int index3 = 0; index3 < items1.length; index3++) {
         items2[index3] = items1[index3];
      }

      this.set(items2);
   }
}
