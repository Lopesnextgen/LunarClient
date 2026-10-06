package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.fog.Fog;
import com.moonsworth.lunar.bridge.fog.Fog2;
import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.entity.EventLivingBase;
import com.moonsworth.lunar.client.event.entity.EventPlayerDied;
import com.moonsworth.lunar.client.hitcolor.FogHandler;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump88;
import java.util.Collection;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin extends Entity implements BridgeExtension2_5, ThreadModuleDump88 {
   @Shadow
   public int lastAttackedEntityTime;
   @Shadow
   public EntityLivingBase lastAttackedEntity;
   @Shadow
   public int hurtTime;
   @Shadow
   public int deathTime;
   @Shadow
   public float renderYawOffset;
   @Shadow
   public float rotationYawHead;
   @Shadow
   public float prevRotationYawHead;
   @Shadow
   public float prevRenderYawOffset;
   @Unique
   public Component lunar$displayNameCache;
   @Shadow
   public float moveForward$v1_12;
   @Shadow
   public float moveVertical;
   @Shadow
   public float prevLimbSwingAmount;
   @Shadow
   public float limbSwingAmount;
   @Shadow
   public float limbSwing;
   @Shadow
   public int maxHurtTime;
   @Unique
   private float lunar$scale = 1.0F;

   @Shadow
   public abstract boolean isPotionActive(Potion var1);

   @Shadow
   public abstract Collection<PotionEffect> getActivePotionEffects();

   @Shadow
   public abstract float getHealth();

   @Shadow
   public abstract int getTotalArmorValue();

   @Shadow
   public abstract float getMaxHealth();

   @Shadow
   public abstract boolean isPlayerSleeping();

   @Shadow
   public abstract PotionEffect getActivePotionEffect(Potion var1);

   @Shadow
   public abstract int getArmSwingAnimationEnd();

   @Shadow
   public abstract ItemStack getEquipmentInSlot(int var1);

   @Shadow
   public abstract ItemStack getHeldItem(EnumHand var1);

   @Shadow
   public abstract ItemStack getHeldItem();

   @Shadow
   public abstract boolean isElytraFlying$v1_12();

   @Shadow
   public abstract boolean isChild();

   @Shadow
   public abstract Team getTeam();

   public EntityLivingBaseMixin(World var1) {
      super(var1);
   }

   @Override
   public int bridge$getLastAttackerTime() {
      return this.lastAttackedEntityTime;
   }

   @Override
   public int bridge$getHurtTime() {
      return this.hurtTime;
   }

   @Override
   public int bridge$getCurrentHurtTime() {
      return ThreadModuleDump63.MC_VERSION <= 0 ? this.hurtTime + 1 : this.hurtTime;
   }

   @Override
   public int bridge$getMaxHurtTime() {
      return this.maxHurtTime;
   }

   @Override
   public boolean bridge$wasJustHurt() {
      int var1 = this.bridge$getCurrentHurtTime();
      return var1 > 0 && var1 == this.bridge$getMaxHurtTime();
   }

   @Override
   public float bridge$getDeathTime() {
      return this.deathTime;
   }

   @Override
   public Optional<BridgeExtension2_5> bridge$getLastAttacker() {
      return Optional.ofNullable((BridgeExtension2_5)this.lastAttackedEntity);
   }

   @Inject(method = {"swingArm$v1_12", "swingItem$v1_7"}, at = @At("HEAD"))
   private void lunar$swingItem(CallbackInfo var1) {
      if (this instanceof EntityPlayer) {
         ThreadModuleDump63.method4().method45().method10((Bridge6_10)this, false);
      }
   }

   @Overwrite
   public boolean getAlwaysRenderNameTagForRender() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.getAlwaysRenderNameTag() : this.getDataWatcher().getWatchableObjectByte(3) == 1;
   }

   @Override
   public boolean bridge$isPotionActive(Fog2 var1) {
      return this.isPotionActive((Potion)var1);
   }

   @Override
   public Collection<Fog> bridge$getActivePotionEffects() {
      return this.getActivePotionEffects();
   }

   @Override
   public boolean bridge$isInLava() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.isInLava() : this.handleLavaMovement$v1_7();
   }

   @Override
   public int bridge$getArmSwingAnimationEnd() {
      return this.getArmSwingAnimationEnd();
   }

   @Override
   public float bridge$getBodyRot() {
      return this.renderYawOffset;
   }

   @Override
   public void bridge$setBodyYRot(float var1) {
      this.renderYawOffset = var1;
   }

   @Override
   public float bridge$getRotationYawHead() {
      return this.rotationYawHead;
   }

   @Override
   public void bridge$setRotationYawHead(float var1) {
      this.rotationYawHead = var1;
   }

   @Override
   public float bridge$getPrevRotationYawHead() {
      return this.prevRotationYawHead;
   }

   @Override
   public void bridge$setPrevRotationYawHead(float var1) {
      this.prevRotationYawHead = var1;
   }

   @Override
   public float bridge$getPreviousRotationYawOffset() {
      return this.prevRenderYawOffset;
   }

   @Override
   public int bridge$getTotalArmorValue() {
      return this.getTotalArmorValue();
   }

   @Override
   public ItemStackBridge bridge$getEquipmentInSlot(EquipmentSlotBridge var1) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         byte var6 = switch (var1) {
            case MAINHAND -> 0;
            case OFFHAND -> 1;
            case FEET -> 2;
            case LEGS -> 3;
            case CHEST -> 4;
            case HEAD -> 5;
         };
         int var3 = 0;

         for (ItemStack var5 : this.getEquipmentAndArmor$v1_12()) {
            if (var6 == var3) {
               return (ItemStackBridge)var5;
            }

            var3++;
         }

         return null;
      } else {
         byte var2 = switch (var1) {
            case MAINHAND -> 0;
            case OFFHAND -> throw new UnsupportedOperationException("Cannot get offhand in legacy!");
            case FEET -> 1;
            case LEGS -> 2;
            case CHEST -> 3;
            case HEAD -> 4;
         };
         return (ItemStackBridge)this.getEquipmentInSlot(var2);
      }
   }

   @Override
   public float bridge$getHealth() {
      return this.getHealth();
   }

   @Override
   public float bridge$getMaxHealth() {
      return this.getMaxHealth();
   }

   @Override
   public boolean bridge$isSleeping() {
      return this.isPlayerSleeping();
   }

   @Override
   public float bridge$getMoveForward() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? this.moveForward$v1_12 : this.moveVertical;
   }

   @Override
   public ItemStackBridge bridge$getHeldItem() {
      return (ItemStackBridge)(ThreadModuleDump63.MC_VERSION >= 5 ? this.getHeldItem(EnumHand.MAIN_HAND) : this.getHeldItem());
   }

   @Override
   public float bridge$getPrevRenderYawOffset() {
      return this.prevRenderYawOffset;
   }

   @Override
   public String bridge$getDisplayName() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.getDisplayName().getFormattedText() : this.getFormattedCommandSenderName$v1_7().getFormattedText();
   }

   @Override
   public Component bridge$getDisplayNameComponent() {
      if (this.lunar$displayNameCache != null) {
         return this.lunar$displayNameCache;
      } else if (ThreadModuleDump63.MC_VERSION >= 5) {
         ChatComponentText var2 = new ChatComponentText(ScorePlayerTeam.formatPlayerName(this.getTeam(), this.getName()));
         return this.lunar$displayNameCache = AdventureTextBridge.asAdventure((Bridge2_42)var2);
      } else if (ThreadModuleDump63.MC_VERSION >= 1) {
         ChatComponentText var1 = new ChatComponentText(ScorePlayerTeam.formatPlayerName(this.getTeam(), this.getName()));
         return this.lunar$displayNameCache = AdventureTextBridge.asAdventure((Bridge2_42)var1);
      } else {
         return this.lunar$displayNameCache = AdventureTextBridge.asAdventure((Bridge2_42)this.getFormattedCommandSenderName$v1_7());
      }
   }

   @Override
   public void lunar$onNameTagUpdate() {
      this.lunar$displayNameCache = null;
   }

   @Override
   public Component bridge$getDisplayNameComponentWithHover() {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? AdventureTextBridge.asAdventure((Bridge2_42)this.getDisplayName())
         : AdventureTextBridge.asAdventure((Bridge2_42)this.getFormattedCommandSenderName$v1_7());
   }

   @Inject(method = "onDeath", at = @At("HEAD"), cancellable = true)
   private void lunar$livingEntityDeathEvent(DamageSource var1, CallbackInfo var2) {
      if (this.world.isRemote) {
         EventPlayerDied var3 = ClientEventBus.method29().method12(EventPlayerDied.class, () -> new EventPlayerDied(this));
         if (var3 != null && var3.isCancelled()) {
            var2.cancel();
         }
      }
   }

   @Inject(method = "onUpdate", at = @At("TAIL"))
   private void lunar$livingEntityUpdateEvent(CallbackInfo var1) {
      if (this.world.isRemote) {
         ClientEventBus.method29().method12(EventLivingBase.EventLiving.class, () -> new EventLivingBase.EventLiving(this));
      }
   }

   @Override
   public Fog bridge$getActivePotionEffect(Fog2 var1) {
      return (Fog)this.getActivePotionEffect((Potion)var1);
   }

   @Override
   public boolean bridge$isElytraFlying() {
      return ThreadModuleDump63.MC_VERSION >= 2 && this.isElytraFlying$v1_12();
   }

   @Override
   public long bridge$getLastAttackedMillis() {
      return FogHandler.method1(this).method5();
   }

   @Override
   public long bridge$getLastHurtMillis() {
      return FogHandler.method1(this).method6();
   }

   @Override
   public long bridge$getLastDamagedMillis() {
      return FogHandler.method1(this).method7();
   }

   @Inject(method = "attackEntityFrom", at = @At("HEAD"))
   private void lunar$setLastHurtTime(DamageSource var1, float var2, CallbackInfoReturnable<Boolean> var3) {
      if (var1 instanceof EntityDamageSource && this instanceof Bridge6_10) {
         FogHandler.method1(this).method2(ThreadModuleDump63.method14());
      }
   }

   @Override
   public void bridge$calculateEntityAnimation() {
      this.prevLimbSwingAmount = this.limbSwingAmount;
      double var1 = this.posX - this.prevPosX;
      double var3 = this.posZ - this.prevPosZ;
      float var5 = (float)Math.min(Math.sqrt(var1 * var1 + var3 * var3) * 4.0, 1.0);
      this.limbSwingAmount = this.limbSwingAmount + (var5 - this.limbSwingAmount) * 0.4F;
      this.limbSwing = this.limbSwing + this.limbSwingAmount;
   }

   @Override
   public boolean bridge$isInvisibleToPlayer() {
      return ThreadModuleDump63.method7() != null && this.isInvisibleToPlayer((EntityPlayer)ThreadModuleDump63.method7());
   }

   @Override
   public boolean bridge$isBaby() {
      return this.isChild();
   }

   @Override
   public boolean bridge$canShieldBeAttacked(double var1, double var3, double var5) {
      if (ThreadModuleDump63.MC_VERSION <= 1) {
         return false;
      } else {
         EntityLivingBase var8 = (EntityLivingBase)this;
         if (var8 instanceof EntityPlayer var7 && var7.isActiveItemStackBlocking$v1_12()) {
            Vec3 var10 = this.getVectorForRotation(this.rotationPitch, this.rotationYaw);
            Vec3 var9 = this.getPositionVector().subtract(new Vec3(var1, var3, var5));
            var9 = var9.normalize();
            return new Vec3(var9.xCoord, 0.0, var9.zCoord).dotProduct(var10) < 0.0;
         } else {
            return false;
         }
      }
   }

   @Override
   public void bridge$setLunarScale(float var1) {
      this.lunar$scale = var1;
   }

   @Override
   public float bridge$getLunarScale() {
      return this.lunar$scale;
   }

   @Override
   public boolean bridge$isHostile() {
      EntityLivingBaseMixin var1 = this;
      return ThreadModuleDump63.MC_VERSION == 5 && var1 instanceof EntityShulker || var1 instanceof EntityGhast || var1 instanceof EntitySlime;
   }
}
