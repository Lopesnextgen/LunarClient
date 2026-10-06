package com.moonsworth.lunar.client.cosmetics;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BridgeType2_4;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.cosmetics.AbstractCosmetic;
import com.moonsworth.lunar.client.cosmetics.CosmeticModelRenderer;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.render.jit.JitAssetIndex;
import com.moonsworth.lunar.client.render.jit.JitPaths;
import com.moonsworth.lunar.client.render.jit.JitAnimatedResource;
import com.moonsworth.lunar.client.cosmetics.gecko.ItemRenderMaterial;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.driver.core.holograms.mixin.HologramSkinLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump34;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.longs.LongSet;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteGiftInfo;

public class OwnedCosmetic implements JsonProviderLegacy {
   private final long field1;
   private final String field2;
   private final CosmeticCategoryType field3;
   private final ResourceLocationBridge field4;
   private final boolean field5;
   private boolean field6;
   private final boolean field7;
   private final long field8;
   private final Instant field9;
   private boolean field10 = false;
   private boolean field11;
   private final List<String> field12;
   private final List<String> field13;
   private final boolean field14;
   private final ItemRenderMaterial field15;
   private final EmoteGiftInfo field16;
   private Set<String> field17;
   private Set<String> field18;

   public OwnedCosmetic(
      long var1,
      String var3,
      ResourceLocationBridge var4,
      CosmeticCategoryType var5,
      boolean var6,
      long var7,
      Instant var9,
      List<String> var10,
      List<String> var11,
      boolean var12,
      ItemRenderMaterial var13,
      EmoteGiftInfo var14,
      boolean var15
   ) {
      this.field1 = var1;
      this.field2 = var3;
      this.field3 = var5;
      this.field4 = var4;
      this.field7 = var6;
      this.field8 = var7;
      this.field9 = var9;
      this.field13 = var10;
      this.field12 = var11;
      this.field14 = var12;
      this.field15 = var13;
      this.field16 = var14;
      this.field11 = var15;
      this.field5 = !JitPaths.method1(var4);
   }

   public CosmeticIndexEntry method2() {
      return ThreadModuleDump63.method4().method53().method60().get((int)this.field1);
   }

   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("id", this.field1);
      var1.addProperty("name", this.field2);
      var1.addProperty("resourceLocation", this.field4.bridge$getPath());
      if (this.field3.getDisplay() != null) {
         var1.addProperty("display", this.field3.getDisplay().name());
      }

      BridgeType2_4 var2 = this.field3.getItemForCosmeticType();
      if (var2 != null) {
         var1.addProperty("itemType", var2.name());
      }

      var1.addProperty("animated", this.field14);
      if (this.field16 != null) {
         var1.add("gifter", this.field16.provide());
      }

      if (this.field15 != null) {
         var1.addProperty("itemMaterial", this.field15.name());
      }

      JsonArray var3 = new JsonArray();
      this.field13.forEach(var3::add);
      var1.add("tags", var3);
      JsonArray var4 = new JsonArray();
      this.field12.forEach(var4::add);
      var1.add("colors", var4);
      var1.addProperty("desiredSkin", HologramSkinLegacy.ASTRONAUT_DARK.name());
      CosmeticManager var5 = ThreadModuleDump63.method4().method53();
      var1.addProperty("isFreeLunarPlus", var5.method65().contains((int)this.field1) && ThreadModuleDump63.method4().method54().method9());
      var1.addProperty("isFavorite", var5.method69().contains((int)this.field1) && ThreadModuleDump63.method4().method54().method9());
      var1.addProperty("isKnown", ((LongSet)ThreadModuleDump63.method4().method41().method9().method21().get()).contains((int)this.field1));
      CosmeticIndexEntry var6 = var5.method60().get((int)this.field1);
      boolean var7 = var6 != null && var6.method14();
      if (var7) {
         var1.addProperty("type", CosmeticCategoryType.DEV_COSMETICS.getName());
      } else {
         var1.addProperty("type", this.field3.getName());
      }

      if (var6 != null) {
         AbstractCosmetic var8 = ThreadModuleDump63.method4().method53().method7(var6);
         if (var8 != null && var8.method1() && var8.method5() != null) {
            EquipmentSlotBridge var9 = var8.method5().getArmorSlot();
            switch (var9) {
               case FEET:
                  var1.addProperty("showOverBoots", true);
                  break;
               case LEGS:
                  var1.addProperty("showOverLeggings", true);
                  break;
               case CHEST:
                  var1.addProperty("showOverChestplate", true);
                  break;
               case HEAD:
                  var1.addProperty("showHatAboveHelmet", true);
            }
         }
      }

      if (this.field8 > 0L) {
         TranslationManager var11 = Client.method109().method67();
         String var15;
         if (this.field8 < System.currentTimeMillis()) {
            var1.addProperty("expireTime", this.field8);
            int var10 = Math.max(1, (int)((System.currentTimeMillis() - this.field8) / 86400000L));
            var15 = var11.method2("gui.cosmetics", var10 > 1 ? "expired_plural" : "expired_singular", var10);
         } else {
            var1.addProperty("expireTime", 0);
            int var16 = Math.max(1, (int)((this.field8 - System.currentTimeMillis()) / 86400000L));
            var15 = var11.method2("gui.cosmetics", var16 > 1 ? "expires_in_plural" : "expires_in_singular", var16);
         }

         var1.addProperty("expiresIn", var15);
      }

      if (this.field9 == null) {
         var1.addProperty("grantedAt", 0);
         var1.addProperty("grantedAtReadable", "Unknown");
      } else {
         LocalDateTime var12 = LocalDateTime.ofInstant(this.field9, ZoneId.systemDefault());
         var1.addProperty("grantedAt", this.field9.getEpochSecond());
         var1.addProperty("grantedAtReadable", var12.format(ThreadModuleDump34.field1));
      }

      if (this.field17 != null) {
         JsonArray var13 = new JsonArray();
         this.field17.forEach(var13::add);
         var1.add("artistErrors", var13);
      }

      if (this.field18 != null) {
         JsonArray var14 = new JsonArray();
         this.field18.forEach(var14::add);
         var1.add("artistWarnings", var14);
      }

      return var1;
   }

   public void method2(CosmeticMetadata var1, @Nullable Bridge5_11 var2, AbstractRenderContext var3, float var4, float var5, float var6, float var7, int var8) {
      Bridge5_12 var9 = ThreadModuleDump63.method3();
      ResourceLocationBridge var10 = this.method3();
      if (this.field3 == CosmeticCategoryType.CLOAK) {
         Bridge8Extension3 var11 = var9.bridge$getTextureManager().method1(var10);
         if (var11 != null) {
            var3.push();
            var3.method16();
            var3.method14();
            var3.method18(var8);
            var3.translate(var4 + var6 / 2.0F, var5 + var7 / 4.0F / 2.0F + 1.0F, 50.0);
            var3.method4(-22.5F, 1.0F, 0.0F, 0.0F);
            var3.method4(-225.0F, 0.0F, 1.0F, 0.0F);
            float var12 = var7 - var7 / 4.0F - 1.0F;
            var3.scale(-var12, var12, var12);
            UUID var13 = var2 == null ? null : var2.bridge$getUniqueID();
            CosmeticModelRenderer.method5(
               var3,
               null,
               var2,
               var11,
               var10,
               () -> {
                  if (ThreadModuleDump63.MC_VERSION >= 6) {
                     CosmeticManager.field4.method1(var3, 0.0315F, var10, var13);
                  } else {
                     ThreadModuleDump63.method3()
                        .bridge$getEntityRenderDispatcher()
                        .bridge$defaultPlayerRenderer()
                        .bridge$getMainModel()
                        .bridge$cloak()
                        .bridge$render(0.0625F, var10);
                  }
               }
            );
            var3.method15();
            var3.method17();
            var3.pop();
            var3.method23();
            var3.method33();
         }
      } else if (this.field3 != CosmeticCategoryType.WINGS) {
         AbstractCosmetic var14 = ThreadModuleDump63.method4().method53().method7(this.method2());
         if (var14 == null) {
            if (!this.field6) {
               this.field6 = true;
               String var16 = String.format("No corresponding index type \"%s\" found for cosmetic: %s", this.method2().method5(), this.method2().getName());
               Inventorymod2.method5(new RuntimeException(var16), "CosmeticEntry");
            }

            return;
         }

         JitAssetIndex var15 = ThreadModuleDump63.method4().method96();
         JitAnimatedResource var17 = var15.method2(this.field4, var0 -> new JitAnimatedResource(var0, false));
         if (!var17.method3()) {
            return;
         }

         if (Bridge.getMinecraftVersion().method21()) {
            var3.method26();
         }

         var3.push();
         var3.method16();
         var3.method14();
         var3.method18(var8);
         Bridge.method14().method2();
         var3.translate(var4 + var6 / 2.0F, var5 + (var7 - 3.0F), 50.0);
         var3.scale(-40.0F, 40.0F, 40.0F);
         var3.method4(-22.5F, 1.0F, 0.0F, 0.0F);
         var3.method4(45.0F, 0.0F, 1.0F, 0.0F);
         var3.method4(180.0F, 0.0F, 1.0F, 0.0F);
         var3.method18();
         Bridge.method14().method2();
         var3.method7(var2x -> {
            var2x.push();
            CosmeticModelRenderer.method1(var2x, null, null, var1, var14, 0.0F, 0.0F, 0.0F, false, false, false);
            var2x.pop();
         }, var3x -> {
            RenderLayerBridge var4x = LunarRenderTypes.field45.get(this.field4);
            var4x.bridge$setupRenderState();
            CosmeticModelRenderer.method3(var3x, null, null, var4x, var1, var14, 0.0F, false, false, -1);
            var4x.bridge$clearRenderState();
         });
         var3.method15();
         var3.method17();
         var3.pop();
         Bridge.method14().method3();
         var3.method23();
         var3.method13();
         var3.method33();
      }
   }

   public ResourceLocationBridge method3() {
      return this.field4;
   }

   public ResourceLocationBridge method4(Bridge6_10 var1) {
      return this.method6(var1 == null ? null : var1.bridge$getUniqueID());
   }

   public ResourceLocationBridge method5(EntityPlayerBridge var1) {
      return this.method6(var1 == null ? null : var1.bridge$getUniqueID());
   }

   public ResourceLocationBridge method6(UUID var1) {
      return this.field11 && var1 != null && !this.field5
         ? ResourceLocationBridge.create(this.field4.bridge$getDomain(), this.field4.bridge$getPath() + "_" + var1)
         : this.method3();
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else {
         return !(var1 instanceof OwnedCosmetic var2)
            ? false
            : this.field1 == var2.field1
               && this.field3 == var2.field3
               && this.field7 == var2.field7
               && this.field2.equals(var2.field2)
               && this.field4.equals(var2.field4);
      }
   }

   public void method7(String var1, boolean var2) {
      if (this.field18 == null) {
         this.field18 = new HashSet<>();
      }

      if (this.field18.add(var1) && var2) {
         Client.method109().method53().method53();
      }
   }

   public void method8(String var1, boolean var2) {
      if (this.field17 == null) {
         this.field17 = new HashSet<>();
      }

      if (this.field17.add(var1) && var2) {
         Client.method109().method53().method53();
      }
   }

   @Generated
   public long method9() {
      return this.field1;
   }

   @Generated
   public String getName() {
      return this.field2;
   }

   @Generated
   public CosmeticCategoryType method10() {
      return this.field3;
   }

   @Generated
   public boolean method11() {
      return this.field5;
   }

   @Generated
   public boolean method12() {
      return this.field6;
   }

   @Generated
   public boolean method13() {
      return this.field11;
   }

   @Generated
   public List<String> getColors() {
      return this.field12;
   }

   @Generated
   public List<String> method14() {
      return this.field13;
   }

   @Generated
   public boolean method15() {
      return this.field14;
   }

   @Generated
   public ItemRenderMaterial method16() {
      return this.field15;
   }

   @Generated
   public Set<String> method17() {
      return this.field17;
   }

   @Generated
   public Set<String> method18() {
      return this.field18;
   }

   @Generated
   public boolean method19() {
      return this.field7;
   }

   @Generated
   public long method20() {
      return this.field8;
   }

   @Generated
   public Instant method21() {
      return this.field9;
   }

   @Generated
   public boolean method22() {
      return this.field10;
   }

   @Generated
   public void method23(boolean var1) {
      this.field10 = var1;
   }

   @Generated
   public void method24(boolean var1) {
      this.field11 = var1;
   }

   @Generated
   public EmoteGiftInfo method25() {
      return this.field16;
   }
}
