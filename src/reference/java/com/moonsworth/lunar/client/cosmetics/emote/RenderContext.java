package com.moonsworth.lunar.client.cosmetics.emote;

import com.eliotlash.molang.ast.Evaluator;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.ModelPlayerBridge;
import com.moonsworth.lunar.bridge.LunarItemType;
import com.moonsworth.lunar.bridge.LunarItemMaterial;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;
import lombok.Generated;
import org.jspecify.annotations.Nullable;

public class RenderContext {
   private @Nullable Bridge5_11 field1;
   private @Nullable EmoteDefinition field2;
   private @Nullable ModelPlayerBridge field3;
   private @Nullable LunarItemType field4;
   private @Nullable LunarItemMaterial field5;
   private @Nullable ItemStackRenderStateBridge field6;
   private final Set<RenderContextType> field7 = EnumSet.noneOf(RenderContextType.class);
   private Evaluator evaluator;
   private @Nullable CosmeticMetadata field8;

   public RenderContext() {
   }

   public Optional<Bridge5_11> method1() {
      return Optional.ofNullable(this.field1);
   }

   public Optional<ModelPlayerBridge> method2() {
      return Optional.ofNullable(this.field3);
   }

   public static RenderContext method3() {
      RenderContext fov100 = new RenderContext();
      fov100.field7.add(RenderContextType.IN_WORLD);
      return fov100;
   }

   public boolean method4(RenderContextType fovtype21) {
      return this.field7.contains(fovtype21);
   }

   public void method5(RenderContextType fovtype21) {
      this.field7.add(fovtype21);
   }

   public static RenderContext method6(Bridge5_11 bridge5_110) {
      RenderContext fov101 = new RenderContext();
      fov101.field7.add(RenderContextType.IN_WORLD);
      fov101.field1 = bridge5_110;
      return fov101;
   }

   public static RenderContext method7(Bridge5_11 bridge5_110, ModelPlayerBridge bridgeextension2_71) {
      RenderContext fov102 = new RenderContext();
      fov102.field7.add(RenderContextType.IN_WORLD);
      fov102.field1 = bridge5_110;
      fov102.field3 = bridgeextension2_71;
      return fov102;
   }

   public static RenderContext method8(Bridge5_11 bridge5_110, ModelPlayerBridge bridgeextension2_71) {
      RenderContext fov102 = new RenderContext();
      fov102.field7.add(RenderContextType.IN_PLAYER_MODEL);
      fov102.field1 = bridge5_110;
      fov102.field3 = bridgeextension2_71;
      return fov102;
   }

   public static RenderContext method9(Bridge5_11 bridge5_110, ModelPlayerBridge bridgeextension2_71) {
      RenderContext fov102 = new RenderContext();
      fov102.field7.add(RenderContextType.IN_PLAYER_MODEL);
      fov102.field1 = bridge5_110;
      fov102.field3 = bridgeextension2_71;
      return fov102;
   }

   public static RenderContext method10() {
      RenderContext fov100 = new RenderContext();
      fov100.field7.add(RenderContextType.IN_GUI);
      return fov100;
   }

   public static RenderContext method11(@Nullable Bridge5_11 bridge5_110) {
      RenderContext fov101 = method10();
      fov101.field1 = bridge5_110;
      return fov101;
   }

   public static RenderContext method12(Bridge5_11 bridge5_110, ModelPlayerBridge bridgeextension2_71) {
      RenderContext fov102 = new RenderContext();
      fov102.field7.add(RenderContextType.IN_WORLD);
      fov102.field7.add(RenderContextType.IN_GUI);
      fov102.field1 = bridge5_110;
      fov102.field3 = bridgeextension2_71;
      return fov102;
   }

   @Generated
   public void method13(@Nullable EmoteDefinition inactive31) {
      this.field2 = inactive31;
   }

   @Generated
   public @Nullable EmoteDefinition method14() {
      return this.field2;
   }

   @Generated
   public @Nullable LunarItemType method15() {
      return this.field4;
   }

   @Generated
   public void method16(@Nullable LunarItemType bridgetype2_41) {
      this.field4 = bridgetype2_41;
   }

   @Generated
   public @Nullable LunarItemMaterial method17() {
      return this.field5;
   }

   @Generated
   public void method18(@Nullable LunarItemMaterial bridgetype3_21) {
      this.field5 = bridgetype3_21;
   }

   @Generated
   public @Nullable ItemStackRenderStateBridge method19() {
      return this.field6;
   }

   @Generated
   public void method20(@Nullable ItemStackRenderStateBridge mixinhelper_141) {
      this.field6 = mixinhelper_141;
   }

   @Generated
   public Set<RenderContextType> method21() {
      return this.field7;
   }

   @Generated
   public Evaluator getEvaluator() {
      return this.evaluator;
   }

   @Generated
   public void setEvaluator(Evaluator evaluator1) {
      this.evaluator = evaluator1;
   }

   @Generated
   public @Nullable CosmeticMetadata method23() {
      return this.field8;
   }

   @Generated
   public void method24(@Nullable CosmeticMetadata gui2handler31) {
      this.field8 = gui2handler31;
   }
}
