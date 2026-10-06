package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.Alert2Iterator2;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import java.util.Set;

public interface Framework2 {
   Set<SettingsPage> method1();

   static Framework2 method2(SettingsPage var0) {
      return new Alert2Iterator2(var0);
   }

   static Framework2 method3(SettingsPage... var0) {
      return new Alert2Iterator2(var0);
   }
}
