package com.moonsworth.lunar.client.mod.skyblock.hidechumbuckets;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntityBase.EventRenderEntity;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.joml.Vector3d;

public class SkyblockHideChumBuckets extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^\\w+'s Chum Bucket$");
   private static final Pattern field9 = Pattern.compile("^\\w+'s Chumcap Bucket$");
   private static final Pattern field10 = Pattern.compile("^(\\d{1,2})/10 Chums( FULL!)?$");
   private static final int field11 = 7;
   private final ToggleOption field12 = (ToggleOption)OptionFactory.method7("showChumBucket").method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showBucketWhenLooking").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final Cache<BridgeExtension, SkyblockHideChumBuckets.Data> field14 = CacheBuilder.newBuilder()
      .weakKeys()
      .expireAfterWrite(30L, TimeUnit.SECONDS)
      .build();

   public SkyblockHideChumBuckets(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.FISHING));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventRenderEntity.class, this::method1);
   }

   private void method1(EventRenderEntity data81) {
      BridgeExtension bridgeextension2 = data81.method1();
      if (!this.method4(bridgeextension2)) {
         data81.cancel();
      }
   }

   private boolean method2(BridgeExtension bridgeextension1) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      double value3 = Math.sqrt(bridge5extension_52.method2(bridgeextension1) * 7.0);
      Vector3d vector3d5 = new Vector3d(
         bridgeextension1.bridge$getPosX() - bridge5extension_52.bridge$getPosX(), bridgeextension1.bridge$getPosY() - bridge5extension_52.bridge$getPosY(), bridgeextension1.bridge$getPosZ() - bridge5extension_52.bridge$getPosZ()
      );
      double value6 = bridge5extension_52.bridge$getLookAngle().angle(vector3d5);
      return Math.abs(value6) < Math.PI / value3;
   }

   private boolean method3(BridgeExtension bridgeextension1) {
      if (!(bridgeextension1 instanceof EntityArmorStandBridge bridgeextension_22)) {
         return false;
      } else {
         ItemStackBridge bridgeextension_43 = bridgeextension_22.bridge$getHelmet();
         if (bridgeextension_43 != null && !bridgeextension_43.bridge$isEmpty()) {
            String text4 = bridgeextension_43.bridge$getDisplayName();
            return text4.contains("Chum Bucket") || text4.contains("Chumcap Bucket");
         } else {
            return false;
         }
      }
   }

   private boolean method4(BridgeExtension bridgeextension1) {
      if (bridgeextension1 == null) {
         return true;
      } else {
         SkyblockHideChumBuckets.Data data2 = (SkyblockHideChumBuckets.Data)this.field14.getIfPresent(bridgeextension1);
         if (data2 != null) {
            BridgeExtension bridgeextension12 = data2.method3();
            bridgeextension12.bridge$stopTurbo(null);
            return this.field12.get() && bridgeextension1 == bridgeextension12 ? true : (Boolean)this.field13.get() && this.method2(bridgeextension12);
         } else if (!(bridgeextension1 instanceof EntityArmorStandBridge bridgeextension_23)) {
            return true;
         } else {
            String text4 = TextBridge.getTextContent(bridgeextension_23.bridge$getCustomName());
            if (text4 == null) {
               return true;
            }

            if (!field8.matcher(text4).matches() && !field9.matcher(text4).matches()) {
               return true;
            }

            BridgeExtension bridgeextension5 = null;
            BridgeExtension bridgeextension6 = null;
            AxisAlignedBBBridge horsestats127 = AxisAlignedBBBridge.method2(
               bridgeextension1.bridge$getPosX() - 0.1,
               bridgeextension1.bridge$getPosY() - 1.0,
               bridgeextension1.bridge$getPosZ() - 0.1,
               bridgeextension1.bridge$getPosX() + 0.1,
               bridgeextension1.bridge$getPosY() + 1.0,
               bridgeextension1.bridge$getPosZ() + 0.1
            );

            for (BridgeExtension bridgeextension10 : Ref.method8().bridge$getEntities(horsestats127, arg0 -> true)) {
               if (bridgeextension10 instanceof EntityArmorStandBridge) {
                  String text11 = TextBridge.getTextContent(bridgeextension10.bridge$getCustomName());
                  if (text11 != null && field10.matcher(text11).matches()) {
                     bridgeextension5 = bridgeextension10;
                  } else if (text11 != null && this.method3(bridgeextension10)) {
                     bridgeextension6 = bridgeextension10;
                  }
               }
            }

            if (bridgeextension5 != null && bridgeextension6 != null) {
               SkyblockHideChumBuckets.Data data13 = new SkyblockHideChumBuckets.Data(bridgeextension1, bridgeextension5, bridgeextension6);
               this.field14.put(bridgeextension1, data13);
               this.field14.put(bridgeextension5, data13);
               this.field14.put(bridgeextension6, data13);
            }

            return false;
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_HIDE_CHUM_BUCKETS";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field12, this.field13})
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   private class Data {
      private final BridgeExtension field1;
      private final BridgeExtension field2;
      private final BridgeExtension field3;

      private Data(BridgeExtension bridgeextension1, BridgeExtension bridgeextension2, BridgeExtension bridgeextension3) {
         this.field1 = bridgeextension1;
         this.field2 = bridgeextension2;
         this.field3 = bridgeextension3;
      }

      public BridgeExtension method1() {
         return this.field1;
      }

      public BridgeExtension method2() {
         return this.field2;
      }

      public BridgeExtension method3() {
         return this.field3;
      }
   }
}
