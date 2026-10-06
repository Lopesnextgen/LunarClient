package com.moonsworth.lunar.client.guiRewindhandlers.mixin;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.input.InputActionLegacy;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.KeybindEvent;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseButtonLegacy;
import com.moonsworth.lunar.client.config.option.AbstractKeybindOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.OptionFeatureLink;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import java.util.ArrayList;
import java.util.List;

public class GuiRewindhandlers6Task implements EventRegistrar {
   private static final List<AbstractKeybindOption<?>> field1 = new ArrayList<>();

   public GuiRewindhandlers6Task() {
      this.handle(KeybindEvent.class, this::method4);
      this.handle(EventMouseButtonLegacy.class, this::method3);
   }

   public static void method1(AbstractKeybindOption<?> var0) {
      field1.add(var0);
   }

   public static void method2(AbstractKeybindOption<?> var0) {
      field1.remove(var0);
   }

   public void method3(EventMouseButtonLegacy var1) {
      if (var1.method2() >= 0) {
         for (AbstractKeybindOption var3 : field1) {
            KeyCode var4 = KeyCode.valueOf("KEY_MOUSE" + (var1.method2() + 1));
            this.method5(var4, var3, var1.method4() == InputActionLegacy.DOWN);
         }
      }
   }

   public void method4(KeybindEvent var1) {
      for (AbstractKeybindOption var3 : field1) {
         this.method5(var1.method10(), var3, var1.method11() == InputActionLegacy.DOWN);
      }
   }

   private void method5(KeyCode var1, AbstractKeybindOption<?> option, boolean var3) {
      if (option.method8().equals(var1)) {
         OptionFeatureLink var4 = (OptionFeatureLink)option.method7(OptionTraits.field8);
         if (var4 == null || var4.<Framework7Extension>getFeature().isEnabled()) {
            if (!option.method11() || ThreadModuleDump63.method11() == null) {
               if (!option.method12() || !GuiRewindhandlers3.method4()) {
                  if (option.isKeyDown() && option.method7() && var3) {
                     option.method11(System.currentTimeMillis());
                     option.method12(true);

                     for (Object var11 : option.method15()) {
                        if (var11 instanceof Runnable var13) {
                           var13.run();
                        }
                     }
                  } else {
                     if (option.isDown() && option.method7() && !var3) {
                        option.method12(false);
                        if (System.currentTimeMillis() - option.method13() <= option.method14()) {
                           for (Object var10 : option.method16()) {
                              if (var10 instanceof BooleanConsumer var12) {
                                 var12.accept(true);
                              }
                           }

                           return;
                        }

                        for (Object var6 : option.method16()) {
                           if (var6 instanceof BooleanConsumer var7) {
                              var7.accept(false);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
