package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.common.collect.Range;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.lunarclient.websocket.cosmetic.v2.FavoriteCosmeticRequest;
import com.lunarclient.websocket.cosmetic.v2.UnfavoriteCosmeticRequest;
import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.cosmetics.OutfitManager;
import com.moonsworth.lunar.client.config.InternalSettings;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import com.moonsworth.lunar.client.cosmetics.CosmeticIndexEntry;
import com.moonsworth.lunar.client.cosmetics.CosmeticType;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.feature.HeightOffsetModuleType;
import com.moonsworth.lunar.client.cosmetics.emote.ConditionalOutfitTree;
import com.moonsworth.lunar.client.inactive.Inactive5;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.holograms.mixin.Holograms;
import com.moonsworth.lunar.client.driver.core.nameplate.LockerContextLegacy;
import com.moonsworth.lunar.client.driver.core.nameplate.LockerSectionLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import com.moonsworth.webosr.javascript.CallbackJS;
import it.unimi.dsi.fastutil.longs.LongSet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class CosmeticsBridge implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   private static final ToggleOption field1 = (ToggleOption)OptionFactory.method7("showClothCloaksForOthers").method31();
   private static final ToggleOption field2 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("flipArm")
         .method15(PhosphorIconLegacy.PI_SWAP_ARROW_HORIZONTAL_STROKE))
      .method31();
   private static FloatOption field3;
   private static final ToggleOption field4 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showHatAboveHelmet")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private static final ToggleOption field5 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showOverChestplate")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private static final ToggleOption field6 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showOverLeggings")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private static final ToggleOption field7 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showOverBoots")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method53().method15();
   }

   @Override
   public JsonElement provide() {
      return this.provide();
   }

   @CallbackJS("getCosmeticData")
   public static String method2(Integer[] var0) {
      CosmeticManager var1 = ThreadModuleDump63.method4().method53();
      JsonArray var2 = new JsonArray();

      for (Integer var6 : var0) {
         CosmeticIndexEntry var7 = var1.method60().get(var6);
         if (var7 != null) {
            boolean var8 = var1.method22(var7.getId()).isPresent();
            CosmeticMetadata var9 = var1.method48(var7.getId(), new JsonObject());
            if (var9 != null) {
               JsonObject var10 = var9.provide().getAsJsonObject();
               var10.addProperty("owned", var8);
               var2.add(var10);
            }
         }
      }

      return var2.toString();
   }

   @CallbackJS("updateCosmeticOption")
   public static String method3(Integer var0, String var1, String var2) {
      CosmeticManager var3 = ThreadModuleDump63.method4().method53();
      UUID var4 = ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile().getId();
      CosmeticMetadata var5 = var3.method23(var0.intValue());
      if (var5 == null) {
         var5 = var3.method66().stream().filter(var1x -> var1x.method4().method9() == var0.intValue()).findFirst().orElse(null);
      }

      if (var5 == null) {
         return method6(var0);
      }

      if (var1.equals(field7.getId())) {
         var5.method6().setShowOverBoots(Boolean.parseBoolean(var2));
      } else if (var1.equals(field6.getId())) {
         var5.method6().setShowOverLeggings(Boolean.parseBoolean(var2));
      } else if (var1.equals(field5.getId())) {
         var5.method6().setShowOverChestplate(Boolean.parseBoolean(var2));
      } else if (var1.equals(field4.getId())) {
         var5.method6().method8(Boolean.parseBoolean(var2));
      } else if (var1.equals(field1.getId())) {
         if (ThreadModuleDump63.method4().method54().method9()) {
            var5.method6().method7(Boolean.parseBoolean(var2));
         } else {
            String var7 = Client.method109().method67().method2("popups", "purchase_lunar_plus");
            ThreadModuleDump63.method4().method69().method3(var7);
         }
      } else if (var1.equals(field2.getId())) {
         List var13 = ThreadModuleDump63.method4().method53().method14(var4, CosmeticCategoryType.PET);
         if (var5.method3()) {
            CosmeticMetadata var8 = (CosmeticMetadata)var13.get(0);
            boolean var9 = var8.method6().method6();
            if (var13.size() == 1) {
               var8.method6().method13(!var9);
            } else {
               CosmeticMetadata var10 = (CosmeticMetadata)var13.get(1);
               if (var9) {
                  var8.method6().method13(false);
                  var10.method6().method13(true);
               } else {
                  var8.method6().method13(true);
                  var10.method6().method13(false);
               }

               var10.method6().method2(var5.method5());
            }
         } else {
            var5.method6().method13(Boolean.parseBoolean(var2));
         }
      } else if (field3 != null && var1.equals(field3.getId())) {
         OwnedCosmetic var16 = var5.method4();
         if (var16 != null && ThreadModuleDump63.method4().method53().method7(var16.method2()) instanceof HeightOffsetModuleType var20 && var20.method8().isPresent()) {
            Range var22 = var20.method8().get();
            var5.method6().method14(method9(var2, var22));
         }
      } else if (var5.method4() instanceof EmoteModel var6 && var6.method6().isPresent()) {
         for (Inactive5 var19 : var6.method6().get().method13()) {
            if (var19.getId().equals(var1)) {
               Inactive5.Type var21 = var19.method2();

               Serializable var11 = switch (var21) {
                  case INT -> Integer.parseInt(var2);
                  case FLOAT -> Float.parseFloat(var2);
                  case BOOLEAN -> Boolean.parseBoolean(var2);
                  default -> var2;
               };
               var5.method5().add(var1, Inactive5.Type.getJsonPrimitive(var11));
               break;
            }
         }
      }

      var5.method6().method2(var5.method5());
      OutfitManager var12 = ThreadModuleDump63.method4().method55();
      if (var12.method17() != null && var5.method3()) {
         var12.method17().method5().method3();
      }

      return method7(var5);
   }

   @CallbackJS("sync")
   public static void method4() {
      ThreadModuleDump63.method4().method41().method9().<init>();
      ThreadModuleDump63.method4().method53().method24();
      ConditionalOutfitTree var0 = ThreadModuleDump63.method4().method55().method17();
      if (var0 != null && var0.method5() != null) {
         var0.method5().method3();
      }
   }

   @CallbackJS("updateContext")
   public static void method5() {
      ThreadModuleDump63.method4().method53().method53();
   }

   @CallbackJS("requestData")
   public static String method6(Integer var0) {
      CosmeticManager var1 = ThreadModuleDump63.method4().method53();
      CosmeticMetadata var2 = var1.method23(var0.intValue());
      if (var2 == null) {
         var2 = var1.method66().stream().filter(var1x -> var1x.method4().method9() == var0.intValue()).findFirst().orElse(null);
      }

      return var2 == null ? "{}" : method7(var2);
   }

   private static String method7(CosmeticMetadata var0) {
      JsonObject var1 = new JsonObject();
      JsonArray var2 = new JsonArray();
      UUID var3 = ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile().getId();
      boolean var4 = ThreadModuleDump63.method4().method54().method9();
      method11(var2, var3, var0, var4);
      method12(var2, var0);
      method10(var2, var3, var0);
      method8(var2, var3, var0);
      if (var0.method4() instanceof EmoteModel var5 && var5.method6().isPresent()) {
         com.moonsworth.lunar.client.inactive.mixin.Gui2Handler var12 = var5.method6().get();

         for (Inactive5 var8 : var12.method13()) {
            ClientOption var9;
            try {
               var9 = Inactive5.Type.getOption(var0, var8);
            } catch (RuntimeException var11) {
               Slayer.error("Failed to build cosmetic option " + var8.getId() + " for " + var12.method2(), var11);
               continue;
            }

            com.moonsworth.lunar.client.config.option.OptionDataProvider var10 = (com.moonsworth.lunar.client.config.option.OptionDataProvider)var9.HIRHCCHIRHRORIICOIHIHCICOIRHHC(
               OptionTraits.field10
            );
            if (var10 != null) {
               var2.add(var10.provide());
            }
         }
      }

      var1.add("options", var2);
      var1.add("cosmetic", var0.provide());
      return var1.toString();
   }

   private static void method8(JsonArray var0, UUID var1, CosmeticMetadata var2) {
      if (ThreadModuleDump63.method4().method53().method7(var2.method4().method2()) instanceof HeightOffsetModuleType var4 && var4.method8().isPresent()) {
         float var5 = var2.method6().getHatHeightOffset();
         Range var6 = var4.method8().get();
         field3 = (FloatOption)((FloatOption.Data)((FloatOption.Data)((FloatOption.Data)OptionFactory.method2("cosmeticHeight")
                     .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.0F))
                  .method8((Float)var6.lowerEndpoint(), (Float)var6.upperEndpoint()))
               .method15(PhosphorIconLegacy.PI_ALIGN_UP_STROKE))
            .method31();
         field3.method1(var5);
         com.moonsworth.lunar.client.config.option.OptionDataProvider var7 = (com.moonsworth.lunar.client.config.option.OptionDataProvider)field3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(
            OptionTraits.field10
         );
         if (var7 != null) {
            var0.add(var7.provide());
         }
      }
   }

   private static float method9(String var0, Range<Float> var1) {
      float var2;
      try {
         var2 = Float.parseFloat(var0);
      } catch (NumberFormatException var4) {
         return 0.0F;
      }

      return !Float.isFinite(var2) ? 0.0F : ThreadModuleDump67.method3(var2, (Float)var1.lowerEndpoint(), (Float)var1.upperEndpoint());
   }

   private static void method10(JsonArray var0, UUID var1, CosmeticMetadata var2) {
      if (var2.method4().method10() == CosmeticCategoryType.PET) {
         field2.method10(var2.method6().method6());
         com.moonsworth.lunar.client.config.option.OptionDataProvider var3 = (com.moonsworth.lunar.client.config.option.OptionDataProvider)field2.HIRHCCHIRHRORIICOIHIHCICOIRHHC(
            OptionTraits.field10
         );
         if (var3 != null) {
            var0.add(var3.provide());
         }
      }
   }

   private static void method11(JsonArray var0, UUID var1, CosmeticMetadata var2, boolean var3) {
      if (var2.method4().method10() == CosmeticCategoryType.CLOAK) {
         if (!var3) {
            var2.method6().method7(false);
         }

         field1.method10(var2.method6().method3());
         com.moonsworth.lunar.client.config.option.OptionDataProvider var4 = (com.moonsworth.lunar.client.config.option.OptionDataProvider)field1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(
            OptionTraits.field10
         );
         if (var4 != null) {
            var0.add(var4.provide());
         }
      }
   }

   private static void method12(JsonArray var0, CosmeticMetadata var1) {
      OwnedCosmetic var2 = var1.method4();
      CosmeticIndexEntry var3 = ThreadModuleDump63.method4().method53().method60().get((int)var2.method9());
      com.moonsworth.lunar.client.cosmetics.AbstractCosmetic var4 = ThreadModuleDump63.method4().method53().method7(var3);
      if (var4 != null && var4.method1() && var4.method5() != null) {
         EquipmentSlotBridge var5 = var4.method5().getArmorSlot();
         switch (var5) {
            case FEET:
               field7.method10(var1.method6().isShowOverBoots());
               com.moonsworth.lunar.client.config.option.OptionDataProvider var9 = (com.moonsworth.lunar.client.config.option.OptionDataProvider)field7.HIRHCCHIRHRORIICOIHIHCICOIRHHC(
                  OptionTraits.field10
               );
               if (var9 != null) {
                  var0.add(var9.provide());
               }
               break;
            case LEGS:
               field6.method10(var1.method6().isShowOverLeggings());
               com.moonsworth.lunar.client.config.option.OptionDataProvider var8 = (com.moonsworth.lunar.client.config.option.OptionDataProvider)field6.HIRHCCHIRHRORIICOIHIHCICOIRHHC(
                  OptionTraits.field10
               );
               if (var8 != null) {
                  var0.add(var8.provide());
               }
               break;
            case CHEST:
               field5.method10(var1.method6().isShowOverChestplate());
               com.moonsworth.lunar.client.config.option.OptionDataProvider var7 = (com.moonsworth.lunar.client.config.option.OptionDataProvider)field5.HIRHCCHIRHRORIICOIHIHCICOIRHHC(
                  OptionTraits.field10
               );
               if (var7 != null) {
                  var0.add(var7.provide());
               }
               break;
            case HEAD:
               field4.method10(var1.method6().method4());
               com.moonsworth.lunar.client.config.option.OptionDataProvider var6 = (com.moonsworth.lunar.client.config.option.OptionDataProvider)field4.HIRHCCHIRHRORIICOIHIHCICOIRHHC(
                  OptionTraits.field10
               );
               if (var6 != null) {
                  var0.add(var6.provide());
               }
         }
      }
   }

   @CallbackJS("markAllAsSeen")
   public static void method13() {
      CosmeticManager var0 = ThreadModuleDump63.method4().method53();
      InternalSettings var1 = ThreadModuleDump63.method4().method41().method9();
      var0.IIORHHIRHIORHRCCCOICCRCHRRCCRH()
         .stream()
         .map(OwnedCosmetic::method9)
         .filter(var1x -> !((LongSet)var1.method21().get()).contains(var1x))
         .forEach(var1.method21()::method1);
      ThreadModuleDump63.method4().method41().method9().<init>();
      ThreadModuleDump63.method4().method53().method53();
   }

   @CallbackJS("markAsSeen")
   public static void method14(CosmeticsBridge.Data var0) {
      var0.method1().forEach(var0x -> ThreadModuleDump63.method4().method41().method9().method21().method1(var0x));
      ThreadModuleDump63.method4().method41().method9().<init>();
      ThreadModuleDump63.method4().method53().method53();
   }

   @CallbackJS("unequipAll")
   public static void method16() {
      Optional.ofNullable(ThreadModuleDump63.method4().method55().method17()).ifPresent(var0 -> {
         com.moonsworth.lunar.client.cosmetics.Outfit var1 = var0.method5();
         var1.method4().clear();
         var1.method3();
         ThreadModuleDump63.method4().method88().method7(ThreadModuleDump63.method7());
      });
   }

   private static void method16(OwnedCosmetic var0, OwnedCosmetic var1) {
      if (var0.method10() != var1.method10()) {
         ThreadModuleDump63.method4().method69().method7(NotificationType.WARNING, var1.getName() + " was unequipped as it is incompatible with " + var0.getName());
      }
   }

   @CallbackJS("showCosmeticLocker")
   public static void method17() {
      LcuiScreen.method15();
      DriverViewportLegacy.method50().method17(DriverRouteRegistryLegacy.field13, new LockerContextLegacy(LockerSectionLegacy.COSMETICS, null, false));
   }

   @CallbackJS("toggleCosmetic")
   public static void method18(int var0) {
      CosmeticManager var1 = ThreadModuleDump63.method4().method53();
      Optional.ofNullable(ThreadModuleDump63.method4().method55().method17()).ifPresent(var2 -> {
         com.moonsworth.lunar.client.cosmetics.Outfit var3 = var2.method5();
         CosmeticMetadata var4 = var3.method4().stream().filter(var1xx -> var1xx.method4().method9() == var0).findFirst().orElse(null);
         if (var4 == null) {
            OwnedCosmetic var5 = var1.method58().get((long)var0);
            if (var5 != null) {
               var4 = new CosmeticMetadata(var5, new JsonObject());
            }
         }

         if (var4 != null) {
            method22(var4, var3.method4(), true);
            var3.method3();
            if (var4.method4().method10() == CosmeticCategoryType.COMPANION && ThreadModuleDump63.method7() != null) {
               ThreadModuleDump63.method4().method88().method7(ThreadModuleDump63.method7());
            }
         }
      });
   }

   @CallbackJS("previewCosmetics")
   public static String method19(CosmeticsBridge.PreviewCosmeticsPayload var0, boolean var1) {
      CosmeticManager var2 = ThreadModuleDump63.method4().method53();
      if (var0 == null) {
         return "{}";
      }

      CosmeticMetadata var3 = Client.method109().method53().method48(var0.field2.id(), var0.field2.method1() == null ? new JsonObject() : var0.field2.method1());
      if (var3 == null) {
         return "{}";
      }

      ArrayList var4 = new ArrayList<>(Arrays.stream(var0.field1).map(var1x -> {
         CosmeticMetadata var2x = null;

         for (OwnedCosmetic var4x : var2.IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
            if (var4x.method9() == var1x.id()) {
               var2x = var2.method48(var1x.id(), var1x.method1() == null ? new JsonObject() : var1x.method1());
               break;
            }
         }

         return var2x;
      }).filter(Objects::nonNull).toList());
      ArrayList var5 = new ArrayList(var4);
      if (!var5.contains(var3)) {
         var5.add(var3);
      }

      List var6 = method22(var3, var4, false);
      var6.removeIf(var1x -> {
         for (CosmeticMetadata var3x : var4) {
            if (var3x.method4().method9() == var1x.method9()) {
               return false;
            }
         }

         return true;
      });
      var6.removeIf(var1x -> var1x.method9() == var3.method4().method9());
      List var7 = method21(var6);
      List var8 = var6.stream().map(OwnedCosmetic::method9).toList();
      var4.removeIf(var1x -> var8.contains(var1x.method4().method9()));
      var4.add(var3);
      var7.removeIf(var1x -> var1x.method4().method10() == var3.method4().method10());
      JsonObject var9 = new JsonObject();
      JsonArray var10 = new JsonArray();

      for (CosmeticMetadata var12 : var1 ? var7 : var4) {
         JsonObject var13 = new JsonObject();
         var13.addProperty("id", var12.method4().method9());
         var13.addProperty("name", var12.method4().getName());
         var13.addProperty("type", var12.method4().method10().getDisplayName());
         var12.method6().method2(var12.method5());
         var13.add("metadata", var12.method5());
         var10.add(var13);
      }

      var9.add("cosmetics", var10);
      return var9.toString();
   }

   @CallbackJS("setCosmeticFavorite")
   public static void method20(Integer var0, Boolean var1) {
      CosmeticManager var2 = ThreadModuleDump63.method4().method53();
      if (var1 && var2.method69().add(var0)) {
         Client.method109().method35().method87().favoriteCosmetic(null, FavoriteCosmeticRequest.newBuilder().setCosmeticId(var0).build(), var0x -> {});
      } else if (!var1 && var2.method69().remove(var0)) {
         Client.method109().method35().method87().unfavoriteCosmetic(null, UnfavoriteCosmeticRequest.newBuilder().setCosmeticId(var0).build(), var0x -> {});
      }

      var2.method53();
   }

   private static List<CosmeticMetadata> method21(List<OwnedCosmetic> var0) {
      ArrayList var1 = new ArrayList();

      for (OwnedCosmetic var3 : var0) {
         for (OwnedCosmetic var5 : ThreadModuleDump63.method4().method53().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
            if (var5.method9() == var3.method9()) {
               var1.add(new CosmeticMetadata(var5, new JsonObject()));
               break;
            }
         }
      }

      return var1;
   }

   private static List<OwnedCosmetic> method22(CosmeticMetadata var0, List<CosmeticMetadata> var1, boolean var2) {
      CosmeticManager var3 = ThreadModuleDump63.method4().method53();
      OwnedCosmetic var4 = var0.method4();
      ArrayList var5 = new ArrayList();
      boolean var6 = var4.method20() > 0L && var4.method20() < System.currentTimeMillis();
      ArrayList var7 = new ArrayList(var1);
      boolean var8 = var1.stream().anyMatch(var1x -> var1x.method4().method9() == var4.method9());
      if (var8 && var2) {
         var5.add(var4);
      } else if (!var6) {
         if (var4 instanceof EmoteModel var9 && var9.method6().isPresent()) {
            String var10 = var9.method6().get().method10();
            Map var11 = var3.method58();
            ThreadModuleDump63.method4().method77().method1(var10).ifPresent(var2x -> var2x.method1().forEach(var2xx -> {
               OwnedCosmetic var3x = (OwnedCosmetic)var11.get(var2xx);
               if (var3x != null) {
                  var5.add(var3x);
               }
            }));
         }

         var7.forEach(var2x -> {
            OwnedCosmetic var3x = var2x.method4();
            if (var3x instanceof EmoteModel var4x && var4x.method6().isPresent()) {
               String var5x = var4x.method6().get().method10();
               ThreadModuleDump63.method4().method77().method1(var5x).ifPresent(var3xx -> var3xx.method1().forEach(var3xxx -> {
                  if (var3xxx == var4.method9()) {
                     var5.add(var3x);
                  }
               }));
            }
         });

         for (CosmeticMetadata var17 : var7) {
            OwnedCosmetic var21 = var17.method4();
            if (var21 != var4) {
               if (var21.method10().getRenderAs() == CosmeticType.HAT && var4.method10().getRenderAs() == CosmeticType.HAT) {
                  HeightOffsetModuleType var24 = var3.method6(var4.method2());
                  HeightOffsetModuleType var26 = var3.method6(var21.method2());
                  if (var24 == null
                     || var26 == null
                     || !var24.method3(var26, var21, var4) && !var26.method3(var24, var21, var4)) {
                     var5.add(var21);
                  }
               } else if (var21.method10() == CosmeticCategoryType.BACKPACK && var4.method10() == CosmeticCategoryType.CLOAK) {
                  var5.add(var21);
               } else if (var21.method10() == CosmeticCategoryType.CLOAK && var4.method10() == CosmeticCategoryType.BACKPACK) {
                  var5.add(var21);
               } else if (var21.method10() == var4.method10()) {
                  List var12 = var1.stream().filter(var0x -> var0x.method4().method10() == CosmeticCategoryType.PET).toList();
                  boolean var13 = var1.stream().anyMatch(var1x -> var1x.method4().method9() == var21.method9());
                  if ((var21.method10() != CosmeticCategoryType.PET || var12.size() != 1 || !var13)
                     && (!var21.method10().isHeldItemCosmetic() || var21.method16() == var4.method16())) {
                     var5.add(var21);
                  }
               }
            }
         }

         if (var2) {
            var1.add(var0);
            if (var4 instanceof EmoteModel var15 && var15.method6().isPresent()) {
               for (Inactive5 var25 : var15.method6().get().method13()) {
                  if (!var0.method5().has(var25.getId())) {
                     var0.method5().add(var25.getId(), Inactive5.Type.getJsonPrimitive(var25.getDefaultValue()));
                  }
               }
            }
         }

         ArrayList var16 = new ArrayList();

         for (CosmeticMetadata var23 : var7) {
            if (var23.method4().method10() == CosmeticCategoryType.PET) {
               var16.add(var23);
            }
         }

         if (var4.method10() == CosmeticCategoryType.PET && var16.size() == 1) {
            CosmeticMetadata var20 = (CosmeticMetadata)var16.get(0);
            var0.method6().method13(!var20.method6().method6());
            var0.method6().method2(var0.method5());
         }
      }

      if (var2) {
         var5.forEach(var2x -> {
            if (var1.stream().anyMatch(var1xx -> var1xx.method4().method9() == var2x.method9())) {
               method16(var4, var2x);
            }

            var1.removeIf(var1xx -> var1xx.method4().method9() == var2x.method9());
         });
      }

      return var5;
   }

   public class Data {
      @SerializedName("seen")
      private final List<Long> field1;

      public Data(List<Long> var1) {
         this.field1 = var1;
      }

      @SerializedName("seen")
      public List<Long> method1() {
         return this.field1;
      }
   }

   public class PreviewCosmeticsPayload {
      @SerializedName("active")
      private final Holograms[] field1;
      @SerializedName("preview")
      private final Holograms field2;

      public PreviewCosmeticsPayload(Holograms[] var1, Holograms var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      @SerializedName("active")
      public Holograms[] method1() {
         return this.field1;
      }

      @SerializedName("preview")
      public Holograms method2() {
         return this.field2;
      }
   }
}
