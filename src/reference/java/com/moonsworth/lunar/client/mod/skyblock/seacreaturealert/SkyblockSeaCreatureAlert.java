package com.moonsworth.lunar.client.mod.skyblock.seacreaturealert;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.config.option.NamedColorOption;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.framework.feature.mod.impl.fishing.mixin.FishingType;
import com.moonsworth.lunar.client.event.entity.EventEntityRemove;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockSeaCreatureAlert extends AbstractFeature {
   private final AlertDisplayListener field8 = (AlertDisplayListener)this.method63(AlertDisplayListener.class);
   private static final Pattern field9 = Pattern.compile("^(?:﴾ )?\\[Lv\\d{1,3}] (?<mobTypes>[^ ]+ )?(?<name>[\\w ]+) [\\dkM,.]+/[\\dkM,.]+❤(?: ﴿)?$");
   public static final ResourceLocationBridge field10 = ResourceLocationBridge.create("entity.experience_orb.pickup");
   private final MultiSelectOption field11 = (MultiSelectOption)((com.moonsworth.lunar.client.config.option.MultiSelectOption.Data)OptionFactory.method27(
            "skyBlockSeaCreatureAlerts"
         )
         .method2(FishingType.names()))
      .method3(FishingType.names())
      .method31();
   private final EnumOption<NamedColorOption> field12 = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "skyblockSeaCreatureAlertColor", NamedColorOption.GREEN
      )
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockSeaCreatureAlertSound")
         .method4(true))
      .method31();
   private final HashMap<String, SkyblockSeaCreatureAlert.Data> field14 = new HashMap<>();

   public SkyblockSeaCreatureAlert(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.FISHING));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventTick.class, this::method1);
      this.handle(EventEntitySpawn.class, this::method2);
      this.handle(EventEntityRemove.class, this::method3);
      this.handle(EventWorldChange.class, this::method4);
   }

   private void method1(EventTick highlightimpl21) {
      if (IslandUtils.isOnIsland()) {
         Bridge5Extension_5 bridge5extension_52 = Ref.method7();
         if (bridge5extension_52 != null) {
            for (SkyblockSeaCreatureAlert.Data data4 : this.field14.values()) {
               if (!data4.method2() && !(bridge5extension_52.method2(data4.method1()) > 64.0)) {
                  data4.method3(true);
                  this.field8
                     .method2(ComparableImpl.method2().method1("SEA_CREATURE").method2(Component.text(data4.getName(), TextComponentFactory.styleOf(this.field12))).method6());
                  if ((Boolean)this.field13.get()) {
                     this.mc.bridge$getSoundHandler().method1(field10);
                  }
               }
            }
         }
      }
   }

   private void method2(EventEntitySpawn highlightimpl6_21) {
      if (IslandUtils.isOnIsland()) {
         BridgeExtension bridgeextension2 = highlightimpl6_21.field1;
         if (bridgeextension2 instanceof EntityArmorStandBridge) {
            String text3 = bridgeextension2.bridge$getUniqueID().toString();
            if (!this.field14.containsKey(text3)) {
               Component component4 = bridgeextension2.bridge$getCustomName();
               if (component4 != null) {
                  String text5 = TextBridge.getTextContent(component4);
                  Matcher matcher6 = field9.matcher(text5);
                  if (matcher6.matches()) {
                     String text7 = matcher6.group("name");
                     if (this.field11.contains(text7)) {
                        this.field14.put(text3, new SkyblockSeaCreatureAlert.Data(bridgeextension2, text7));
                     }
                  }
               }
            }
         }
      }
   }

   private void method3(EventEntityRemove highlightimpl121) {
      if (IslandUtils.isOnIsland()) {
         BridgeExtension bridgeextension2 = highlightimpl121.method1();
         if (bridgeextension2 instanceof EntityArmorStandBridge) {
            this.field14.remove(bridgeextension2.bridge$getUniqueID().toString());
         }
      }
   }

   private void method4(EventWorldChange data31) {
      this.field14.clear();
   }

   public String getId() {
      return "SKYBLOCK_SEA_CREATURE_ALERT";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field11, this.field12, this.field13});
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   private static class Data {
      private final BridgeExtension field1;
      private final String field2;
      private boolean field3;

      @Generated
      public BridgeExtension method1() {
         return this.field1;
      }

      @Generated
      public String getName() {
         return this.field2;
      }

      @Generated
      public boolean method2() {
         return this.field3;
      }

      @Generated
      public Data(BridgeExtension bridgeextension1, String text2) {
         this.field1 = bridgeextension1;
         this.field2 = text2;
      }

      @Generated
      public void method3(boolean flag1) {
         this.field3 = flag1;
      }
   }
}
