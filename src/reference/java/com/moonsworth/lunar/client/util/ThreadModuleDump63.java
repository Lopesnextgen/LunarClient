package com.moonsworth.lunar.client.util;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.IResourceBridge;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.Bridge7_2;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.KeyBindingClashEntry;
import com.moonsworth.lunar.bridge.horsestats.KeyBindingOrigin;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.render.shader.ShaderStateHelper;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.feature.debug.Gui2Extension;
import com.moonsworth.lunar.client.config.option.AbstractKeybindOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.OptionFeatureLink;
import com.moonsworth.lunar.client.driver.DriverOverlayRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DualMarkerScreenLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.mixin.EntityRenderer4;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.waypoints.WebSocketClientIterator;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;
import com.moonsworth.lunar.client.framework.Ref;

public class ThreadModuleDump63 {
   public static final boolean field1 = method42();
   @NotNull
   private static final IchorPipeline field2 = field1 ? null : IchorAPI.getPipeline(ThreadModuleDump63.class.getClassLoader()).orElseThrow();
   @com.moonsworth.lunar.ichor.util.Annotation2
   public static final int MC_VERSION = field1 ? 0 : Config.method36(field2.method34().method6()).getOrdinal();
   private static final Map<ThreadModuleDump63.Data, Set<KeyBindingClashEntry>> field3 = new HashMap<>();
   private static final Map<Object, ThreadModuleDump63.Data> field4 = new HashMap<>();
   private static final Set<AbstractKeybindOption<?>> field5 = new LinkedHashSet<>();
   private static int field6;
   private static final Map<String, String> field7 = new HashMap<>();

   public static boolean method1() {
      return MC_VERSION >= 39 && !ShaderStateHelper.method8();
   }

   @com.moonsworth.lunar.ichor.util.Annotation2
   public static boolean hasModule(String var0) {
      return !field1 && field2.hasModule(var0);
   }

   public static boolean method2() {
      return Bridge.method9() != null;
   }

   public static @UnknownNullability Bridge5_12 method3() {
      Bridge5_12 var0 = Bridge.method9();
      if (var0 == null) {
         if (field1) {
            return null;
         } else {
            throw new IllegalStateException("Minecraft client is not available (To check use Ref.isMcLoaded())");
         }
      } else {
         return var0;
      }
   }

   public static Client method4() {
      return Client.method109();
   }

   public static Optional<EntityRenderer4> method5() {
      return Optional.ofNullable(method4().method35());
   }

   public static Optional<WebSocketClientIterator> method6() {
      return Optional.ofNullable(method4().method36());
   }

   public static Bridge5Extension_5 method7() {
      return method3().bridge$getPlayer();
   }

   public static Itemcounter6Extension method8() {
      return field1 ? null : method3().bridge$getWorld();
   }

   public static ClientPacketListenerBridge method9() {
      return method3().bridge$getClientPacketListener();
   }

   public static Bridge10_2 method10() {
      return method3().bridge$getFontRenderer();
   }

   @Nullable
   public static Class<?> method11() {
      return method3().bridge$getCurrentScreen() == null
         ? null
         : (
            method3().bridge$getCurrentScreen() instanceof Bridge5Extension62 var1
               ? (var1.method2() instanceof MainMenuButton var0 ? var0.method17().getClass() : var1.method2().getClass())
               : method3().bridge$getCurrentScreen().getClass()
         );
   }

   public static DualMarkerScreenLegacy method12() {
      if (method3().bridge$getCurrentScreen() instanceof Bridge5Extension62 var0) {
         return var0.method2() instanceof DualMarkerScreenLegacy var3 ? var3 : null;
      } else {
         return null;
      }
   }

   public static Bridge2_43 method13() {
      return method3().bridge$getEntityRenderDispatcher();
   }

   public static long method14() {
      Rewind var0 = method4().method40().method85();
      return var0.method19() ? method3().bridge$getSystemTime() : System.nanoTime() / 1000000L;
   }

   public static long method15() {
      Rewind var0 = method4().method40().method85();
      return var0.method19() ? method3().bridge$getSystemTime() * 1000000L : System.nanoTime();
   }

   public static void method16(String var0, boolean var1) {
      if (method7() != null) {
         System.out.println(var0);
         if (var1) {
            method7()
               .method1(
                  Component.text(AdventureChatFormatting.GRAY + "[" + AdventureChatFormatting.AQUA + "LC" + AdventureChatFormatting.GRAY + "] ").append(Component.text(var0))
               );
         } else {
            method7().bridge$addChatMessage(Bridge.method8().method13(var0));
         }
      }
   }

   public static void method17(String var0) {
      method16(var0, true);
   }

   public static boolean method18() {
      return method3().bridge$getCurrentServerData() != null && "lunar".equals(method3().bridge$getCurrentServerData().getLunarServer());
   }

   private static void method19(ThreadModuleDump63.Data var0, Object var1) {
      KeyBindingClashEntry var2 = method24(var1);
      if (var2 != null) {
         field7.put(var2.id(), method25(var1));
         if (field4.containsKey(var1)) {
            ThreadModuleDump63.Data var3 = field4.get(var1);
            if (var3.equals(var0)) {
               return;
            }

            Set var4 = field3.get(var3);
            if (var4 != null && var4.removeIf(var1x -> var1x.id().equals(var2.id()))) {
               method20(var4);
            }
         }

         Set var5;
         if (!var0.key().equals(KeyCode.KEY_NONE.name()) && !method23(var1)) {
            field3.putIfAbsent(var0, new HashSet<>());
            var5 = field3.get(var0);
            var5.add(var2);
         } else {
            var5 = Collections.emptySet();
         }

         field4.put(var1, var0);
         if (var5.isEmpty()) {
            method21(var1, var5);
         } else {
            method20(var5);
         }
      }
   }

   private static void method20(Set<KeyBindingClashEntry> var0) {
      for (KeyBindingClashEntry var2 : var0) {
         method21(var2.method2(), var0);
      }
   }

   private static void method21(Object var0, Set<KeyBindingClashEntry> var1) {
      if (var0 instanceof AbstractKeybindOption var2) {
         if (var2.method6(var1)) {
            field5.add(var2);
         }
      } else if (var0 instanceof MixinHelper_15 var3 && !SimpleKeybindOption.keybindRegistry.containsValue(var3)) {
         var3.bridge$setClashesWith(var1);
      }
   }

   private static void method22() {
      if (--field6 <= 0 && !field5.isEmpty()) {
         ArrayList var0 = new ArrayList<>(field5);
         field5.clear();

         for (AbstractKeybindOption var2 : var0) {
            var2.method10();
         }
      }
   }

   private static boolean method23(Object var0) {
      if (var0 instanceof ModifierKeybindOption var1) {
         return !var1.method21();
      } else if (!(var0 instanceof MixinHelper_15 var2)) {
         return var0 instanceof SimpleKeybindOption var3 ? var3.method18() : false;
      } else {
         return SimpleKeybindOption.keybindRegistry.containsValue(var2)
            || method4() != null && method4().method40() != null && method4().method40().method10().contains(var2.bridge$getCategory());
      }
   }

   private static KeyBindingClashEntry method24(Object var0) {
      if (var0 instanceof AbstractKeybindOption var1) {
         return new KeyBindingClashEntry(var1.getId(), KeyBindingOrigin.LUNAR, var0);
      } else {
         return var0 instanceof MixinHelper_15 var2 ? new KeyBindingClashEntry(var2.bridge$getUntranslatedKeyDescription(), KeyBindingOrigin.MINECRAFT, var0) : null;
      }
   }

   private static String method25(Object var0) {
      if (var0 instanceof AbstractKeybindOption var1) {
         return var1.getName();
      } else {
         return var0 instanceof MixinHelper_15 var2 ? var2.bridge$getKeyDescription() : null;
      }
   }

   public static String method26(Collection<KeyBindingClashEntry> var0) {
      return method27(var0, null);
   }

   public static String method27(Collection<KeyBindingClashEntry> var0, String var1) {
      if (var0.isEmpty()) {
         return "";
      }

      StringBuilder var2 = new StringBuilder();
      int var3 = 0;

      for (KeyBindingClashEntry var5 : var0) {
         if (var5.id().equals(var1)) {
            var3++;
         } else {
            String var6 = var5.id();
            var2.append(field7.getOrDefault(var6, var6));
            if (var3 != var0.size() - 1) {
               var2.append(", ");
            }

            var3++;
         }
      }

      return var2.toString();
   }

   public static void method28(Object var0) {
      if (method2() && method3().bridge$getGameSettings() != null) {
         field6++;

         try {
            method29(var0);
         } finally {
            method22();
         }
      }
   }

   private static void method29(@Nullable Object var0) {
      if (!field3.isEmpty() && var0 != null) {
         if (var0 instanceof ModifierKeybindOption var6) {
            if (!method30(var6)) {
               method19(new ThreadModuleDump63.Data(var6.get().method8().name(), var6.get().method6(), var6.get().method5(), var6.get().method7()), var0);
            }
         } else if (var0 instanceof SimpleKeybindOption var10) {
            if (!method30(var10)) {
               method19(new ThreadModuleDump63.Data(var10.get().name()), var0);
            }
         } else if (var0 instanceof MixinHelper_15 var14) {
            method19(new ThreadModuleDump63.Data(var14.bridge$getKey().name()), var0);
         }
      } else {
         ArrayList var1 = new ArrayList<>(field4.keySet());

         for (ModifierKeybindOption var3 : ModifierKeybindOption.field15) {
            if (var3.method21() && !method30(var3)) {
               method19(new ThreadModuleDump63.Data(var3.get().method8().name(), var3.get().method6(), var3.get().method5(), var3.get().method7()), var3);
               var1.remove(var3);
            }
         }

         for (SimpleKeybindOption var11 : SimpleKeybindOption.keybindRegistry.keySet()) {
            if (!method30(var11)) {
               method19(new ThreadModuleDump63.Data(var11.get().name()), var11);
               var1.remove(var11);
            }
         }

         for (MixinHelper_15 var5 : method3().bridge$getGameSettings().bridge$getKeyBindings()) {
            method19(new ThreadModuleDump63.Data(var5.bridge$getKey().name()), var5);
            var1.remove(var5);
         }

         for (Object var13 : var1) {
            method19(new ThreadModuleDump63.Data(KeyCode.KEY_NONE.name()), var13);
         }

         Bridge.method8().method67();
      }
   }

   private static boolean method30(AbstractKeybindOption<?> var0) {
      if (var0.isHidden()) {
         return true;
      }

      OptionFeatureLink var1 = (OptionFeatureLink)var0.method7(OptionTraits.field8);
      return var1 != null && !var1.<Framework7Extension>getFeature().isEnabled();
   }

   public static Bridge7_8 method31(Bridge5Extension6 var0) {
      if (var0 instanceof Bridge5Extension62 var1) {
         if (var1.method2() instanceof MainMenuButton var2) {
            if (var2.method17() instanceof Bridge7_8 var6) {
               return var6;
            }

            if (var2.method17() instanceof Bridge5Extension62 var4) {
               return method31(var4);
            }
         }

         return var1.method2();
      } else {
         return null;
      }
   }

   public static boolean method32() {
      Client var0 = method4();
      if (var0 == null) {
         return false;
      }

      ModsSettings var1 = var0.method40();
      return var1 != null && var1.method84() != null ? var1.method84().method22() : false;
   }

   public static boolean method33() {
      return method34(Gui2Extension.ALL);
   }

   public static boolean method34(Gui2Extension var0) {
      if (com.moonsworth.lunar.client.framework.build.LunarBuildData.field4) {
         return false;
      }

      Client var1 = method4();
      if (var1 == null) {
         return false;
      }

      ModsSettings var2 = var1.method40();
      return var2 == null ? false : var2.method79().method2(var0);
   }

   public static boolean method35(Bridge5_11 var0) {
      return var0.bridge$getGameProfile().getId().equals(method3().bridge$getSession().bridge$getProfile().getId());
   }

   public static ResourceLocationBridge method36(String var0) {
      return ResourceLocationBridge.create(var0);
   }

   public static ResourceLocationBridge method37(String var0, String var1) {
      return ResourceLocationBridge.create(var0, var1);
   }

   public static InputStream method38(ResourceLocationBridge var0) {
      Bridge11_2 var1 = method3().bridge$getResourceManager();
      IResourceBridge var2 = var1.bridge$getResource(var0);
      if (var2 != null) {
         return var2.bridge$getInputStream();
      } else {
         throw new RuntimeException("Unable to find resource: " + var0);
      }
   }

   public static InputStream method39(String var0) {
      return method38(ResourceLocationBridge.create(var0));
   }

   public static JsonElement method40(ResourceLocationBridge var0) {
      Bridge11_2 var1 = method3().bridge$getResourceManager();
      IResourceBridge var2 = var1.bridge$getResource(var0);
      if (var2 == null) {
         throw new RuntimeException("Unable to find json file: " + var0);
      }

      try {
         return (JsonElement)ThreadModuleDump48.field22.fromJson(IOUtils.toString(var2.bridge$getInputStream()), JsonElement.class);
      } catch (IOException var4) {
         var4.printStackTrace();
         throw new RuntimeException(var4);
      }
   }

   public static boolean method41() {
      return DriverViewportLegacy.method50().method64() == DriverOverlayRegistryLegacy.field2;
   }

   private static boolean method42() {
      for (StackTraceElement var3 : Thread.currentThread().getStackTrace()) {
         if (var3.getClassName().startsWith("org.junit.")) {
            return true;
         }
      }

      return false;
   }

   public static boolean method43() {
      Bridge7_2 var0 = Bridge.method42().method85();
      return var0.field2.equals("OpenGL");
   }

   @Generated
   public static Map<String, String> method44() {
      return field7;
   }

   class Data {
      private final String field1;
      private final boolean field2;
      private final boolean field3;
      private final boolean field4;

      public Data(String var1) {
         this(var1, false, false, false);
      }

      Data(String var1, boolean var2, boolean var3, boolean var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }

      public String key() {
         return this.field1;
      }

      public boolean method1() {
         return this.field2;
      }

      public boolean method2() {
         return this.field3;
      }

      public boolean method3() {
         return this.field4;
      }
   }
}
