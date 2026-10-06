package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldChanged;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.util.Coordinates;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Iterator;
import java.util.TreeSet;
import net.kyori.adventure.text.Component;

public class GuiRewindhandlersHandler26 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final TreeSet<ComparableImpl> field7 = new TreeSet<>();

   public GuiRewindhandlersHandler26() {
      this.handle(HudBaseRenderEvent.class, this::method5);
      this.handle(EventWorldChanged.class, this::method6);
      this.handle(EventClientTick.class, this::method1);
   }

   private void method1(EventClientTick var1) {
      this.field7.removeIf(var0 -> var0.method4() < ThreadModuleDump63.method3().bridge$getSystemTime());
   }

   public void method2(ComparableImpl var1) {
      this.method3(var1.method3());
      this.field7.add(var1);
   }

   public void method3(String var1) {
      this.field7.removeIf(var1x -> var1x.method3().equals(var1));
   }

   public void method4(String var1, Component var2, LightingExtension443 var3, Framework7Extension var4) {
      if ((Boolean)var3.get()) {
         long var5 = (long)((Float)var3.method10().get() * 1000.0F);
         if (var5 > 0L) {
            this.method2(ComparableImpl.method2().method1(var1).method2(var2).method3(var5).method4(var3.method12()).method6());
         }

         Coordinates.method2(var3.method11(), var4);
      }
   }

   private void method5(HudBaseRenderEvent var1) {
      if (!this.field7.isEmpty()) {
         Iterator var2 = this.field7.descendingIterator();
         Object var3 = var2.hasNext() ? ((ComparableImpl)var2.next()).getComponent() : Component.empty();
         Object var4 = var2.hasNext() ? ((ComparableImpl)var2.next()).getComponent() : Component.empty();
         MixinHelper_4 var5 = var1.method2();
         float var6 = ThreadModuleDump63.method10().bridge$getStringWidth((Component)var3);
         float var7 = ThreadModuleDump63.method10().bridge$getStringWidth((Component)var4);
         float var8 = Math.max(var6, var7 / 2.0F);
         float var9 = (float)var1.method3().method10() / var8 / 3.0F;
         var9 *= ThreadModuleDump63.method4().method40().method82().method24().get();
         var5.push();
         var5.method39((float)var1.method3().method10() / 2.0F, (float)(var1.method3().method11() / 2.0));
         var5.method40(var9, var9);
         var5.method11(ThreadModuleDump63.method10(), (Component)var3, -var6 / 2.0F, -ThreadModuleDump63.method10().method19() * 1.5F, -1, true);
         var5.method40(0.5F, 0.5F);
         var5.method11(ThreadModuleDump63.method10(), (Component)var4, -var7 / 2.0F, -ThreadModuleDump63.method10().method19(), -1, true);
         var5.pop();
      }
   }

   private void method6(EventWorldChanged var1) {
      this.field7.clear();
   }
}
