package com.moonsworth.lunar.mixin.mixin;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.bridge.ContainerMarker;
import com.moonsworth.lunar.bridge.MovementStateBridge;
import com.moonsworth.lunar.bridge.hitbox.Hitbox;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.MovementInput;
import net.minecraft.util.Session;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityClientPlayerMP.class)
public abstract class EntityClientPlayerMPMixin extends EntityPlayerSP implements Bridge5Extension_5 {
   @Final
   @Shadow
   public NetHandlerPlayClient sendQueue;

   @Shadow
   public abstract void sendPlayerAbilities();

   @Shadow
   public abstract void sendHorseJump();

   @Shadow
   public abstract String getClientBrand();

   @Shadow
   public abstract void sendChatMessage(String var1);

   @Shadow
   public abstract StatFileWriter getStatFileWriter();

   @Shadow
   public abstract void swingItem();

   @Shadow
   public abstract EntityItem dropOneItem(boolean var1);

   public EntityClientPlayerMPMixin(Minecraft var1, World var2, Session var3, int var4) {
      super(var1, var2, var3, var4);
   }

   @Override
   public Optional<String> bridge$getClientBrand() {
      return Optional.ofNullable(this.getClientBrand());
   }

   @Override
   public ClientPacketListenerBridge bridge$getSendQueue() {
      return (ClientPacketListenerBridge)this.sendQueue;
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
      this.sendHorseJump();
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
   public float bridge$getYSize() {
      return this.yOffset;
   }

   @Override
   public void bridge$setYSize(float var1) {
      this.yOffset = var1;
   }

   @Override
   public Hitbox bridge$getStatsCounter() {
      return (Hitbox)this.getStatFileWriter();
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
      return 0;
   }

   @Override
   public void bridge$swingHand(int var1) {
      this.swingItem();
   }

   @Override
   public void bridge$swingHandVisually(int var1) {
      if (!this.isSwingInProgress || this.swingProgressInt >= this.getArmSwingAnimationEnd() / 2 || this.swingProgressInt < 0) {
         this.swingProgressInt = -1;
         this.isSwingInProgress = true;
      }
   }

   @Override
   public int bridge$getUsedItemHand() {
      return 0;
   }

   @Override
   public void bridge$startUsingItem(int var1) {
      ItemStack var2 = this.getHeldItem();
      this.setItemInUse(var2, var2.getItem().getMaxItemUseDuration(var2));
   }

   @Override
   public void bridge$stopUsingItem() {
      this.clearItemInUse();
   }

   @Override
   public void bridge$drop(boolean var1) {
      this.dropOneItem(var1);
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
   public boolean bridge$canSeeName(Bridge6_10 var1) {
      return !var1.bridge$isInvisibleTo(this);
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
      return -1.0F;
   }

   @Override
   public float bridge$getCurrentMood() {
      return 0.0F;
   }

   @Override
   public float bridge$getAttackStrengthScale() {
      throw new AbstractMethodErrorImpl();
   }

   @Override
   public float bridge$getCurrentItemAttackStrengthDelay() {
      throw new AbstractMethodErrorImpl();
   }
}
