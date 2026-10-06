package com.moonsworth.lunar.client.framework;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.ResourceBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.bridge.Bridge7_2;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.NetHandlerPlayClientBridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.KeyBindingBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.minecraft.KeyBindingEntry;
import com.moonsworth.lunar.bridge.minecraft.KeyBindingSource;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.render.shader.ShaderStateHelper;
import com.moonsworth.lunar.client.framework.feature.debug.DebugType;
import com.moonsworth.lunar.client.config.option.KeyBind;
import com.moonsworth.lunar.client.config.option.AbstractKeybindOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.OptionFeatureLink;
import com.moonsworth.lunar.client.driver.DriverOverlayRegistry;
import com.moonsworth.lunar.client.driver.core.DualMarkerScreenLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.network.websocket.AssetServerClient;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindMod;
import com.moonsworth.lunar.client.network.ipc.WebSocketClientIterator;
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

public class Ref {
   public static final boolean field1 = method42();
   @NotNull
   private static final IchorPipeline field2 = field1 ? null : (IchorPipeline)IchorAPI.getPipeline(Ref.class.getClassLoader()).orElseThrow();
   @com.moonsworth.lunar.ichor.util.KeepName
   public static final int MC_VERSION = field1 ? 0 : Config.method36(field2.method34().method6()).getOrdinal();
   private static final Map<Ref.Data, Set<KeyBindingEntry>> field3 = new HashMap<>();
   private static final Map<Object, Ref.Data> field4 = new HashMap<>();
   private static final Set<AbstractKeybindOption<?>> field5 = new LinkedHashSet<>();
   private static int field6;
   private static final Map<String, String> field7 = new HashMap<>();

   public Ref() {
   }

   public static boolean method1() {
      return MC_VERSION >= 39 && !ShaderStateHelper.method8();
   }

   @com.moonsworth.lunar.ichor.util.KeepName
   public static boolean hasModule(String text0) {
      return !field1 && field2.hasModule(text0);
   }

   public static boolean method2() {
      return Bridge.method9() != null;
   }

   public static @UnknownNullability MinecraftBridge method3() {
      MinecraftBridge bridge5_120 = Bridge.method9();
      if (bridge5_120 == null) {
         if (field1) {
            return null;
         } else {
            throw new IllegalStateException("Minecraft client is not available (To check use Ref.isMcLoaded())");
         }
      } else {
         return bridge5_120;
      }
   }

   public static Client method4() {
      return Client.method109();
   }

   public static Optional<AssetServerClient> method5() {
      return Optional.ofNullable(method4().method35());
   }

   public static Optional<WebSocketClientIterator> method6() {
      return Optional.ofNullable(method4().method36());
   }

   public static Bridge5Extension_5 method7() {
      return method3().bridge$getPlayer();
   }

   public static WorldBridgeExtension method8() {
      return field1 ? null : method3().bridge$getWorld();
   }

   public static NetHandlerPlayClientBridge method9() {
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
            method3().bridge$getCurrentScreen() instanceof Bridge5Extension62 bridge5extension621
               ? (bridge5extension621.method2() instanceof MainMenuButton bridge7task0 ? bridge7task0.method17().getClass() : bridge5extension621.method2().getClass())
               : method3().bridge$getCurrentScreen().getClass()
         );
   }

   public static DualMarkerScreenLegacy method12() {
      if (method3().bridge$getCurrentScreen() instanceof Bridge5Extension62 bridge5extension620) {
         return bridge5extension620.method2() instanceof DualMarkerScreenLegacy bridge7handler3 ? bridge7handler3 : null;
      } else {
         return null;
      }
   }

   public static EntityRenderDispatcherBridge method13() {
      return method3().bridge$getEntityRenderDispatcher();
   }

   public static long method14() {
      RewindMod rewind0 = method4().method40().method85();
      return rewind0.method19() ? method3().bridge$getSystemTime() : System.nanoTime() / 1000000L;
   }

   public static long method15() {
      RewindMod rewind0 = method4().method40().method85();
      return rewind0.method19() ? method3().bridge$getSystemTime() * 1000000L : System.nanoTime();
   }

   public static void method16(String text0, boolean flag1) {
      if (method7() != null) {
         System.out.println(text0);
         if (flag1) {
            method7()
               .HRICOROOOCCOCOROCRHHCRRIRCOICO(
                  Component.text(ChatFormatting.GRAY + "[" + ChatFormatting.AQUA + "LC" + ChatFormatting.GRAY + "] ").append(Component.text(text0))
               );
         } else {
            method7().bridge$addChatMessage(Bridge.method8().method13(text0));
         }
      }
   }

   public static void method17(String text0) {
      method16(text0, true);
   }

   public static boolean method18() {
      return method3().bridge$getCurrentServerData() != null && "lunar".equals(method3().bridge$getCurrentServerData().getLunarServer());
   }

   private static void method19(Ref.Data data0, Object obj1) {
      KeyBindingEntry horsestats182 = method24(obj1);
      if (horsestats182 != null) {
         field7.put(horsestats182.id(), method25(obj1));
         if (field4.containsKey(obj1)) {
            Ref.Data data3 = field4.get(obj1);
            if (data3.equals(data0)) {
               return;
            }

            Set set4 = field3.get(data3);
            if (set4 != null && set4.removeIf(arg1x -> arg1x.id().equals(horsestats182.id()))) {
               method20(set4);
            }
         }

         Set set5;
         if (!data0.key().equals(KeyCode.KEY_NONE.name()) && !method23(obj1)) {
            field3.putIfAbsent(data0, new HashSet<>());
            set5 = field3.get(data0);
            set5.add(horsestats182);
         } else {
            set5 = Collections.emptySet();
         }

         field4.put(obj1, data0);
         if (set5.isEmpty()) {
            method21(obj1, set5);
         } else {
            method20(set5);
         }
      }
   }

   private static void method20(Set<KeyBindingEntry> set0) {
      for (KeyBindingEntry horsestats182 : set0) {
         method21(horsestats182.method2(), set0);
      }
   }

   private static void method21(Object obj0, Set<KeyBindingEntry> set1) {
      if (obj0 instanceof AbstractKeybindOption lightingextension49132) {
         if (lightingextension49132.method6(set1)) {
            field5.add(lightingextension49132);
         }
      } else if (obj0 instanceof KeyBindingBridge mixinhelper_153 && !SimpleKeybindOption.keybindRegistry.containsValue(mixinhelper_153)) {
         mixinhelper_153.bridge$setClashesWith(set1);
      }
   }

   private static void method22() {
      if (--field6 <= 0 && !field5.isEmpty()) {
         ArrayList list0 = new ArrayList<>(field5);
         field5.clear();

         for (AbstractKeybindOption lightingextension49132 : list0) {
            lightingextension49132.method10();
         }
      }
   }

   private static boolean method23(Object obj0) {
      if (obj0 instanceof ModifierKeybindOption lightingextension491331) {
         return !lightingextension491331.method21();
      } else if (!(obj0 instanceof KeyBindingBridge mixinhelper_152)) {
         return obj0 instanceof SimpleKeybindOption lightingextension491323 ? lightingextension491323.method18() : false;
      } else {
         return SimpleKeybindOption.keybindRegistry.containsValue(mixinhelper_152)
            || method4() != null && method4().method40() != null && method4().method40().method10().contains(mixinhelper_152.bridge$getCategory());
      }
   }

   private static KeyBindingEntry method24(Object obj0) {
      if (obj0 instanceof AbstractKeybindOption lightingextension49131) {
         return new KeyBindingEntry(lightingextension49131.getId(), KeyBindingSource.LUNAR, obj0);
      } else {
         return obj0 instanceof KeyBindingBridge mixinhelper_152 ? new KeyBindingEntry(mixinhelper_152.bridge$getUntranslatedKeyDescription(), KeyBindingSource.MINECRAFT, obj0) : null;
      }
   }

   private static String method25(Object obj0) {
      if (obj0 instanceof AbstractKeybindOption lightingextension49131) {
         return lightingextension49131.getName();
      } else {
         return obj0 instanceof KeyBindingBridge mixinhelper_152 ? mixinhelper_152.bridge$getKeyDescription() : null;
      }
   }

   public static String method26(Collection<KeyBindingEntry> list0) {
      return method27(list0, null);
   }

   public static String method27(Collection<KeyBindingEntry> list0, String text1) {
      if (list0.isEmpty()) {
         return "";
      }

      StringBuilder builder2 = new StringBuilder();
      int index3 = 0;

      for (KeyBindingEntry horsestats185 : list0) {
         if (horsestats185.id().equals(text1)) {
            index3++;
         } else {
            String text6 = horsestats185.id();
            builder2.append(field7.getOrDefault(text6, text6));
            if (index3 != list0.size() - 1) {
               builder2.append(", ");
            }

            index3++;
         }
      }

      return builder2.toString();
   }

   public static void method28(Object obj0) {
      if (method2() && method3().bridge$getGameSettings() != null) {
         field6++;

         try {
            method29(obj0);
         } finally {
            method22();
         }
      }
   }

   private static void method29(@Nullable Object obj0) {
      if (!field3.isEmpty() && obj0 != null) {
         if (obj0 instanceof ModifierKeybindOption lightingextension491336) {
            if (!method30(lightingextension491336)) {
               method19(
                  new Ref.Data(
                     ((KeyBind)lightingextension491336.get()).method8().name(),
                     ((KeyBind)lightingextension491336.get()).method6(),
                     ((KeyBind)lightingextension491336.get()).method5(),
                     ((KeyBind)lightingextension491336.get()).method7()
                  ),
                  obj0
               );
            }
         } else if (obj0 instanceof SimpleKeybindOption lightingextension4913210) {
            if (!method30(lightingextension4913210)) {
               method19(new Ref.Data(((KeyCode)lightingextension4913210.get()).name()), obj0);
            }
         } else if (obj0 instanceof KeyBindingBridge mixinhelper_1514) {
            method19(new Ref.Data(mixinhelper_1514.bridge$getKey().name()), obj0);
         }
      } else {
         ArrayList list1 = new ArrayList<>(field4.keySet());

         for (ModifierKeybindOption lightingextension491333 : ModifierKeybindOption.field15) {
            if (lightingextension491333.method21() && !method30(lightingextension491333)) {
               method19(
                  new Ref.Data(
                     ((KeyBind)lightingextension491333.get()).method8().name(),
                     ((KeyBind)lightingextension491333.get()).method6(),
                     ((KeyBind)lightingextension491333.get()).method5(),
                     ((KeyBind)lightingextension491333.get()).method7()
                  ),
                  lightingextension491333
               );
               list1.remove(lightingextension491333);
            }
         }

         for (SimpleKeybindOption lightingextension4913211 : SimpleKeybindOption.keybindRegistry.keySet()) {
            if (!method30(lightingextension4913211)) {
               method19(new Ref.Data(((KeyCode)lightingextension4913211.get()).name()), lightingextension4913211);
               list1.remove(lightingextension4913211);
            }
         }

         for (KeyBindingBridge mixinhelper_155 : method3().bridge$getGameSettings().bridge$getKeyBindings()) {
            method19(new Ref.Data(mixinhelper_155.bridge$getKey().name()), mixinhelper_155);
            list1.remove(mixinhelper_155);
         }

         for (Object obj13 : list1) {
            method19(new Ref.Data(KeyCode.KEY_NONE.name()), obj13);
         }

         Bridge.method8().method67();
      }
   }

   private static boolean method30(AbstractKeybindOption<?> lightingextension49130) {
      if (lightingextension49130.isHidden()) {
         return true;
      }

      OptionFeatureLink nameplate31 = (OptionFeatureLink)lightingextension49130.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field8);
      return nameplate31 != null && !nameplate31.getFeature().isEnabled();
   }

   public static Bridge7_8 method31(GuiScreenBridge bridge5extension60) {
      if (bridge5extension60 instanceof Bridge5Extension62 bridge5extension621) {
         if (bridge5extension621.method2() instanceof MainMenuButton bridge7task2) {
            if (bridge7task2.method17() instanceof Bridge7_8 bridge7_86) {
               return bridge7_86;
            }

            if (bridge7task2.method17() instanceof Bridge5Extension62 bridge5extension624) {
               return method31(bridge5extension624);
            }
         }

         return bridge5extension621.method2();
      } else {
         return null;
      }
   }

   public static boolean method32() {
      Client client0 = method4();
      if (client0 == null) {
         return false;
      }

      ModsSettings fogloader31 = client0.method40();
      return fogloader31 != null && fogloader31.method84() != null ? fogloader31.method84().method22() : false;
   }

   public static boolean method33() {
      return method34(DebugType.ALL);
   }

   public static boolean method34(DebugType gui2extension0) {
      if (com.moonsworth.lunar.client.framework.build.LunarBuildData.field4) {
         return false;
      }

      Client client1 = method4();
      if (client1 == null) {
         return false;
      }

      ModsSettings fogloader32 = client1.method40();
      return fogloader32 == null ? false : fogloader32.method79().method2(gui2extension0);
   }

   public static boolean method35(Bridge5_11 bridge5_110) {
      return bridge5_110.bridge$getGameProfile().getId().equals(method3().bridge$getSession().bridge$getProfile().getId());
   }

   public static ResourceLocationBridge method36(String text0) {
      return ResourceLocationBridge.create(text0);
   }

   public static ResourceLocationBridge method37(String text0, String text1) {
      return ResourceLocationBridge.create(text0, text1);
   }

   public static InputStream method38(ResourceLocationBridge horsestats140) {
      Bridge11_2 bridge11_21 = method3().bridge$getResourceManager();
      ResourceBridge bridge152 = bridge11_21.bridge$getResource(horsestats140);
      if (bridge152 != null) {
         return bridge152.bridge$getInputStream();
      } else {
         throw new RuntimeException("Unable to find resource: " + horsestats140);
      }
   }

   public static InputStream method39(String text0) {
      return method38(ResourceLocationBridge.create(text0));
   }

   public static JsonElement method40(ResourceLocationBridge horsestats140) {
      Bridge11_2 bridge11_21 = method3().bridge$getResourceManager();
      ResourceBridge bridge152 = bridge11_21.bridge$getResource(horsestats140);
      if (bridge152 == null) {
         throw new RuntimeException("Unable to find json file: " + horsestats140);
      }

      try {
         return (JsonElement)LunarConstants.field22.fromJson(IOUtils.toString(bridge152.bridge$getInputStream()), JsonElement.class);
      } catch (IOException exception4) {
         exception4.printStackTrace();
         throw new RuntimeException(exception4);
      }
   }

   public static boolean method41() {
      return DriverViewportLegacy.method50().method64() == DriverOverlayRegistry.field2;
   }

   private static boolean method42() {
      for (StackTraceElement stacktraceelement3 : Thread.currentThread().getStackTrace()) {
         if (stacktraceelement3.getClassName().startsWith("org.junit.")) {
            return true;
         }
      }

      return false;
   }

   public static boolean method43() {
      Bridge7_2 bridge7_20 = Bridge.method42().method85();
      return bridge7_20.field2.equals("OpenGL");
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

      public Data(String text1) {
         this(text1, false, false, false);
      }

      Data(String text1, boolean flag2, boolean flag3, boolean flag4) {
         this.field1 = text1;
         this.field2 = flag2;
         this.field3 = flag3;
         this.field4 = flag4;
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
