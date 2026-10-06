package com.moonsworth.lunar.client.mod.skyblock.smoothteleport;

import com.google.common.collect.Sets;
import com.moonsworth.lunar.bridge.NBTTagListBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.bridge.DataComponentTypes;
import com.moonsworth.lunar.bridge.CompoundTagComponent;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.AnimatedValue;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.AnimatedValue.Type;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.event.player.EventItemUse;
import com.moonsworth.lunar.client.event.mixin.gui.EventTeleportBase.EventTeleportPost;
import com.moonsworth.lunar.client.event.mixin.highlight.EventCameraOffset;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Set;
import org.joml.Vector3d;

public class SkyblockSmoothTeleport extends AbstractFeature {
   private static final Set<String> field8 = Sets.newHashSet(
      new String[]{"ASPECT_OF_THE_END", "ASPECT_OF_THE_VOID", "ASPECT_OF_THE_LEECH1", "ASPECT_OF_THE_LEECH2", "ASPECT_OF_THE_LEECH3"}
   );
   private static final Set<String> field9 = Sets.newHashSet(new String[]{"NECRON_BLADE", "HYPERION", "VALKYRIE", "ASTRAEA", "SCYLLA"});
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("smoothAote").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("smoothHyperion").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("smoothEtherwarp").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption field13 = (FloatOption)((Data)((Data)OptionFactory.method2("animationTimeMs").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(100.0F))
         .method8(0.0F, 500.0F))
      .method31();
   private final Vector3d field14 = new Vector3d();
   private final AnimatedValue field15 = new AnimatedValue(1.0, Type.SIN_OUT);
   private final Vector3d field16 = new Vector3d();
   private final Vector3d field17 = new Vector3d();
   private int field18 = 0;
   private long field19 = 0L;

   public SkyblockSmoothTeleport(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.ITEMS));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventItemUse.class, this::method1);
      this.handle(EventTeleportPost.class, this::method2);
      this.handle(EventCameraOffset.class, this::method3);
   }

   private void method1(EventItemUse highlightimpl181) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         ItemStackBridge bridgeextension_43 = highlightimpl181.method2();
         String text4 = SkyblockItemUtil.method2(bridgeextension_43);
         if (!text4.isEmpty()) {
            boolean flag5 = bridge5extension_52.bridge$isSneaking();
            CompoundTagComponent mixinhelper_106 = (CompoundTagComponent)bridgeextension_43.bridge$getDataComponent(DataComponentTypes.field1);
            boolean flag7 = SkyblockItemUtil.method20(mixinhelper_106);
            boolean flag8 = field8.contains(text4);
            boolean flag9 = method5(bridgeextension_43, text4);
            if (!flag5 || !flag7 || (Boolean)this.field12.get()) {
               if (flag5 && flag7 || !flag8 || (Boolean)this.field10.get()) {
                  if (!flag9 || (Boolean)this.field11.get()) {
                     if (flag8 || flag9 || flag7 && flag5) {
                        this.field18++;
                        this.field19 = Ref.method3().bridge$getSystemTime();
                     }
                  }
               }
            }
         }
      }
   }

   private void method2(EventTeleportPost data161) {
      if (this.field18 > 0) {
         if (Ref.method3().bridge$getSystemTime() - this.field19 > 1000L) {
            this.field18 = 0;
         } else {
            this.field18--;
            this.field15.animate(0.0, 1.0, (long)((Float)this.field13.get()).floatValue());
            this.field17.set(this.field16);
         }
      }
   }

   private void method3(EventCameraOffset highlightimpl41) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         this.field16
            .set(bridge5extension_52.method3(), bridge5extension_52.method4(), bridge5extension_52.method5())
            .sub(this.field14);
         float value3 = highlightimpl41.method2();
         double value4 = bridge5extension_52.method8(value3);
         double value6 = bridge5extension_52.method9(value3);
         double value8 = bridge5extension_52.method10(value3);
         double value10 = this.field15.getValue();
         double value12 = value4 - (value4 * value10 + this.field17.x * (1.0 - value10));
         double value14 = value6 - (value6 * value10 + this.field17.y * (1.0 - value10));
         double value16 = value8 - (value8 * value10 + this.field17.z * (1.0 - value10));
         this.field14.set(value12, value14, value16);
         highlightimpl41.method1((float)value12, (float)value14, (float)value16);
      }
   }

   public void method3(boolean flag1) {
      if (!flag1) {
         this.field14.set(0.0, 0.0, 0.0);
         this.field18 = 0;
      }
   }

   private static boolean method5(ItemStackBridge bridgeextension_40, String text1) {
      if (!field9.contains(text1)) {
         return false;
      } else {
         CompoundTagBridge bridge_572 = SkyblockItemUtil.method1(bridgeextension_40);
         if (bridge_572 == null) {
            return false;
         } else {
            NBTTagListBridge bridge3_63 = bridge_572.bridge$getList("ability_scroll", 8);
            if (bridge3_63 != null && bridge3_63.bridge$size() > 2) {
               String text4 = bridge3_63.bridge$getString(2);
               return text4 != null && !text4.isEmpty();
            } else {
               return false;
            }
         }
      }
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_SMOOTH_TELEPORT";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.method9(new ClientOption[]{this.field13, this.field10, this.field11, this.field12})
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method1(new ModCategory[]{ModCategory.field5})
         .method2(new String[]{"Smooth AOTE", "Smooth AOTV", "Smooth Hyperion"})
         .method11(this);
   }
}
