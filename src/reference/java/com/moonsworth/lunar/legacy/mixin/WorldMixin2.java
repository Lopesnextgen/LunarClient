package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_3;
import com.moonsworth.lunar.client.mod.render.weatherchanger.WeatherChanger;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(World.class)
public abstract class WorldMixin2 {
   @Final
   @Shadow
   public boolean isRemote;

   @Shadow
   public abstract void playSound(double var1, double var3, double var5, String var7, float var8, float var9, boolean var10);

   @Shadow
   public abstract void playSound(double var1, double var3, double var5, SoundEvent var7, SoundCategory var8, float var9, float var10, boolean var11);

   @Shadow
   public abstract boolean addWeatherEffect(Entity var1);

   @Inject(method = "updateEntities", at = @At("RETURN"))
   public void lunar$updateEntities(CallbackInfo var1) {
      if (this.isRemote) {
         WeatherChanger var2 = ThreadModuleDump63.method4().method40().method55();
         if (var2.isEnabled()) {
            Bridge5Extension_5 var3 = ThreadModuleDump63.method7();
            if (var2.method17().get() && var3 != null) {
               ThreadLocalRandom var4 = ThreadLocalRandom.current();
               if (var4.nextFloat() < 0.002F * var2.method21().get()) {
                  float var5 = var2.method22().get();
                  float var6 = var2.method23().get();
                  double var7 = var3.bridge$getPosX() + (var4.nextFloat() - 0.5F) * 2.0F * var5;
                  double var9 = var3.bridge$getPosY() + (var4.nextFloat() - 0.5F) * 2.0F * var6;
                  double var11 = var3.bridge$getPosZ() + (var4.nextFloat() - 0.5F) * 2.0F * var5;
                  EntityLightningBolt var13;
                  if (ThreadModuleDump63.MC_VERSION == 5) {
                     var13 = new EntityLightningBolt((World)this, var7, var9, var11, true);
                  } else {
                     var13 = new EntityLightningBolt((World)this, var7, var9, var11);
                  }

                  ((Bridge6_3)var13).bridge$setAddedByWeatherChanger(true);
                  this.addWeatherEffect(var13);
                  if (var2.method19().get()) {
                     float var14 = 0.8F + var4.nextFloat() * 0.2F;
                     float var15 = 0.5F + var4.nextFloat() * 0.2F;
                     if (ThreadModuleDump63.MC_VERSION == 5) {
                        this.playSound(var7, var9, var11, SoundEvents.ENTITY_LIGHTNING_THUNDER, SoundCategory.WEATHER, 10000.0F, var14, false);
                        this.playSound(var7, var9, var11, SoundEvents.ENTITY_LIGHTNING_IMPACT, SoundCategory.WEATHER, 2.0F, var15, false);
                     } else {
                        this.playSound(var7, var9, var11, "ambient.weather.thunder", 10000.0F, var14, false);
                        this.playSound(var7, var9, var11, "random.explode", 2.0F, var15, false);
                     }
                  }
               }
            }
         }
      }
   }
}
