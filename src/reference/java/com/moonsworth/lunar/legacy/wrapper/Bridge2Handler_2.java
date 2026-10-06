package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge2_11;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.BridgeType2_9;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.mod.hud.bossbar.Bossbar;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import net.minecraft.client.gui.BossInfoClient;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.BossInfo.Color;
import net.minecraft.world.BossInfo.Overlay;

public class Bridge2Handler_2 implements Bridge2_11 {
   @Annotation2(min = 5)
   private BossInfoClient field1;

   @Annotation2(max = 1)
   public Bridge2Handler_2() {
   }

   @Annotation2(min = 5)
   public Bridge2Handler_2(BossInfoClient var1) {
      this.field1 = var1;
   }

   @Override
   public float method1() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? this.field1.getPercent() : BossStatus.healthScale;
   }

   @Override
   public float method2() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? this.field1.rawPercent : BossStatus.healthScale;
   }

   @Override
   public void method3(float var1) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.field1.setPercent(var1);
      } else {
         BossStatus.healthScale = var1;
      }
   }

   @Override
   public Optional<Component> method4() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return this.field1 == null ? Optional.empty() : Optional.ofNullable((Bridge2_42)this.field1.getName()).map(AdventureTextBridge::asAdventure);
      } else {
         return Optional.ofNullable(BossStatus.bossName).map(AdventureTextBridge::asAdventure);
      }
   }

   @Override
   public void method5(Component var1) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.field1.setName((IChatComponent)AdventureTextBridge.asBridge(var1));
      } else {
         BossStatus.bossName = AdventureTextBridge.asLegacyString(var1);
      }
   }

   @Override
   public int method6() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? 1 : BossStatus.statusBarTime;
   }

   @Override
   public void method7(int var1) {
      if (ThreadModuleDump63.MC_VERSION <= 1) {
         BossStatus.statusBarTime = var1;
      }
   }

   @Override
   public boolean method8() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? this.field1.color != null : BossStatus.hasColorModifier;
   }

   @Override
   public void method9(MixinHelper_4 var1, float var2, float var3, float var4, float var5, float var6) {
      int var7 = ThreadModuleDump23.method11(var4, var5, var6, 1.0F);
      ResourceLocationBridge var8 = ThreadModuleDump63.MC_VERSION <= 1 ? (var7 == -1 ? Bossbar.field9 : Bossbar.field8) : Bossbar.field10;
      var1.method44(var0 -> {
         var0.method29().method14();
         var0.method29().method4(BridgeType2_9.GL_SRC_ALPHA, BridgeType2_9.GL_ONE_MINUS_SRC_ALPHA, BridgeType2_9.GL_ONE, BridgeType2_9.GL_ZERO);
      });
      short var9 = 182;
      int var10 = (int)(this.method1() * (var9 + 1));
      byte var11 = 0;
      int var12 = 0;
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         var11 = (byte)(ThreadModuleDump63.method4().method40().method65().method13().get() ? Color.WHITE.ordinal() : this.field1.getColor().ordinal());
         var12 = this.field1.overlay.ordinal();
      }

      int var13 = ThreadModuleDump63.MC_VERSION >= 5 ? var11 * 5 * 2 : 74;
      int var14 = ThreadModuleDump63.MC_VERSION >= 5 ? 80 + (var12 - 1) * 5 * 2 : 0;
      LcuiScreen.method47(var1, var8, var2, var3, 0, var13, var9, 5, var7);
      if (ThreadModuleDump63.MC_VERSION >= 5 && this.field1.getOverlay() != Overlay.PROGRESS) {
         LcuiScreen.method47(var1, var8, var2, var3, 0, var14, 182, 5, var7);
      }

      if (var10 > 0) {
         LcuiScreen.method47(var1, var8, var2, var3, 0, var13 + 5, var10, 5, var7);
         if (ThreadModuleDump63.MC_VERSION >= 5 && this.field1.getOverlay() != Overlay.PROGRESS) {
            LcuiScreen.method47(var1, var8, var2, var3, 0, var14 + 5, var10, 5, var7);
         }
      }
   }
}
