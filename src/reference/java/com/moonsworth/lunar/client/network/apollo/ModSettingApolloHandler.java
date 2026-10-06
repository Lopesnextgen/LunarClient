package com.moonsworth.lunar.client.network.apollo;

import com.google.common.base.CaseFormat;
import com.google.protobuf.Message;
import com.lunarclient.apollo.modsetting.v1.InstalledModsRequest;
import com.lunarclient.apollo.modsetting.v1.InstalledModsResponse;
import com.lunarclient.apollo.modsetting.v1.Mod;
import com.lunarclient.apollo.modsetting.v1.ModGroup;
import com.lunarclient.apollo.modsetting.v1.Mod.Builder;
import com.lunarclient.apollo.modsetting.v1.ModGroup.Type;
import com.lunarclient.apollo.option.Option;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.alert.mixin.AlertType;
import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.ui.notification.Notification;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.options.OptionUpdateEvent;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Ichor5Handler_2;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import io.leangen.geantyref.GenericTypeReflector;
import java.awt.Color;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;

public class ModSettingApolloHandler extends ApolloModuleHandler implements Calculator2 {
   private Set<Framework7Extension> field4;
   private Set<Framework7Extension> field5;
   private int field6 = 20;
   private final ApolloModSettingsBridge field7 = new ApolloModSettingsBridge();

   public ModSettingApolloHandler() {
      super("mod_setting", "Mod Setting");
      this.handle(OptionUpdateEvent.class, this::method7);
      this.handle(EventClientTick.class, this::method8);
      this.handle(DisconnectEvent.class, this::method9);
   }

   @Override
   public Collection<Option<?, ?, ?>> method1() {
      return ApolloModSettingsBridge.field1.getModSettingsOptions();
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(InstalledModsRequest.class);
   }

   @Override
   protected void onDisable() {
      this.method15();
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
      var1.unpack(InstalledModsRequest.class).ifPresent(this::method4);
   }

   private void method4(InstalledModsRequest var1) {
      List var2 = IchorAPI.getPipeline(Client.class).orElseThrow().method18().toList();
      byte var3 = 100;
      int var4 = Math.max(1, (int)Math.ceil((double)var2.size() / var3));

      for (int var5 = 0; var5 < var4; var5++) {
         int var6 = var5 * var3;
         int var7 = Math.min(var6 + var3, var2.size());
         List var8 = var2.subList(var6, var7);
         Map var9 = var8.stream().collect(Collectors.groupingBy(this::method5, LinkedHashMap::new, Collectors.mapping(this::method6, Collectors.toList())));
         List var10 = var9.entrySet()
            .stream()
            .map(var0 -> ModGroup.newBuilder().setType((Type)var0.getKey()).addAllMods((Iterable)var0.getValue()).build())
            .toList();
         InstalledModsResponse var11 = InstalledModsResponse.newBuilder()
            .setRequestId(var1.getRequestId())
            .setPage(var5)
            .setTotalPages(var4)
            .addAllModGroups(var10)
            .build();
         this.sendPacket(var11);
      }
   }

   private Type method5(Ichor5Handler_2 var1) {
      boolean var2 = Bridge.getMinecraftVersion().method19();
      if (var2) {
         return var1.method5() ? Type.TYPE_FABRIC_INTERNAL : Type.TYPE_FABRIC_EXTERNAL;
      } else {
         return var1.method5() ? Type.TYPE_FORGE_INTERNAL : Type.TYPE_FORGE_EXTERNAL;
      }
   }

   private Mod method6(Ichor5Handler_2 var1) {
      Builder var2 = Mod.newBuilder().setId(var1.getId());
      if (var1.getVersion() != null) {
         var2.setVersion(var1.getVersion());
      }

      return var2.build();
   }

   private void method7(OptionUpdateEvent var1) {
      Option var2 = var1.getOption();
      Object var3 = var1.getValue();
      if (!(this.getOptions() instanceof OptionsImpl var4 && var4.method1(var2.getKey()).isEmpty())) {
         this.method10(var2, var3);
      }
   }

   private void method8(EventClientTick var1) {
      if (this.field5 != null) {
         if (this.field4 != null && this.field5.containsAll(this.field4)) {
            this.field5 = null;
         } else if (this.field6 > 0) {
            this.field6--;
         } else {
            Set var2 = this.field5;
            this.method15();
            if (!ThreadModuleDump63.method4().method40().method85().method19()) {
               List var3 = var2.stream().filter(var0 -> {
                  ModEnabledState var1x = (ModEnabledState)var0.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field6);
                  return var1x == null ? true : var1x.method1().map(ClientOption::get).orElse(true);
               }).map(var0 -> {
                  ModDetails var1x = (ModDetails)var0.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
                  return var1x != null ? var1x.getName() : var0.getId();
               }).toList();
               Serializable var4 = String.join(", ", var3);
               if (!var4.isEmpty()) {
                  String var5 = this.OHROCHICOIOICHOCRROORRCIIICIHO("mods-disabled-message", new Object[]{AdventureChatFormatting.RED + var4});
                  Notification var6 = new Notification(CosmeticManager.field43, AdventureChatFormatting.RED + "Mods Disabled", var5);
                  var6.method10(5000L);
                  ThreadModuleDump63.method3().bridge$submit(() -> ThreadModuleDump63.method4().method69().method10(var6));
               }
            }
         }
      }
   }

   private void method9(DisconnectEvent var1) {
      this.field4 = null;
   }

   private void method10(Option<?, ?, ?> var1, Object var2) {
      String[] var3 = var1.getPath();
      String var4 = var3[0];
      String var5 = var3[1];
      Object var6 = var2 instanceof String var7 ? method16(var1, var7) : var2;
      ThreadModuleDump63.method4()
         .method40()
         .IIORHHIRHIORHRCCCOICCRCHRRCCRH()
         .stream()
         .filter(var1x -> var4.equalsIgnoreCase(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, var1x.getId())))
         .forEach(var5x -> {
            if (var5.equalsIgnoreCase("ENABLED")) {
               this.method11(var5x, var6);
            } else {
               this.method12(var5x, var5, var6);
            }

            Slayer.method3("[Apollo] [Mod Settings] %s.%s updated to '%s'", var4, var5, var2);
         });
      ThreadModuleDump63.method4().method84().method15(this.getId(), var5 + ":" + var2);
   }

   private void method11(Framework7Extension var1, Object var2) {
      Alert2 var3 = (Alert2)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field4);
      if (var3 != null) {
         var3.method1(var1, AlertType.SERVER, (Boolean)var2);
      }

      if (var2 == Boolean.FALSE) {
         this.method14(var1);
      }
   }

   private void method12(Framework7Extension var1, String var2, Object var3) {
      AlertExtension var4 = (AlertExtension)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
      if (var4 != null) {
         for (Framework7Extension var6 : var4.getChildren()) {
            if (var2.equalsIgnoreCase(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, var6.getId()))) {
               ClientOption var7 = var6.method3(Framework.field6).flatMap(ModEnabledState::method1).orElse(null);
               this.method13(var6, var7, (Alert2)var6.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field4), var3);
            }
         }
      }

      Framework5 var8 = (Framework5)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
      if (var8 != null) {
         var8.method4(var3x -> {
            Alert2 var4x = (Alert2)var3x.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field5);
            if (var4x != null && !var3x.method2(OptionTraits.field12)) {
               String var5 = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, var3x.getId());
               if (var2.equalsIgnoreCase(var5)) {
                  this.method13(var3x, var3x, var4x, var3);
               }
            }

            return false;
         });
      }
   }

   private void method13(Object var1, @Nullable ClientOption var2, @Nullable Alert2 var3, Object var4) {
      if (var3 != null) {
         if (var4 == null) {
            var3.method1(var1, AlertType.SERVER, null);
         } else {
            if (var2 != null) {
               var3.method1(var1, AlertType.SERVER, var4);
            }
         }
      }
   }

   private void method14(Framework7Extension var1) {
      Set var2 = this.field5 == null ? new HashSet() : this.field5;
      if (var2.add(var1)) {
         this.field5 = var2;
      }
   }

   private void method15() {
      if (this.field5 != null && !this.field5.isEmpty()) {
         this.field4 = this.field5;
      }

      this.field5 = null;
      this.field6 = 20;
   }

   @Override
   public String getLanguagePath() {
      return "settings";
   }

   @VisibleForTesting
   public static Object method16(Option<?, ?, ?> var0, String var1) {
      if (!Color.class.isAssignableFrom(GenericTypeReflector.erase(var0.getTypeToken().getType()))) {
         return var1;
      }

      try {
         return ColorOption.method24(var1);
      } catch (NumberFormatException var3) {
         return var1;
      }
   }

   @Generated
   public ApolloModSettingsBridge method17() {
      return this.field7;
   }
}
