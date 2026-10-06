package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.audio.LunarSoundPlayer;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Iterator;
import java.util.TreeSet;
import net.kyori.adventure.text.Component;

public class AlertDisplayListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final TreeSet<ComparableImpl> field7 = new TreeSet<>();

   public AlertDisplayListener() {
      this.handle(EventRenderHudBase.class, this::method5);
      this.handle(EventWorldChange.class, this::method6);
      this.handle(EventTick.class, this::method1);
   }

   private void method1(EventTick event) {
      this.field7.removeIf(arg0 -> arg0.method4() < Ref.method3().bridge$getSystemTime());
   }

   public void method2(ComparableImpl comparable) {
      this.method3(comparable.method3());
      this.field7.add(comparable);
   }

   public void method3(String text1) {
      this.field7.removeIf(arg1x -> arg1x.method3().equals(text1));
   }

   public void method4(String text1, Component component2, LightingExtension443 lightingExtension443, Framework7Extension framework7) {
      if ((Boolean)lightingExtension443.get()) {
         long number5 = (long)((Float)lightingExtension443.method10().get() * 1000.0F);
         if (number5 > 0L) {
            this.method2(ComparableImpl.method2().method1(text1).method2(component2).method3(number5).method4(lightingExtension443.method12()).method6());
         }

         LunarSoundPlayer.method2(lightingExtension443.method11(), framework7);
      }
   }

   private void method5(EventRenderHudBase event) {
      if (!this.field7.isEmpty()) {
         Iterator iterator2 = this.field7.descendingIterator();
         Object obj3 = iterator2.hasNext() ? ((ComparableImpl)iterator2.next()).getComponent() : Component.empty();
         Object obj4 = iterator2.hasNext() ? ((ComparableImpl)iterator2.next()).getComponent() : Component.empty();
         MixinHelper_4 mixinhelper_45 = event.method2();
         float value6 = Ref.method10().bridge$getStringWidth((Component)obj3);
         float value7 = Ref.method10().bridge$getStringWidth((Component)obj4);
         float value8 = Math.max(value6, value7 / 2.0F);
         float value9 = (float)event.method3().method10() / value8 / 3.0F;
         value9 *= Ref.method4().method40().method82().method24().get();
         mixinhelper_45.push();
         mixinhelper_45.method39((float)event.method3().method10() / 2.0F, (float)(event.method3().method11() / 2.0));
         mixinhelper_45.method40(value9, value9);
         mixinhelper_45.method11(Ref.method10(), (Component)obj3, -value6 / 2.0F, -Ref.method10().method19() * 1.5F, -1, true);
         mixinhelper_45.method40(0.5F, 0.5F);
         mixinhelper_45.method11(Ref.method10(), (Component)obj4, -value7 / 2.0F, -Ref.method10().method19(), -1, true);
         mixinhelper_45.pop();
      }
   }

   private void method6(EventWorldChange event) {
      this.field7.clear();
   }
}
