package com.moonsworth.lunar.client.guiRewindhandlers;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.horsestats.DamageSourceQuery;
import com.moonsworth.lunar.client.framework.feature.knockbacktrainer.mixin.Knockbacktrainer;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.combat.EventPlayerDamaged;
import com.moonsworth.lunar.client.event.combat.PlayerKnockbackEvent;
import com.moonsworth.lunar.client.event.entity.EventHurtAnimation;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

public class GuiRewindhandlersHandler29 extends DynamicListener {
   private static final int field7 = 1;
   private int field8;
   private double field9;
   private boolean field10;
   private int field11;
   private boolean field12;
   private double field13;
   private boolean field14;
   private int field15;
   @Nullable
   private DamageSourceQuery field16;

   private GuiRewindhandlersHandler29() {
      this.handle(EventClientTick.class, this::method1);
      this.handle(PlayerKnockbackEvent.class, this::method2);
      this.handle(EventHurtAnimation.class, this::method3);
      this.handle(EventPlayerDamaged.class, this::method4);
      this.handle(EventWorldLifecycle.EventWorldLoaded.class, var1 -> this.method10());
   }

   @Override
   protected void onEnable() {
      this.method10();
   }

   @Override
   protected void onDisable() {
      this.method10();
   }

   private void method1(EventClientTick var1) {
      this.field8++;
      Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
      if (var2 != null) {
         this.field9 = var2.bridge$getMotionY();
         this.method6();
      }
   }

   private void method2(PlayerKnockbackEvent var1) {
      Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
      if (var2 != null && var1.method1() == var2) {
         this.field10 = true;
         this.field11 = this.field8;
         this.field12 = var2.bridge$isOnGround();
         this.field13 = this.field9;
         this.method5();
      }
   }

   private void method3(EventHurtAnimation var1) {
      Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
      if (var2 != null && var1.method1() == var2) {
         this.field14 = true;
         this.field15 = this.field8;
         this.method5();
      }
   }

   private void method4(EventPlayerDamaged var1) {
      Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
      if (var2 != null && var1.method1() == var2) {
         this.field16 = var1.method2();
      }
   }

   private void method5() {
      if (this.field10 && this.field14 && method9(this.field11, this.field15) <= 1) {
         this.method7();
      }
   }

   private void method6() {
      this.method5();
      if (this.field10 && this.field8 - this.field11 > 1) {
         this.field10 = false;
      }

      if (this.field14 && this.field8 - this.field15 > 1) {
         this.field14 = false;
         this.field16 = null;
      }
   }

   private void method7() {
      boolean var1 = this.field12;
      double var2 = this.field13;
      boolean var4 = !var1 && var2 < 0.0;
      DamageSourceQuery var5 = this.field16;
      this.method8();
      ClientEventBus.method29().method12(Knockbacktrainer.class, () -> new Knockbacktrainer(var4, var5));
   }

   private void method8() {
      this.field10 = false;
      this.field14 = false;
      this.field16 = null;
   }

   private static int method9(int value, int var1) {
      return Math.abs(value - var1);
   }

   private void method10() {
      this.field8 = 0;
      this.field9 = 0.0;
      this.method8();
      this.field11 = 0;
      this.field15 = 0;
      this.field12 = false;
      this.field13 = 0.0;
   }
}
