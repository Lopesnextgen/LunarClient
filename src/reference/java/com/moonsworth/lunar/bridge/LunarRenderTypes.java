package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.config.Config;
import it.unimi.dsi.fastutil.floats.Float2ObjectMap;
import it.unimi.dsi.fastutil.floats.Float2ObjectOpenHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

public class LunarRenderTypes {
   public static ResourceLocationBridge field1 = ResourceLocationBridge.create("");
   public static RenderLayerBridge field2;
   @com.moonsworth.lunar.ichor.Annotation2(max = 34)
   public static RenderLayerBridge field3;
   public static RenderLayerBridge field4;
   public static RenderLayerBridge field5;
   public static RenderTypeResolver field6;
   public static RenderTypeResolver field7;
   public static RenderTypeResolver field8;
   public static RenderTypeResolver field9;
   public static RenderTypeResolver field10;
   @com.moonsworth.lunar.ichor.Annotation2(max = 29)
   public static RenderTypeResolver field11;
   public static RenderTypeResolver field12;
   public static RenderTypeResolver field13;
   public static RenderLayerBridge field14;
   public static RenderLayerBridge field15;
   public static RenderLayerBridge field16;
   public static RenderLayerBridge field17;
   public static RenderLayerBridge field18;
   public static RenderLayerBridge field19;
   public static RenderLayerBridge field20;
   public static RenderLayerBridge field21;
   public static RenderLayerBridge field22;
   public static RenderLayerBridge field23;
   public static RenderLayerBridge field24;
   public static RenderLayerBridge field25;
   public static RenderLayerBridge field26;
   public static RenderLayerBridge field27;
   public static RenderLayerBridge field28;
   public static RenderLayerBridge field29;
   public static RenderLayerBridge field30;
   public static RenderLayerBridge field31;
   public static RenderTypeResolver field32;
   public static RenderTypeResolver field33;
   public static RenderTypeResolver field34;
   public static RenderTypeResolver field35;
   public static RenderTypeResolver field36;
   public static RenderTypeResolver field37;
   public static RenderTypeResolver field38;
   public static RenderTypeResolver field39;
   public static RenderLayerBridge field40;
   public static MixinHelper2_4 field41;
   public static MixinHelper2_4 field42;
   public static RenderTypeResolver field43;
   public static RenderTypeResolver field44;
   public static RenderTypeResolver field45;
   public static RenderTypeResolver field46;
   public static RenderTypeResolver field47;
   public static RenderTypeResolver field48;
   public static RenderLayerBridge field49;
   public static RenderLayerBridge field50;
   public static RenderLayerBridge field51;
   public static RenderLayerBridge field52;
   public static RenderLayerBridge field53;
   public static RenderTypeResolver field54;
   public static RenderTypeResolver field55;
   public static RenderLayerBridge field56;
   public static RenderLayerBridge field57;
   public static RenderLayerBridge field58;
   public static RenderTypeResolver field59;
   public static RenderTypeResolver field60;
   public static RenderTypeResolver field61;
   public static RenderLayerBridge field62;
   public static RenderLayerBridge field63;
   @com.moonsworth.lunar.ichor.Annotation2(max = 5, onReturn = true)
   public static Function<Bridge_63, RenderLayerBridge> field64 = var0 -> new RenderLayerBridge() {
      @Override
      public String bridge$getName() {
         return "LUNAR_LEGACY_DYNAMIC_QUADS";
      }

      @Override
      public DrawMode bridge$getVertexFormatMode() {
         return DrawMode.QUADS;
      }

      @Override
      public Bridge_63 bridge$getVertexFormat() {
         return var0;
      }

      @NotNull
      @Override
      public Bridge_45 bridge$getRenderPipeline() {
         throw new IllegalStateException("LUNAR_LEGACY_DYNAMIC_QUADS doesn't have a render pipeline!");
      }

      @Override
      public Optional<Bridge6_8> bridge$getShaderUniforms() {
         return Optional.empty();
      }
   };

   public static void method1() {
      field33 = method3(LunarRenderTypes::method6);
      field36 = method3(LunarRenderTypes::method9);
      field32 = method3(LunarRenderTypes::method8);
      if (Bridge.getMinecraftVersion().method4(Config.field30)) {
         field35 = method3(LunarRenderTypes::method10);
      }

      field37 = method3(LunarRenderTypes::method11);
      field38 = method3(LunarRenderTypes::method12);
      field19 = Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method9(MixinHelper7$Type4.SMOOTH)
         .method14(MixinHelper_2.field43, "lunar_gui_triangles", 256, false, false, false);
      field23 = Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method14(MixinHelper_2.field47, "lunar_gui_triangles_depth", 256, false, false, false);
      field24 = Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method14(MixinHelper_2.field48, "lunar_gui_triangles_depth_write", 256, false, false, false);
      field54 = method3(LunarRenderTypes::method31);
   }

   public static void method2(boolean var0) {
      if (var0) {
         field2 = Bridge.method8()
            .method81()
            .method1(MixinHelper7$Type3.LIGHTMAP)
            .method8(MixinHelper7$Data7.method4())
            .method14(MixinHelper_2.field16, "solid", 4194304, true, false, true);
         field3 = Bridge.method8()
            .method81()
            .method1(MixinHelper7$Type3.LIGHTMAP)
            .method8(MixinHelper7$Data7.method4())
            .method14(MixinHelper_2.field17, "cutout_mipped", 4194304, true, false, true);
         field4 = Bridge.method8()
            .method81()
            .method1(MixinHelper7$Type3.LIGHTMAP)
            .method8(MixinHelper7$Data7.method3())
            .method14(MixinHelper_2.field18, "cutout", 786432, true, false, true);
         field5 = Bridge.method8()
            .method81()
            .method7(MixinHelper7$Data8.method1())
            .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
            .method4(MixinHelper7$Type6.ITEM_ENTITY_TARGET)
            .method14(MixinHelper_2.field22, "lines", 1536, false, false, false);
         field14 = Bridge.method8()
            .method81()
            .method8(
               MixinHelper7$Data7.method1(
                  ResourceLocationBridge.create(
                     Bridge.getMinecraftVersion().method5(Config.field17) ? "textures/misc/enchanted_item_glint.png" : "textures/misc/enchanted_glint_item.png"
                  )
               )
            )
            .method6(MixinHelper7$Type5.GLINT_TEXTURING)
            .method14(MixinHelper_2.field31, "glint", 1536, false, false, false);
         if (Bridge.getMinecraftVersion().method5(Config.field30)) {
            field11 = method3(LunarRenderTypes::method5);
         }
      }

      field6 = method3(LunarRenderTypes::method18);
      field7 = method3(LunarRenderTypes::method19);
      field8 = method3(LunarRenderTypes::method20);
      field9 = method3(LunarRenderTypes::method21);
      field10 = method3(LunarRenderTypes::method24);
      field46 = method3(LunarRenderTypes::method26);
      field47 = method3(LunarRenderTypes::method27);
      field48 = method3(LunarRenderTypes::method25);
      field15 = Bridge.method8().method81().method14(MixinHelper_2.field39, "lunar_overlay", 256, false, false, false);
      field16 = Bridge.method8().method81().method14(MixinHelper_2.field40, "lunar_overlay_no_cull", 256, false, false, false);
      field17 = Bridge.method8().method81().method14(MixinHelper_2.field41, "lunar_overlay_offset", 256, false, false, false);
      field18 = Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method14(MixinHelper_2.field42, "lunar_gui_triangle_strip", 256, false, false, false);
      field29 = Bridge.method8().method81().method14(MixinHelper_2.field20, "lunar_gui_quads", 256, false, false, false);
      field30 = Bridge.method8().method81().method14(MixinHelper_2.field32, "lunar_gui_quads_no_depth", 256, false, false, false);
      field31 = Bridge.method8().method81().method14(MixinHelper_2.field33, "lunar_gui_quads_depth_write_no_test", 256, false, false, false);
      field25 = Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method14(MixinHelper_2.field49, "lunar_selection_box", 256, false, false, false);
      field39 = method3(LunarRenderTypes::method16);
      field45 = method3(LunarRenderTypes::method17);
      field41 = method4(LunarRenderTypes::method13);
      field42 = method4(LunarRenderTypes::method14);
      field40 = method15();
      field43 = method3(LunarRenderTypes::method28);
      field44 = method3(LunarRenderTypes::method29);
      field49 = Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method1(MixinHelper7$Type3.LIGHTMAP)
         .method14(MixinHelper_2.field54, "lunar_waypoint_beam", 256, false, false, false);
      field50 = Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method1(MixinHelper7$Type3.LIGHTMAP)
         .method8(MixinHelper7$Data7.method1(ResourceLocationBridge.create("textures/misc/forcefield.png")))
         .method14(MixinHelper_2.field56, "lunar_border", 256, false, false, false);
      field51 = Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method1(MixinHelper7$Type3.LIGHTMAP)
         .method14(MixinHelper_2.field55, "lunar_hologram", 256, false, false, false);
      field52 = Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.NO_LAYERING)
         .method1(MixinHelper7$Type3.LIGHTMAP)
         .method14(MixinHelper_2.field57, "lunar_skyblock_esp", 256, false, false, false);
      field53 = Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.NO_LAYERING)
         .method1(MixinHelper7$Type3.LIGHTMAP)
         .method14(MixinHelper_2.field58, "lunar_translucent_quads", 256, false, false, false);
      field55 = method3(LunarRenderTypes::method30);
      field12 = method3(LunarRenderTypes::method32);
      field13 = method3(LunarRenderTypes::method33);
      field26 = Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method14(MixinHelper_2.field64, "lunar_block_overlay_generic", 256, false, false, false);
      field27 = Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method14(MixinHelper_2.field65, "lunar_block_overlay_inverted", 256, false, false, false);
      field28 = Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method14(MixinHelper_2.field66, "lunar_block_overlay_darken", 256, false, false, false);
      field22 = Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method9(MixinHelper7$Type4.SMOOTH)
         .method14(MixinHelper_2.field46, "lunar_gui_triangles_no_write", 256, false, false, false);
      field56 = Bridge.method8()
         .method81()
         .method11(var0x -> {
            if (!Bridge.getMinecraftVersion().method6(Config.field6)) {
               var0x.method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING);
            }
         })
         .method8(MixinHelper7$Data7.method1(ResourceLocationBridge.create("lunar", "hud_caching_texture")))
         .method9(MixinHelper7$Type4.SMOOTH)
         .method14(MixinHelper_2.field68, "lunar_gui_texture_color", 256, false, false, false);
      field57 = Bridge.method8()
         .method81()
         .method11(var0x -> {
            if (!Bridge.getMinecraftVersion().method6(Config.field6)) {
               var0x.method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING);
            }
         })
         .method8(MixinHelper7$Data7.method1(ResourceLocationBridge.create("lunar", "stencil_emulator")))
         .method14(MixinHelper_2.field69, "lunar_stencil_emulation", 256, false, false, false);
      field58 = Bridge.method8()
         .method81()
         .method11(var0x -> {
            if (!Bridge.getMinecraftVersion().method6(Config.field6)) {
               var0x.method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING);
            }
         })
         .method8(MixinHelper7$Data7.method1(ResourceLocationBridge.create("lunar", "stencil_emulator")))
         .method14(MixinHelper_2.field70, "lunar_stencil_emulation_inverted", 256, false, false, false);
      field20 = Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method9(MixinHelper7$Type4.SMOOTH)
         .method14(MixinHelper_2.field44, "lunar_gui_triangles_inverted", 256, false, false, false);
      field59 = method3(LunarRenderTypes::method34);
      field60 = method3(LunarRenderTypes::method22);
      field61 = method3(LunarRenderTypes::method23);
      field21 = Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method14(MixinHelper_2.field45, "lunar_gui_triangles_stencil", 256, false, false, false);
      field34 = method3(LunarRenderTypes::method7);
      field62 = Bridge.method8()
         .method81()
         .method8(MixinHelper7$Data7.method1(ResourceLocationBridge.create("textures/font/ascii.png")))
         .method9(MixinHelper7$Type4.SMOOTH)
         .method14(MixinHelper_2.field75, "lunar_light_overlay_text", 256, false, false, false);
      field63 = Bridge.method8()
         .method81()
         .method8(MixinHelper7$Data7.method1(ResourceLocationBridge.create("lunar", "textures/height_limit/barrier.png")))
         .method9(MixinHelper7$Type4.SMOOTH)
         .method14(MixinHelper_2.field75, "lunar_height_limit_barrier", 256, false, false, false);
   }

   public static RenderTypeResolver method3(final RenderTypeResolver var0) {
      return new RenderTypeResolver() {
         private final Map<ResourceLocationBridge, RenderLayerBridge> field1 = new HashMap<>();

         @Override
         public RenderLayerBridge get(ResourceLocationBridge var1) {
            return this.field1.computeIfAbsent(var1, var0::get);
         }
      };
   }

   private static MixinHelper2_4 method4(final MixinHelper2_4 var0) {
      return new MixinHelper2_4() {
         private final Float2ObjectMap<RenderLayerBridge> field1 = new Float2ObjectOpenHashMap();

         @Override
         public RenderLayerBridge get(float var1) {
            return (RenderLayerBridge)this.field1.computeIfAbsent(var1, var0::get);
         }
      };
   }

   private static RenderLayerBridge method5(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8().method81().method8(MixinHelper7$Data7.method1(var0)).method14(MixinHelper_2.field29, "vignette", 256, false, false, false);
   }

   private static RenderLayerBridge method6(ResourceLocationBridge var0) {
      method35(var0);
      Bridge_45 var1 = Bridge.getMinecraftVersion().method4(Config.field31) ? MixinHelper_2.field21 : MixinHelper_2.field34;
      return Bridge.method8()
         .method81()
         .method8(MixinHelper7$Data7.method1(var0))
         .method9(MixinHelper7$Type4.SMOOTH)
         .method14(var1, "lunar_gui_texture_color", 256, false, false, false);
   }

   private static RenderLayerBridge method7(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8()
         .method81()
         .method8(MixinHelper7$Data7.method1(var0))
         .method9(MixinHelper7$Type4.SMOOTH)
         .method14(MixinHelper_2.field35, "lunar_gui_texture_color_depth", 256, false, false, false);
   }

   private static RenderLayerBridge method8(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8()
         .method81()
         .method8(MixinHelper7$Data7.method1(var0))
         .method9(MixinHelper7$Type4.SMOOTH)
         .method14(MixinHelper_2.field36, "lunar_gui_texture_glint", 256, false, false, false);
   }

   private static RenderLayerBridge method9(ResourceLocationBridge var0) {
      method35(var0);
      Bridge_45 var1 = Bridge.getMinecraftVersion().method4(Config.field31) ? MixinHelper_2.field21 : MixinHelper_2.field34;
      return Bridge.method8()
         .method81()
         .method8(MixinHelper7$Data7.method2(var0, true, false))
         .method9(MixinHelper7$Type4.SMOOTH)
         .method14(var1, "lunar_gui_texture_color_blur", 256, false, false, false);
   }

   private static RenderLayerBridge method10(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method8(MixinHelper7$Data7.method1(var0))
         .method14(MixinHelper_2.field37, "lunar_gui_texture_color_no_alpha", 256, false, false, false);
   }

   private static RenderLayerBridge method11(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method8(MixinHelper7$Data7.method1(var0))
         .method14(MixinHelper_2.field38, "lunar_gui_texture_color", 256, false, false, false);
   }

   private static RenderLayerBridge method12(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method8(MixinHelper7$Data7.method2(var0, true, false))
         .method14(MixinHelper_2.field38, "lunar_gui_texture_color_blur", 256, false, false, false);
   }

   private static RenderLayerBridge method13(float var0) {
      return Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method4(MixinHelper7$Type6.ITEM_ENTITY_TARGET)
         .method7(MixinHelper7$Data8.method2(var0))
         .method14(MixinHelper_2.field22, "lunar_lines", 256, false, false, false);
   }

   private static RenderLayerBridge method14(float var0) {
      return Bridge.method8()
         .method81()
         .method3(MixinHelper7$Type.NO_LAYERING)
         .method4(MixinHelper7$Type6.ITEM_ENTITY_TARGET)
         .method7(MixinHelper7$Data8.method2(var0))
         .method14(MixinHelper_2.field63, "lunar_lines_esp", 256, false, false, false);
   }

   private static RenderLayerBridge method15() {
      return Bridge.method8().method81().method3(MixinHelper7$Type.NO_LAYERING).method14(MixinHelper_2.field50, "lunar_3D", 256, true, false, true);
   }

   private static RenderLayerBridge method16(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8()
         .method81()
         .method1(MixinHelper7$Type3.LIGHTMAP)
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method8(MixinHelper7$Data7.method1(var0))
         .method14(MixinHelper_2.field23, "lunar_particle", 256, false, true, false);
   }

   private static RenderLayerBridge method17(ResourceLocationBridge var0) {
      method35(var0);
      if (var0.bridge$getDomain().equalsIgnoreCase("lunar") && var0.bridge$getPath().equalsIgnoreCase("lunar:empty")) {
         var0 = field1;
      }

      return Bridge.method8()
         .method81()
         .method10(MixinHelper7$Type2.DEFAULT)
         .method1(MixinHelper7$Type3.LIGHTMAP)
         .method2(MixinHelper7$Type7.OVERLAY)
         .method8(MixinHelper7$Data7.method1(var0))
         .method12(true)
         .method9(MixinHelper7$Type4.SMOOTH)
         .method14(MixinHelper_2.field51, "lunar_cosmetic", 256, false, false, true);
   }

   private static RenderLayerBridge method18(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8()
         .method81()
         .method1(MixinHelper7$Type3.LIGHTMAP)
         .method2(MixinHelper7$Type7.OVERLAY)
         .method8(MixinHelper7$Data7.method1(var0))
         .method14(MixinHelper_2.field25, "lunar_entity_solid", 256, false, false, true);
   }

   private static RenderLayerBridge method19(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8()
         .method81()
         .method10(MixinHelper7$Type2.DEFAULT)
         .method1(MixinHelper7$Type3.LIGHTMAP)
         .method2(MixinHelper7$Type7.OVERLAY)
         .method8(MixinHelper7$Data7.method1(var0))
         .method14(MixinHelper_2.field24, "lunar_entity_cutout", 256, false, true, true);
   }

   private static RenderLayerBridge method20(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8()
         .method81()
         .method1(MixinHelper7$Type3.LIGHTMAP)
         .method2(MixinHelper7$Type7.OVERLAY)
         .method8(MixinHelper7$Data7.method1(var0))
         .method14(MixinHelper_2.field26, "lunar_entity_cutout_no_cull", 256, false, true, true);
   }

   private static RenderLayerBridge method21(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8()
         .method81()
         .method10(MixinHelper7$Type2.DEFAULT)
         .method1(MixinHelper7$Type3.LIGHTMAP)
         .method2(MixinHelper7$Type7.OVERLAY)
         .method8(MixinHelper7$Data7.method1(var0))
         .method14(MixinHelper_2.field67, "lunar_entity_cutout_no_cull", 1536, false, false, true);
   }

   private static RenderLayerBridge method22(ResourceLocationBridge var0) {
      return Bridge.method8()
         .method81()
         .method1(MixinHelper7$Type3.LIGHTMAP)
         .method2(MixinHelper7$Type7.OVERLAY)
         .method8(MixinHelper7$Data7.method1(var0))
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method14(MixinHelper_2.field73, "lunar_armor_cutout_no_cull_triangles", 1536, true, false, true);
   }

   private static RenderLayerBridge method23(ResourceLocationBridge var0) {
      return Bridge.method8()
         .method81()
         .method1(MixinHelper7$Type3.LIGHTMAP)
         .method2(MixinHelper7$Type7.OVERLAY)
         .method8(MixinHelper7$Data7.method1(var0))
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method14(MixinHelper_2.field74, "lunar_armor_decal_cutout_no_cull_triangles", 1536, true, false, true);
   }

   private static RenderLayerBridge method24(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8()
         .method81()
         .method10(MixinHelper7$Type2.DEFAULT)
         .method1(MixinHelper7$Type3.LIGHTMAP)
         .method2(MixinHelper7$Type7.OVERLAY)
         .method8(MixinHelper7$Data7.method1(var0))
         .method14(MixinHelper_2.field52, "lunar_entity_translucent", 256, false, true, true);
   }

   private static RenderLayerBridge method25(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8()
         .method81()
         .method10(MixinHelper7$Type2.DEFAULT)
         .method1(MixinHelper7$Type3.LIGHTMAP)
         .method2(MixinHelper7$Type7.OVERLAY)
         .method8(MixinHelper7$Data7.method1(var0))
         .method14(MixinHelper_2.field53, "lunar_entity_translucent_no_depth_write", 256, false, true, false);
   }

   private static RenderLayerBridge method26(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8()
         .method81()
         .method11(var0x -> {
            if (Bridge.getMinecraftVersion().method19()) {
               var0x.method1(MixinHelper7$Type3.LIGHTMAP);
            }
         })
         .method2(MixinHelper7$Type7.OVERLAY)
         .method8(MixinHelper7$Data7.method1(var0))
         .method14(MixinHelper_2.field27, "lunar_entity_translucent_no_cull", 256, false, true, true);
   }

   private static RenderLayerBridge method27(ResourceLocationBridge var0) {
      return Bridge.method8()
         .method81()
         .method11(var0x -> {
            if (Bridge.getMinecraftVersion().method19()) {
               var0x.method1(MixinHelper7$Type3.LIGHTMAP);
            }
         })
         .method2(MixinHelper7$Type7.OVERLAY)
         .method8(MixinHelper7$Data7.method1(var0))
         .method14(MixinHelper_2.field28, "lunar_entity_translucent_cull", 256, false, true, true);
   }

   private static RenderLayerBridge method28(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8()
         .method81()
         .method11(var0x -> {
            if (Bridge.getMinecraftVersion().method19()) {
               var0x.method1(MixinHelper7$Type3.LIGHTMAP);
            }
         })
         .method2(MixinHelper7$Type7.OVERLAY)
         .method8(MixinHelper7$Data7.method1(var0))
         .method9(MixinHelper7$Type4.SMOOTH)
         .method14(MixinHelper_2.field60, "lunar_cloth_cloak", 256, false, false, true);
   }

   private static RenderLayerBridge method29(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8()
         .method81()
         .method1(MixinHelper7$Type3.LIGHTMAP)
         .method2(MixinHelper7$Type7.OVERLAY)
         .method8(MixinHelper7$Data7.method1(var0))
         .method9(MixinHelper7$Type4.SMOOTH)
         .method14(MixinHelper_2.field61, "lunar_cloth_cloak_outline", 256, false, false, true);
   }

   private static RenderLayerBridge method30(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8()
         .method81()
         .method11(var0x -> {
            if (Bridge.getMinecraftVersion().method19()) {
               var0x.method1(MixinHelper7$Type3.LIGHTMAP);
            }
         })
         .method2(MixinHelper7$Type7.OVERLAY)
         .method8(MixinHelper7$Data7.method1(var0))
         .method12(true)
         .method14(MixinHelper_2.field62, "lunar_cloak", 256, false, true, true);
   }

   private static RenderLayerBridge method31(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8()
         .method81()
         .method8(MixinHelper7$Data7.method1(var0))
         .method14(MixinHelper_2.field59, "lunar_gui_textured_premultiplied", 256, false, false, false);
   }

   private static RenderLayerBridge method32(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8().method81().method8(MixinHelper7$Data7.method1(var0)).method14(MixinHelper_2.field30, "lunar_crosshair", 256, false, false, false);
   }

   private static RenderLayerBridge method33(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8()
         .method81()
         .method8(MixinHelper7$Data7.method2(var0, true, false))
         .method14(MixinHelper_2.field30, "lunar_crosshair_blur", 256, false, false, false);
   }

   private static RenderLayerBridge method34(ResourceLocationBridge var0) {
      method35(var0);
      return Bridge.method8()
         .method81()
         .method8(MixinHelper7$Data7.method1(var0))
         .method3(MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING)
         .method9(MixinHelper7$Type4.SMOOTH)
         .method10(MixinHelper7$Type2.DEFAULT)
         .method1(MixinHelper7$Type3.LIGHTMAP)
         .method14(MixinHelper_2.field71, "lunar_sprays", 256, false, false, false);
   }

   public static void method35(ResourceLocationBridge var0) {
      if (var0 == null) {
         LogManager.getLogger("Lunar Client").error("Creating rendertype with null resource location", new Throwable());
      }
   }
}
