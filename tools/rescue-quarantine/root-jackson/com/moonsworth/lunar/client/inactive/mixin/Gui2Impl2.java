package com.moonsworth.lunar.client.inactive.mixin;

import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.fov.mixin.Gui2Type;
import com.moonsworth.lunar.client.inactive.Inactive4;
import com.moonsworth.lunar.client.inactive.InactiveType;
import com.moonsworth.lunar.client.inactive.InactiveType3;
import com.moonsworth.lunar.client.inactive.InactiveType4;
import com.moonsworth.lunar.client.inactive.InactiveType5;
import com.moonsworth.lunar.client.inactive.mixin.gui.Gui2;
import lombok.Generated;

public class Gui2Impl2 extends Gui2Handler {
   private Gui2 field20;
   private InactiveType4 field21 = InactiveType4.HEAD;
   private Inactive4 field22;
   private InactiveType5 field23 = InactiveType5.NONE;
   private InactiveType field24 = InactiveType.NONE;
   private InactiveType3 field25 = InactiveType3.ANY;
   private boolean field26 = false;

   @Annotation27("render_first_person")
   public InactiveType5 method20() {
      return this.method6() == Gui2Type.SUITS ? InactiveType5.DOUBLE_ARM : this.field23;
   }

   @Annotation27("state_machine")
   @Generated
   public Gui2 method21() {
      return this.field20;
   }

   @Annotation27("state_machine")
   @Generated
   public void method3(Gui2 var1) {
      this.field20 = var1;
   }

   @Annotation27("attached_bone")
   @Generated
   public InactiveType4 method22() {
      return this.field21;
   }

   @Annotation27("attached_bone")
   @Generated
   public void method5(InactiveType4 var1) {
      this.field21 = var1;
   }

   @Annotation27("item_transformation")
   @Generated
   public Inactive4 method23() {
      return this.field22;
   }

   @Annotation27("item_transformation")
   @Generated
   public void method7(Inactive4 var1) {
      this.field22 = var1;
   }

   @Annotation27("render_first_person")
   @Generated
   public void method8(InactiveType5 var1) {
      this.field23 = var1;
   }

   @Annotation27("item_render_type")
   @Generated
   public InactiveType method24() {
      return this.field24;
   }

   @Annotation27("item_render_type")
   @Generated
   public void method10(InactiveType var1) {
      this.field24 = var1;
   }

   @Annotation27("item_render_material")
   @Generated
   public InactiveType3 method25() {
      return this.field25;
   }

   @Annotation27("item_render_material")
   @Generated
   public void method12(InactiveType3 var1) {
      this.field25 = var1;
   }

   @Annotation27("offset_with_chestplate")
   @Generated
   public boolean method26() {
      return this.field26;
   }

   @Annotation27("offset_with_chestplate")
   @Generated
   public void method14(boolean var1) {
      this.field26 = var1;
   }
}
