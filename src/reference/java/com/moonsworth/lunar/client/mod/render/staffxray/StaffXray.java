package com.moonsworth.lunar.client.mod.render.staffxray;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.BlocksBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.StaffXrayState;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerChange;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Generated;

public class StaffXray extends AbstractFeature {
   private final Set<Bridge3_23> field8;
   private final MultiSelectOption field9;
   private final ModifierKeybindOption field10 = (ModifierKeybindOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method18("xrayToggle")
         .method5(KeyCode.KEY_X)
         .method18(this))
      .method11()
      .method31();
   private final IntegerOption field11 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "opacity"
            )
            .method4(120))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 255))
      .method31();
   private boolean field12;

   public StaffXray() {
      super(false);
      Set set1 = this.mc.bridge$xrayBlocks();
      this.field8 = new LinkedHashSet<>(set1);
      LinkedHashSet set2 = new LinkedHashSet(set1.size());

      for (Bridge3_23 bridge3_234 : set1) {
         set2.add(bridge3_234.bridge$getRegistryName());
      }

      ArrayList list7 = new ArrayList();
      list7.addAll(set2);

      for (Bridge3_23 bridge3_235 : Bridge.method34().method113()) {
         String text6 = bridge3_235.bridge$getRegistryName();
         if (!set2.contains(text6)) {
            list7.add(text6);
         }
      }

      this.field9 = (MultiSelectOption)((com.moonsworth.lunar.client.config.option.MultiSelectOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method27(
               "xrayBlockList"
            )
            .method3(list7)
            .method2(set2))
         .method5(arg1x -> this.method13())
         .method31();
      this.field12 = false;
      this.method2(ModTraits.field2, StaffXrayState.field1);
      LunarEventBus.method29().method2(EventServerChange.class, arg1x -> {
         this.method8(false);
         if (Ref.method8() != null && !arg1x.method1()) {
            this.mc.bridge$getLevelRenderer().bridge$reloadChunks();
         }

         ((StaffXrayState)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field2)).method2(false);
      });
   }

   public String getId() {
      return "STAFF_XRAY";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field10});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field11})).method6(0);
      lightingextension231.method9(new ClientOption[]{this.field9});
      this.field10.method3(() -> {
         if (((StaffXrayState)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field2)).method1()) {
            this.field12 = !this.field12;
            this.mc.bridge$getLevelRenderer().bridge$reloadChunks();
         }
      });
      this.field9.CICORRHIOIIOORRRICCORIOIOCIHII(arg1x -> this.method13());
   }

   private void method13() {
      this.field8.clear();
      BlocksBridge bridge_561 = Bridge.method34();

      for (String text3 : (Set)this.field9.get()) {
         Bridge3_23 bridge3_234 = bridge_561.method112(text3);
         if (bridge3_234 != null) {
            this.field8.add(bridge3_234);
         }
      }

      if (Ref.method8() != null) {
         this.mc.bridge$getLevelRenderer().bridge$reloadChunks();
      }
   }

   public void method3(boolean flag1) {
      if (!flag1) {
         this.method8(false);
         if (Ref.method8() != null) {
            this.mc.bridge$getLevelRenderer().bridge$reloadChunks();
         }
      }

      if (!((StaffXrayState)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field2)).method1()) {
         ((ModEnabledState)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field6)).setEnabled(false);
      }
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method8().method11(this);
   }

   @Generated
   public Set<Bridge3_23> method14() {
      return this.field8;
   }

   @Generated
   public IntegerOption method15() {
      return this.field11;
   }

   @Generated
   public boolean method16() {
      return this.field12;
   }

   @Generated
   public void method8(boolean flag1) {
      this.field12 = flag1;
   }
}
