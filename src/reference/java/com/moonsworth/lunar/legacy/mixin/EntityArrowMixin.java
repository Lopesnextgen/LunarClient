package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension2_3;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.combat.ProjectileBaseEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.math.RayTraceResult.MovingObjectType;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityArrow.class)
public abstract class EntityArrowMixin extends Entity {
   @Shadow
   public boolean inGround;

   protected EntityArrowMixin(World var1) {
      super(var1);
   }

   @Annotation2(max = 1)
   @Redirect(method = "onUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z"))
   public boolean lunar$projectileDestinationEvent(Entity var1, DamageSource var2, float var3) {
      if (this.world.isRemote) {
         ClientEventBus.method29().method12(ProjectileBaseEvent.EventProjectileImpact.class, () -> new ProjectileBaseEvent.EventProjectileImpact((BridgeExtension2_3)this, (BridgeExtension)var1));
      }

      return var1.attackEntityFrom(var2, var3);
   }

   @Annotation2(min = 5)
   @Redirect(
      method = "onUpdate",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/EntityArrow;onHit$v1_12(Lnet/minecraft/util/math/RayTraceResult;)V")
   )
   private void lunar$projectileDestinationEvent(EntityArrow var1, MovingObjectPosition var2) {
      if (!this.world.isRemote) {
         var1.onHit$v1_12(var2);
      } else {
         if (var2.entityHit != null) {
            ClientEventBus.method29().method12(ProjectileBaseEvent.EventProjectileImpact.class, () -> new ProjectileBaseEvent.EventProjectileImpact((BridgeExtension2_3)this, (BridgeExtension)var2.entityHit));
         } else if (ThreadModuleDump63.MC_VERSION >= 5 && var2.typeOfHit == MovingObjectType.BLOCK) {
            ClientEventBus.method29().method12(ProjectileBaseEvent.EventProjectileImpact.class, () -> new ProjectileBaseEvent.EventProjectileImpact((BridgeExtension2_3)this, null));
         } else if (ThreadModuleDump63.MC_VERSION < 5 && var2.typeOfHit == MovingObjectType.BLOCK) {
            ClientEventBus.method29().method12(ProjectileBaseEvent.EventProjectileImpact.class, () -> new ProjectileBaseEvent.EventProjectileImpact((BridgeExtension2_3)this, null));
         }

         var1.onHit$v1_12(var2);
      }
   }

   @Annotation2(max = 0)
   @Redirect(
      method = "onUpdate",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/block/Block;onEntityCollidedWithBlock$v1_7(Lnet/minecraft/world/World;IIILnet/minecraft/entity/Entity;)V"
      )
   )
   public void lunar$projectileDestinationEvent(Block var1, World var2, int var3, int var4, int var5, Entity var6) {
      if (var2.isRemote) {
         ClientEventBus.method29().method12(ProjectileBaseEvent.EventProjectileImpact.class, () -> new ProjectileBaseEvent.EventProjectileImpact((BridgeExtension2_3)this, null));
      }

      var1.onEntityCollidedWithBlock(var2, var3, var4, var5, var6);
   }

   @Annotation2(1)
   @Redirect(
      method = "onUpdate",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getMetaFromState$v1_8(Lnet/minecraft/block/state/IBlockState;)I")
   )
   private int lunar$projectileDestinationEvent(Block var1, IBlockState var2) {
      if (this.inGround && this.world.isRemote) {
         ClientEventBus.method29().method12(ProjectileBaseEvent.EventProjectileImpact.class, () -> new ProjectileBaseEvent.EventProjectileImpact((BridgeExtension2_3)this, null));
      }

      return var1.getMetaFromState(var2);
   }
}
