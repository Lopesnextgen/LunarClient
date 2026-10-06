package com.moonsworth.lunar.client.framework.listener;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.event.input.InputAction;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventKeybind;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseButton;
import com.moonsworth.lunar.client.config.option.AbstractKeybindOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.OptionFeatureLink;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import java.util.ArrayList;
import java.util.List;

public class KeybindOptionListener implements EventBusAccess {
   private static final List<AbstractKeybindOption<?>> field1 = new ArrayList<>();

   public KeybindOptionListener() {
      this.handle(EventKeybind.class, this::method4);
      this.handle(EventMouseButton.class, this::method3);
   }

   public static void method1(AbstractKeybindOption<?> lightingextension49130) {
      field1.add(lightingextension49130);
   }

   public static void method2(AbstractKeybindOption<?> lightingextension49130) {
      field1.remove(lightingextension49130);
   }

   public void method3(EventMouseButton highlightimpl31) {
      if (highlightimpl31.method2() >= 0) {
         for (AbstractKeybindOption lightingextension49133 : field1) {
            KeyCode bridgetype_84 = KeyCode.valueOf("KEY_MOUSE" + (highlightimpl31.method2() + 1));
            this.method5(bridgetype_84, lightingextension49133, highlightimpl31.method4() == InputAction.DOWN);
         }
      }
   }

   public void method4(EventKeybind highlightimpl1) {
      for (AbstractKeybindOption lightingextension49133 : field1) {
         this.method5(highlightimpl1.method10(), lightingextension49133, highlightimpl1.method11() == InputAction.DOWN);
      }
   }

   private void method5(KeyCode bridgetype_81, AbstractKeybindOption<?> lightingextension49132, boolean flag3) {
      if (lightingextension49132.method8().equals(bridgetype_81)) {
         OptionFeatureLink nameplate34 = (OptionFeatureLink)lightingextension49132.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field8);
         if (nameplate34 == null || nameplate34.getFeature().isEnabled()) {
            if (!lightingextension49132.method11() || Ref.method11() == null) {
               if (!lightingextension49132.method12() || !ScreenInteractionHandler.method4()) {
                  if (lightingextension49132.isKeyDown() && lightingextension49132.method7() && flag3) {
                     lightingextension49132.method11(System.currentTimeMillis());
                     lightingextension49132.method12(true);

                     for (Object obj11 : lightingextension49132.method15()) {
                        if (obj11 instanceof Runnable runnable13) {
                           runnable13.run();
                        }
                     }
                  } else {
                     if (lightingextension49132.isDown() && lightingextension49132.method7() && !flag3) {
                        lightingextension49132.method12(false);
                        if (System.currentTimeMillis() - lightingextension49132.method13() <= lightingextension49132.method14()) {
                           for (Object obj10 : lightingextension49132.method16()) {
                              if (obj10 instanceof BooleanConsumer booleanconsumer12) {
                                 booleanconsumer12.accept(true);
                              }
                           }

                           return;
                        }

                        for (Object obj6 : lightingextension49132.method16()) {
                           if (obj6 instanceof BooleanConsumer booleanconsumer7) {
                              booleanconsumer7.accept(false);
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
