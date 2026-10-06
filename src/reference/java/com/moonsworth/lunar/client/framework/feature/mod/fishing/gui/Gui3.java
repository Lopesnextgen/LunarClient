package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_37;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge3_25;
import com.moonsworth.lunar.bridge.Bridge3_6;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.Bridge_32;
import com.moonsworth.lunar.bridge.Bridge_57;
import com.moonsworth.lunar.bridge.ItemDataComponentTypes;
import com.moonsworth.lunar.bridge.PropertyMapDataComponent;
import com.moonsworth.lunar.bridge.ItemLoreComponent;
import com.moonsworth.lunar.bridge.CompoundTagDataComponent;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.GuiType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.GuiType2;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.ichor.Annotation2;
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

public final class Gui3 {
   private static final Pattern field1 = Pattern.compile("^(?:(?:BUY|SELL) )?(?<shard>.+) Shard$");

   @Nullable
   public static Bridge_57 method1(@Nullable ItemStackBridge var0) {
      if (var0 != null && !var0.bridge$isEmpty()) {
         CompoundTagDataComponent var1 = (CompoundTagDataComponent)var0.bridge$getDataComponent(ItemDataComponentTypes.field1);
         return var1 == null ? null : var1.bridge$getData();
      } else {
         return null;
      }
   }

   @NotNull
   public static String method2(@Nullable ItemStackBridge var0) {
      Bridge_57 var1 = method1(var0);
      return var1 == null ? "" : var1.bridge$getString("id");
   }

   @NotNull
   public static String method3(@Nullable ItemStackBridge var0) {
      if (var0 == null) {
         return "";
      }

      String var1 = method2(var0);
      if (var1.isEmpty() || var1.equals("ATTRIBUTE_SHARD")) {
         Matcher var2 = field1.matcher(var0.bridge$getRawDisplayName());
         if (var2.matches()) {
            return "SHARD_" + var2.group("shard").replaceAll(" ", "_").toUpperCase(Locale.ROOT);
         }
      }

      Bridge_57 var11 = method1(var0);
      if (var11 == null) {
         return var1;
      }

      return switch (var1) {
         case "PET" -> {
            String var25;
            try {
               JsonObject var30 = (JsonObject)ThreadModuleDump48.field22.fromJson(var11.bridge$getString("petInfo"), JsonObject.class);
               String var32 = var30.get("type").getAsString();
               String var34 = var30.get("tier").getAsString();
               var25 = var32 + ";" + var34;
            } catch (Exception var10) {
               var25 = var1;
               yield var25;
            }

            yield var25;
         }
         case "ENCHANTED_BOOK" -> {
            Bridge_57 var29 = var11.bridge$getCompoundTag("enchantments");
            if (var29 == null) {
               String var21 = var1;
               yield var21;
            } else {
               Set var31 = var29.bridge$getAllKeys();
               if (var31.size() != 1) {
                  String var22 = var1;
                  yield var22;
               } else {
                  String var33 = (String)var31.iterator().next();
                  int var35 = var29.bridge$getInteger(var33);
                  String var23 = "ENCHANTMENT_" + var33.toUpperCase() + "_" + var35;
                  yield var23;
               }
            }
         }
         case "RUNE", "UNIQUE_RUNE" -> {
            Bridge_57 var28 = var11.bridge$getCompoundTag("runes");
            if (var28 == null) {
               String var18 = var1;
               yield var18;
            } else {
               Set var7 = var28.bridge$getAllKeys();
               if (var7.size() != 1) {
                  String var19 = var1;
                  yield var19;
               } else {
                  String var8 = (String)var7.iterator().next();
                  int var9 = var28.bridge$getInteger(var8);
                  String var20 = var1 + ";" + var8.toUpperCase() + ";" + var9;
                  yield var20;
               }
            }
         }
         case "PARTY_HAT_CRAB", "PARTY_HAT_CRAB_ANIMATED" -> {
            String var27 = var11.bridge$getString("party_hat_color");
            if (var27.isEmpty()) {
               String var16 = var1;
               yield var16;
            } else {
               String var17 = var1 + "_" + var27.toUpperCase();
               yield var17;
            }
         }
         case "PARTY_HAT_SLOTH" -> {
            String var26 = var11.bridge$getString("party_hat_emoji");
            if (var26.isEmpty()) {
               String var14 = var1;
               yield var14;
            } else {
               String var15 = var1 + "_" + var26.toUpperCase();
               yield var15;
            }
         }
         case "ABICASE" -> {
            String var6 = var11.bridge$getString("model");
            if (var6.isEmpty()) {
               String var12 = var1;
               yield var12;
            } else {
               String var13 = var1 + "_" + var6.toUpperCase();
               yield var13;
            }
         }
         default -> {
            String var5 = var1;
            yield var5;
         }
      };
   }

   public static Component deserialize(String var0) {
      try {
         JsonObject var1 = (JsonObject)ThreadModuleDump48.field22.fromJson(var0, JsonObject.class);
         return GsonComponentSerializer.gson().deserializeFromTree(var1);
      } catch (Exception var2) {
         return Component.text(var0);
      }
   }

   public static Gui3.Type2 method4(ItemStackBridge var0) {
      if (var0 != null && !var0.bridge$isEmpty()) {
         List var1 = Bridge.method28().method94();
         return method9(var0, var1);
      } else {
         return null;
      }
   }

   public static Gui3.Type2 method5(ItemStackBridge var0) {
      if (var0 != null && !var0.bridge$isEmpty()) {
         List var1 = Bridge.method28().method93();
         return method9(var0, var1);
      } else {
         return null;
      }
   }

   public static Gui3.Type2 method6(ItemStackBridge var0) {
      if (var0 != null && !var0.bridge$isEmpty()) {
         List var1 = Bridge.method28().method97();
         return method9(var0, var1);
      } else {
         return null;
      }
   }

   public static Gui3.Type2 method7(ItemStackBridge var0) {
      if (var0 != null && !var0.bridge$isEmpty()) {
         List var1 = Bridge.method28().method96();
         return method9(var0, var1);
      } else {
         return null;
      }
   }

   public static Gui3.Type2 method8(ItemStackBridge var0) {
      if (var0 != null && !var0.bridge$isEmpty()) {
         List var1 = Bridge.method28().method95();
         return method9(var0, var1);
      } else {
         return null;
      }
   }

   @Nullable
   private static Gui3.Type2 method9(ItemStackBridge var0, List<Bridge6_4> var1) {
      for (int var2 = 0; var2 < var1.size(); var2++) {
         if (var0.bridge$getItem() == var1.get(var2)) {
            return Gui3.Type2.fromIndex(var2);
         }
      }

      return null;
   }

   public static Gui3.Type2 method10(Bridge3_23 var0, @Nullable Itemcounter6 var1, @Nullable Vector3iBridge var2) {
      if (var0 == null) {
         return null;
      }

      List var3 = Bridge.method34().method8();

      for (int var4 = 0; var4 < var3.size(); var4++) {
         if (var0 == var3.get(var4)) {
            return Gui3.Type2.fromIndex(var4);
         }
      }

      return null;
   }

   public static Optional<String> method11(ItemStackBridge var0) {
      PropertyMapDataComponent var1 = (PropertyMapDataComponent)var0.bridge$getDataComponent(ItemDataComponentTypes.field48);
      if (var1 == null) {
         return Optional.empty();
      }

      Bridge_32 var2 = var1.bridge$getPropertyMap();
      if (var2 == null) {
         return Optional.empty();
      }

      List var3 = var2.bridge$get("textures");
      return var3 != null && !var3.isEmpty() ? Optional.ofNullable(((Bridge2_37)var3.get(0)).bridge$getValue()) : Optional.empty();
   }

   public static ItemStackBridge method12(String var0, String var1, String var2) {
      ItemStackBridge var3 = Bridge.method8().method38(Bridge.method28().method5());
      var3.bridge$setProfile(UUID.fromString(var0), var1, var2);
      return var3;
   }

   public static ItemStackBridge method13(String var0) {
      byte[] var1 = ("{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/" + var0 + "\"}}}").getBytes(StandardCharsets.UTF_8);
      return method12(UUID.nameUUIDFromBytes(var1).toString(), Base64.getEncoder().encodeToString(var1), null);
   }

   public static List<String> method14(ItemStackBridge var0) {
      ArrayList var1 = new ArrayList();
      if (var0 != null && !var0.bridge$isEmpty()) {
         ItemLoreComponent var2 = (ItemLoreComponent)var0.bridge$getDataComponent(ItemDataComponentTypes.field8);
         if (var2 == null) {
            return var1;
         }

         for (Bridge3_25 var4 : var2.bridge$getLines()) {
            Component var5 = var4.moonBridge$asAdventureComponent();
            var1.add(AdventureTextBridge.asLegacyString(var5));
         }

         return var1;
      } else {
         return var1;
      }
   }

   public static List<String> method15(ItemStackBridge var0) {
      ArrayList var1 = new ArrayList();
      if (var0 != null && !var0.bridge$isEmpty()) {
         ItemLoreComponent var2 = (ItemLoreComponent)var0.bridge$getDataComponent(ItemDataComponentTypes.field8);

         for (Bridge3_25 var4 : var2.bridge$getLines()) {
            Component var5 = var4.moonBridge$asAdventureComponent();
            var1.add(AdventureTextBridge.getTextContent(var5));
         }

         return var1;
      } else {
         return var1;
      }
   }

   public static List<Component> method16(ItemStackBridge var0) {
      ArrayList var1 = new ArrayList();
      if (var0 != null && !var0.bridge$isEmpty()) {
         ItemLoreComponent var2 = (ItemLoreComponent)var0.bridge$getDataComponent(ItemDataComponentTypes.field8);
         if (var2 == null) {
            return var1;
         }

         for (Bridge3_25 var4 : var2.bridge$getLines()) {
            var1.add(var4.moonBridge$asAdventureComponent());
         }

         return var1;
      } else {
         return var1;
      }
   }

   public static boolean method17(ItemStackBridge var0, ItemStackBridge var1) {
      if (var0 != null && var1 != null) {
         Bridge_57 var2 = method1(var0);
         Bridge_57 var3 = method1(var1);
         if (var2 != null && var3 != null) {
            String var4 = var2.bridge$getString("uuid");
            String var5 = var3.bridge$getString("uuid");
            return var4 != null && var4.equals(var5);
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static String method18(ItemStackBridge var0) {
      Bridge_57 var1 = method1(var0);
      return var1 == null ? null : var1.bridge$getString("uuid");
   }

   public static ItemStackBridge method19(Gui3.Type2 var0) {
      List var1 = Bridge.method28().method95();
      int var2 = var0.ordinal();
      if (var2 >= var1.size()) {
         return null;
      }

      Bridge6_4 var3 = (Bridge6_4)var1.get(var2);
      return Bridge.method8().method38(var3);
   }

   public static boolean method20(CompoundTagDataComponent var0) {
      if (var0 == null) {
         return false;
      }

      Bridge_57 var1 = var0.bridge$getData();
      return var1 == null ? false : var1.bridge$getInteger("ethermerge") == 1;
   }

   public static boolean method21(String var0) {
      return var0.equals("ASPECT_OF_THE_END")
         || var0.equals("ASPECT_OF_THE_VOID")
         || var0.equals("NECRONS_BLADE")
         || var0.equals("HYPERION")
         || var0.equals("ASTRAEA")
         || var0.equals("VALKYRIE")
         || var0.equals("SCYLLA");
   }

   public static Bridge3_6 method22(String var0) {
      byte[] var1 = Base64.getDecoder().decode(var0);
      return method24(var1);
   }

   public static Bridge3_6 method23(byte[] var0) {
      byte[] var1 = Base64.getDecoder().decode(var0);
      return method24(var1);
   }

   public static Bridge3_6 method24(byte[] var0) {
      ByteArrayInputStream var1 = new ByteArrayInputStream(var0);
      Bridge_57 var2 = Bridge.method51().method1(var1);
      return var2.bridge$getList("i", 10);
   }

   public static TextComponent method25(Bridge_57 var0) {
      Bridge_57 var1 = var0.bridge$getCompoundTag("tag").bridge$getCompoundTag("display");
      if (var1.bridge$isEmpty()) {
         return Component.text("None", NamedTextColor.RED);
      }

      TextComponent var2 = AdventureTextBridge.asAdventure(var1.bridge$getString("Name"));
      TextComponent var3 = var2;
      Bridge3_6 var4 = var1.bridge$getList("Lore", 8);

      for (int var5 = 0; var5 < var4.bridge$size(); var5++) {
         String var6 = var4.bridge$getString(var5);
         var3 = (TextComponent)((TextComponent)var3.appendNewline()).append(AdventureTextBridge.asAdventure(var6));
      }

      return (TextComponent)var2.hoverEvent(HoverEvent.showText(var3));
   }

   public static Bridge_57 method26(@Nullable String var0) {
      if (var0 == null) {
         return null;
      }

      try {
         return Bridge.method51().method1(new ByteArrayInputStream(Base64.getDecoder().decode(var0)));
      } catch (IOException var2) {
         Inventorymod2.method5(var2, "Failed to parse inventory");
         return null;
      }
   }

   @Annotation2(min = 33)
   @Nullable
   public static ItemStackBridge method27(Bridge_57 var0) {
      try {
         short var1 = var0.bridge$getShort("id");
         if (var1 == 141) {
            var1 = 391;
         }

         short var2 = var0.bridge$contains("Damage", 2) ? var0.bridge$getShort("Damage") : 0;
         byte var3 = var0.bridge$getByte("Count");
         Bridge_57 var4 = var0.bridge$getCompoundTag("tag");
         Bridge_57 var5 = var4.bridge$getCompoundTag("display");
         Bridge3_6 var6 = var5.bridge$getList("Lore", 8);
         ArrayList var7 = new ArrayList();

         for (int var8 = 0; var8 < var6.bridge$size(); var8++) {
            TextComponent var9 = AdventureTextBridge.asAdventure(var6.bridge$getString(var8));
            var7.add(var9.style(var9.style().decoration(TextDecoration.ITALIC, false)));
         }

         Bridge6_4 var20 = Bridge.method28().method22("minecraft:" + GuiType2.idToNewName(var1, var2));
         ItemStackBridge var21 = Bridge.method8().method38(var20);
         var21.bridge$setItemDamage(var2);
         TextComponent var10 = AdventureTextBridge.asAdventure(var5.bridge$getString("Name"));
         var21.bridge$setStackDisplayName(var10.style(var10.style().decoration(TextDecoration.ITALIC, false)));
         var21.bridge$setLore(var7);
         var21.bridge$setStackSize(var3);
         if (var4.bridge$contains("ench", 9)) {
            var21.bridge$setEnchantments(Map.of(Bridge.method32().method1(), 1));
         }

         if (var4.bridge$contains("SkullOwner", 10)) {
            Bridge_57 var11 = var4.bridge$getCompoundTag("SkullOwner");
            String var12 = var11.bridge$getString("Id");
            Bridge_57 var13 = var11.bridge$getCompoundTag("Properties");
            Bridge3_6 var14 = var13.bridge$getList("textures", 10);
            Bridge_57 var15 = var14.bridge$getCompoundAt(0);
            String var16 = var15.bridge$getString("Value");
            String var17 = var15.bridge$getString("Signature");
            var21.bridge$setProfile(UUID.fromString(var12), var16, var17);
         }

         if (var5.bridge$contains("color", 3)) {
            var21.bridge$setArmorColor(var5.bridge$getInteger("color"));
         }

         if (var4.bridge$contains("CustomPotionEffects", 9)) {
            ArrayList var22 = new ArrayList();
            Bridge3_6 var24 = var4.bridge$getList("CustomPotionEffects", 10);

            for (int var25 = 0; var25 < var24.bridge$size(); var25++) {
               Bridge_57 var26 = var24.bridge$getCompoundAt(var25);
               byte var27 = var26.bridge$getByte("Id");
               byte var28 = var26.bridge$getByte("Amplifier");
               int var29 = var26.bridge$getInteger("Duration");
               GuiType var18 = GuiType.getById(var27);
               if (var18 != null && var18.getPotion() != null) {
                  var22.add(Bridge.method36().method10(var18.getPotion().bridge$getID(), var18.getId(), var28, var29));
               }
            }

            var21.bridge$setPotionEffects(var22);
         }

         var21.bridge$setSkyBlockExtraAttributes(var4.bridge$getCompoundTag("ExtraAttributes"));
         var21.bridge$sbHideTooltipComponents();
         Bridge_57 var23 = var0.bridge$getCompoundTag("components");
         if (var23.bridge$contains("minecraft:item_model", 8)) {
            var21.bridge$setItemModel(ResourceLocationBridge.create(var23.bridge$getString("minecraft:item_model")));
         }

         return var21;
      } catch (Exception var19) {
         return null;
      }
   }

   @Annotation2(min = 33)
   @Nullable
   public static List<ItemStackBridge> method28(@Nullable String var0) {
      Bridge_57 var1 = method26(var0);
      if (var1 == null) {
         return null;
      }

      ArrayList var2 = new ArrayList();
      Bridge3_6 var3 = var1.bridge$getList("i", 10);

      for (int var4 = 0; var4 < var3.bridge$size(); var4++) {
         Bridge_57 var5 = var3.bridge$getCompoundAt(var4);
         ItemStackBridge var6 = method27(var5);
         if (var6 != null) {
            var2.add(var6);
         } else {
            var2.add(Bridge.method8().method41());
         }
      }

      return var2;
   }

   @NotNull
   public static Object2IntOpenHashMap<String> method29(@Nullable ItemStackBridge var0) {
      Object2IntOpenHashMap var1 = new Object2IntOpenHashMap();
      Bridge_57 var2 = method1(var0);
      if (var2 == null) {
         return var1;
      }

      Bridge_57 var3 = var2.bridge$getCompoundTag("enchantments");
      if (var3 == null) {
         return var1;
      }

      for (String var5 : var3.bridge$getAllKeys()) {
         var1.put(var5, var3.bridge$getInteger(var5));
      }

      return var1;
   }

   @Generated
   private Gui3() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public enum Type2 {
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

      public static Gui3.Type2 fromIndex(int var0) {
         return values()[var0];
      }
   }
}
