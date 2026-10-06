package com.moonsworth.lunar.client.mixin;

import com.moonsworth.lunar.client.util.collection.MapIterator;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.Map;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.DataWatcher.WatchableObject;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DataWatcher.class)
public class DataWatcherMixin {
   @Mutable
   @Final
   @Shadow
   public Map<Integer, WatchableObject> watchedObjects;

   public DataWatcherMixin() {
   }

   @Inject(method = "<init>(Lnet/minecraft/entity/Entity;)V", at = @At("TAIL"))
   private void lunar$injectFastMap(Entity entity1, CallbackInfo callback2) {
      this.watchedObjects = new MapIterator(32, arg1x -> {
         Int2ObjectOpenHashMap int2objectopenhashmap2x = new Int2ObjectOpenHashMap();
         arg1x.accept(int2objectopenhashmap2x);
         this.watchedObjects = int2objectopenhashmap2x;
      });
   }
}
