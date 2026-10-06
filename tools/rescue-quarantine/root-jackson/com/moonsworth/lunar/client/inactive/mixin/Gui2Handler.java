package com.moonsworth.lunar.client.inactive.mixin;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.Annotation6;
import com.moonsworth.lunar.bridge.horsestats.Horsestats14;
import com.moonsworth.lunar.client.fov.mixin.Gui2Type;
import com.moonsworth.lunar.client.inactive.Inactive4;
import com.moonsworth.lunar.client.inactive.Inactive5;
import com.moonsworth.lunar.client.inactive.InactiveType2;
import com.moonsworth.lunar.client.inactive.InactiveType6;
import com.moonsworth.lunar.client.inactive.MixinHelper102;
import com.moonsworth.lunar.client.markers.mixin.gui.Gui2;
import com.moonsworth.lunar.client.util.ThreadModuleDump91;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

public class Gui2Handler implements Gui2 {
   public static final Map<String, Class<? extends Gui2Handler>> field1;
   private Horsestats14 field2;
   @Annotation27("animation")
   private Inactive4 field3;
   @Annotation27("model")
   private Inactive4 field4;
   @Annotation27("texture")
   private Inactive4 field5;
   @Annotation27("type")
   private Gui2Type field6;
   @Annotation27("show_during_emote")
   private boolean field7 = true;
   @Annotation27("animated_texture")
   private boolean animatedTexture = false;
   @Annotation27("is_translucent")
   private boolean field8 = false;
   @Annotation27("hide_with")
   private InactiveType2[] field9 = new InactiveType2[0];
   @Annotation27("compat")
   private String field10 = "allow_all";
   @Annotation27("special_cosmetic_type")
   private InactiveType6 field11 = InactiveType6.NONE;
   @Annotation27("transformations")
   @Annotation6(IHRORRHHCRCIHHRRIHRHICCCRRCRRI = MixinHelper102.class)
   private List<ThreadModuleDump91> field12 = new ArrayList<>();
   @Annotation27("cosmetic_options")
   private List<Inactive5> field13 = new ArrayList<>();
   @Annotation27("hide_head")
   private boolean field14 = false;
   @Annotation27("hide_body")
   private boolean field15 = false;
   @Annotation27("hide_right_arm")
   private boolean field16 = false;
   @Annotation27("hide_left_arm")
   private boolean field17 = false;
   @Annotation27("hide_right_leg")
   private boolean field18 = false;
   @Annotation27("hide_left_leg")
   private boolean field19 = false;

   @Override
   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("animated_texture", this.isAnimatedTexture());
      JsonArray var2 = new JsonArray();
      this.method13().forEach(var1x -> var2.add(var1x.provide()));
      var1.add("cosmetic_options", var2);
      return var1;
   }

   @Generated
   public Horsestats14 method2() {
      return this.field2;
   }

   @Generated
   public Inactive4 method3() {
      return this.field3;
   }

   @Generated
   public Inactive4 method4() {
      return this.field4;
   }

   @Generated
   public Inactive4 method5() {
      return this.field5;
   }

   @Generated
   public Gui2Type method6() {
      return this.field6;
   }

   @Generated
   public boolean method7() {
      return this.field7;
   }

   @Generated
   public boolean isAnimatedTexture() {
      return this.animatedTexture;
   }

   @Generated
   public boolean method8() {
      return this.field8;
   }

   @Generated
   public InactiveType2[] method9() {
      return this.field9;
   }

   @Generated
   public String method10() {
      return this.field10;
   }

   @Generated
   public InactiveType6 method11() {
      return this.field11;
   }

   @Generated
   public List<ThreadModuleDump91> method12() {
      return this.field12;
   }

   @Generated
   public List<Inactive5> method13() {
      return this.field13;
   }

   @Generated
   public boolean method14() {
      return this.field14;
   }

   @Generated
   public boolean method15() {
      return this.field15;
   }

   @Generated
   public boolean method16() {
      return this.field16;
   }

   @Generated
   public boolean method17() {
      return this.field17;
   }

   @Generated
   public boolean method18() {
      return this.field18;
   }

   @Generated
   public boolean method19() {
      return this.field19;
   }

   @Generated
   public void method20(Horsestats14 var1) {
      this.field2 = var1;
   }

   @Generated
   public void method21(Inactive4 var1) {
      this.field3 = var1;
   }

   @Generated
   public void method22(Inactive4 var1) {
      this.field4 = var1;
   }

   @Generated
   public void method23(Inactive4 var1) {
      this.field5 = var1;
   }

   @Generated
   public void method24(Gui2Type var1) {
      this.field6 = var1;
   }

   @Generated
   public void method25(boolean var1) {
      this.field7 = var1;
   }

   @Generated
   public void setAnimatedTexture(boolean var1) {
      this.animatedTexture = var1;
   }

   @Generated
   public void method27(boolean var1) {
      this.field8 = var1;
   }

   @Generated
   public void method28(InactiveType2[] var1) {
      this.field9 = var1;
   }

   @Generated
   public void method29(String var1) {
      this.field10 = var1;
   }

   @Generated
   public void method30(InactiveType6 var1) {
      this.field11 = var1;
   }

   @Generated
   public void method31(List<ThreadModuleDump91> var1) {
      this.field12 = var1;
   }

   @Generated
   public void method32(List<Inactive5> var1) {
      this.field13 = var1;
   }

   @Generated
   public void method33(boolean var1) {
      this.field14 = var1;
   }

   @Generated
   public void method34(boolean var1) {
      this.field15 = var1;
   }

   @Generated
   public void method35(boolean var1) {
      this.field16 = var1;
   }

   @Generated
   public void method36(boolean var1) {
      this.field17 = var1;
   }

   @Generated
   public void method37(boolean var1) {
      this.field18 = var1;
   }

   @Generated
   public void method38(boolean var1) {
      this.field19 = var1;
   }

   static {
      HashMap var0 = new HashMap();

      for (Gui2Type var4 : Gui2Type.values()) {
         var0.put(var4.getName(), Gui2Impl2.class);
      }

      var0.put("companion", Gui2Impl.class);
      field1 = ImmutableMap.copyOf(var0);
   }
}
