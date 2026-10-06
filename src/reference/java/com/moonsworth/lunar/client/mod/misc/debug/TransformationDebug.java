package com.moonsworth.lunar.client.mod.misc.debug;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;

public class TransformationDebug extends AbstractFeature {
   public static float field8;
   public static float field9;
   public static float field10;
   public static float field11;
   public static float field12;
   public static float field13;
   private final FloatOption field14 = (FloatOption)((Data)((Data)OptionFactory.method2("xRotate").method4(0.0F))
         .method8(-360.0F, 360.0F))
      .method31();
   private final FloatOption field15 = (FloatOption)((Data)((Data)OptionFactory.method2("yRotate").method4(0.0F))
         .method8(-360.0F, 360.0F))
      .method31();
   private final FloatOption field16 = (FloatOption)((Data)((Data)OptionFactory.method2("zRotate").method4(0.0F))
         .method8(-360.0F, 360.0F))
      .method31();
   private final FloatOption field17 = (FloatOption)((Data)((Data)OptionFactory.method2("xTranslate").method4(0.0F))
         .method8(-100.0F, 100.0F))
      .method31();
   private final FloatOption field18 = (FloatOption)((Data)((Data)OptionFactory.method2("yTranslate").method4(0.0F))
         .method8(-100.0F, 100.0F))
      .method31();
   private final FloatOption field19 = (FloatOption)((Data)((Data)OptionFactory.method2("zTranslate").method4(0.0F))
         .method8(-100.0F, 100.0F))
      .method31();

   public TransformationDebug() {
      super(false);
   }

   public String getId() {
      return "TRANSFORMATION_DEBUG";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(
         new OptionProvider[]{this.field14, this.field15, this.field16, this.field17, this.field18, this.field19, OptionFactory.method14("resetRotate").method4(() -> {
            this.field14.method1(0.0F);
            this.field15.method1(0.0F);
            this.field16.method1(0.0F);
         }), OptionFactory.method14("resetTranslate").method4(() -> {
            this.field17.method1(0.0F);
            this.field18.method1(0.0F);
            this.field19.method1(0.0F);
         }), OptionFactory.method14("resetAll").method4(() -> {
            this.field14.method1(0.0F);
            this.field15.method1(0.0F);
            this.field16.method1(0.0F);
            this.field17.method1(0.0F);
            this.field18.method1(0.0F);
            this.field19.method1(0.0F);
         })}
      );
      this.field14.CICORRHIOIIOORRRICCORIOIOCIHII(arg0 -> field8 = arg0);
      this.field15.CICORRHIOIIOORRRICCORIOIOCIHII(arg0 -> field9 = arg0);
      this.field16.CICORRHIOIIOORRRICCORIOIOCIHII(arg0 -> field10 = arg0);
      this.field17.CICORRHIOIIOORRRICCORIOIOCIHII(arg0 -> field11 = arg0);
      this.field18.CICORRHIOIIOORRRICCORIOIOCIHII(arg0 -> field12 = arg0);
      this.field19.CICORRHIOIIOORRRICCORIOIOCIHII(arg0 -> field13 = arg0);
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field7}).method11(this);
   }

   static {
      if (LunarBuildData.field4) {
         throw new RuntimeException("Some developer left debug code in production, laugh at them!");
      }
   }
}
