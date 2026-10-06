package com.moonsworth.lunar.client.cosmetics.molang;

import com.eliotlash.molang.utils.MolangUtils;
import com.moonsworth.lunar.bridge.Bridge;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import lombok.Generated;

public class QueryDefaults {
   private static Object2DoubleMap<String> field1 = new Object2DoubleOpenHashMap();

   public QueryDefaults() {
   }

   public static void method1() {
      field1.put("query.anim_time", 0.0);
      field1.put("query.ground_speed", 0.0);
      field1.put("query.absolute_speed", 0.0);
      field1.put("query.x_velocity", 0.0);
      field1.put("query.y_velocity", 0.0);
      field1.put("query.z_velocity", 0.0);
      field1.put("query.local_x_velocity", 0.0);
      field1.put("query.local_y_velocity", 0.0);
      field1.put("query.local_z_velocity", 0.0);
      field1.put("query.head_y_rotation", 0.0);
      field1.put("query.head_x_rotation", 0.0);
      field1.put("query.partial_ticks", 0.0);
      field1.put("query.world_x_pos", 0.0);
      field1.put("query.world_y_pos", 0.0);
      field1.put("query.world_z_pos", 0.0);
      field1.put("query.minecraft_version", Double.parseDouble(Bridge.getMinecraftVersion().method44()));
      field1.put("query.body_x_rotation", 0.0);
      field1.put("query.body_y_rotation", 0.0);
      field1.put("query.distance_from_surface", 0.0);
      field1.put("query.last_damaged", 0.0);
      field1.put("query.last_attacked_someone", 0.0);
      field1.put("query.last_attacked_by", 0.0);
      field1.put("query.left_arm_x_rotation", 0.0);
      field1.put("query.left_arm_y_rotation", 0.0);
      field1.put("query.left_arm_z_rotation", 0.0);
      field1.put("query.right_arm_x_rotation", 0.0);
      field1.put("query.right_arm_y_rotation", 0.0);
      field1.put("query.right_arm_z_rotation", 0.0);
      field1.put("query.left_leg_x_rotation", 0.0);
      field1.put("query.left_leg_y_rotation", 0.0);
      field1.put("query.left_leg_z_rotation", 0.0);
      field1.put("query.right_leg_x_rotation", 0.0);
      field1.put("query.right_leg_y_rotation", 0.0);
      field1.put("query.right_leg_z_rotation", 0.0);
      field1.put("query.tool_material", -1.0);
      field1.put("query.is_sprinting", MolangUtils.booleanToFloat(false));
      field1.put("query.is_running", MolangUtils.booleanToFloat(false));
      field1.put("query.is_crouching", MolangUtils.booleanToFloat(false));
      field1.put("query.is_on_fire", MolangUtils.booleanToFloat(false));
      field1.put("query.is_in_water", MolangUtils.booleanToFloat(false));
      field1.put("query.is_riding_boat", MolangUtils.booleanToFloat(false));
      field1.put("query.head_slot_equipped", MolangUtils.booleanToFloat(false));
      field1.put("query.body_slot_equipped", MolangUtils.booleanToFloat(false));
      field1.put("query.legs_slot_equipped", MolangUtils.booleanToFloat(false));
      field1.put("query.feet_slot_equipped", MolangUtils.booleanToFloat(false));
      field1.put("query.is_on_ground", MolangUtils.booleanToFloat(false));
      field1.put("query.is_raining", MolangUtils.booleanToFloat(false));
      field1.put("query.is_swimming", MolangUtils.booleanToFloat(false));
      field1.put("query.is_flying", MolangUtils.booleanToFloat(false));
      field1.put("query.is_elytra_flying", MolangUtils.booleanToFloat(false));
      field1.put("query.surface_blocked", MolangUtils.booleanToFloat(false));
      field1.put("query.is_moving", MolangUtils.booleanToFloat(false));
      field1.put("query.is_in_gui", MolangUtils.booleanToFloat(false));
      field1.put("query.is_in_preview_model", MolangUtils.booleanToFloat(false));
      field1.put("query.is_slim_model", MolangUtils.booleanToFloat(false));
      field1.put("query.is_radio_playing", MolangUtils.booleanToFloat(false));
      field1.put("query.is_blocking", MolangUtils.booleanToFloat(false));
   }

   @Generated
   public static Object2DoubleMap<String> method2() {
      return field1;
   }
}
