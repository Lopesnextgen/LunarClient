package com.moonsworth.lunar.client.mod.render.heightlimit;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.heightlimit.HeightLimitVariant;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import lombok.Generated;

public class HeightLimitProfile extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showOverlay")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<HeightLimit.Type2> field9 = (EnumOption<HeightLimit.Type2>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "overlayMode", HeightLimit.Type2.DARKEN
      )
      .method31();
   private final IntegerOption field10 = (IntegerOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4("gradientHeight")
         .OCRRICRIORICCCRHIOHORCICIHHICO(0, 16))
      .method31();
   private final ColorOption field11 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "darkenColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1342177280))
      .method15()
      .method31();
   private final ColorOption field12 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "barrierColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
      .method15()
      .method31();
   private final ToggleOption field13 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("topFaceOnly")
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showHud")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<HeightLimit.Type3> field15 = (EnumOption<HeightLimit.Type3>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "style", HeightLimit.Type3.CLASSIC
      )
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showTitle")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("mapName")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("heightLimit")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("currentHeight")
      .method31();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("distanceToHeightLimit")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final HeightLimitVariant field21;
   private final HeightLimit field22;

   HeightLimitProfile(HeightLimit heightlimit1, boolean flag2, HeightLimitVariant heightlimittype3) {
      super(flag2);
      this.field22 = heightlimit1;
      this.field21 = heightlimittype3;
      this.method24(ModTraits.field16, ChildModBinding.method3(heightlimit1));
   }

   public String getId() {
      throw new IllegalStateException("HeightLimitProfile must be created using HeightLimitProfile#create!");
   }

   public static HeightLimitProfile method1(HeightLimit heightlimit0, boolean flag1, HeightLimitVariant heightlimittype2) {
      final String text3 = heightlimittype2.getFeatureId();
      return new HeightLimitProfile(heightlimit0, flag1, heightlimittype2) {
         @Override
         public String getId() {
            return text3;
         }
      };
   }

   public void method3(boolean flag1) {
      if (this.field21 == HeightLimitVariant.BEDWARS || this.field21 == HeightLimitVariant.BRIDGE) {
         this.field22.method14();
      }

      this.field22.method46().markDirty();
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method1(
         "blockOverlayOptions",
         arg1x -> arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
            this.field8,
            arg1xx -> {
               arg1xx.method9(new ClientOption[]{this.field9});
               arg1xx.method9(new ClientOption[]{this.field10, this.field11})
                  .method3(() -> this.field9.get() == HeightLimit.Type2.BARRIER);
               arg1xx.method9(new ClientOption[]{this.field12}).method3(() -> this.field9.get() == HeightLimit.Type2.DARKEN);
               arg1xx.method9(new ClientOption[]{this.field13});
            }
         )
      );
      lightingextension231.method1("hudDisplayOptions", arg1x -> arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field14, arg1xx -> {
         arg1xx.method9(new ClientOption[]{this.field15});
         arg1xx.method9(new ClientOption[]{this.field16}).method3(() -> this.field15.get() == HeightLimit.Type3.COMPACT);
         if (this.field21 != HeightLimitVariant.VANILLA) {
            arg1xx.method9(new ClientOption[]{this.field17});
         }

         arg1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field18, arg1xxx -> arg1xxx.method9(new ClientOption[]{this.field19}));
         arg1xx.method9(new ClientOption[]{this.field20});
      }));
      this.field8.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.field22.method46().markDirty());
      this.field9.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.field22.method46().markDirty());
      this.field10.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.field22.method46().markDirty());
      this.field13.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.field22.method46().markDirty());
   }

   public boolean method13() {
      return ((ModEnabledState)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field6)).isEnabled();
   }

   public int method14() {
      return this.field9.get() != HeightLimit.Type2.BARRIER ? (Integer)this.field10.get() : 0;
   }

   public int[] method6(int index1) {
      int[] items2 = new int[index1 + 1];
      int number3 = this.field11.method13();
      int number4 = number3 >>> 24 & 0xFF;

      for (int index5 = 0; index5 <= index1; index5++) {
         float value6 = index1 <= 0 ? 1.0F : 1.0F - index5 / (index1 + 1.0F);
         items2[index5] = Math.round(number4 * value6) << 24 | number3 & 16777215;
      }

      return items2;
   }

   @Generated
   public ToggleOption method15() {
      return this.field8;
   }

   @Generated
   public EnumOption<HeightLimit.Type2> method16() {
      return this.field9;
   }

   @Generated
   public IntegerOption method17() {
      return this.field10;
   }

   @Generated
   public ColorOption method19() {
      return this.field11;
   }

   @Generated
   public ColorOption method21() {
      return this.field12;
   }

   @Generated
   public ToggleOption method22() {
      return this.field13;
   }

   @Generated
   public ToggleOption method23() {
      return this.field14;
   }

   @Generated
   public EnumOption<HeightLimit.Type3> method24() {
      return this.field15;
   }

   @Generated
   public ToggleOption method25() {
      return this.field16;
   }

   @Generated
   public ToggleOption method26() {
      return this.field17;
   }

   @Generated
   public ToggleOption method27() {
      return this.field18;
   }

   @Generated
   public ToggleOption method28() {
      return this.field19;
   }

   @Generated
   public ToggleOption method29() {
      return this.field20;
   }

   @Generated
   public HeightLimitVariant method30() {
      return this.field21;
   }

   @Generated
   public HeightLimit method34() {
      return this.field22;
   }
}
