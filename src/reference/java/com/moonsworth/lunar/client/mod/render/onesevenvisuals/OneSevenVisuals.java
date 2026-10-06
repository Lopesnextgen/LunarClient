package com.moonsworth.lunar.client.mod.render.onesevenvisuals;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.client.ui.widget.ResetDefaultsWidget;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class OneSevenVisuals extends AbstractFeature {
   private static final Set<String> LEGACY_CHILD_IDS = Set.of(
      "ONE_SEVEN_ITEMS_LEGACY", "ONE_SEVEN_ANIMATIONS_LEGACY", "ONE_SEVEN_ITEMS_MODERN", "ONE_SEVEN_ANIMATIONS_MODERN"
   );
   @Annotation2(1)
   private final OneSevenItemsLegacy itemsLegacy = new OneSevenItemsLegacy(this);
   @Annotation2(1)
   private final OneSevenAnimationsLegacy animationsLegacy = new OneSevenAnimationsLegacy(this);
   @Annotation2(min = 5)
   private final OneSevenItemsModern itemsModern = new OneSevenItemsModern(this);
   @Annotation2(min = 5)
   private final OneSevenAnimationsModern animationsModern = new OneSevenAnimationsModern(this);
   private final ToggleOption useItemWhileDigging = toggleOption("useItemWhileDigging");
   private final ToggleOption alwaysSwing = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "alwaysSwing"
         )
         .method4(true))
      .method31();

   public OneSevenVisuals() {
      super(true);
      this.method12(Framework.field18, var0 -> var0.method5(Config.field1));
   }

   @Override
   public String getId() {
      return "ONE_SEVEN_VISUALS";
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      ((SettingsSectionImpl)var1.method11(new ClientOption[]{this.useItemWhileDigging}))
         .method9(new Config[]{Config.field2});
      var1.method11(new ClientOption[]{this.alwaysSwing});
   }

   @Override
   protected boolean method24(String var1) {
      return field8.contains(var1);
   }

   @Override
   protected List<Framework7Extension> method9() {
      if (ThreadModuleDump63.MC_VERSION > 1) {
         return ImmutableList.of(this.animationsModern, this.itemsModern);
      } else {
         return (List<Framework7Extension>)(ThreadModuleDump63.MC_VERSION == 1 ? ImmutableList.of(this.animationsLegacy, this.itemsLegacy) : Collections.emptyList());
      }
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method2("Animations", "Old Animations Mod", "OAM").method1(Calculator2Handler.field3).method11(this);
   }

   public boolean isAlwaysSwingEnabled() {
      return this.isEnabled() && this.alwaysSwing.get();
   }

   @Annotation2(1)
   public boolean isUseItemWhileDiggingEnabled() {
      return this.isEnabled() && this.useItemWhileDigging.get();
   }

   @Annotation2(1)
   public OneSevenItemsLegacy getItemsLegacy() {
      return this.itemsLegacy;
   }

   @Annotation2(1)
   public OneSevenAnimationsLegacy getAnimationsLegacy() {
      return this.animationsLegacy;
   }

   @Annotation2(min = 5)
   public OneSevenItemsModern getItemsModern() {
      return this.itemsModern;
   }

   @Annotation2(min = 5)
   public OneSevenAnimationsModern getAnimationsModern() {
      return this.animationsModern;
   }

   public static ToggleOption toggleOption(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return (ToggleOption)((ToggleOption.ToggleOptionBuilder)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(var0)
               .method4(true))
            .method9(ResetDefaultsWidget::new))
         .method31();
   }
}
