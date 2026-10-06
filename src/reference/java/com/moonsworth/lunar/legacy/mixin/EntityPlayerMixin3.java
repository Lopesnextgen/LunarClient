package com.moonsworth.lunar.legacy.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityPlayer.class)
public interface EntityPlayerMixin3 {
   @Mutable
   @Accessor("gameProfile")
   void bridge$setGameProfile(GameProfile var1);
}
