package com.moonsworth.lunar.client.driver.hologram;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LunarItemType;
import com.moonsworth.lunar.bridge.LunarItemMaterial;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.cosmetics.OutfitManager;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager.Data;
import com.moonsworth.lunar.client.cosmetics.emote.Emote;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContextKind;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.cosmetics.Outfit;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.EventHologramUpdate;
import com.moonsworth.lunar.client.cosmetics.emote.MorphRenderer;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator2;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.Generated;
import mchorse.emoticons.capabilities.cosmetic.EmoteController;
import mchorse.emoticons.common.EmoteAPI;
import org.jspecify.annotations.Nullable;

public class EmoteHologram extends com.moonsworth.lunar.client.driver.core.holograms.Holograms2<EmoteHologram> {
   private transient UUID uuid;
   @SerializedName("skin")
   private HologramSkin field22;
   @SerializedName("display")
   private HologramCameraPreset display;
   @SerializedName("emoteId")
   private int field23;
   @SerializedName("emoteMetadata")
   private int field24;
   @SerializedName("cosmetics")
   private HologramEntry[] field25;
   @SerializedName("emoteOnHover")
   private boolean field26;
   @SerializedName("canHoverCosmetics")
   private boolean field27;
   @SerializedName("renderContext")
   private RenderContextKind field28;
   @SerializedName("skinOverride")
   private SkinOverride field29;
   @SerializedName("renderNametag")
   private boolean renderNametag;
   @SerializedName("itemType")
   private LunarItemType field30;
   @SerializedName("itemMaterial")
   private LunarItemMaterial field31;
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

   public EmoteHologram() {
   }

   @Override
   public void init() {
      if (!this.initialized) {
         this.uuid = UUID.randomUUID();
         this.field35 = new ArrayList<>();
         Ref.method4().method55().method10(this.uuid, this.field35);
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
         HologramsIterator2.method17(this.uuid, arg1x -> Ref.method4().method45().method7(arg1x, this.field36, this.field24, null, 0));
      }

      if (Ref.method3().bridge$getSession() != null) {
         UUID uuid1 = Ref.method3().bridge$getSession().bridge$getProfile().getId();
         Data data2 = (Data)Ref.method4().method53().method63().get(uuid1);
         Ref.method4().method53().method63().put(this.uuid, data2);
      }

      OutfitManager foghandler263 = Ref.method4().method55();
      if (foghandler263.method17() != null) {
         Outfit gui2iterator4 = foghandler263.method4(this.uuid);
         if (gui2iterator4 != null && foghandler263.method17().method5().method9() != gui2iterator4.method9()) {
            gui2iterator4.method13(foghandler263.method17().method5().method9());
         }
      }

      super.init();
   }

   @Override
   public void remove() {
      if (this.field36 != null) {
         HologramsIterator2.method17(this.uuid, arg0 -> Ref.method4().method45().method11(arg0, true, false));
      }

      this.field38 = false;
      Ref.method4().method53().removePlayer(this.uuid);
      Ref.method4().method55().removePlayer(this.uuid);
      Ref.method4().method45().IORHHHROCRRHORHRCHCCHHIHICCRCO().remove(this.uuid);
      EmoteController.getCache().remove(this.uuid);
      Ref.method4().method70().method12().remove(this.uuid);
      super.remove();
   }

   public void method1(EmoteHologram holograms2iterator1) {
      this.init();
      super.method8(holograms2iterator1);
      this.field22 = holograms2iterator1.field22;
      this.display = holograms2iterator1.display;
      this.field26 = holograms2iterator1.field26;
      this.field27 = holograms2iterator1.field27;
      this.field25 = holograms2iterator1.field25;
      this.field29 = holograms2iterator1.field29;
      this.field28 = holograms2iterator1.field28;
      this.field32 = holograms2iterator1.field32;
      this.field33 = holograms2iterator1.field33;
      this.field34 = holograms2iterator1.field34;
      if (this.field30 != holograms2iterator1.field30 || this.field31 != holograms2iterator1.field31) {
         this.field31 = holograms2iterator1.field31;
         this.field30 = holograms2iterator1.field30;
      }

      if (this.field23 != holograms2iterator1.field23) {
         this.field23 = holograms2iterator1.field23;
         this.field24 = holograms2iterator1.field24;
         if (holograms2iterator1.field23 == 0) {
            HologramsIterator2.method17(this.uuid, arg0 -> Ref.method4().method45().method11(arg0, true, false));
            this.field38 = false;
         } else {
            this.method5();
         }
      }

      this.method6();
      this.method4();
   }

   private void method4() {
      ItemStackBridge bridgeextension_41 = null;
      ItemStackBridge bridgeextension_42 = null;
      boolean flag3 = false;
      boolean flag4 = false;
      if (Ref.MC_VERSION >= 5) {
         for (CosmeticMetadata gui2handler36 : this.field35) {
            if (gui2handler36.method4().method10() == CosmeticCategoryType.SHIELDS) {
               flag3 = true;
            }

            if (gui2handler36.method4().method10() == CosmeticCategoryType.HAND) {
               flag4 = true;
            }

            if (flag3 && flag4) {
               break;
            }
         }
      }

      if (flag3) {
         ItemBridge bridge6_48 = Bridge.method28().method22("shield");
         if (bridge6_48 != null) {
            bridgeextension_42 = Bridge.method8().method38(bridge6_48);
         }
      }

      if (this.field30 != null && this.field31 != null) {
         String text9 = switch (this.field31) {
            case WOOD -> "wooden";
            case GOLD -> "golden";
            default -> this.field31.getMaterial();
         };
         String text10 = text9 + "_" + this.field30.getId();
         ItemBridge bridge6_47 = Bridge.method28().method22(text10);
         if (bridge6_47 != null) {
            bridgeextension_41 = Bridge.method8().method38(bridge6_47);
         }
      }

      if (bridgeextension_41 == null && bridgeextension_42 == null) {
         this.field40 = this.method8();
         this.field41 = this.method8();
      } else {
         if (bridgeextension_41 == null && !flag4) {
            bridgeextension_41 = bridgeextension_42;
            bridgeextension_42 = null;
         }

         this.field40 = bridgeextension_41 == null ? this.method8() : bridgeextension_41;
         this.field41 = bridgeextension_42 == null ? this.method8() : bridgeextension_42;
      }
   }

   private void method5() {
      this.field36 = Ref.method4().method45().method13(this.field23);
      if (this.field36 != null) {
         HologramsIterator2.method17(this.uuid, arg1 -> {
            Ref.method4().method45().method7(arg1, this.field36, this.field24, null, 0);
            if (this.field26) {
               EmoteAPI.setEmoteProgress(arg1, 0.5F);
            }
         });
         this.field38 = !this.field26;
      }
   }

   private void method6() {
      for (HologramEntry holograms4 : this.field25) {
         CosmeticMetadata gui2handler35 = this.field35
            .stream()
            .filter(arg1 -> arg1.method4().method9() == holograms4.id())
            .findFirst()
            .orElse(Ref.method4().method53().method48(holograms4.id(), holograms4.method1()));
         if (gui2handler35 != null) {
            gui2handler35.method4().method23(this.field27);
            this.field35.stream().filter(arg1 -> arg1.method4().method9() == holograms4.id()).findFirst().ifPresentOrElse(arg1 -> {
               JsonObject json2 = holograms4.method1() == null ? new JsonObject() : holograms4.method1();
               arg1.method6(json2);
               arg1.method6().method1(json2);
            }, () -> this.field35.add(gui2handler35));
         }
      }

      this.field35.removeIf(arg1 -> Arrays.stream(this.field25).noneMatch(arg1x -> arg1x.id() == arg1.method4().method9()));
      Ref.method4().method88().method11(this.uuid);
   }

   private ItemStackBridge method8() {
      return Ref.MC_VERSION >= 5 ? Bridge.method8().method41() : null;
   }

   @Override
   public boolean method9(AbstractRenderContext bridgeextension_91) {
      return this.field26 && !this.field38 && !this.field39 ? false : super.method9(bridgeextension_91);
   }

   @Override
   public int method11() {
      return this.field27 ? 60 : super.method11();
   }

   @Override
   public void method10(AbstractRenderContext bridgeextension_91) {
      this.field39 = false;
      super.method10(bridgeextension_91);
   }

   @Override
   public void tick() {
      if (!this.field26 || this.field38 || this.field20 <= 1) {
         this.field39 = this.field26;
         HologramsIterator2.method17(this.uuid, arg1 -> {
            LunarEventBus.method29().method12(EventHologramUpdate.class, () -> new EventHologramUpdate(arg1));

            for (MorphRenderer holograms83 : Ref.method4().method70().method3(this.uuid)) {
               holograms83.method3();
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
   public HologramSkin method10() {
      return this.field22;
   }

   @Generated
   public HologramCameraPreset getDisplay() {
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
   public HologramEntry[] method22() {
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
   public SkinOverride method33() {
      return this.field29;
   }

   @Generated
   public boolean isRenderNametag() {
      return this.renderNametag;
   }

   @Generated
   public LunarItemType method34() {
      return this.field30;
   }

   @Generated
   public LunarItemMaterial method35() {
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
   public void method28(boolean flag1) {
      this.field38 = flag1;
   }
}
