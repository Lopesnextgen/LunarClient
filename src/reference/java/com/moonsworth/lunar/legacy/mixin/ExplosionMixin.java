package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventExplosion;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Explosion.class)
public class ExplosionMixin {
   @Final
   @Shadow
   public World world;
   @Final
   @Shadow
   public double x;
   @Final
   @Shadow
   public double y;
   @Final
   @Shadow
   public double z;
   @Final
   @Shadow
   public float size;

   public ExplosionMixin() {
   }

   @Inject(method = "doExplosionB", at = @At("TAIL"))
   private void lunar$doExplosion(boolean flag, CallbackInfo callback2) {
      Ref.method3()
         .bridge$submit(
            () -> LunarEventBus.method29().method12(EventExplosion.class, () -> new EventExplosion((Itemcounter6)this.world, this.x, this.y, this.z, this.size))
         );
   }
}
