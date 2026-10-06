package com.moonsworth.lunar.client.driver.core.holograms.mixin;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BridgeType2_4;
import com.moonsworth.lunar.bridge.BridgeType3_2;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.cosmetics.OutfitManager;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager.Data;
import com.moonsworth.lunar.client.cosmetics.emote.Emote;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContextKind;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.cosmetics.Outfit;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.HologramUpdateEvent;
import com.moonsworth.lunar.client.cosmetics.emote.MorphRenderer;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.Generated;
import mchorse.emoticons.capabilities.cosmetic.EmoteController;
import mchorse.emoticons.common.EmoteAPI;
import org.jspecify.annotations.Nullable;

public class EmoteHologramLegacy extends com.moonsworth.lunar.client.driver.core.holograms.Holograms2<EmoteHologramLegacy> {
   private transient UUID uuid;
   @SerializedName("skin")
   private HologramSkinLegacy field22;
   @SerializedName("display")
   private HologramCameraPresetLegacy display;
   @SerializedName("emoteId")
   private int field23;
   @SerializedName("emoteMetadata")
   private int field24;
   @SerializedName("cosmetics")
   private Holograms[] field25;
   @SerializedName("emoteOnHover")
   private boolean field26;
   @SerializedName("canHoverCosmetics")
   private boolean field27;
   @SerializedName("renderContext")
   private RenderContextKind field28;
   @SerializedName("skinOverride")
   private SkinOverrideLegacy field29;
   @SerializedName("renderNametag")
   private boolean renderNametag;
   @SerializedName("itemType")
   private BridgeType2_4 field30;
   @SerializedName("itemMaterial")
   private BridgeType3_2 field31;
   @SerializedName("showCompanions")
   private boolean field32;
   @SerializedName("companionOffsetX")
   private @Nullable Float field33;
   @SerializedName("companionOffsetY")
   private @Nullable Float field34;
   private transient List<CosmeticMetadata> field35;
   private transient Emote field36;
   private transient boolean field37;
   private transient boolean field38;
   private transient boolean field39;
   private transient ItemStackBridge field40 = this.method8();
   private transient ItemStackBridge field41 = this.method8();

   @Override
   public void init() {
      if (!this.initialized) {
         this.uuid = UUID.randomUUID();
         this.field35 = new ArrayList<>();
         ThreadModuleDump63.method4().method55().method10(this.uuid, this.field35);
      }

      if (!this.field37 && Client.method109().method76().field8) {
         this.method6();
         this.method4();
         if (this.field23 != 0) {
            this.method5();
         }

         this.field37 = true;
      }

      if (this.field36 != null && this.field36.method3() && this.field38) {
         HologramsIterator2.method17(this.uuid, var1x -> ThreadModuleDump63.method4().method45().method7(var1x, this.field36, this.field24, null, 0));
      }

      if (ThreadModuleDump63.method3().bridge$getSession() != null) {
         UUID var1 = ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile().getId();
         Data var2 = (Data)ThreadModuleDump63.method4().method53().method63().get(var1);
         ThreadModuleDump63.method4().method53().method63().put(this.uuid, var2);
      }

      OutfitManager var3 = ThreadModuleDump63.method4().method55();
      if (var3.method17() != null) {
         Outfit var4 = var3.method4(this.uuid);
         if (var4 != null && var3.method17().method5().method9() != var4.method9()) {
            var4.method13(var3.method17().method5().method9());
         }
      }

      super.init();
   }

   @Override
   public void remove() {
      if (this.field36 != null) {
         HologramsIterator2.method17(this.uuid, var0 -> ThreadModuleDump63.method4().method45().method11(var0, true, false));
      }

      this.field38 = false;
      ThreadModuleDump63.method4().method53().removePlayer(this.uuid);
      ThreadModuleDump63.method4().method55().removePlayer(this.uuid);
      ThreadModuleDump63.method4().method45().IORHHHROCRRHORHRCHCCHHIHICCRCO().remove(this.uuid);
      EmoteController.getCache().remove(this.uuid);
      ThreadModuleDump63.method4().method70().method12().remove(this.uuid);
      super.remove();
   }

   public void method1(EmoteHologramLegacy var1) {
      this.init();
      super.method8(var1);
      this.field22 = var1.field22;
      this.display = var1.display;
      this.field26 = var1.field26;
      this.field27 = var1.field27;
      this.field25 = var1.field25;
      this.field29 = var1.field29;
      this.field28 = var1.field28;
      this.field32 = var1.field32;
      this.field33 = var1.field33;
      this.field34 = var1.field34;
      if (this.field30 != var1.field30 || this.field31 != var1.field31) {
         this.field31 = var1.field31;
         this.field30 = var1.field30;
      }

      if (this.field23 != var1.field23) {
         this.field23 = var1.field23;
         this.field24 = var1.field24;
         if (var1.field23 == 0) {
            HologramsIterator2.method17(this.uuid, var0 -> ThreadModuleDump63.method4().method45().method11(var0, true, false));
            this.field38 = false;
         } else {
            this.method5();
         }
      }

      this.method6();
      this.method4();
   }

   private void method4() {
      ItemStackBridge var1 = null;
      ItemStackBridge var2 = null;
      boolean var3 = false;
      boolean var4 = false;
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         for (CosmeticMetadata var6 : this.field35) {
            if (var6.method4().method10() == CosmeticCategoryType.SHIELDS) {
               var3 = true;
            }

            if (var6.method4().method10() == CosmeticCategoryType.HAND) {
               var4 = true;
            }

            if (var3 && var4) {
               break;
            }
         }
      }

      if (var3) {
         Bridge6_4 var8 = Bridge.method28().method22("shield");
         if (var8 != null) {
            var2 = Bridge.method8().method38(var8);
         }
      }

      if (this.field30 != null && this.field31 != null) {
         String var9 = switch (this.field31) {
            case WOOD -> "wooden";
            case GOLD -> "golden";
            default -> this.field31.getMaterial();
         };
         String var10 = var9 + "_" + this.field30.getId();
         Bridge6_4 var7 = Bridge.method28().method22(var10);
         if (var7 != null) {
            var1 = Bridge.method8().method38(var7);
         }
      }

      if (var1 == null && var2 == null) {
         this.field40 = this.method8();
         this.field41 = this.method8();
      } else {
         if (var1 == null && !var4) {
            var1 = var2;
            var2 = null;
         }

         this.field40 = var1 == null ? this.method8() : var1;
         this.field41 = var2 == null ? this.method8() : var2;
      }
   }

   private void method5() {
      this.field36 = ThreadModuleDump63.method4().method45().method13(this.field23);
      if (this.field36 != null) {
         HologramsIterator2.method17(this.uuid, var1 -> {
            ThreadModuleDump63.method4().method45().method7(var1, this.field36, this.field24, null, 0);
            if (this.field26) {
               EmoteAPI.setEmoteProgress(var1, 0.5F);
            }
         });
         this.field38 = !this.field26;
      }
   }

   private void method6() {
      for (Holograms var4 : this.field25) {
         CosmeticMetadata var5 = this.field35
            .stream()
            .filter(var1 -> var1.method4().method9() == var4.id())
            .findFirst()
            .orElse(ThreadModuleDump63.method4().method53().method48(var4.id(), var4.method1()));
         if (var5 != null) {
            var5.method4().method23(this.field27);
            this.field35.stream().filter(var1 -> var1.method4().method9() == var4.id()).findFirst().ifPresentOrElse(var1 -> {
               JsonObject var2 = var4.method1() == null ? new JsonObject() : var4.method1();
               var1.method6(var2);
               var1.method6().method1(var2);
            }, () -> this.field35.add(var5));
         }
      }

      this.field35.removeIf(var1 -> Arrays.stream(this.field25).noneMatch(var1x -> var1x.id() == var1.method4().method9()));
      ThreadModuleDump63.method4().method88().method11(this.uuid);
   }

   private ItemStackBridge method8() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? Bridge.method8().method41() : null;
   }

   @Override
   public boolean method9(AbstractRenderContext var1) {
      return this.field26 && !this.field38 && !this.field39 ? false : super.method9(var1);
   }

   @Override
   public int method11() {
      return this.field27 ? 60 : super.method11();
   }

   @Override
   public void method10(AbstractRenderContext var1) {
      this.field39 = false;
      super.method10(var1);
   }

   @Override
   public void tick() {
      if (!this.field26 || this.field38 || this.field20 <= 1) {
         this.field39 = this.field26;
         HologramsIterator2.method17(this.uuid, var1 -> {
            ClientEventBus.method29().method12(HologramUpdateEvent.class, () -> new HologramUpdateEvent(var1));

            for (MorphRenderer var3 : ThreadModuleDump63.method4().method70().method3(this.uuid)) {
               var3.method3();
            }
         });
      }

      super.tick();
   }

   @Generated
   public UUID getUuid() {
      return this.uuid;
   }

   @Generated
   public HologramSkinLegacy method10() {
      return this.field22;
   }

   @Generated
   public HologramCameraPresetLegacy getDisplay() {
      return this.display;
   }

   @Generated
   public int getEmoteId() {
      return this.field23;
   }

   @Generated
   public int getEmoteMetadata() {
      return this.field24;
   }

   @Generated
   public Holograms[] method22() {
      return this.field25;
   }

   @Generated
   public boolean method23() {
      return this.field26;
   }

   @Generated
   public boolean method31() {
      return this.field27;
   }

   @Generated
   public RenderContextKind method32() {
      return this.field28;
   }

   @Generated
   public SkinOverrideLegacy method33() {
      return this.field29;
   }

   @Generated
   public boolean isRenderNametag() {
      return this.renderNametag;
   }

   @Generated
   public BridgeType2_4 method34() {
      return this.field30;
   }

   @Generated
   public BridgeType3_2 method35() {
      return this.field31;
   }

   @Generated
   public boolean method36() {
      return this.field32;
   }

   @Generated
   public @Nullable Float method37() {
      return this.field33;
   }

   @Generated
   public @Nullable Float method38() {
      return this.field34;
   }

   @Generated
   public List<CosmeticMetadata> method39() {
      return this.field35;
   }

   @Generated
   public Emote method40() {
      return this.field36;
   }

   @Generated
   public boolean method41() {
      return this.field37;
   }

   @Generated
   public boolean isEmoting() {
      return this.field38;
   }

   @Generated
   public boolean method42() {
      return this.field39;
   }

   @Generated
   public ItemStackBridge method43() {
      return this.field40;
   }

   @Generated
   public ItemStackBridge method44() {
      return this.field41;
   }

   @Generated
   public void method28(boolean var1) {
      this.field38 = var1;
   }
}
