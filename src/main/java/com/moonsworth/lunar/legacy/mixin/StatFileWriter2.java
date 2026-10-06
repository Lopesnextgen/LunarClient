package com.moonsworth.lunar.legacy.mixin;

import net.kyori.adventure.key.Key;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ResourceLocation.class)
@Implements(@Interface(iface = Key.class, prefix = "adventure$"))
public abstract class StatFileWriter2 {
   @Final
   @Shadow
   public String namespace;
   @Final
   @Shadow
   public String path;

   public String adventure$namespace() {
      return this.namespace;
   }

   public String adventure$value() {
      return this.path;
   }

   public String adventure$asString() {
      return this.namespace + ":" + this.path;
   }
}
