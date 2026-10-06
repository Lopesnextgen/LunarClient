package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jetbrains.annotations.Nullable;

public abstract class Fishing3_3 {
   public abstract String getCommand();

   public List<String> getAliases() {
      return List.of();
   }

   public List<String> method1() {
      return Stream.concat(Stream.of(this.getCommand()), this.getAliases().stream()).toList();
   }

   public boolean method2() {
      return false;
   }

   public boolean isEnabledByDefault() {
      return true;
   }

   public boolean method3() {
      return true;
   }

   public EnumSet<Gui2Extension> method4() {
      return EnumSet.of(Gui2Extension.PARTY);
   }

   public final Set<String> method5() {
      return this.method4().stream().map(Gui2Extension::id).collect(Collectors.toSet());
   }

   public abstract void method6(String var1, String var2, String[] var3, Gui2Extension var4);

   protected final void method7(String var1) {
      this.method8(null, var1);
   }

   protected final void method8(@Nullable Gui2Extension var1, String var2) {
      String var3 = var1 != null ? var1.getCommand() + " " : "";
      ThreadModuleDump63.method4().method40().method82().method187().method15().add(var3 + var2);
   }
}
