package com.moonsworth.lunar.client.framework.listener;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.minecraft.DamageSourceBridge;
import com.moonsworth.lunar.client.framework.feature.knockbacktrainer.KnockbackEvent;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.combat.EventPlayerReceiveDamage;
import com.moonsworth.lunar.client.event.combat.EventPlayerKnockback;
import com.moonsworth.lunar.client.event.entity.EventEntityHurtAnimation;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.framework.Ref;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

public class KnockbackListener extends DynamicListener {
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
   private DamageSourceBridge field16;

   private KnockbackListener() {
      this.handle(EventTick.class, this::method1);
      this.handle(EventPlayerKnockback.class, this::method2);
      this.handle(EventEntityHurtAnimation.class, this::method3);
      this.handle(EventPlayerReceiveDamage.class, this::method4);
      this.handle(EventWorld.EventWorldLoad.class, arg1 -> this.method10());
   }

   @Override
   protected void onEnable() {
      this.method10();
   }

   @Override
   protected void onDisable() {
      this.method10();
   }

   private void method1(EventTick event) {
      this.field8++;
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         this.field9 = bridge5extension_52.bridge$getMotionY();
         this.method6();
      }
   }

   private void method2(EventPlayerKnockback highlightimpl11_21) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null && highlightimpl11_21.method1() == bridge5extension_52) {
         this.field10 = true;
         this.field11 = this.field8;
         this.field12 = bridge5extension_52.bridge$isOnGround();
         this.field13 = this.field9;
         this.method5();
      }
   }

   private void method3(EventEntityHurtAnimation event) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null && event.method1() == bridge5extension_52) {
         this.field14 = true;
         this.field15 = this.field8;
         this.method5();
      }
   }

   private void method4(EventPlayerReceiveDamage event) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null && event.method1() == bridge5extension_52) {
         this.field16 = event.method2();
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
      boolean flag1 = this.field12;
      double value2 = this.field13;
      boolean flag4 = !flag1 && value2 < 0.0;
      DamageSourceBridge horsestats195 = this.field16;
      this.method8();
      LunarEventBus.method29().method12(KnockbackEvent.class, () -> new KnockbackEvent(flag4, horsestats195));
   }

   private void method8() {
      this.field10 = false;
      this.field14 = false;
      this.field16 = null;
   }

   private static int method9(int value, int value2) {
      return Math.abs(value - value2);
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
