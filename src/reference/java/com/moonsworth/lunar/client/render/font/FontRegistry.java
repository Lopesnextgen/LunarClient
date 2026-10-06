package com.moonsworth.lunar.client.render.font;

import com.google.common.collect.ImmutableSet;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.loading.LoadingStage;
import com.moonsworth.lunar.client.framework.loading.LoadableResource;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.framework.loading.ItemSetHandler;
import java.util.Set;
import lombok.Generated;

public class FontRegistry extends ItemSetHandler<CachedFontImpl> implements LoadingStage {
   private static FontRegistry field2;
   public static CachedFontImpl field3;
   public static CachedFontImpl field4;
   public static CachedFontImpl field5;
   public static CachedFontImpl field6;
   public static CachedFontImpl field7;
   public static CachedFontImpl field8;
   public static CachedFontImpl field9;
   public static CachedFontImpl field10;
   public static CachedFontImpl field11;
   public static CachedFontImpl field12;
   public static CachedFontImpl field13;
   public static CachedFontImpl field14;
   public static CachedFontImpl field15;
   public static CachedFontImpl field16;
   public static CachedFontImpl field17;
   public static CachedFontImpl field18;
   public static CachedFontImpl field19;
   private static final ResourceLocationBridge field20 = ResourceLocationBridge.create("lunar", "fonts/roboto-bold.ttf");
   private static final ResourceLocationBridge field21 = ResourceLocationBridge.create("lunar", "fonts/roboto-medium.ttf");
   private static final ResourceLocationBridge field22 = ResourceLocationBridge.create("lunar", "fonts/roboto-light.ttf");
   private static final ResourceLocationBridge field23 = ResourceLocationBridge.create("lunar", "fonts/raleway-extrabold.ttf");
   private static final ResourceLocationBridge field24 = ResourceLocationBridge.create("lunar", "fonts/raleway-light.ttf");

   public FontRegistry() {
      field2 = this;
   }

   @Override
   protected Set<CachedFontImpl> method3() {
      return ImmutableSet.of(
         field4 = new CachedFontImpl(field20, 10.0F),
         field6 = new CachedFontImpl(field20, 12.0F),
         field8 = new CachedFontImpl(field20, 14.0F),
         field10 = new CachedFontImpl(field20, 16.0F),
         field13 = new CachedFontImpl(field20, 18.0F),
         field7 = new CachedFontImpl(field22, 14.0F),
         new CachedFontImpl[]{
            field5 = new CachedFontImpl(field22, 12.0F),
            field14 = new CachedFontImpl(field21, 13.0F),
            field3 = new CachedFontImpl(field21, 10.0F),
            field9 = new CachedFontImpl(field22, 16.0F),
            field11 = new CachedFontImpl(field22, 18.0F),
            field12 = new CachedFontImpl(field21, 18.0F),
            field15 = new CachedFontImpl(field20, 22.0F),
            field16 = new CachedFontImpl(field22, 22.0F),
            field19 = new CachedFontImpl(field22, 38.0F),
            field17 = new CachedFontImpl(field23, 22.0F),
            field18 = new CachedFontImpl(field24, 22.0F)
         }
      );
   }

   public static FontRegistry method5() {
      return field2;
   }

   @Override
   public void init() {
      super.init();
   }

   public String getCategory() {
      return "Font Textures";
   }

   public Set<? extends LoadableResource> method1() {
      return this.method13();
   }

   @Generated
   public static CachedFontImpl method6() {
      return field3;
   }

   @Generated
   public static CachedFontImpl method7() {
      return field4;
   }

   @Generated
   public static CachedFontImpl method8() {
      return field5;
   }

   @Generated
   public static CachedFontImpl method9() {
      return field6;
   }

   @Generated
   public static CachedFontImpl method10() {
      return field7;
   }

   @Generated
   public static CachedFontImpl method11() {
      return field8;
   }

   @Generated
   public static CachedFontImpl method12() {
      return field9;
   }

   @Generated
   public static CachedFontImpl method13() {
      return field10;
   }

   @Generated
   public static CachedFontImpl method14() {
      return field11;
   }

   @Generated
   public static CachedFontImpl method15() {
      return field12;
   }

   @Generated
   public static CachedFontImpl method16() {
      return field13;
   }

   @Generated
   public static CachedFontImpl method17() {
      return field14;
   }

   @Generated
   public static CachedFontImpl method18() {
      return field15;
   }

   @Generated
   public static CachedFontImpl method19() {
      return field16;
   }

   @Generated
   public static CachedFontImpl method20() {
      return field17;
   }

   @Generated
   public static CachedFontImpl method21() {
      return field18;
   }

   @Generated
   public static CachedFontImpl method22() {
      return field19;
   }

   @Generated
   public static ResourceLocationBridge method23() {
      return field21;
   }
}
