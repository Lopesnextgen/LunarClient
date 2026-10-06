package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.Framework2;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import java.util.Set;

public class Alert2Iterator2 implements Framework2 {
   private final Set<SettingsPage> field1;

   public Alert2Iterator2(SettingsPage var1) {
      this.field1 = Set.of(var1);
   }

   public Alert2Iterator2(SettingsPage[] var1) {
      this.field1 = Set.of(var1);
   }

   @Override
   public Set<SettingsPage> method1() {
      return this.field1;
   }
}
