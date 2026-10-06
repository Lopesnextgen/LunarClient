package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.hitbox.Hitbox;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_3;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3i;

public interface Bridge5Extension_5 extends Bridge5_11 {
   ClientPacketListenerBridge bridge$getSendQueue();

   default void method1(String var1, float var2, float var3) {
      this.bridge$getWorld().bridge$playSound(this.bridge$getPosX(), this.bridge$getPosY(), this.bridge$getPosZ(), var1, var2, var3, false);
   }

   default void method2(String var1, double var2, double var4, double var6, float var8, float var9) {
      this.bridge$getWorld().bridge$playSound(var2, var4, var6, var1, var8, var9, false);
   }

   Optional<String> bridge$getClientBrand();

   void bridge$sendChatMessage(String var1);

   void bridge$sendCommand(String var1);

   MovementStateBridge bridge$getMovementInput();

   void bridge$onCriticalHit(BridgeExtension var1);

   void bridge$setMovementInput(MovementStateBridge var1);

   @Override
   boolean bridge$isSprinting();

   void bridge$setSprinting(boolean var1);

   boolean bridge$isRidingHorse();

   void bridge$sendRidingJumpPacket();

   int bridge$getSprintToggleTimer();

   void bridge$setSprintToggleTimer(int var1);

   void bridge$pushOutOfBlocks(double var1, double var3, double var5);

   void bridge$sendPlayerAbilities();

   void bridge$setHorseJumpPowerCounter(int var1);

   int bridge$getHorseJumpPowerCounter();

   void bridge$setHorseJumpPower(float var1);

   float bridge$getHorseJumpPower();

   ContainerMarker bridge$getOpenContainer();

   default float bridge$getYSize() {
      return 0.0F;
   }

   default void bridge$setYSize(float var1) {
   }

   default Itemcounter_3 method6() {
      Itemcounter6 var1 = this.bridge$getWorld();
      Vector3i var2 = new Vector3i(this.bridge$getBlockX(), this.bridge$getBlockY(), this.bridge$getBlockZ());
      return var1.bridge$getChunkFromBlockCoords(var2).bridge$getBiome(var2, var1.bridge$getWorldChunkManager());
   }

   Hitbox bridge$getStatsCounter();

   int bridge$getSwingProgress();

   boolean bridge$isSwingInProgress();

   int bridge$getSwingingArm();

   void bridge$swingHand(int var1);

   void bridge$swingHandVisually(int var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 5)
   void bridge$setAttackStrengthTicker(int var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 5)
   int bridge$getAttackStrengthTicker();

   @com.moonsworth.lunar.ichor.Annotation2(min = 35)
   void bridge$setItemSwapTicker(int var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 35)
   int bridge$getItemSwapTicker();

   float bridge$getAttackStrengthScale();

   float bridge$getCurrentItemAttackStrengthDelay();

   int bridge$getUsedItemHand();

   void bridge$startUsingItem(int var1);

   void bridge$stopUsingItem();

   void bridge$drop(boolean var1);

   boolean bridge$hasHealth();

   int bridge$getExperienceLevel();

   boolean bridge$canSeeName(Bridge6_10 var1);

   void bridge$closeScreen();

   boolean bridge$isPlayerSleeping();

   float bridge$getSleepProgress();

   float bridge$getCooldown(@NotNull ItemStackBridge var1);

   float bridge$getCurrentMood();

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   boolean bridge$canEnterStandingPose();
}
