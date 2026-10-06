package com.moonsworth.lunar.legacy.mixin;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.bridge.ContainerMarker;
import com.moonsworth.lunar.bridge.MovementStateBridge;
import com.moonsworth.lunar.bridge.hitbox.Hitbox;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.CPacketEntityAction.Action;
import net.minecraft.scoreboard.Team;
import net.minecraft.scoreboard.Team.EnumVisible;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.EnumHand;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovementInput;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Annotation2(min = 1)
@Mixin(EntityPlayerSP.class)
public abstract class EntityPlayerSPMixin extends AbstractClientPlayer implements Bridge5Extension_5 {
   @Final
   @Shadow
   public NetHandlerPlayClient connection$v1_8;
   @Shadow
   public int sprintToggleTimer;
   @Shadow
   public int horseJumpPowerCounter;
   @Shadow
   public float horseJumpPower;
   @Shadow
   public MovementInput movementInput;
   @Shadow
   public Minecraft mc;
   @Final
   @Shadow
   public net.minecraft.stats.StatFileWriter statWriter;
   @Shadow
   public float prevRenderArmYaw;
   @Shadow
   public float renderArmYaw;
   @Shadow
   public float renderArmPitch;
   @Shadow
   public float prevRenderArmPitch;
   @Shadow
   public float lastReportedPitch;
   @Shadow
   public float lastReportedYaw;
   @Shadow
   public double lastReportedPosX;
   @Shadow
   public double lastReportedPosY;
   @Shadow
   public double lastReportedPosZ;

   @Shadow
   public abstract void onCriticalHit(Entity var1);

   @Shadow
   public abstract void setSprinting(boolean var1);

   @Shadow
   public abstract boolean pushOutOfBlocks(double var1, double var3, double var5);

   @Shadow
   public abstract boolean isRidingHorse();

   @Shadow
   public abstract float getHorseJumpPower();

   @Shadow
   public abstract String getClientBrand();

   @Shadow
   public abstract String getServerBrand$v1_12();

   @Shadow
   public abstract void sendHorseJump();

   @Shadow
   public abstract void sendChatMessage(String var1);

   @Shadow
   public abstract void swingArm$v1_12(EnumHand var1);

   @Shadow
   public abstract void swingItem();

   @Shadow
   public abstract void setActiveHand$v1_12(EnumHand var1);

   @Shadow
   public abstract void resetActiveHand$v1_12();

   @Shadow
   public abstract EntityItem dropOneItem(boolean var1);

   @Shadow
   public abstract EntityItem dropItem(boolean var1);

   @Shadow
   public abstract void closeScreen();

   public EntityPlayerSPMixin(World var1, GameProfile var2) {
      super(var1, var2);
   }

   @Override
   public Optional<String> bridge$getClientBrand() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? Optional.ofNullable(this.getServerBrand$v1_12()) : Optional.ofNullable(this.getClientBrand());
   }

   @Override
   public ClientPacketListenerBridge bridge$getSendQueue() {
      return (ClientPacketListenerBridge)this.connection$v1_8;
   }

   @Override
   public void bridge$sendChatMessage(String var1) {
      this.sendChatMessage(var1);
   }

   @Override
   public void bridge$sendCommand(String var1) {
      this.sendChatMessage(var1);
   }

   @Override
   public MovementStateBridge bridge$getMovementInput() {
      return (MovementStateBridge)this.movementInput;
   }

   @Override
   public void bridge$onCriticalHit(BridgeExtension var1) {
      this.onCriticalHit((Entity)var1);
   }

   @Override
   public void bridge$setMovementInput(MovementStateBridge var1) {
      this.movementInput = (MovementInput)var1;
   }

   @Override
   public boolean bridge$isSprinting() {
      return this.isSprinting();
   }

   @Override
   public void bridge$setSprinting(boolean var1) {
      this.setSprinting(var1);
   }

   @Override
   public void bridge$pushOutOfBlocks(double var1, double var3, double var5) {
      this.pushOutOfBlocks(var1, var3, var5);
   }

   @Override
   public void bridge$sendPlayerAbilities() {
      this.sendPlayerAbilities();
   }

   @Override
   public boolean bridge$isRidingHorse() {
      return this.isRidingHorse();
   }

   @Override
   public void bridge$sendRidingJumpPacket() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.connection$v1_8
            .sendPacket((Packet)(new C0BPacketEntityAction(this, Action.START_RIDING_JUMP$v1_12, MathHelper.floor$v1_12(this.getHorseJumpPower() * 100.0F))));
      } else {
         this.sendHorseJump();
      }
   }

   @Override
   public int bridge$getSprintToggleTimer() {
      return this.sprintToggleTimer;
   }

   @Override
   public void bridge$setSprintToggleTimer(int var1) {
      this.sprintToggleTimer = var1;
   }

   @Override
   public void bridge$setHorseJumpPowerCounter(int var1) {
      this.horseJumpPowerCounter = var1;
   }

   @Override
   public void bridge$setHorseJumpPower(float var1) {
      this.horseJumpPower = var1;
   }

   @Override
   public float bridge$getHorseJumpPower() {
      return this.horseJumpPower;
   }

   @Override
   public ContainerMarker bridge$getOpenContainer() {
      return (ContainerMarker)this.openContainer;
   }

   @Override
   public int bridge$getHorseJumpPowerCounter() {
      return this.horseJumpPowerCounter;
   }

   @Override
   public Hitbox bridge$getStatsCounter() {
      return (Hitbox)this.statWriter;
   }

   @Override
   public int bridge$getSwingProgress() {
      return this.swingProgressInt;
   }

   @Override
   public boolean bridge$isSwingInProgress() {
      return this.isSwingInProgress;
   }

   @Override
   public int bridge$getSwingingArm() {
      if (ThreadModuleDump63.MC_VERSION == 5) {
         return this.swingingHand$v1_12 != null ? this.swingingHand$v1_12.ordinal() : 0;
      } else {
         return 0;
      }
   }

   @Override
   public void bridge$swingHand(int var1) {
      if (ThreadModuleDump63.MC_VERSION == 5) {
         this.swingArm$v1_12(EnumHand.values()[var1]);
      } else {
         this.swingItem();
      }
   }

   @Override
   public void bridge$swingHandVisually(int var1) {
      if (!this.isSwingInProgress || this.swingProgressInt >= this.getArmSwingAnimationEnd() / 2 || this.swingProgressInt < 0) {
         this.swingProgressInt = -1;
         this.isSwingInProgress = true;
         if (ThreadModuleDump63.MC_VERSION >= 5) {
            this.swingingHand$v1_12 = EnumHand.values()[var1];
         }
      }
   }

   @Annotation2(min = 5)
   @Override
   public void bridge$setAttackStrengthTicker(int var1) {
      this.ticksSinceLastSwing$v1_12 = var1;
   }

   @Annotation2(min = 5)
   @Override
   public int bridge$getAttackStrengthTicker() {
      return this.ticksSinceLastSwing$v1_12;
   }

   @Override
   public float bridge$getAttackStrengthScale() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return this.getCooledAttackStrength$v1_12(0.0F);
      } else {
         throw new AbstractMethodErrorImpl();
      }
   }

   @Override
   public float bridge$getCurrentItemAttackStrengthDelay() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return this.getCooldownPeriod$v1_12();
      } else {
         throw new AbstractMethodErrorImpl();
      }
   }

   @Override
   public int bridge$getUsedItemHand() {
      return ThreadModuleDump63.MC_VERSION <= 1 ? 0 : this.getActiveHand$v1_12().ordinal();
   }

   @Override
   public void bridge$startUsingItem(int var1) {
      if (ThreadModuleDump63.MC_VERSION <= 1) {
         ItemStack var2 = this.getHeldItem();
         this.setItemInUse(var2, var2.getItem().getMaxItemUseDuration(var2));
      } else {
         this.setActiveHand$v1_12(EnumHand.values()[var1]);
      }
   }

   @Override
   public void bridge$stopUsingItem() {
      if (ThreadModuleDump63.MC_VERSION <= 1) {
         this.clearItemInUse();
      } else {
         this.resetActiveHand$v1_12();
      }
   }

   @Override
   public void bridge$drop(boolean var1) {
      if (ThreadModuleDump63.MC_VERSION == 1) {
         this.dropOneItem(var1);
      } else {
         this.dropItem(var1);
      }
   }

   @Override
   public boolean bridge$hasHealth() {
      return !this.capabilities.isCreativeMode && !this.bridge$isSpectator();
   }

   @Override
   public int bridge$getExperienceLevel() {
      return this.experienceLevel;
   }

   @Override
   public void bridge$closeScreen() {
      this.closeScreen();
   }

   @Override
   public Vec3Bridge bridge$getLastReportedLookAngle() {
      return (Vec3Bridge)this.getVectorForRotation(this.lastReportedPitch, this.lastReportedYaw);
   }

   @Override
   public Vector3d bridge$getLastReportedPos() {
      return new Vector3d(this.lastReportedPosX, this.lastReportedPosY, this.lastReportedPosZ);
   }

   @Override
   public boolean bridge$canSeeName(Bridge6_10 var1) {
      if (var1 == this) {
         return true;
      }

      Team var2;
      Team var3;
      if (ThreadModuleDump63.MC_VERSION == 1) {
         var2 = ((EntityPlayer)var1).getTeam();
         var3 = this.getTeam();
      } else {
         var2 = ((EntityPlayer)var1).getTeam();
         var3 = this.getTeam();
      }

      if (var2 == null) {
         return !var1.bridge$isInvisibleTo(this);
      }

      EnumVisible var4 = var2.getNameTagVisibility();

      return switch (var4) {
         case ALWAYS -> true;
         case NEVER -> false;
         case HIDE_FOR_OTHER_TEAMS -> var3 == null || var2.isSameTeam(var3);
         case HIDE_FOR_OWN_TEAM -> var3 == null || !var2.isSameTeam(var3);
         default -> throw new IncompatibleClassChangeError();
      };
   }

   @Override
   public boolean bridge$isPlayerSleeping() {
      return this.isPlayerSleeping();
   }

   @Override
   public float bridge$getSleepProgress() {
      return this.getSleepTimer();
   }

   @Override
   public float bridge$getCooldown(@NotNull ItemStackBridge var1) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         CooldownTracker var2 = this.getCooldownTracker$v1_12();
         if (var2.hasCooldown((Item)var1.bridge$getItem())) {
            return var2.getCooldown((Item)var1.bridge$getItem(), 0.0F);
         }
      }

      return -1.0F;
   }

   @Override
   public float bridge$getCurrentMood() {
      return 0.0F;
   }
}
