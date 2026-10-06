package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.MixinHelper_3;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType.Type;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.util.EnumParticleTypes;
import org.joml.Vector3f;

public class MixinHelper implements MixinHelper_3 {
   public void method1(Horsestats20 var1, Vector3f[] var2) {
      if (ThreadModuleDump63.MC_VERSION > 0) {
         if (ThreadModuleDump63.method2()) {
            ThreadLocalRandom var3 = ThreadLocalRandom.current();
            int var4 = var2.length;

            for (int var5 = 0; var5 < 40; var5++) {
               double var6 = var3.nextFloat() * 0.8 - 0.4;
               double var8 = var3.nextFloat() * 2.0F;
               double var10 = var3.nextFloat() * 0.8 - 0.4;
               EffectRenderer var12 = ((Minecraft)ThreadModuleDump63.method3()).field_92047_az;
               EntityFX var13 = var12.spawnEffectParticle(
                  EnumParticleTypes.CRIT.getParticleID(),
                  var1.bridge$getX() + 0.5,
                  var1.bridge$getY() + 0.5,
                  var1.bridge$getZ() + 0.5,
                  var6,
                  var8,
                  var10,
                  new int[0]
               );
               if (var13 != null) {
                  Vector3f var14 = var2[var3.nextInt(var4)];
                  var13.particleRed = var14.x;
                  var13.particleGreen = var14.y;
                  var13.particleBlue = var14.z;
                  var13.particleGravity = 0.5F;
                  var13.particleMaxAge = (int)(2.0F / (var3.nextFloat() * 0.9F + 0.1F));
               }
            }
         }
      }
   }

   public void method2(Vector3f var1, HorsestatsType_2 var2, int var3, float var4) {
      if (ThreadModuleDump63.MC_VERSION > 0 && ThreadModuleDump63.method3() != null) {
         float var5 = var1.x() + 0.5F + var2.getOffsetX() * -0.45F;
         float var6 = var1.y() + 0.5F + var2.getOffsetY() * -0.45F;
         float var7 = var1.z() + 0.5F + var2.getOffsetZ() * -0.45F;
         Type var8 = var2.getAxis();
         EffectRenderer var9 = ((Minecraft)ThreadModuleDump63.method3()).field_92047_az;
         ThreadLocalRandom var10 = ThreadLocalRandom.current();
         float var11 = (var4 - 1.0F) * 0.1F;
         float var12 = var4 * 0.5F;

         for (int var13 = 0; var13 < 27.0F + var4 * 3.0F; var13++) {
            double var14 = (var10.nextFloat() - 0.5) * (var8 == Type.X ? 0.02 : 0.15 + var11);
            double var16 = (var10.nextFloat() - 0.5) * (var8 == Type.Y ? 0.02 : 0.15 + var11);
            double var18 = (var10.nextFloat() - 0.5) * (var8 == Type.Z ? 0.02 : 0.15 + var11);
            EntityFX var20 = var9.spawnEffectParticle(
               EnumParticleTypes.SNOW_SHOVEL.getParticleID(),
               var8 == Type.X ? var5 + var10.nextFloat() * 0.15 : var1.x() + 0.5 + var10.nextFloat() * var4 - var12,
               var8 == Type.Y ? var6 + var10.nextFloat() * 0.15 : var1.y() + 0.5 + var10.nextFloat() * var4 - var12,
               var8 == Type.Z ? var7 + var10.nextFloat() * 0.15 : var1.z() + 0.5 + var10.nextFloat() * var4 - var12,
               var14,
               var16,
               var18,
               new int[0]
            );
            if (var20 != null) {
               var20.particleRed = ThreadModuleDump23.method5(var3);
               var20.particleGreen = ThreadModuleDump23.method6(var3);
               var20.particleBlue = ThreadModuleDump23.method7(var3);
               var20.particleGravity = 0.0F;
               var20.particleMaxAge = (int)(var10.nextFloat() * 14.0F + 7.0F + var4);
            }
         }
      }
   }
}
