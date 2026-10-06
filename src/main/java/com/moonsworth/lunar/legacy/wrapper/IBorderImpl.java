package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.world.border.IBorderListener;
import net.minecraft.world.border.WorldBorder;

@VersionGate(min = 1)
public class IBorderImpl implements IBorderListener {
   public IBorderImpl() {
   }

   public void onSizeChanged(WorldBorder worldborder1, double value2) {
   }

   public void onTransitionStarted(WorldBorder worldborder1, double value2, double value4, long value) {
   }

   public void onCenterChanged(WorldBorder worldborder1, double value2, double value4) {
   }

   public void onWarningTimeChanged(WorldBorder worldborder1, int number2) {
   }

   public void onWarningDistanceChanged(WorldBorder worldborder1, int number2) {
   }

   public void onDamageAmountChanged(WorldBorder worldborder1, double value2) {
   }

   public void onDamageBufferChanged(WorldBorder worldborder1, double value2) {
   }
}
