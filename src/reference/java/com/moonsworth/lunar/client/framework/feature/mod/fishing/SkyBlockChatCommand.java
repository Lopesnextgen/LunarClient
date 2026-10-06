package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.framework.Ref;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jetbrains.annotations.Nullable;

public abstract class SkyBlockChatCommand {
   public SkyBlockChatCommand() {
   }

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

   public EnumSet<FishingChatType> method4() {
      return EnumSet.of(FishingChatType.PARTY);
   }

   public final Set<String> method5() {
      return this.method4().stream().map(FishingChatType::id).collect(Collectors.toSet());
   }

   public abstract void method6(String text1, String text2, String[] items3, FishingChatType gui2extension4);

   protected final void method7(String text1) {
      this.method8(null, text1);
   }

   protected final void method8(@Nullable FishingChatType gui2extension1, String text2) {
      String text3 = gui2extension1 != null ? gui2extension1.getCommand() + " " : "";
      Ref.method4().method40().method82().method187().method15().add(text3 + text2);
   }
}
