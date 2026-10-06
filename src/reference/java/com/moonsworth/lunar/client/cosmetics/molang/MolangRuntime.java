package com.moonsworth.lunar.client.cosmetics.molang;

import com.eliotlash.molang.ast.Expr;
import com.eliotlash.molang.ast.Stmt;
import com.eliotlash.molang.ast.Stmt.Expression;
import com.eliotlash.molang.utils.MolangUtils;
import com.eliotlash.molang.variables.ExecutionContext;
import com.eliotlash.molang.variables.RuntimeVariable;
import com.eliotlash.molang.variables.VariableFlavor;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.IOException2_2;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.CameraBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.LunarItemType;
import com.moonsworth.lunar.bridge.minecraft.EntityEquipmentSlotBridge;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager.Data;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContextType;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.molang.MolangScope;
import com.moonsworth.lunar.client.cosmetics.molang.MolangStmtCompiler;
import com.moonsworth.lunar.client.cosmetics.molang.MolangVariable;
import com.moonsworth.lunar.client.render.particle.AngleMath;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;
import com.moonsworth.lunar.client.inactive.Inactive5;
import com.moonsworth.lunar.client.cosmetics.gecko.AnimationTicker;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.GeckolibCosmeticManager;
import com.moonsworth.lunar.client.cosmetics.molang.EvaluatorImpl;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import com.moonsworth.lunar.client.cosmetics.gecko.IBoneSerializer;
import com.moonsworth.lunar.client.cosmetics.DummyPlayer;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import lombok.Generated;
import org.joml.Vector3d;
import software.bernie.geckolib3.core.processor.IBone;

public class MolangRuntime {
   private static final double field1 = 20.0;
   private final ExecutionContext field2;
   private final EvaluatorImpl field3;
   private final MolangScope field4 = new MolangScope();
   private final Map<Object, MolangStmtCompiler> field5 = new IdentityHashMap<>();
   private final Map<List<IBone>, List<MolangRuntime.BoneQuery>> field6 = new WeakHashMap<>();
   private final MolangVariable field7 = this.field4.method7("query.x_velocity");
   private final MolangVariable field8 = this.field4.method7("query.y_velocity");
   private final MolangVariable field9 = this.field4.method7("query.z_velocity");
   private final MolangVariable field10 = this.field4.method7("query.absolute_speed");
   private final MolangVariable field11 = this.field4.method7("query.ground_speed");
   private final MolangVariable field12 = this.field4.method7("query.local_x_velocity");
   private final MolangVariable field13 = this.field4.method7("query.local_y_velocity");
   private final MolangVariable field14 = this.field4.method7("query.local_z_velocity");
   private final MolangVariable field15 = this.field4.method7("query.is_moving");
   private final MolangVariable field16 = this.field4.method7("query.entity_y_rotation");
   private final MolangVariable field17 = this.field4.method7("query.entity_y_rotation_target");
   private final MolangVariable field18 = this.field4.method7("query.camera_yaw");
   private final MolangVariable field19 = this.field4.method7("query.camera_pitch");
   private final MolangVariable field20 = this.field4.method7("query.camera_x");
   private final MolangVariable field21 = this.field4.method7("query.camera_y");
   private final MolangVariable field22 = this.field4.method7("query.camera_z");
   private final MolangVariable field23 = this.field4.method7("query.is_flying");
   private final MolangVariable field24 = this.field4.method7("query.is_elytra_flying");
   private final MolangVariable field25 = this.field4.method7("query.is_sprinting");
   private final MolangVariable field26 = this.field4.method7("query.is_running");
   private final MolangVariable field27 = this.field4.method7("query.is_crouching");
   private final MolangVariable field28 = this.field4.method7("query.is_on_fire");
   private final MolangVariable field29 = this.field4.method7("query.is_riding_boat");
   private final MolangVariable field30 = this.field4.method7("query.head_slot_equipped");
   private final MolangVariable field31 = this.field4.method7("query.body_slot_equipped");
   private final MolangVariable field32 = this.field4.method7("query.legs_slot_equipped");
   private final MolangVariable field33 = this.field4.method7("query.feet_slot_equipped");
   private final MolangVariable field34 = this.field4.method7("query.is_on_ground");
   private final MolangVariable field35 = this.field4.method7("query.is_swimming");
   private final MolangVariable field36 = this.field4.method7("query.is_blocking");
   private final MolangVariable field37 = this.field4.method7("query.last_damaged");
   private final MolangVariable field38 = this.field4.method7("query.last_attacked_someone");
   private final MolangVariable field39 = this.field4.method7("query.last_attacked_by");
   private final MolangVariable field40 = this.field4.method7("query.world_x_pos");
   private final MolangVariable field41 = this.field4.method7("query.world_y_pos");
   private final MolangVariable field42 = this.field4.method7("query.world_z_pos");
   private final MolangVariable field43 = this.field4.method7("query.body_x_rotation");
   private final MolangVariable field44 = this.field4.method7("query.is_in_water");
   private final MolangVariable field45 = this.field4.method7("query.distance_from_surface");
   private final MolangVariable field46 = this.field4.method7("query.surface_blocked");
   private final MolangVariable field47 = this.field4.method7("query.is_raining");
   private final MolangVariable field48 = this.field4.method7("query.head_y_rotation");
   private final MolangVariable field49 = this.field4.method7("query.head_x_rotation");
   private final MolangVariable field50 = this.field4.method7("query.body_y_rotation");
   private final MolangVariable field51 = this.field4.method7("query.left_arm_x_rotation");
   private final MolangVariable field52 = this.field4.method7("query.left_arm_y_rotation");
   private final MolangVariable field53 = this.field4.method7("query.left_arm_z_rotation");
   private final MolangVariable field54 = this.field4.method7("query.right_arm_x_rotation");
   private final MolangVariable field55 = this.field4.method7("query.right_arm_y_rotation");
   private final MolangVariable field56 = this.field4.method7("query.right_arm_z_rotation");
   private final MolangVariable field57 = this.field4.method7("query.left_leg_x_rotation");
   private final MolangVariable field58 = this.field4.method7("query.left_leg_y_rotation");
   private final MolangVariable field59 = this.field4.method7("query.left_leg_z_rotation");
   private final MolangVariable field60 = this.field4.method7("query.right_leg_x_rotation");
   private final MolangVariable field61 = this.field4.method7("query.right_leg_y_rotation");
   private final MolangVariable field62 = this.field4.method7("query.right_leg_z_rotation");
   private final MolangVariable field63 = this.field4.method7("query.is_slim_model");
   private final MolangVariable field64 = this.field4.method7("query.is_radio_playing");
   private final MolangVariable field65 = this.field4.method7("query.minecraft_version");
   private final MolangVariable field66 = this.field4.method7("query.tick");
   private final MolangVariable field67 = this.field4.method7("query.partial_ticks");
   private final MolangVariable field68 = this.field4.method7("query.is_in_gui");
   private final MolangVariable field69 = this.field4.method7("query.is_in_preview_model");
   private final RuntimeVariable field70;
   private final RuntimeVariable field71;
   private final MolangVariable field72 = this.field4.method7("query.life_time");
   private final MolangVariable field73 = this.field4.method7("query.anim_time");
   private final MolangVariable field74 = this.field4.method7("query.owner_distance");
   private MolangStmtCompiler field75 = null;

   public MolangRuntime(ExecutionContext executioncontext1, EvaluatorImpl evaluatorimpl2) {
      this.field2 = executioncontext1;
      this.field3 = evaluatorimpl2;
      this.field70 = executioncontext1.getCachedVariable(VariableFlavor.QUERY, "life_time");
      this.field71 = executioncontext1.getCachedVariable(VariableFlavor.QUERY, "anim_time");
      this.field4.method1();

      try {
         GeckolibCosmeticManager.method2(this.field4);
      } catch (IOException2_2 ioexception2_24) {
         throw new RuntimeException(ioexception2_24);
      }

      EmoteModel.method9(this.field4);
   }

   public double evaluate(List<Stmt> list1) {
      this.method2();
      MolangStmtCompiler fps62 = this.field5.computeIfAbsent(list1, arg2x -> MolangStmtCompiler.method1(list1, this.field4));
      return fps62.run();
   }

   public double method1(Expr expr1) {
      this.method2();
      MolangStmtCompiler fps62 = this.field5.computeIfAbsent(expr1, arg2x -> MolangStmtCompiler.method2(new Expression(expr1), this.field4));
      return fps62.run();
   }

   private void method2() {
      this.field72.value = this.field2.getVariableMap().getOrDefault(this.field70, 0.0);
      this.field73.value = this.field2.getVariableMap().getOrDefault(this.field71, 0.0);
   }

   public void update() {
      if (this.field75 == null) {
         this.field75 = GeckolibCosmeticManager.method3(this.field4);
      }

      this.field75.run();
   }

   public void clearCache() {
      this.field5.clear();
   }

   public void method3(VariableFlavor variableflavor1, String text2, double value3) {
      this.field4.method7(variableflavor1.name + "." + text2).value = value3;
   }

   public void method4(MolangVariable fps7handler1, RenderContext fov102, double value3) {
      boolean flag5 = fov102.method23() != null && fov102.method23().method6().method6();
      fps7handler1.value = value3 * (flag5 ? -1 : 1);
   }

   public void method5(AnimationTicker inactive_21, RenderContext fov102, List<IBone> list3) {
      Bridge5_11 bridge5_114 = (Bridge5_11)fov102.method1().orElseThrow();

      for (MolangRuntime.BoneQuery data57 : this.field6.computeIfAbsent(list3, arg2x -> {
         ArrayList list3x = new ArrayList();

         for (IBone ibone5 : list3) {
            list3x.add(new MolangRuntime.BoneQuery(ibone5));
         }

         return list3x;
      })) {
         data57.update();
      }

      this.method6(bridge5_114, fov102);
      this.field67.value = (Ref.method14() - inactive_21.field1) / 1000.0;
   }

   public void method6(Bridge5_11 bridge5_111, RenderContext fov102) {
      EmoteDefinition inactive33 = fov102.method14();
      if (bridge5_111 instanceof DummyPlayer) {
         this.field7.value = 0.0;
         this.field8.value = 0.0;
         this.field9.value = 0.0;
         this.field74.value = 0.0;
         this.field10.value = 0.0;
         this.field11.value = 0.0;
         this.field12.value = 0.0;
         this.field13.value = 0.0;
         this.field14.value = 0.0;
      } else {
         double value4;
         double value6;
         double value8;
         double value10;
         if (inactive33 != null) {
            value4 = (inactive33.bridge$getPosX() - inactive33.method11()) * 20.0;
            value6 = (inactive33.bridge$getPosY() - inactive33.method12()) * 20.0;
            value8 = (inactive33.bridge$getPosZ() - inactive33.method13()) * 20.0;
            this.field74.value = Math.sqrt(inactive33.method26(bridge5_111));
            value10 = inactive33.getYaw();
         } else {
            value4 = (bridge5_111.bridge$getPosX() - bridge5_111.bridge$lastTickX()) * 20.0;
            value6 = (bridge5_111.bridge$getPosY() - bridge5_111.bridge$lastTickY()) * 20.0;
            value8 = (bridge5_111.bridge$getPosZ() - bridge5_111.bridge$lastTickZ()) * 20.0;
            this.field74.value = 0.0;
            value10 = bridge5_111.bridge$getRotationYaw();
         }

         this.field7.value = value4;
         this.field8.value = value6;
         this.field9.value = value8;
         this.field10.value = Math.sqrt(value4 * value4 + value6 * value6 + value8 * value8);
         double value12 = Math.sqrt(value4 * value4 + value8 * value8);
         this.field11.value = value12;
         this.field15.value = MolangUtils.booleanToFloat(value12 > 0.25);
         Vector3d vector3d14 = new Vector3d(value4, value6, value8);
         Vector3d vector3d15 = vector3d14.rotateY(Math.toRadians(value10));
         this.method4(this.field12, fov102, vector3d15.x);
         this.field13.value = vector3d15.y;
         this.field14.value = vector3d15.z;
         this.field16.value = value10;
      }

      if (Bridge.getMinecraftVersion().method19()) {
         CameraBridge bridge2_1916 = (CameraBridge)Ref.method13().bridge$getCamera().orElse(null);
         if (bridge2_1916 != null) {
            this.field18.value = bridge2_1916.bridge$getYaw();
            this.field19.value = bridge2_1916.bridge$getPitch();
            this.field20.value = bridge2_1916.bridge$getPosX();
            this.field21.value = bridge2_1916.bridge$getPosY();
            this.field22.value = bridge2_1916.bridge$getPosZ();
         }
      } else {
         this.field18.value = bridge5_111.bridge$getRotationYaw();
         this.field19.value = bridge5_111.bridge$getRotationPitch();
         this.field20.value = bridge5_111.bridge$getPosX();
         this.field21.value = bridge5_111.bridge$getPosY();
         this.field22.value = bridge5_111.bridge$getPosZ();
      }

      if (inactive33 != null) {
         this.field23.value = MolangUtils.booleanToFloat(false);
         this.field24.value = MolangUtils.booleanToFloat(false);
         this.field25.value = MolangUtils.booleanToFloat(false);
         this.field26.value = MolangUtils.booleanToFloat(false);
         this.field27.value = MolangUtils.booleanToFloat(false);
         this.field28.value = MolangUtils.booleanToFloat(inactive33.bridge$isOnFire());
         this.field29.value = MolangUtils.booleanToFloat(false);
         this.field30.value = MolangUtils.booleanToFloat(false);
         this.field31.value = MolangUtils.booleanToFloat(false);
         this.field32.value = MolangUtils.booleanToFloat(false);
         this.field33.value = MolangUtils.booleanToFloat(false);
         this.field34.value = MolangUtils.booleanToFloat(inactive33.isOnGround());
         this.field35.value = MolangUtils.booleanToFloat(false);
         this.field36.value = MolangUtils.booleanToFloat(false);
         this.field37.value = 0.0;
         this.field38.value = 0.0;
         this.field39.value = 0.0;
         this.field43.value = 0.0;
         this.field17.value = inactive33.method40();
         this.field40.value = inactive33.bridge$getPosX();
         this.field41.value = inactive33.bridge$getPosY();
         this.field42.value = inactive33.bridge$getPosZ();
         this.field44.value = MolangUtils.booleanToFloat(inactive33.method6());
         if (fov102.method4(RenderContextType.IN_WORLD)) {
            this.field45.value = 0.0;
            this.field46.value = MolangUtils.booleanToFloat(false);
            boolean flag17 = bridge5_111.bridge$getWorld().bridge$isRaining();
            this.field47.value = MolangUtils.booleanToFloat(flag17);
         } else {
            this.field47.value = MolangUtils.booleanToFloat(false);
            this.field45.value = 0.0;
            this.field46.value = 0.0;
         }
      } else {
         this.field23.value = MolangUtils.booleanToFloat(bridge5_111.bridge$isFlying());
         this.field24.value = MolangUtils.booleanToFloat(bridge5_111.bridge$isElytraFlying());
         this.field25.value = MolangUtils.booleanToFloat(bridge5_111.bridge$isSprinting());
         this.field26.value = MolangUtils.booleanToFloat(bridge5_111.bridge$isSprinting());
         this.field27.value = MolangUtils.booleanToFloat(bridge5_111.bridge$isVisiblyCrouching());
         this.field28.value = MolangUtils.booleanToFloat(bridge5_111.bridge$isOnFire());
         this.field29.value = MolangUtils.booleanToFloat(bridge5_111.bridge$isRidingBoat());
         this.field30.value = MolangUtils.booleanToFloat(bridge5_111.bridge$getArmor(EntityEquipmentSlotBridge.HEAD) != null);
         this.field31.value = MolangUtils.booleanToFloat(bridge5_111.bridge$getArmor(EntityEquipmentSlotBridge.CHEST) != null);
         this.field32.value = MolangUtils.booleanToFloat(bridge5_111.bridge$getArmor(EntityEquipmentSlotBridge.LEGS) != null);
         this.field33.value = MolangUtils.booleanToFloat(bridge5_111.bridge$getArmor(EntityEquipmentSlotBridge.FEET) != null);
         this.field34.value = MolangUtils.booleanToFloat(bridge5_111.bridge$isOnGround());
         this.field35.value = MolangUtils.booleanToFloat(bridge5_111.bridge$isSwimming());
         this.field17.value = 0.0;
         if (fov102.method19() == null) {
            this.field36.value = MolangUtils.booleanToFloat(bridge5_111.bridge$isBlocking());
         } else {
            boolean flag18;
            if (fov102.method19() instanceof ItemStackBridge) {
               flag18 = bridge5_111.bridge$getItemInUse().isPresent() && bridge5_111.bridge$getItemInUse().get() == fov102.method19();
            } else {
               flag18 = fov102.method19().bridge$isItemInUse();
            }

            this.field36.value = MolangUtils.booleanToFloat(
               fov102.method19().bridge$getLunarItemType() == LunarItemType.SHIELD && bridge5_111.bridge$isUsingItem() && flag18
            );
         }

         long number19 = Ref.method14();
         this.field37.value = (number19 - bridge5_111.bridge$getLastDamagedMillis()) / 1000.0;
         this.field38.value = (number19 - bridge5_111.bridge$getLastAttackedMillis()) / 1000.0;
         this.field39.value = (number19 - bridge5_111.bridge$getLastHurtMillis()) / 1000.0;
         this.field40.value = bridge5_111.bridge$getPosX();
         this.field41.value = bridge5_111.bridge$getPosY();
         this.field42.value = bridge5_111.bridge$getPosZ();
         if (bridge5_111.bridge$isSwimming()) {
            this.field43.value = bridge5_111.bridge$getRotationPitch();
         } else {
            this.field43.value = 0.0;
         }

         if (fov102.method4(RenderContextType.IN_WORLD)) {
            Bridge3_23 bridge3_2322 = bridge5_111.bridge$getWorld().bridge$getBlockAt(bridge5_111.bridge$getPosX(), bridge5_111.bridge$getPosY() + 1.0, bridge5_111.bridge$getPosZ());
            this.field44.value = MolangUtils.booleanToFloat(bridge3_2322.bridge$isWater());
            this.field45.value = this.method7(bridge5_111);
            this.field46.value = MolangUtils.booleanToFloat(this.method8(bridge5_111));
            boolean flag7 = bridge5_111.bridge$getWorld().bridge$isRaining();
            this.field47.value = MolangUtils.booleanToFloat(flag7);
         } else {
            this.field44.value = MolangUtils.booleanToFloat(false);
            this.field47.value = MolangUtils.booleanToFloat(false);
            this.field45.value = 0.0;
            this.field46.value = 0.0;
         }
      }

      CosmeticMetadata gui2handler320 = fov102.method23();
      if (gui2handler320 != null) {
         EmoteModel gui2iterator5 = (EmoteModel)gui2handler320.method4();
         gui2iterator5.method6().ifPresent(arg2x -> arg2x.method13().forEach(arg2xx -> {
            if (!gui2handler320.method5().has(arg2xx.getId())) {
               Inactive5.Type.loadDefaults(gui2handler320, arg2xx);
            }

            String text3x = arg2xx.method3().split("\\.")[1];
            JsonElement element4x = gui2handler320.method5().get(arg2xx.getId());
            if (element4x.isJsonPrimitive()) {
               JsonPrimitive json5x = element4x.getAsJsonPrimitive();
               if (json5x.isBoolean()) {
                  this.method3(VariableFlavor.OPTION, text3x, MolangUtils.booleanToFloat(json5x.getAsBoolean()));
               } else if (json5x.isNumber()) {
                  double value6x = MathUtils.method11(json5x.getAsDouble(), arg2xx.method6(), arg2xx.method8());
                  this.method3(VariableFlavor.OPTION, text3x, value6x);
               }
            }
         }));
      }

      fov102.method2().ifPresent(arg2x -> {
         this.method4(this.field48, fov102, AngleMath.wrapDegrees(Math.toDegrees(arg2x.bridge$bipedHead().bridge$getRotateAngleY())));
         this.field49.value = Math.toDegrees(arg2x.bridge$bipedHead().bridge$getRotateAngleX());
         this.method4(this.field50, fov102, Math.toDegrees(arg2x.bridge$bipedBody().bridge$getRotateAngleY()));
         this.method4(this.field51, fov102, Math.toDegrees(arg2x.bridge$bipedLeftArm().bridge$getRotateAngleX()));
         this.method4(this.field52, fov102, Math.toDegrees(arg2x.bridge$bipedLeftArm().bridge$getRotateAngleY()));
         this.method4(this.field53, fov102, Math.toDegrees(arg2x.bridge$bipedLeftArm().bridge$getRotateAngleZ()));
         this.method4(this.field54, fov102, Math.toDegrees(arg2x.bridge$bipedRightArm().bridge$getRotateAngleX()));
         this.method4(this.field55, fov102, Math.toDegrees(arg2x.bridge$bipedRightArm().bridge$getRotateAngleY()));
         this.method4(this.field56, fov102, Math.toDegrees(arg2x.bridge$bipedRightArm().bridge$getRotateAngleZ()));
         this.method4(this.field57, fov102, Math.toDegrees(arg2x.bridge$bipedLeftLeg().bridge$getRotateAngleX()));
         this.method4(this.field58, fov102, Math.toDegrees(arg2x.bridge$bipedLeftLeg().bridge$getRotateAngleY()));
         this.method4(this.field59, fov102, Math.toDegrees(arg2x.bridge$bipedLeftLeg().bridge$getRotateAngleZ()));
         this.method4(this.field60, fov102, Math.toDegrees(arg2x.bridge$bipedRightLeg().bridge$getRotateAngleX()));
         this.method4(this.field61, fov102, Math.toDegrees(arg2x.bridge$bipedRightLeg().bridge$getRotateAngleY()));
         this.method4(this.field62, fov102, Math.toDegrees(arg2x.bridge$bipedRightLeg().bridge$getRotateAngleZ()));
         this.field63.value = MolangUtils.booleanToFloat(arg2x.bridge$isSlim());
      });
      Data data21 = (Data)Ref.method4().method53().method63().get(bridge5_111.bridge$getUniqueID());
      if (data21 != null) {
         this.field64.value = MolangUtils.booleanToFloat(data21.method10());
      }

      this.field65.value = Double.parseDouble(Bridge.getMinecraftVersion().method44());
      this.field66.value = EventTick.field1;
      this.field68.value = MolangUtils.booleanToFloat(fov102.method4(RenderContextType.IN_GUI));
      this.field69.value = MolangUtils.booleanToFloat(fov102.method4(RenderContextType.IN_PLAYER_MODEL));
   }

   private double method7(Bridge5_11 bridge5_111) {
      int number2 = (int)bridge5_111.bridge$getPosY();
      Bridge3_23 bridge3_233 = bridge5_111.bridge$getWorld().bridge$getBlockAt(bridge5_111.bridge$getPosX(), ++number2, bridge5_111.bridge$getPosZ());
      if (bridge3_233.bridge$isWater()) {
         int number4 = Ref.method8().bridge$getMaxBuildHeight();

         while (number2 < number4) {
            if (bridge5_111.bridge$getWorld().bridge$getBlockAt(bridge5_111.bridge$getPosX(), ++number2, bridge5_111.bridge$getPosZ()).bridge$isAir()) {
               return number2 - bridge5_111.bridge$getPosY() - 1.0;
            }
         }
      }

      return 0.0;
   }

   private boolean method8(Bridge5_11 bridge5_111) {
      int number2 = (int)bridge5_111.bridge$getPosY() + 2;
      Bridge3_23 bridge3_233 = bridge5_111.bridge$getWorld().bridge$getBlockAt(bridge5_111.bridge$getPosX(), number2, bridge5_111.bridge$getPosZ());
      if (bridge3_233.bridge$isWater()) {
         int number4 = Ref.method8().bridge$getMaxBuildHeight();

         while (number2 < number4) {
            bridge3_233 = bridge5_111.bridge$getWorld().bridge$getBlockAt(bridge5_111.bridge$getPosX(), ++number2, bridge5_111.bridge$getPosZ());
            if (!bridge3_233.bridge$isWater()) {
               return !bridge3_233.bridge$isAir();
            }
         }
      }

      return !bridge3_233.bridge$isAir() && !bridge3_233.bridge$isWater();
   }

   @Generated
   public ExecutionContext getContext() {
      return this.field2;
   }

   @Generated
   public EvaluatorImpl method9() {
      return this.field3;
   }

   @Generated
   public MolangScope method10() {
      return this.field4;
   }

   private class BoneQuery {
      private final IBone field1;
      private final MolangVariable field2;
      private final MolangVariable field3;
      private final MolangVariable field4;
      private final MolangVariable field5;
      private final MolangVariable field6;
      private final MolangVariable field7;
      private final MolangVariable field8;
      private final MolangVariable field9;
      private final MolangVariable field10;

      public BoneQuery(IBone ibone2) {
         this.field1 = ibone2;
         if (ibone2 instanceof IBoneSerializer iboneserializer3) {
            this.field2 = MolangRuntime.this.field4.method7("query." + iboneserializer3.field9);
            this.field3 = MolangRuntime.this.field4.method7("query." + iboneserializer3.field10);
            this.field4 = MolangRuntime.this.field4.method7("query." + iboneserializer3.field11);
            this.field5 = MolangRuntime.this.field4.method7("query." + iboneserializer3.field12);
            this.field6 = MolangRuntime.this.field4.method7("query." + iboneserializer3.field13);
            this.field7 = MolangRuntime.this.field4.method7("query." + iboneserializer3.field14);
            this.field8 = MolangRuntime.this.field4.method7("query." + iboneserializer3.field15);
            this.field9 = MolangRuntime.this.field4.method7("query." + iboneserializer3.field16);
            this.field10 = MolangRuntime.this.field4.method7("query." + iboneserializer3.field17);
         } else {
            this.field2 = MolangRuntime.this.field4.method7("query.bone_" + ibone2.getName() + "_pos_x");
            this.field3 = MolangRuntime.this.field4.method7("query.bone_" + ibone2.getName() + "_pos_y");
            this.field4 = MolangRuntime.this.field4.method7("query.bone_" + ibone2.getName() + "_pos_z");
            this.field5 = MolangRuntime.this.field4.method7("query.bone_" + ibone2.getName() + "_rot_x");
            this.field6 = MolangRuntime.this.field4.method7("query.bone_" + ibone2.getName() + "_rot_y");
            this.field7 = MolangRuntime.this.field4.method7("query.bone_" + ibone2.getName() + "_rot_z");
            this.field8 = MolangRuntime.this.field4.method7("query.bone_" + ibone2.getName() + "_scale_x");
            this.field9 = MolangRuntime.this.field4.method7("query.bone_" + ibone2.getName() + "_scale_y");
            this.field10 = MolangRuntime.this.field4.method7("query.bone_" + ibone2.getName() + "_scale_z");
         }
      }

      public void update() {
         this.field2.value = this.field1.getPositionX();
         this.field3.value = this.field1.getPositionY();
         this.field4.value = this.field1.getPositionZ();
         this.field5.value = this.field1.getRotationX();
         this.field6.value = this.field1.getRotationY();
         this.field7.value = this.field1.getRotationZ();
         this.field8.value = this.field1.getScaleX();
         this.field9.value = this.field1.getScaleY();
         this.field10.value = this.field1.getScaleZ();
      }
   }
}
