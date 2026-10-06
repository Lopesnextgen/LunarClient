package com.moonsworth.lunar.client.cosmetics.inactive.mixin;

import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.fov.mixin.Gui2Type;
import com.moonsworth.lunar.client.cosmetics.inactive.MolangResourceProvider;
import com.moonsworth.lunar.client.cosmetics.inactive.InactiveType;
import com.moonsworth.lunar.client.cosmetics.inactive.ItemRenderMaterial;
import com.moonsworth.lunar.client.cosmetics.inactive.AttachedBone;
import com.moonsworth.lunar.client.cosmetics.inactive.FirstPersonArmMode;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationControllerDefinition;
import lombok.Generated;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Handler;

public class ItemCosmeticDefinition extends Gui2Handler {
   private AnimationControllerDefinition field20;
   private AttachedBone field21 = AttachedBone.HEAD;
   private MolangResourceProvider field22;
   private FirstPersonArmMode field23 = FirstPersonArmMode.NONE;
   private InactiveType field24 = InactiveType.NONE;
   private ItemRenderMaterial field25 = ItemRenderMaterial.ANY;
   private boolean field26 = false;

   public ItemCosmeticDefinition() {
   }

   @Annotation27("render_first_person")
   public FirstPersonArmMode method20() {
      return this.method6() == Gui2Type.SUITS ? FirstPersonArmMode.DOUBLE_ARM : this.field23;
   }

   @Annotation27("state_machine")
   @Generated
   public AnimationControllerDefinition method21() {
      return this.field20;
   }

   @Annotation27("state_machine")
   @Generated
   public void method3(AnimationControllerDefinition gui21) {
      this.field20 = gui21;
   }

   @Annotation27("attached_bone")
   @Generated
   public AttachedBone method22() {
      return this.field21;
   }

   @Annotation27("attached_bone")
   @Generated
   public void method5(AttachedBone inactivetype41) {
      this.field21 = inactivetype41;
   }

   @Annotation27("item_transformation")
   @Generated
   public MolangResourceProvider method23() {
      return this.field22;
   }

   @Annotation27("item_transformation")
   @Generated
   public void method7(MolangResourceProvider inactive41) {
      this.field22 = inactive41;
   }

   @Annotation27("render_first_person")
   @Generated
   public void method8(FirstPersonArmMode inactivetype51) {
      this.field23 = inactivetype51;
   }

   @Annotation27("item_render_type")
   @Generated
   public InactiveType method24() {
      return this.field24;
   }

   @Annotation27("item_render_type")
   @Generated
   public void method10(InactiveType inactivetype1) {
      this.field24 = inactivetype1;
   }

   @Annotation27("item_render_material")
   @Generated
   public ItemRenderMaterial method25() {
      return this.field25;
   }

   @Annotation27("item_render_material")
   @Generated
   public void method12(ItemRenderMaterial inactivetype31) {
      this.field25 = inactivetype31;
   }

   @Annotation27("offset_with_chestplate")
   @Generated
   public boolean method26() {
      return this.field26;
   }

   @Annotation27("offset_with_chestplate")
   @Generated
   public void method14(boolean flag1) {
      this.field26 = flag1;
   }
}
