package com.moonsworth.lunar.client.mod.combat.combo;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.event.entity.EventEntityStatus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.framework.Ref;

public class Combo extends AbstractFeature {
   private static final int field8 = 4;
   private int field9;
   private boolean field10;
   private int field11;

   public Combo() {
      super(false);
      this.method2(ModTraits.field1, TypedHudRenderer.method22(0.0F, 0.0F, HudAnchor.TOP_RIGHT, HudSize.method1(10, 18, 22, 46, 56, 62), arg1 -> {
         int number2 = (Integer)this.method1("combo", this.field9);
         return number2 == 0 ? this.method14("noCombo", new Object[0]) : this.method14("combo", new Object[]{number2});
      }));
      this.method14(EventTick.class, this::method13);
      this.handle(EventEntityStatus.class, this::method2);
      this.handle(EventSecond.class, arg1 -> {
         Bridge5Extension_5 bridge5extension_52 = Ref.method7();
         if (bridge5extension_52 != null) {
            if (!bridge5extension_52.bridge$getLastAttacker().isPresent() || bridge5extension_52.method2() - bridge5extension_52.bridge$getLastAttackerTime() >= 80) {
               if (this.field10) {
                  this.field9 = 0;
               }

               this.field10 = !this.field10;
            }
         }
      });
   }

   public String getId() {
      return "COMBO";
   }

   private void method13() {
      Bridge5Extension_5 bridge5extension_51 = this.mc.bridge$getPlayer();
      if (bridge5extension_51 != null && bridge5extension_51.bridge$wasJustHurt()) {
         this.field9 = 0;
      }
   }

   private void method2(EventEntityStatus highlightimpl111) {
      if (highlightimpl111.method1() instanceof Bridge6_10) {
         Bridge5Extension_5 bridge5extension_52 = Ref.method7();
         if (bridge5extension_52 == null) {
            return;
         }

         bridge5extension_52.bridge$getLastAttacker().ifPresent(arg3 -> {
            if (arg3 == highlightimpl111.method1()) {
               int number4 = bridge5extension_52.bridge$getLastAttackerTime();
               if (this.field11 != number4) {
                  this.field11 = number4;
                  if (Math.abs(number4 - bridge5extension_52.method2()) <= 4) {
                     this.field9++;
                  }
               }
            }
         });
      }
   }
}
