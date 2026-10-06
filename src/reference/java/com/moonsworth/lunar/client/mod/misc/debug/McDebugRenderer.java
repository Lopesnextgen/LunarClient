package com.moonsworth.lunar.client.mod.misc.debug;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.ConfigRangeBuilder;
import java.util.function.BooleanSupplier;
import lombok.Generated;

public class McDebugRenderer extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)OptionFactory.method7("pathfinding").method31();
   private final ToggleOption field9 = (ToggleOption)OptionFactory.method7("waterDebug").method31();
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("heightMap").method31();
   private final ToggleOption field11 = (ToggleOption)OptionFactory.method7("collisionBox").method31();
   private final ToggleOption field12 = (ToggleOption)OptionFactory.method7("neighborUpdates").method31();
   private final ToggleOption field13 = (ToggleOption)OptionFactory.method7("structure").method31();
   private final ToggleOption field14 = (ToggleOption)OptionFactory.method7("light").method31();
   private final ToggleOption field15 = (ToggleOption)OptionFactory.method7("worldGen").method31();
   private final ToggleOption field16 = (ToggleOption)OptionFactory.method7("solidFace").method31();
   private final ToggleOption field17 = (ToggleOption)OptionFactory.method7("chunk").method31();
   private final ToggleOption field18 = (ToggleOption)OptionFactory.method7("brain").method31();
   private final ToggleOption field19 = (ToggleOption)OptionFactory.method7("villageSections").method31();
   private final ToggleOption field20 = (ToggleOption)OptionFactory.method7("bee").method31();
   private final ToggleOption field21 = (ToggleOption)OptionFactory.method7("raid").method31();
   private final ToggleOption field22 = (ToggleOption)OptionFactory.method7("goal").method31();
   private final ToggleOption field23 = (ToggleOption)OptionFactory.method7("gameEvent").method31();

   public McDebugRenderer(boolean flag1) {
      super(flag1);
      this.method2(ModTraits.field18, ConfigRangeBuilder::method13);
   }

   public String getId() {
      return "MC_DEBUG_RENDERER";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      BooleanSupplier booleansupplier2 = () -> this.mc.bridge$getCurrentServerData() != null;
      ((SettingsSectionImpl)lightingextension231.method9(
            new ClientOption[]{
               this.field8, this.field12, this.field15, this.field18, this.field19, this.field20, this.field21, this.field22, this.field23
            }
         ))
         .method2(booleansupplier2);
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field13}))
         .method2(booleansupplier2)
         .method6(7);
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field23}))
         .method2(booleansupplier2)
         .method6(8);
      lightingextension231.method9(new ClientOption[]{this.field9, this.field10, this.field11, this.field14, this.field16, this.field17});
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field6, ModCategory.field7}).method11(this);
   }

   @Generated
   public ToggleOption method13() {
      return this.field8;
   }

   @Generated
   public ToggleOption method14() {
      return this.field9;
   }

   @Generated
   public ToggleOption method15() {
      return this.field10;
   }

   @Generated
   public ToggleOption method16() {
      return this.field11;
   }

   @Generated
   public ToggleOption method17() {
      return this.field12;
   }

   @Generated
   public ToggleOption method19() {
      return this.field13;
   }

   @Generated
   public ToggleOption method21() {
      return this.field14;
   }

   @Generated
   public ToggleOption method22() {
      return this.field15;
   }

   @Generated
   public ToggleOption method23() {
      return this.field16;
   }

   @Generated
   public ToggleOption method24() {
      return this.field17;
   }

   @Generated
   public ToggleOption method25() {
      return this.field18;
   }

   @Generated
   public ToggleOption method26() {
      return this.field19;
   }

   @Generated
   public ToggleOption method27() {
      return this.field20;
   }

   @Generated
   public ToggleOption method28() {
      return this.field21;
   }

   @Generated
   public ToggleOption method29() {
      return this.field22;
   }

   @Generated
   public ToggleOption method30() {
      return this.field23;
   }
}
