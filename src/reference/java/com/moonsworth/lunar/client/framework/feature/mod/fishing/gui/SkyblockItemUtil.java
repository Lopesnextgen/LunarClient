package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.PropertyBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.ChatComponentStyleBridge;
import com.moonsworth.lunar.bridge.NBTTagListBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.PropertyMapBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.bridge.DataComponentTypes;
import com.moonsworth.lunar.bridge.PropertyMapComponent;
import com.moonsworth.lunar.bridge.LoreComponent;
import com.moonsworth.lunar.bridge.CompoundTagComponent;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.GuiType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.LegacyItemRegistry;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.ichor.VersionGate;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SkyblockItemUtil {
   private static final Pattern field1 = Pattern.compile("^(?:(?:BUY|SELL) )?(?<shard>.+) Shard$");

   @Nullable
   public static CompoundTagBridge method1(@Nullable ItemStackBridge bridgeextension_40) {
      if (bridgeextension_40 != null && !bridgeextension_40.bridge$isEmpty()) {
         CompoundTagComponent mixinhelper_101 = (CompoundTagComponent)bridgeextension_40.bridge$getDataComponent(DataComponentTypes.field1);
         return mixinhelper_101 == null ? null : mixinhelper_101.bridge$getData();
      } else {
         return null;
      }
   }

   @NotNull
   public static String method2(@Nullable ItemStackBridge bridgeextension_40) {
      CompoundTagBridge bridge_571 = method1(bridgeextension_40);
      return bridge_571 == null ? "" : bridge_571.bridge$getString("id");
   }

   @NotNull
   public static String method3(@Nullable ItemStackBridge bridgeextension_40) {
      if (bridgeextension_40 == null) {
         return "";
      }

      String text1 = method2(bridgeextension_40);
      if (text1.isEmpty() || text1.equals("ATTRIBUTE_SHARD")) {
         Matcher matcher2 = field1.matcher(bridgeextension_40.bridge$getRawDisplayName());
         if (matcher2.matches()) {
            return "SHARD_" + matcher2.group("shard").replaceAll(" ", "_").toUpperCase(Locale.ROOT);
         }
      }

      CompoundTagBridge bridge_5711 = method1(bridgeextension_40);
      if (bridge_5711 == null) {
         return text1;
      }

      return switch (text1) {
         case "PET" -> {
            String text25;
            try {
               JsonObject json30 = (JsonObject)LunarConstants.field22.fromJson(bridge_5711.bridge$getString("petInfo"), JsonObject.class);
               String text32 = json30.get("type").getAsString();
               String text34 = json30.get("tier").getAsString();
               text25 = text32 + ";" + text34;
            } catch (Exception exception10) {
               text25 = text1;
               yield yield25;
            }

            yield yield25;
         }
         case "ENCHANTED_BOOK" -> {
            CompoundTagBridge bridge_5729 = bridge_5711.bridge$getCompoundTag("enchantments");
            if (bridge_5729 == null) {
               String text21 = text1;
               yield yield21;
            } else {
               Set set31 = bridge_5729.bridge$getAllKeys();
               if (set31.size() != 1) {
                  String text22 = text1;
                  yield yield22;
               } else {
                  String text33 = (String)set31.iterator().next();
                  int number35 = bridge_5729.bridge$getInteger(text33);
                  String text23 = "ENCHANTMENT_" + text33.toUpperCase() + "_" + number35;
                  yield yield23;
               }
            }
         }
         case "RUNE", "UNIQUE_RUNE" -> {
            CompoundTagBridge bridge_5728 = bridge_5711.bridge$getCompoundTag("runes");
            if (bridge_5728 == null) {
               String text18 = text1;
               yield yield18;
            } else {
               Set set7 = bridge_5728.bridge$getAllKeys();
               if (set7.size() != 1) {
                  String text19 = text1;
                  yield yield19;
               } else {
                  String text8 = (String)set7.iterator().next();
                  int number9 = bridge_5728.bridge$getInteger(text8);
                  String text20 = text1 + ";" + text8.toUpperCase() + ";" + number9;
                  yield yield20;
               }
            }
         }
         case "PARTY_HAT_CRAB", "PARTY_HAT_CRAB_ANIMATED" -> {
            String text27 = bridge_5711.bridge$getString("party_hat_color");
            if (text27.isEmpty()) {
               String text16 = text1;
               yield yield16;
            } else {
               String text17 = text1 + "_" + text27.toUpperCase();
               yield yield17;
            }
         }
         case "PARTY_HAT_SLOTH" -> {
            String text26 = bridge_5711.bridge$getString("party_hat_emoji");
            if (text26.isEmpty()) {
               String text14 = text1;
               yield yield14;
            } else {
               String text15 = text1 + "_" + text26.toUpperCase();
               yield yield15;
            }
         }
         case "ABICASE" -> {
            String text6 = bridge_5711.bridge$getString("model");
            if (text6.isEmpty()) {
               String text12 = text1;
               yield yield12;
            } else {
               String text13 = text1 + "_" + text6.toUpperCase();
               yield yield13;
            }
         }
         default -> {
            String text5 = text1;
            yield yield5;
         }
      };
   }

   public static Component deserialize(String text0) {
      try {
         JsonObject json1 = (JsonObject)LunarConstants.field22.fromJson(text0, JsonObject.class);
         return GsonComponentSerializer.gson().deserializeFromTree(json1);
      } catch (Exception exception2) {
         return Component.text(text0);
      }
   }

   public static SkyblockItemUtil.DyeColor method4(ItemStackBridge bridgeextension_40) {
      if (bridgeextension_40 != null && !bridgeextension_40.bridge$isEmpty()) {
         List list1 = Bridge.method28().method94();
         return method9(bridgeextension_40, list1);
      } else {
         return null;
      }
   }

   public static SkyblockItemUtil.DyeColor method5(ItemStackBridge bridgeextension_40) {
      if (bridgeextension_40 != null && !bridgeextension_40.bridge$isEmpty()) {
         List list1 = Bridge.method28().method93();
         return method9(bridgeextension_40, list1);
      } else {
         return null;
      }
   }

   public static SkyblockItemUtil.DyeColor method6(ItemStackBridge bridgeextension_40) {
      if (bridgeextension_40 != null && !bridgeextension_40.bridge$isEmpty()) {
         List list1 = Bridge.method28().method97();
         return method9(bridgeextension_40, list1);
      } else {
         return null;
      }
   }

   public static SkyblockItemUtil.DyeColor method7(ItemStackBridge bridgeextension_40) {
      if (bridgeextension_40 != null && !bridgeextension_40.bridge$isEmpty()) {
         List list1 = Bridge.method28().method96();
         return method9(bridgeextension_40, list1);
      } else {
         return null;
      }
   }

   public static SkyblockItemUtil.DyeColor method8(ItemStackBridge bridgeextension_40) {
      if (bridgeextension_40 != null && !bridgeextension_40.bridge$isEmpty()) {
         List list1 = Bridge.method28().method95();
         return method9(bridgeextension_40, list1);
      } else {
         return null;
      }
   }

   @Nullable
   private static SkyblockItemUtil.DyeColor method9(ItemStackBridge bridgeextension_40, List<ItemBridge> list1) {
      for (int index2 = 0; index2 < list1.size(); index2++) {
         if (bridgeextension_40.bridge$getItem() == list1.get(index2)) {
            return SkyblockItemUtil.DyeColor.fromIndex(index2);
         }
      }

      return null;
   }

   public static SkyblockItemUtil.DyeColor method10(Bridge3_23 bridge3_230, @Nullable Itemcounter6 itemcounter61, @Nullable Vec3iBridge horsestats202) {
      if (bridge3_230 == null) {
         return null;
      }

      List list3 = Bridge.method34().method8();

      for (int index4 = 0; index4 < list3.size(); index4++) {
         if (bridge3_230 == list3.get(index4)) {
            return SkyblockItemUtil.DyeColor.fromIndex(index4);
         }
      }

      return null;
   }

   public static Optional<String> method11(ItemStackBridge bridgeextension_40) {
      PropertyMapComponent mixinhelper61 = (PropertyMapComponent)bridgeextension_40.bridge$getDataComponent(DataComponentTypes.field48);
      if (mixinhelper61 == null) {
         return Optional.empty();
      }

      PropertyMapBridge bridge_322 = mixinhelper61.bridge$getPropertyMap();
      if (bridge_322 == null) {
         return Optional.empty();
      }

      List list3 = bridge_322.bridge$get("textures");
      return list3 != null && !list3.isEmpty() ? Optional.ofNullable(((PropertyBridge)list3.get(0)).bridge$getValue()) : Optional.empty();
   }

   public static ItemStackBridge method12(String text0, String text1, String text2) {
      ItemStackBridge bridgeextension_43 = Bridge.method8().method38(Bridge.method28().method5());
      bridgeextension_43.bridge$setProfile(UUID.fromString(text0), text1, text2);
      return bridgeextension_43;
   }

   public static ItemStackBridge method13(String text0) {
      byte[] items1 = ("{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/" + text0 + "\"}}}").getBytes(StandardCharsets.UTF_8);
      return method12(UUID.nameUUIDFromBytes(items1).toString(), Base64.getEncoder().encodeToString(items1), null);
   }

   public static List<String> method14(ItemStackBridge bridgeextension_40) {
      ArrayList list1 = new ArrayList();
      if (bridgeextension_40 != null && !bridgeextension_40.bridge$isEmpty()) {
         LoreComponent mixinhelper7_52 = (LoreComponent)bridgeextension_40.bridge$getDataComponent(DataComponentTypes.field8);
         if (mixinhelper7_52 == null) {
            return list1;
         }

         for (ChatComponentStyleBridge bridge3_254 : mixinhelper7_52.bridge$getLines()) {
            Component component5 = bridge3_254.moonBridge$asAdventureComponent();
            list1.add(TextBridge.asLegacyString(component5));
         }

         return list1;
      } else {
         return list1;
      }
   }

   public static List<String> method15(ItemStackBridge bridgeextension_40) {
      ArrayList list1 = new ArrayList();
      if (bridgeextension_40 != null && !bridgeextension_40.bridge$isEmpty()) {
         LoreComponent mixinhelper7_52 = (LoreComponent)bridgeextension_40.bridge$getDataComponent(DataComponentTypes.field8);

         for (ChatComponentStyleBridge bridge3_254 : mixinhelper7_52.bridge$getLines()) {
            Component component5 = bridge3_254.moonBridge$asAdventureComponent();
            list1.add(TextBridge.getTextContent(component5));
         }

         return list1;
      } else {
         return list1;
      }
   }

   public static List<Component> method16(ItemStackBridge bridgeextension_40) {
      ArrayList list1 = new ArrayList();
      if (bridgeextension_40 != null && !bridgeextension_40.bridge$isEmpty()) {
         LoreComponent mixinhelper7_52 = (LoreComponent)bridgeextension_40.bridge$getDataComponent(DataComponentTypes.field8);
         if (mixinhelper7_52 == null) {
            return list1;
         }

         for (ChatComponentStyleBridge bridge3_254 : mixinhelper7_52.bridge$getLines()) {
            list1.add(bridge3_254.moonBridge$asAdventureComponent());
         }

         return list1;
      } else {
         return list1;
      }
   }

   public static boolean method17(ItemStackBridge bridgeextension_40, ItemStackBridge bridgeextension_41) {
      if (bridgeextension_40 != null && bridgeextension_41 != null) {
         CompoundTagBridge bridge_572 = method1(bridgeextension_40);
         CompoundTagBridge bridge_573 = method1(bridgeextension_41);
         if (bridge_572 != null && bridge_573 != null) {
            String text4 = bridge_572.bridge$getString("uuid");
            String text5 = bridge_573.bridge$getString("uuid");
            return text4 != null && text4.equals(text5);
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static String method18(ItemStackBridge bridgeextension_40) {
      CompoundTagBridge bridge_571 = method1(bridgeextension_40);
      return bridge_571 == null ? null : bridge_571.bridge$getString("uuid");
   }

   public static ItemStackBridge method19(SkyblockItemUtil.DyeColor type20) {
      List list1 = Bridge.method28().method95();
      int index2 = type20.ordinal();
      if (index2 >= list1.size()) {
         return null;
      }

      ItemBridge bridge6_43 = (ItemBridge)list1.get(index2);
      return Bridge.method8().method38(bridge6_43);
   }

   public static boolean method20(CompoundTagComponent mixinhelper_100) {
      if (mixinhelper_100 == null) {
         return false;
      }

      CompoundTagBridge bridge_571 = mixinhelper_100.bridge$getData();
      return bridge_571 == null ? false : bridge_571.bridge$getInteger("ethermerge") == 1;
   }

   public static boolean method21(String text0) {
      return text0.equals("ASPECT_OF_THE_END")
         || text0.equals("ASPECT_OF_THE_VOID")
         || text0.equals("NECRONS_BLADE")
         || text0.equals("HYPERION")
         || text0.equals("ASTRAEA")
         || text0.equals("VALKYRIE")
         || text0.equals("SCYLLA");
   }

   public static NBTTagListBridge method22(String text0) {
      byte[] items1 = Base64.getDecoder().decode(text0);
      return method24(items1);
   }

   public static NBTTagListBridge method23(byte[] items0) {
      byte[] items1 = Base64.getDecoder().decode(items0);
      return method24(items1);
   }

   public static NBTTagListBridge method24(byte[] items0) {
      ByteArrayInputStream bytearrayinputstream1 = new ByteArrayInputStream(items0);
      CompoundTagBridge bridge_572 = Bridge.method51().method1(bytearrayinputstream1);
      return bridge_572.bridge$getList("i", 10);
   }

   public static TextComponent method25(CompoundTagBridge bridge_570) {
      CompoundTagBridge bridge_571 = bridge_570.bridge$getCompoundTag("tag").bridge$getCompoundTag("display");
      if (bridge_571.bridge$isEmpty()) {
         return Component.text("None", NamedTextColor.RED);
      }

      TextComponent text2 = TextBridge.asAdventure(bridge_571.bridge$getString("Name"));
      TextComponent text3 = text2;
      NBTTagListBridge bridge3_64 = bridge_571.bridge$getList("Lore", 8);

      for (int index5 = 0; index5 < bridge3_64.bridge$size(); index5++) {
         String text6 = bridge3_64.bridge$getString(index5);
         text3 = (TextComponent)((TextComponent)text3.appendNewline()).append(TextBridge.asAdventure(text6));
      }

      return (TextComponent)text2.hoverEvent(HoverEvent.showText(text3));
   }

   public static CompoundTagBridge method26(@Nullable String text0) {
      if (text0 == null) {
         return null;
      }

      try {
         return Bridge.method51().method1(new ByteArrayInputStream(Base64.getDecoder().decode(text0)));
      } catch (IOException exception2) {
         CrashReporter.method5(exception2, "Failed to parse inventory");
         return null;
      }
   }

   @VersionGate(min = 33)
   @Nullable
   public static ItemStackBridge method27(CompoundTagBridge bridge_570) {
      try {
         short number1 = bridge_570.bridge$getShort("id");
         if (number1 == 141) {
            number1 = 391;
         }

         short number2 = bridge_570.bridge$contains("Damage", 2) ? bridge_570.bridge$getShort("Damage") : 0;
         byte number3 = bridge_570.bridge$getByte("Count");
         CompoundTagBridge bridge_574 = bridge_570.bridge$getCompoundTag("tag");
         CompoundTagBridge bridge_575 = bridge_574.bridge$getCompoundTag("display");
         NBTTagListBridge bridge3_66 = bridge_575.bridge$getList("Lore", 8);
         ArrayList list7 = new ArrayList();

         for (int index8 = 0; index8 < bridge3_66.bridge$size(); index8++) {
            TextComponent text9 = TextBridge.asAdventure(bridge3_66.bridge$getString(index8));
            list7.add(text9.style(text9.style().decoration(TextDecoration.ITALIC, false)));
         }

         ItemBridge bridge6_420 = Bridge.method28().method22("minecraft:" + LegacyItemRegistry.idToNewName(number1, number2));
         ItemStackBridge bridgeextension_421 = Bridge.method8().method38(bridge6_420);
         bridgeextension_421.bridge$setItemDamage(number2);
         TextComponent text10 = TextBridge.asAdventure(bridge_575.bridge$getString("Name"));
         bridgeextension_421.bridge$setStackDisplayName(text10.style(text10.style().decoration(TextDecoration.ITALIC, false)));
         bridgeextension_421.bridge$setLore(list7);
         bridgeextension_421.bridge$setStackSize(number3);
         if (bridge_574.bridge$contains("ench", 9)) {
            bridgeextension_421.bridge$setEnchantments(Map.of(Bridge.method32().method1(), 1));
         }

         if (bridge_574.bridge$contains("SkullOwner", 10)) {
            CompoundTagBridge bridge_5711 = bridge_574.bridge$getCompoundTag("SkullOwner");
            String text12 = bridge_5711.bridge$getString("Id");
            CompoundTagBridge bridge_5713 = bridge_5711.bridge$getCompoundTag("Properties");
            NBTTagListBridge bridge3_614 = bridge_5713.bridge$getList("textures", 10);
            CompoundTagBridge bridge_5715 = bridge3_614.bridge$getCompoundAt(0);
            String text16 = bridge_5715.bridge$getString("Value");
            String text17 = bridge_5715.bridge$getString("Signature");
            bridgeextension_421.bridge$setProfile(UUID.fromString(text12), text16, text17);
         }

         if (bridge_575.bridge$contains("color", 3)) {
            bridgeextension_421.bridge$setArmorColor(bridge_575.bridge$getInteger("color"));
         }

         if (bridge_574.bridge$contains("CustomPotionEffects", 9)) {
            ArrayList list22 = new ArrayList();
            NBTTagListBridge bridge3_624 = bridge_574.bridge$getList("CustomPotionEffects", 10);

            for (int index25 = 0; index25 < bridge3_624.bridge$size(); index25++) {
               CompoundTagBridge bridge_5726 = bridge3_624.bridge$getCompoundAt(index25);
               byte number27 = bridge_5726.bridge$getByte("Id");
               byte number28 = bridge_5726.bridge$getByte("Amplifier");
               int number29 = bridge_5726.bridge$getInteger("Duration");
               GuiType guitype18 = GuiType.getById(number27);
               if (guitype18 != null && guitype18.getPotion() != null) {
                  list22.add(Bridge.method36().method10(guitype18.getPotion().bridge$getID(), guitype18.getId(), number28, number29));
               }
            }

            bridgeextension_421.bridge$setPotionEffects(list22);
         }

         bridgeextension_421.bridge$setSkyBlockExtraAttributes(bridge_574.bridge$getCompoundTag("ExtraAttributes"));
         bridgeextension_421.bridge$sbHideTooltipComponents();
         CompoundTagBridge bridge_5723 = bridge_570.bridge$getCompoundTag("components");
         if (bridge_5723.bridge$contains("minecraft:item_model", 8)) {
            bridgeextension_421.bridge$setItemModel(ResourceLocationBridge.create(bridge_5723.bridge$getString("minecraft:item_model")));
         }

         return bridgeextension_421;
      } catch (Exception exception19) {
         return null;
      }
   }

   @VersionGate(min = 33)
   @Nullable
   public static List<ItemStackBridge> method28(@Nullable String text0) {
      CompoundTagBridge bridge_571 = method26(text0);
      if (bridge_571 == null) {
         return null;
      }

      ArrayList list2 = new ArrayList();
      NBTTagListBridge bridge3_63 = bridge_571.bridge$getList("i", 10);

      for (int index4 = 0; index4 < bridge3_63.bridge$size(); index4++) {
         CompoundTagBridge bridge_575 = bridge3_63.bridge$getCompoundAt(index4);
         ItemStackBridge bridgeextension_46 = method27(bridge_575);
         if (bridgeextension_46 != null) {
            list2.add(bridgeextension_46);
         } else {
            list2.add(Bridge.method8().method41());
         }
      }

      return list2;
   }

   @NotNull
   public static Object2IntOpenHashMap<String> method29(@Nullable ItemStackBridge bridgeextension_40) {
      Object2IntOpenHashMap object2intopenhashmap1 = new Object2IntOpenHashMap();
      CompoundTagBridge bridge_572 = method1(bridgeextension_40);
      if (bridge_572 == null) {
         return object2intopenhashmap1;
      }

      CompoundTagBridge bridge_573 = bridge_572.bridge$getCompoundTag("enchantments");
      if (bridge_573 == null) {
         return object2intopenhashmap1;
      }

      for (String text5 : bridge_573.bridge$getAllKeys()) {
         object2intopenhashmap1.put(text5, bridge_573.bridge$getInteger(text5));
      }

      return object2intopenhashmap1;
   }

   @Generated
   private SkyblockItemUtil() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public enum DyeColor {
      WHITE,
      ORANGE,
      MAGENTA,
      LIGHT_BLUE,
      YELLOW,
      LIME,
      PINK,
      GRAY,
      LIGHT_GRAY,
      CYAN,
      PURPLE,
      BLUE,
      BROWN,
      GREEN,
      RED,
      BLACK;

      DyeColor() {
      }

      public static SkyblockItemUtil.DyeColor fromIndex(int index0) {
         return values()[index0];
      }
   }
}
