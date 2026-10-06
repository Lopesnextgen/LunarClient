package com.moonsworth.lunar.client.cosmetics.emote;

import com.eliotlash.molang.ast.Evaluator;
import com.eliotlash.molang.variables.ExecutionContext;
import com.eliotlash.molang.variables.VariableFlavor;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.world.BiomeCategory;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import com.moonsworth.lunar.client.fov.Gui2Iterator;
import com.moonsworth.lunar.client.cosmetics.molang.MolangScope;
import com.moonsworth.lunar.client.cosmetics.molang.MolangBuiltin;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.cosmetics.gecko.AnimationTicker;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.GeckolibCosmeticManager;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Handler;
import com.moonsworth.lunar.client.cosmetics.gecko.AnimationEventImpl;
import com.moonsworth.lunar.client.cosmetics.molang.MolangRuntime;
import com.moonsworth.lunar.client.cosmetics.molang.MolangCheckBiome;
import com.moonsworth.lunar.client.cosmetics.molang.MolangCheckBiomeCategory;
import com.moonsworth.lunar.client.cosmetics.molang.MolangCheckSnowyBiome;
import com.moonsworth.lunar.client.cosmetics.molang.MolangLightLevel;
import com.moonsworth.lunar.client.cosmetics.molang.MolangSmoothQuery;
import com.moonsworth.lunar.client.cosmetics.gecko.BedrockAnimatedModel;
import com.moonsworth.lunar.client.cosmetics.gecko.IBoneSerializer;
import com.moonsworth.lunar.client.cosmetics.gecko.BedrockGeometry;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lombok.Generated;
import mchorse.mclib.utils.Interpolations;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimatableModel;
import software.bernie.geckolib3.core.builder.Animation;
import software.bernie.geckolib3.core.easing.EasingType;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.core.snapshot.BoneSnapshot;
import com.moonsworth.lunar.client.cosmetics.molang.EvaluatorImpl;

public abstract class EmoteModel<T extends IAnimatable>
   extends BedrockAnimatedModel<T>
   implements com.moonsworth.lunar.client.cosmetics.gecko.ModelTextureProvider<T>,
   IAnimatableModel<T> {
   private final AnimationProcessor<?> field3;
   private BedrockGeometry field4;
   private RenderContext field5;
   private final HashMap<EmoteModel.RuntimeKey, MolangRuntime> field6 = new HashMap<>();
   private final Map<Integer, EmoteModel.InterpolationState> field7 = new HashMap<>();
   private final double tickOffset = Math.random();

   private MolangRuntime method1(BridgeExtension bridgeextension1, int number2) {
      EmoteModel.RuntimeKey data3 = new EmoteModel.RuntimeKey(bridgeextension1.bridge$getUniqueID(), number2);
      return this.field6.computeIfAbsent(data3, arg0 -> {
         EvaluatorImpl evaluatorimpl1x = new EvaluatorImpl();
         return new MolangRuntime(new ExecutionContext(evaluatorimpl1x), evaluatorimpl1x);
      });
   }

   protected EmoteModel() {
      this.field3 = new AnimationProcessor(this);
   }

   public void method2(IBoneSerializer iboneserializer1) {
      this.registerModelRenderer(iboneserializer1);

      for (IBoneSerializer iboneserializer3 : iboneserializer1.field1) {
         this.method2(iboneserializer3);
      }
   }

   public void method3(T value1, Integer number2, @Nullable AnimationEvent animationevent3) {
      if (!((Gui2Iterator)value1).method6().map(Gui2Handler::method3).isEmpty()) {
         AnimationData animationdata4 = value1.getFactory().getOrCreateAnimationData(number2);
         if (animationdata4.startTick == null) {
            animationdata4.startTick = this.getCurrentTick();
         }

         if (animationdata4.ticker == null) {
            animationdata4.ticker = new AnimationTicker();
            ((AnimationTicker)animationdata4.ticker).field2 = EventTick.field1;
         }

         if (animationdata4.ticker instanceof AnimationTicker inactive_25 && EventTick.field1 != inactive_25.field2) {
            animationdata4.tick = animationdata4.tick + (EventTick.field1 - inactive_25.field2);
            inactive_25.field2 = EventTick.field1;
         }

         if (!Ref.method3().bridge$isGamePaused() || animationdata4.shouldPlayWhilePaused) {
            this.IIRRRHOOIHIOHCHHOIOROIIRICRIOI = animationdata4.tick + Ref.method3().bridge$getTimer().method1();
         }

         this.method4(
            this.IIRRRHOOIHIOHCHHOIOROIIRICRIOI + this.tickOffset,
            number2,
            this.field3.getModelRendererList(),
            animationdata4,
            arg3x -> {
               if (!this.field3.getModelRendererList().isEmpty()) {
                  AnimationEventImpl animationeventimpl4x = new AnimationEventImpl((Gui2Iterator)value1, 0.0F, 0.0F, 0.0F, false, Collections.emptyList(), this.field5);
                  GeckolibCosmeticManager.method18((Bridge5_11)this.field5.method1().orElse(null));
                  animationeventimpl4x.animationTick = this.IIRRRHOOIHIOHCHHOIOROIIRICRIOI;
                  this.field3.preAnimationSetup(animationeventimpl4x.getAnimatable(), this.IIRRRHOOIHIOHCHHOIOROIIRICRIOI);
                  if (Ref.method4().method40().method73().isValid() && this.field5.method1().isPresent() && value1 instanceof Gui2Iterator gui2iterator5x) {
                     Ref.method4()
                        .method40()
                        .method73()
                        .method8(gui2iterator5x.getName(), this.method1((BridgeExtension)this.field5.method1().get(), number2));
                  }

                  this.field3
                     .tickAnimation(value1, number2, this.IIRRRHOOIHIOHCHHOIOROIIRICRIOI, animationeventimpl4x, arg3x.getEvaluator(), arg3x, this.ICHHRCIRICRHRRCICCRCHROOIHICRI);
               }
            }
         );
         ((AnimationTicker)animationdata4.ticker).field1 = Ref.method14();
      }
   }

   private void method4(double value1, Integer number3, List<IBone> list4, AnimationData animationdata5, Consumer<ExecutionContext> consumer6) {
      EmoteModel.InterpolationState data27 = this.field7.computeIfAbsent(number3, arg0 -> new EmoteModel.InterpolationState());
      if (data27.field3 != null && data27.field3.size() != list4.size()) {
         data27 = new EmoteModel.InterpolationState();
         this.field7.put(number3, data27);
      }

      int number8 = (int)value1;
      boolean flag9 = data27.field2 == null;
      if (!flag9 && data27.field1 == number8) {
         this.field5.setEvaluator(data27.evaluator);
      } else {
         ExecutionContext executioncontext10 = this.method6((AnimationTicker)animationdata5.ticker, number3, this.field5, this.field3.getModelRendererList());
         data27.evaluator = executioncontext10.getEvaluator();
         this.field5.setEvaluator(executioncontext10.getEvaluator());
         consumer6.accept(executioncontext10);
         data27.method1(list4);
         data27.field1 = number8;
         if (flag9) {
            data27.field2 = data27.field3;
         }
      }

      data27.method2(list4, value1 % 1.0);
   }

   public AnimationProcessor getAnimationProcessor() {
      return this.field3;
   }

   public void registerModelRenderer(IBone ibone1) {
      this.field3.registerModelRenderer(ibone1);
   }

   public Animation getAnimation(String text1, IAnimatable ianimatable2) {
      return this.field5 == null
         ? null
         : Client.method109().method76().method10(this.method4(ianimatable2, this.field5.getEvaluator())).map(arg1x -> arg1x.method1(text1)).orElse(null);
   }

   @Override
   public Optional<BedrockGeometry> method1(ResourceLocationBridge horsestats141) {
      Optional optional2 = super.method1(horsestats141);
      optional2.filter(arg1x -> !arg1x.equals(this.field4)).ifPresent(arg1x -> {
         this.field3.clearModelRendererList();

         for (IBoneSerializer iboneserializer3 : arg1x.field1) {
            this.method2(iboneserializer3);
         }

         this.field4 = arg1x;
      });
      return optional2;
   }

   public ExecutionContext method6(AnimationTicker inactive_21, int number2, RenderContext fov103, List<IBone> list4) {
      ExecutionContext executioncontext5;
      MolangRuntime highlight6;
      EvaluatorImpl evaluatorimpl7;
      if (fov103.method14() != null) {
         highlight6 = fov103.method14().method30();
         executioncontext5 = highlight6.getContext();
         evaluatorimpl7 = highlight6.method9();
         highlight6.method5(inactive_21, fov103, list4);
      } else if (fov103.method1().isPresent()) {
         Bridge5_11 bridge5_118 = (Bridge5_11)fov103.method1().get();
         highlight6 = this.method1(bridge5_118, number2);
         executioncontext5 = highlight6.getContext();
         evaluatorimpl7 = highlight6.method9();
         highlight6.method5(inactive_21, fov103, list4);
      } else {
         highlight6 = null;
         executioncontext5 = Evaluator.getGlobalEvaluator().getContext();
         evaluatorimpl7 = new EvaluatorImpl();
      }

      evaluatorimpl7.setExecutionContext(executioncontext5);
      evaluatorimpl7.method1(highlight6);
      if (highlight6 != null) {
         highlight6.update();
      }

      return executioncontext5;
   }

   public double getCurrentTick() {
      return (float)Ref.method14() / 1000.0F * 20.0F;
   }

   public void setMolangQueries(IAnimatable ianimatable1, double value2) {
   }

   private static void method7(MolangScope fps110, VariableFlavor variableflavor1, String text2) {
      String text3 = text2 + "_smooth";

      for (EasingType easingtype7 : EasingType.values()) {
         String text8 = text3;
         if (easingtype7 != EasingType.NONE) {
            text8 = text3 + "_" + easingtype7.name().toLowerCase();
         }

         method8(fps110, variableflavor1, text8, new MolangSmoothQuery(variableflavor1.name + "." + text2, easingtype7, fps110));
      }
   }

   private static void method8(MolangScope fps110, VariableFlavor variableflavor1, String text2, MolangBuiltin fps7extension3) {
      fps110.method2(variableflavor1.name + "." + text2, fps7extension3);
   }

   public static void method9(MolangScope fps110) {
      method7(fps110, VariableFlavor.QUERY, "is_flying");
      method7(fps110, VariableFlavor.QUERY, "is_elytra_flying");
      method7(fps110, VariableFlavor.QUERY, "is_moving");
      method7(fps110, VariableFlavor.QUERY, "is_sprinting");
      method7(fps110, VariableFlavor.QUERY, "is_running");
      method7(fps110, VariableFlavor.QUERY, "is_crouching");
      method7(fps110, VariableFlavor.QUERY, "is_on_fire");
      method7(fps110, VariableFlavor.QUERY, "is_riding_boat");
      method7(fps110, VariableFlavor.QUERY, "head_slot_equipped");
      method7(fps110, VariableFlavor.QUERY, "body_slot_equipped");
      method7(fps110, VariableFlavor.QUERY, "legs_slot_equipped");
      method7(fps110, VariableFlavor.QUERY, "feet_slot_equipped");
      method7(fps110, VariableFlavor.QUERY, "is_on_ground");
      method7(fps110, VariableFlavor.QUERY, "is_swimming");
      method7(fps110, VariableFlavor.QUERY, "is_in_water");
      method7(fps110, VariableFlavor.QUERY, "is_raining");
      method8(fps110, VariableFlavor.LUNAR, "check_for_biome", new MolangCheckBiome());

      for (BiomeCategory itemcountertype2_24 : BiomeCategory.values()) {
         String text5 = "check_for_" + itemcountertype2_24.getName().toLowerCase();
         method8(fps110, VariableFlavor.LUNAR, text5, new MolangCheckBiomeCategory(itemcountertype2_24));
      }

      method8(fps110, VariableFlavor.LUNAR, "check_for_snowy_biome", new MolangCheckSnowyBiome());
      method8(fps110, VariableFlavor.LUNAR, "get_light_level", new MolangLightLevel());
   }

   @Generated
   public RenderContext method10() {
      return this.field5;
   }

   @Generated
   public void method11(RenderContext fov101) {
      this.field5 = fov101;
   }

   static {
      Evaluator.getGlobalEvaluator().setExecutionContext(new ExecutionContext(Evaluator.getGlobalEvaluator()));
   }

   private class RuntimeKey {
      private final UUID field1;
      private final int field2;

      private RuntimeKey(UUID uuid1, int number2) {
         this.field1 = uuid1;
         this.field2 = number2;
      }

      public UUID method1() {
         return this.field1;
      }

      public int method2() {
         return this.field2;
      }
   }

   private static class InterpolationState {
      private int field1;
      private List<BoneSnapshot> field2;
      private List<BoneSnapshot> field3;
      private Evaluator evaluator;

      private InterpolationState() {
      }

      public void method1(List<IBone> list1) {
         ArrayList list2 = new ArrayList(list1.size());

         for (IBone ibone4 : list1) {
            list2.add(new BoneSnapshot(ibone4));
         }

         this.field2 = this.field3;
         this.field3 = list2;
      }

      public void method2(List<IBone> list1, double value2) {
         for (int index4 = 0; index4 < list1.size(); index4++) {
            IBone ibone5 = (IBone)list1.get(index4);
            BoneSnapshot bonesnapshot6 = this.field2.get(index4);
            BoneSnapshot bonesnapshot7 = this.field3.get(index4);
            double value8 = this.method3(bonesnapshot7);
            double value10 = this.method3(bonesnapshot6);
            if (!(value8 > value10 * 20.0) && !(value8 < value10 / 20.0)) {
               ibone5.setPositionX(method4(value2, bonesnapshot6.positionOffsetX, bonesnapshot7.positionOffsetX));
               ibone5.setPositionY(method4(value2, bonesnapshot6.positionOffsetY, bonesnapshot7.positionOffsetY));
               ibone5.setPositionZ(method4(value2, bonesnapshot6.positionOffsetZ, bonesnapshot7.positionOffsetZ));
               ibone5.setRotationX(method5(value2, bonesnapshot6.rotationValueX, bonesnapshot7.rotationValueX));
               ibone5.setRotationY(method5(value2, bonesnapshot6.rotationValueY, bonesnapshot7.rotationValueY));
               ibone5.setRotationZ(method5(value2, bonesnapshot6.rotationValueZ, bonesnapshot7.rotationValueZ));
               ibone5.setScaleX(method4(value2, bonesnapshot6.scaleValueX, bonesnapshot7.scaleValueX));
               ibone5.setScaleY(method4(value2, bonesnapshot6.scaleValueY, bonesnapshot7.scaleValueY));
               ibone5.setScaleZ(method4(value2, bonesnapshot6.scaleValueZ, bonesnapshot7.scaleValueZ));
            } else {
               ibone5.setPositionX(bonesnapshot6.positionOffsetX);
               ibone5.setPositionY(bonesnapshot6.positionOffsetY);
               ibone5.setPositionZ(bonesnapshot6.positionOffsetZ);
               ibone5.setRotationX(bonesnapshot6.rotationValueX);
               ibone5.setRotationY(bonesnapshot6.rotationValueY);
               ibone5.setRotationZ(bonesnapshot6.rotationValueZ);
               ibone5.setScaleX(bonesnapshot6.scaleValueX);
               ibone5.setScaleY(bonesnapshot6.scaleValueY);
               ibone5.setScaleZ(bonesnapshot6.scaleValueZ);
            }
         }
      }

      private double method3(BoneSnapshot bonesnapshot1) {
         return bonesnapshot1.scaleValueX * bonesnapshot1.scaleValueY * bonesnapshot1.scaleValueZ;
      }

      private static float method4(double value0, float value2, float value3) {
         return (float)Interpolations.lerp(value2, value3, value0);
      }

      private static float method5(double value0, float value2, float value3) {
         return (float)Math.toRadians(Interpolations.lerpYaw(Math.toDegrees(value2), Math.toDegrees(value3), value0));
      }
   }
}
