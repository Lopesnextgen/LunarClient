package com.moonsworth.lunar.client.driver.bridge;

import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.util.UUID;

public class OutfitBridge implements DriverGuiExtension, GuiIterator.Extension {
   public OutfitBridge() {
   }

   @Override
   public GuiIterator getProvider() {
      return Ref.method4().method55().method16();
   }

   @CallbackJS("add")
   public static void add(String text0) {
      Ref.method4().method55().method7(text0);
   }

   @CallbackJS("delete")
   public static void method2(UUID uuid0) {
      Ref.method4().method55().method8(uuid0);
   }

   @CallbackJS("setDefault")
   public static void method3(UUID uuid0) {
      Ref.method4().method55().method9(uuid0);
   }

   @CallbackJS("setFavorite")
   public static void method4(UUID uuid0) {
      Ref.method4().method55().method13(uuid0);
   }

   @CallbackJS("rename")
   public static void method5(UUID uuid0, String text1) {
      Ref.method4().method55().method12(uuid0, text1);
   }
}
