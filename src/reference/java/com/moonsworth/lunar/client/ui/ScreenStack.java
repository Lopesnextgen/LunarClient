package com.moonsworth.lunar.client.ui;

import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.client.fishing.Fishing;
import com.moonsworth.lunar.client.fishing.highlight.Fishing2Extension;
import com.moonsworth.lunar.client.framework.ItemSetHandler;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.screen.ResolutionChangeEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import java.util.LinkedHashSet;
import java.util.Set;

public class ScreenStack extends ItemSetHandler<Bridge7_8> implements EventRegistrar {
   private long field2 = 0L;

   public ScreenStack() {
      this.handle(EventClientTick.class, this::method8);
      this.handle(ResolutionChangeEvent.class, this::method9);
   }

   protected Set<Bridge7_8> method3() {
      return new LinkedHashSet<>();
   }

   public void method2(Bridge7_8 var1) {
      this.method13().add(var1);
      ThreadModuleDump71 var2 = new ThreadModuleDump71(ThreadModuleDump63.method3());
      var1.method2(ThreadModuleDump63.method3(), new MarkerModel.Data4(var2.getScaledWidth(), var2.getScaledHeight()));
   }

   public void method3(Bridge7_8 var1) {
      this.method13().removeIf(var1x -> var1x == var1);
   }

   public void method4(AbstractRenderContext var1) {
      Fishing.method2(Fishing2Extension.class).ifPresent(var1x -> {
         if (var1x.method1() && var1x.method4()) {
            this.method5();
         }
      });
      if (ThreadModuleDump63.method4()
         .method40()
         .method85()
         .method17(var0 -> ((Nameplate4)var0.method42().get()).method4() == null || ((Nameplate4)var0.method42().get()).method4().isPaused() || var0.method25())
         )
       {
         this.method5();
      }

      this.method6(var1);
   }

   private void method5() {
      if (System.currentTimeMillis() - this.field2 >= 50L) {
         for (Bridge7_8 var2 : this.method13()) {
            var2.updateScreen();
         }

         this.field2 = System.currentTimeMillis();
      }
   }

   private void method6(AbstractRenderContext var1) {
      LegacyGuiGraphicsBridge var2 = new LegacyGuiGraphicsBridge(var1);

      for (Bridge7_8 var4 : this.method13()) {
         var4.method1(var2, new MarkerModel.Data4(0.0, 0.0), 0.0F);
      }
   }

   public void method7(Bridge5_12 var1, int var2, int var3) {
      for (Bridge7_8 var5 : this.method13()) {
         var5.method2(var1, new MarkerModel.Data4(var2, var3));
      }
   }

   public void method8(EventClientTick var1) {
      this.field2 = System.currentTimeMillis();

      for (Bridge7_8 var3 : this.method13()) {
         var3.updateScreen();
      }
   }

   public void method9(ResolutionChangeEvent var1) {
      this.method7(ThreadModuleDump63.method3(), var1.getScaledWidth(), var1.getScaledHeight());
   }
}
