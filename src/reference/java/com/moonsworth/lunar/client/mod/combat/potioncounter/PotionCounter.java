package com.moonsworth.lunar.client.mod.combat.potioncounter;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemPotionBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.InventoryPlayerBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.potioncounter.PotionCounterType;
import com.moonsworth.lunar.client.event.player.EventInventoryUpdate;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;
import org.jetbrains.annotations.Nullable;

public class PotionCounter extends AbstractFeature {
   private static final int field8 = 6;
   private final EnumOption<PotionCounterType> field9 = (EnumOption<PotionCounterType>)OptionFactory.method10("potionCounter", PotionCounterType.POTION)
      .method31();
   private int field10;
   private boolean field11 = true;

   public PotionCounter() {
      super(false);
      this.method9(ModTraits.field1, new PotionCounter.Data());
      this.handle(EventInventoryUpdate.class, arg1 -> this.field11 = true);
      this.handle(EventTick.class, arg1 -> {
         if (this.field11) {
            this.field10 = this.count();
            this.field11 = false;
         }
      });
      this.field9.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1 -> this.field11 = true);
   }

   public String getId() {
      return "POTION_COUNTER";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field3}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field9}));
   }

   private int count() {
      if (Ref.method7() == null) {
         return 0;
      }

      InventoryPlayerBridge bridge_241 = Ref.method7().bridge$getInventory();
      Stream stream2 = Stream.of(bridge_241.bridge$getMainInventory(), bridge_241.bridge$getOffhandInventory()).flatMap(Collection::stream).filter(Objects::nonNull);
      return this.field9.get() == PotionCounterType.SOUP
         ? stream2.filter(arg0 -> arg0.bridge$getItem().bridge$isMushroomStew()).mapToInt(ItemStackBridge::bridge$getStackSize).sum()
         : stream2.filter(arg0 -> arg0.bridge$getItem() != null && arg0.bridge$getItem().bridge$isItemPotion())
            .filter(arg0 -> Bridge.method36().method14(arg0))
            .filter(arg0 -> ((ItemPotionBridge)arg0.bridge$getItem()).bridge$getEffects(arg0).stream().anyMatch(arg0x -> arg0x.bridge$getPotionID() == 6))
            .mapToInt(ItemStackBridge::bridge$getStackSize)
            .sum();
   }

   private class Data extends TypedHudRenderer<String> {
      public Data() {
         super(0.0F, 62.0F, HudAnchor.TOP_CENTER);
      }

      public HudSize method15() {
         return HudSize.method1(10, 18, 22, 40, 56, 62);
      }

      @Nullable
      public String method2(boolean flag1) {
         int number2 = Ref.method7() == null ? 0 : PotionCounter.this.field10;
         String text3 = PotionCounter.this.field9.get() == PotionCounterType.SOUP ? "soup" : "pot";
         String text4 = number2 == 1 ? text3 : text3 + "s";
         return number2 + " " + text4;
      }
   }
}
