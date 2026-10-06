package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.config.Config;

public class MixinHelper_2 {
   public static Bridge$Extension field1;
   public static Bridge$Extension field2;
   public static Bridge$Extension field3;
   public static Bridge$Extension field4;
   public static Bridge$Extension field5;
   public static Bridge$Extension field6;
   public static Bridge$Extension field7;
   public static Bridge$Extension field8;
   public static Bridge$Extension field9;
   public static Bridge$Extension field10;
   public static Bridge$Extension field11;
   public static Bridge$Extension field12;
   public static Bridge$Extension field13;
   public static Bridge$Extension field14;
   public static Bridge$Extension field15;
   public static Bridge_45 field16;
   public static Bridge_45 field17;
   public static Bridge_45 field18;
   public static Bridge_45 field19;
   public static Bridge_45 field20;
   public static Bridge_45 field21;
   public static Bridge_45 field22;
   public static Bridge_45 field23;
   public static Bridge_45 field24;
   public static Bridge_45 field25;
   public static Bridge_45 field26;
   public static Bridge_45 field27;
   public static Bridge_45 field28;
   public static Bridge_45 field29;
   public static Bridge_45 field30;
   public static Bridge_45 field31;
   public static Bridge_45 field32;
   public static Bridge_45 field33;
   public static Bridge_45 field34;
   public static Bridge_45 field35;
   public static Bridge_45 field36;
   public static Bridge_45 field37;
   public static Bridge_45 field38;
   public static Bridge_45 field39;
   public static Bridge_45 field40;
   public static Bridge_45 field41;
   public static Bridge_45 field42;
   public static Bridge_45 field43;
   public static Bridge_45 field44;
   public static Bridge_45 field45;
   public static Bridge_45 field46;
   public static Bridge_45 field47;
   public static Bridge_45 field48;
   public static Bridge_45 field49;
   public static Bridge_45 field50;
   public static Bridge_45 field51;
   public static Bridge_45 field52;
   public static Bridge_45 field53;
   public static Bridge_45 field54;
   public static Bridge_45 field55;
   public static Bridge_45 field56;
   public static Bridge_45 field57;
   public static Bridge_45 field58;
   public static Bridge_45 field59;
   public static Bridge_45 field60;
   public static Bridge_45 field61;
   public static Bridge_45 field62;
   public static Bridge_45 field63;
   public static Bridge_45 field64;
   public static Bridge_45 field65;
   public static Bridge_45 field66;
   public static Bridge_45 field67;
   public static Bridge_45 field68;
   public static Bridge_45 field69;
   public static Bridge_45 field70;
   public static Bridge_45 field71;
   public static Bridge_45 field72;
   public static Bridge_45 field73;
   public static Bridge_45 field74;
   public static Bridge_45 field75;

   public static void method1(boolean var0, BridgeImplementation.Extension var1) {
      if (var0) {
         method5();
         field21 = Bridge.method8().method80().method42(field10).method27(DepthTestMode.NO_DEPTH_TEST).method1("core/position_tex_color").method44();
      }

      method4();
      Config var2 = Bridge.method8().getMinecraftVersion();
      boolean var3 = var2.method6(Config.field18);
      boolean var4 = var2.method4(Config.field40);
      field34 = Bridge.method8().method80().method42(field10).method27(DepthTestMode.NO_DEPTH_TEST).method1("core/position_tex_color").method44();
      field36 = Bridge.method8()
         .method80()
         .method42(field10)
         .method27(DepthTestMode.NO_DEPTH_TEST)
         .method1("core/position_tex_color")
         .method32(Bridge2_15.field6)
         .method44();
      field38 = Bridge.method8().method80().method42(field11).method27(DepthTestMode.NO_DEPTH_TEST).method1("core/position_tex_color").method44();
      field43 = Bridge.method8()
         .method80()
         .method42(field9)
         .method1(var3 ? "core/position_color" : "core/rendertype_gui")
         .method27(DepthTestMode.NO_DEPTH_TEST)
         .method11(Bridge_27.field3, DrawMode.TRIANGLES)
         .method32(Bridge2_15.field8)
         .method37(false)
         .method44();
      field47 = Bridge.method8()
         .method80()
         .method42(field9)
         .method1(var3 ? "core/position_color" : "core/rendertype_gui")
         .method27(var4 ? DepthTestMode.GEQUAL_DEPTH_TEST : DepthTestMode.LEQUAL_DEPTH_TEST)
         .method11(Bridge_27.field3, DrawMode.TRIANGLES)
         .method32(Bridge2_15.field8)
         .method37(false)
         .method44();
      field48 = Bridge.method8()
         .method80()
         .method42(field9)
         .method1(var3 ? "core/position_color" : "core/rendertype_gui")
         .method27(var4 ? DepthTestMode.GEQUAL_DEPTH_TEST : DepthTestMode.LEQUAL_DEPTH_TEST)
         .method11(Bridge_27.field3, DrawMode.TRIANGLES)
         .method32(Bridge2_15.field8)
         .method44();
      field59 = Bridge.method8()
         .method80()
         .method42(field4)
         .method1("core/position_tex")
         .method7("core/position_tex")
         .method4("core/position_tex")
         .method18("Sampler0")
         .method15("ALPHA_CUTOUT", "0.0")
         .method27(DepthTestMode.NO_DEPTH_TEST)
         .method11(Bridge_27.field2, DrawMode.QUADS)
         .method32(Bridge2_15.field14)
         .method37(false)
         .method44();
      method3(var0, var1);
   }

   public static void method2(boolean var0, BridgeImplementation.Extension var1) {
      method3(var0, var1);
      boolean var2 = Bridge.getMinecraftVersion().method6(Config.field18);
      if (var0) {
         if (field1 == null) {
            method5();
         }

         var1.register(
            field16 = Bridge.method8().method80().method42(field8).method1("core/rendertype_solid").method44(),
            field17 = Bridge.method8().method80().method42(field8).method1("core/rendertype_cutout_mipped").method14("ALPHA_CUTOUT", 0.5F).method44(),
            field18 = Bridge.method8().method80().method42(field8).method1("core/rendertype_cutout").method14("ALPHA_CUTOUT", 0.1F).method44(),
            field19 = Bridge.method8().method80().method42(field8).method1("core/rendertype_translucent").method32(Bridge2_15.field8).method44(),
            field20 = Bridge.method8()
               .method80()
               .method42(field9)
               .method27(DepthTestMode.NO_DEPTH_TEST)
               .method1(var2 ? "core/position_color" : "core/rendertype_gui")
               .method44(),
            field22 = Bridge.method8().method80().method42(field13).method1("core/rendertype_lines").method44(),
            field23 = Bridge.method8().method80().method42(field14).method1("core/particle").method32(Bridge2_15.field8).method44(),
            field24 = Bridge.method8()
               .method80()
               .method42(field15)
               .method1("core/rendertype_entity_cutout")
               .method14("ALPHA_CUTOUT", 0.1F)
               .method18("Sampler1")
               .method44(),
            field25 = Bridge.method8().method80().method42(field15).method1("core/rendertype_entity_solid").method18("Sampler1").method44(),
            field26 = Bridge.method8()
               .method80()
               .method42(field15)
               .method1("core/rendertype_entity_cutout_no_cull")
               .method14("ALPHA_CUTOUT", 0.1F)
               .method18("Sampler1")
               .method29(false)
               .method44(),
            field52 = Bridge.method8()
               .method80()
               .method42(field15)
               .method1("core/rendertype_entity_translucent")
               .method14("ALPHA_CUTOUT", 0.1F)
               .method18("Sampler1")
               .method32(Bridge2_15.field8)
               .method29(true)
               .method44(),
            field30 = Bridge.method8().method80().method42(field10).method1("core/position_tex_color").method32(Bridge2_15.field15).method44(),
            field31 = Bridge.method8()
               .method80()
               .method42(field1)
               .method42(field3)
               .method1("core/rendertype_glint")
               .method7("core/rendertype_glint")
               .method4("core/rendertype_glint")
               .method18("Sampler0")
               .method37(false)
               .method29(false)
               .method27(DepthTestMode.EQUAL_DEPTH_TEST)
               .method32(Bridge2_15.field6)
               .method11(Bridge_27.field2, DrawMode.QUADS)
               .method44()
         );
      }

      Config var3 = Bridge.method8().getMinecraftVersion();
      boolean var4 = var3.method4(Config.field40);
      var1.register(
         field32 = Bridge.method8()
            .method80()
            .method42(field9)
            .method27(DepthTestMode.NO_DEPTH_TEST)
            .method37(false)
            .method1(var2 ? "core/position_color" : "core/rendertype_gui")
            .method44(),
         field33 = Bridge.method8()
            .method80()
            .method42(field9)
            .method27(DepthTestMode.NO_DEPTH_TEST)
            .method37(true)
            .method1(var2 ? "core/position_color" : "core/rendertype_gui")
            .method44(),
         field35 = Bridge.method8()
            .method80()
            .method42(field10)
            .method27(var4 ? DepthTestMode.GEQUAL_DEPTH_TEST : DepthTestMode.LEQUAL_DEPTH_TEST)
            .method1("core/position_tex_color")
            .method44(),
         field42 = Bridge.method8()
            .method80()
            .method42(field9)
            .method1(var2 ? "core/position_color" : "core/rendertype_gui")
            .method27(DepthTestMode.NO_DEPTH_TEST)
            .method11(Bridge_27.field3, DrawMode.TRIANGLE_STRIP)
            .method32(Bridge2_15.field8)
            .method37(false)
            .method44(),
         field39 = Bridge.method8()
            .method80()
            .method42(field9)
            .method1(var2 ? "core/position_color" : "core/rendertype_gui_overlay")
            .method27(var4 ? DepthTestMode.GEQUAL_DEPTH_TEST : DepthTestMode.LEQUAL_DEPTH_TEST)
            .method11(Bridge_27.field3, DrawMode.QUADS)
            .method32(Bridge2_15.field7)
            .method37(false)
            .method44(),
         field40 = Bridge.method8()
            .method80()
            .method42(field9)
            .method1(var2 ? "core/position_color" : "core/rendertype_gui_overlay")
            .method27(var4 ? DepthTestMode.GEQUAL_DEPTH_TEST : DepthTestMode.LEQUAL_DEPTH_TEST)
            .method11(Bridge_27.field3, DrawMode.QUADS)
            .method32(Bridge2_15.field7)
            .method37(false)
            .method29(false)
            .method44(),
         field41 = Bridge.method8()
            .method80()
            .method42(field9)
            .method1(var2 ? "core/position_color" : "core/rendertype_gui_overlay")
            .method27(var4 ? DepthTestMode.GEQUAL_DEPTH_TEST : DepthTestMode.LEQUAL_DEPTH_TEST)
            .method11(Bridge_27.field3, DrawMode.QUADS)
            .method32(Bridge2_15.field7)
            .method37(false)
            .method40(var4 ? 1.0F : -1.0F, var4 ? 10.0F : -10.0F)
            .method44(),
         field64 = Bridge.method8()
            .method80()
            .method42(field9)
            .method1(var2 ? "core/position_color" : "core/rendertype_gui_overlay")
            .method27(var4 ? DepthTestMode.GEQUAL_DEPTH_TEST : DepthTestMode.LEQUAL_DEPTH_TEST)
            .method11(Bridge_27.field3, DrawMode.QUADS)
            .method32(Bridge2_15.field13)
            .method37(false)
            .method44(),
         field65 = Bridge.method8()
            .method80()
            .method42(field9)
            .method1(var2 ? "core/position_color" : "core/rendertype_gui_overlay")
            .method27(var4 ? DepthTestMode.GEQUAL_DEPTH_TEST : DepthTestMode.LEQUAL_DEPTH_TEST)
            .method11(Bridge_27.field3, DrawMode.QUADS)
            .method32(new Bridge2_15(BridgeType2_8.DST_COLOR, BridgeType2_8.DST_COLOR, BridgeType2_8.ONE_MINUS_DST_COLOR, BridgeType2_8.ONE_MINUS_DST_COLOR))
            .method37(false)
            .method44(),
         field66 = Bridge.method8()
            .method80()
            .method42(field9)
            .method1(var2 ? "core/position_color" : "core/rendertype_gui_overlay")
            .method27(var4 ? DepthTestMode.GEQUAL_DEPTH_TEST : DepthTestMode.LEQUAL_DEPTH_TEST)
            .method11(Bridge_27.field3, DrawMode.QUADS)
            .method32(new Bridge2_15(BridgeType2_8.ZERO, BridgeType2_8.DST_COLOR))
            .method37(false)
            .method44(),
         field49 = Bridge.method8()
            .method80()
            .method42(field9)
            .method1(var2 ? "core/position_color" : "core/rendertype_gui")
            .method27(DepthTestMode.NO_DEPTH_TEST)
            .method11(Bridge_27.field3, DrawMode.TRIANGLES)
            .method32(Bridge2_15.field9)
            .method37(false)
            .method44(),
         field50 = Bridge.method8()
            .method80()
            .method42(field12)
            .method1("core/position_color")
            .method11(Bridge_27.field3, DrawMode.TRIANGLE_STRIP)
            .method32(Bridge2_15.field8)
            .method27(var4 ? DepthTestMode.GEQUAL_DEPTH_TEST : DepthTestMode.LEQUAL_DEPTH_TEST)
            .method37(false)
            .method44(),
         field51 = Bridge.method8()
            .method80()
            .method42(field15)
            .method1("core/rendertype_entity_cutout")
            .method14("ALPHA_CUTOUT", 0.1F)
            .method12("NO_OVERLAY")
            .method39(var0x -> {
               if (Bridge.getMinecraftVersion().method3(Config.field29)) {
                  var0x.method12("APPLY_TEXTURE_MATRIX");
               }

               if (Bridge.getMinecraftVersion() == Config.field30) {
                  var0x.method22("TextureMat", GlslUniformType.MATRIX4X4);
               }
            })
            .method11(Bridge_27.field12, DrawMode.TRIANGLES)
            .method32(Bridge2_15.field8)
            .method29(false)
            .method44(),
         field27 = Bridge.method8()
            .method80()
            .method42(field15)
            .method1("core/rendertype_entity_translucent")
            .method14("ALPHA_CUTOUT", 0.1F)
            .method18("Sampler1")
            .method32(Bridge2_15.field8)
            .method29(false)
            .method44(),
         field28 = Bridge.method8()
            .method80()
            .method42(field15)
            .method1("core/rendertype_entity_translucent")
            .method14("ALPHA_CUTOUT", 0.1F)
            .method18("Sampler1")
            .method32(Bridge2_15.field8)
            .method29(true)
            .method30()
            .method44(),
         field29 = Bridge.method8()
            .method80()
            .method42(field10)
            .method1("core/position_tex_color")
            .method32(Bridge2_15.field12)
            .method27(null)
            .method37(false)
            .method44(),
         field54 = Bridge.method8()
            .method80()
            .method42(field12)
            .method1("core/position_color")
            .method11(Bridge_27.field3, DrawMode.TRIANGLES)
            .method32(Bridge2_15.field10)
            .method29(true)
            .method27(var4 ? DepthTestMode.GEQUAL_DEPTH_TEST : DepthTestMode.LEQUAL_DEPTH_TEST)
            .method37(true)
            .method44(),
         field55 = Bridge.method8()
            .method80()
            .method42(field9)
            .method1(var2 ? "core/position_color" : "core/rendertype_gui_overlay")
            .method27(var4 ? DepthTestMode.GEQUAL_DEPTH_TEST : DepthTestMode.LEQUAL_DEPTH_TEST)
            .method11(Bridge_27.field3, DrawMode.TRIANGLE_STRIP)
            .method32(Bridge2_15.field10)
            .method37(false)
            .method44(),
         field56 = Bridge.method8()
            .method80()
            .method42(field12)
            .method1("core/position_color")
            .method11(Bridge_27.field8, DrawMode.QUADS)
            .method32(Bridge2_15.field7)
            .method29(false)
            .method27(var4 ? DepthTestMode.GEQUAL_DEPTH_TEST : DepthTestMode.LEQUAL_DEPTH_TEST)
            .method37(false)
            .method44(),
         field57 = Bridge.method8()
            .method80()
            .method42(field12)
            .method1("core/position_color")
            .method11(Bridge_27.field3, DrawMode.QUADS)
            .method32(Bridge2_15.field13)
            .method29(false)
            .method37(false)
            .method27(DepthTestMode.NO_DEPTH_TEST)
            .method44(),
         field58 = Bridge.method8()
            .method80()
            .method42(field12)
            .method1("core/position_color")
            .method11(Bridge_27.field3, DrawMode.QUADS)
            .method32(Bridge2_15.field13)
            .method29(false)
            .method37(false)
            .method44(),
         field60 = Bridge.method8()
            .method80()
            .method42(field15)
            .method1("core/rendertype_entity_cutout")
            .method18("Sampler1")
            .method14("ALPHA_CUTOUT", 0.1F)
            .method11(Bridge_27.field12, DrawMode.TRIANGLE_STRIP)
            .method29(true)
            .method44(),
         field61 = Bridge.method8()
            .method80()
            .method42(field15)
            .method1("core/rendertype_entity_cutout")
            .method18("Sampler1")
            .method14("ALPHA_CUTOUT", 0.1F)
            .method11(Bridge_27.field12, DrawMode.QUADS)
            .method29(true)
            .method44(),
         field62 = Bridge.method8()
            .method80()
            .method42(field15)
            .method1(Bridge.getMinecraftVersion().method5(Config.field23) ? "core/position_tex_color_normal" : "core/position_tex_color_normal_replacement")
            .method7(Bridge.getMinecraftVersion().method5(Config.field40) ? "core/entity" : "core/position_tex_normal_v26_2")
            .method4(Bridge.getMinecraftVersion().method5(Config.field40) ? "core/entity" : "core/position_tex_normal_v26_2")
            .method18("Sampler1")
            .method14("ALPHA_CUTOUT", 0.1F)
            .method11(Bridge_27.field5, DrawMode.QUADS)
            .method29(true)
            .method44(),
         field67 = Bridge.method8()
            .method80()
            .method42(field15)
            .method1("core/rendertype_entity_cutout_no_cull")
            .method11(Bridge_27.field12, DrawMode.TRIANGLES)
            .method18("Sampler1")
            .method29(false)
            .method32(Bridge2_15.field8)
            .method14("ALPHA_CUTOUT", 0.1F)
            .method44(),
         field53 = Bridge.method8()
            .method80()
            .method42(field15)
            .method1("core/rendertype_entity_translucent")
            .method11(Bridge_27.field12, DrawMode.QUADS)
            .method18("Sampler1")
            .method29(false)
            .method32(Bridge2_15.field8)
            .method14("ALPHA_CUTOUT", 0.1F)
            .method37(false)
            .method44(),
         field63 = Bridge.method8()
            .method80()
            .method42(field13)
            .method1("core/rendertype_lines")
            .method32(Bridge2_15.field10)
            .method29(false)
            .method27(DepthTestMode.NO_DEPTH_TEST)
            .method37(false)
            .method44(),
         field46 = Bridge.method8()
            .method80()
            .method42(field9)
            .method1(var2 ? "core/position_color" : "core/rendertype_gui")
            .method27(DepthTestMode.NO_DEPTH_TEST)
            .method11(Bridge_27.field3, DrawMode.TRIANGLES)
            .method32(Bridge2_15.field8)
            .method34(false, false)
            .method37(false)
            .method44(),
         field68 = Bridge.method8()
            .method80()
            .method42(field10)
            .method27(DepthTestMode.NO_DEPTH_TEST)
            .method37(false)
            .method1("core/position_tex_color")
            .method32(new Bridge2_15(BridgeType2_8.ONE, BridgeType2_8.ONE_MINUS_SRC_ALPHA, BridgeType2_8.ONE, BridgeType2_8.ONE))
            .method44(),
         field69 = Bridge.method8()
            .method80()
            .method42(field10)
            .method27(DepthTestMode.NO_DEPTH_TEST)
            .method37(false)
            .method1("core/position_tex_color")
            .method32(new Bridge2_15(BridgeType2_8.ONE, BridgeType2_8.ONE_MINUS_SRC_ALPHA))
            .method44(),
         field70 = Bridge.method8()
            .method80()
            .method42(field10)
            .method27(DepthTestMode.NO_DEPTH_TEST)
            .method37(false)
            .method1("core/position_tex_color")
            .method32(Bridge2_15.field15)
            .method44(),
         field44 = Bridge.method8()
            .method80()
            .method42(field9)
            .method1(var2 ? "core/position_color" : "core/rendertype_gui")
            .method27(DepthTestMode.NO_DEPTH_TEST)
            .method11(Bridge_27.field3, DrawMode.TRIANGLES)
            .method32(Bridge2_15.field15)
            .method37(false)
            .method44(),
         field71 = Bridge.method8()
            .method80()
            .method42(field14)
            .method27(var4 ? DepthTestMode.GEQUAL_DEPTH_TEST : DepthTestMode.LEQUAL_DEPTH_TEST)
            .method37(true)
            .method29(true)
            .method1("core/particle")
            .method32(Bridge2_15.field8)
            .method14("ALPHA_CUTOUT", 0.1F)
            .method44(),
         field73 = Bridge.method8()
            .method80()
            .method42(field15)
            .method11(Bridge_27.field12, DrawMode.TRIANGLES)
            .method1(Bridge.method8().getMinecraftVersion().method4(Config.field33) ? "pipeline/armor_cutout_no_cull" : "core/rendertype_armor_cutout_no_cull")
            .method14("ALPHA_CUTOUT", 0.1F)
            .method18("Sampler1")
            .method27(var4 ? DepthTestMode.GEQUAL_DEPTH_TEST : DepthTestMode.LEQUAL_DEPTH_TEST)
            .method29(false)
            .method44(),
         field74 = Bridge.method8()
            .method80()
            .method42(field15)
            .method11(Bridge_27.field12, DrawMode.TRIANGLES)
            .method1(
               Bridge.method8().getMinecraftVersion().method4(Config.field33) ? "pipeline/armor_decal_cutout_no_cull" : "core/rendertype_armor_cutout_no_cull"
            )
            .method14("ALPHA_CUTOUT", 0.1F)
            .method18("Sampler1")
            .method27(DepthTestMode.EQUAL_DEPTH_TEST)
            .method29(false)
            .method44(),
         field45 = Bridge.method8()
            .method80()
            .method42(field9)
            .method1(var2 ? "core/position_color" : "core/rendertype_gui")
            .method27(DepthTestMode.NO_DEPTH_TEST)
            .method11(Bridge_27.field3, DrawMode.TRIANGLES)
            .method32(new Bridge2_15(BridgeType2_8.ZERO, BridgeType2_8.ZERO, BridgeType2_8.ZERO, BridgeType2_8.ZERO))
            .method37(false)
            .method44(),
         field75 = Bridge.method8()
            .method80()
            .method42(field10)
            .method27(var4 ? DepthTestMode.GEQUAL_DEPTH_TEST : DepthTestMode.LEQUAL_DEPTH_TEST)
            .method37(false)
            .method1("core/position_tex_color")
            .method44()
      );
      if (var3.method4(Config.field30)) {
         field72 = Bridge.method8()
            .method80()
            .method42(field15)
            .method1("pipeline/armor_cutout_no_cull")
            .method14("ALPHA_CUTOUT", 0.1F)
            .method18("Sampler1")
            .method29(false)
            .method44();
         var1.register(
            field37 = Bridge.method8()
               .method80()
               .method42(field10)
               .method27(DepthTestMode.NO_DEPTH_TEST)
               .method1("core/lunar_position_tex_color_no_alpha")
               .method7("core/position_tex_color")
               .method4("core/lunar_position_tex_color_no_alpha")
               .method44()
         );
      }
   }

   private static void method3(boolean var0, BridgeImplementation.Extension var1) {
      if (var0) {
         var1.register(field21);
         var1.register(field34);
      }

      var1.register(field43);
      var1.register(field59);
   }

   private static void method4() {
      field11 = Bridge.method8()
         .method80()
         .method42(field4)
         .method7("core/position_tex_color")
         .method4("core/position_tex_color")
         .method27(DepthTestMode.NO_DEPTH_TEST)
         .method18("Sampler0")
         .method32(Bridge2_15.field13)
         .method11(Bridge_27.field8, DrawMode.QUADS)
         .method43();
   }

   private static void method5() {
      if (Bridge.getMinecraftVersion().method4(Config.field30)) {
         throw new RuntimeException("Attempting to register vanilla snippets on a version which already defines them");
      }

      boolean var0 = Bridge.getMinecraftVersion().method6(Config.field18);
      boolean var1 = Bridge.getMinecraftVersion().method6(Config.field26);
      field1 = Bridge.method8().method80().method22("ModelViewMat", GlslUniformType.MATRIX4X4).method22("ProjMat", GlslUniformType.MATRIX4X4).method43();
      field2 = Bridge.method8()
         .method80()
         .method22("FogStart", GlslUniformType.FLOAT)
         .method22("FogEnd", GlslUniformType.FLOAT)
         .method22("FogShape", GlslUniformType.INT)
         .method43();
      field3 = Bridge.method8().method80().method42(field2).method22("FogColor", GlslUniformType.VEC4).method43();
      field4 = Bridge.method8().method80().method42(field1).method22("ColorModulator", GlslUniformType.VEC4).method43();
      field5 = Bridge.method8().method80().method42(field4).method42(field3).method43();
      field6 = Bridge.method8().method80().method42(field5).method22("ModelOffset", GlslUniformType.VEC3).method43();
      field7 = Bridge.method8()
         .method80()
         .method42(field4)
         .method42(field3)
         .method22("Light0_Direction", GlslUniformType.VEC3)
         .method22("Light1_Direction", GlslUniformType.VEC3)
         .method43();
      field8 = Bridge.method8()
         .method80()
         .method42(field6)
         .method7("core/terrain")
         .method4("core/terrain")
         .method18("Sampler0")
         .method18("Sampler2")
         .method11(Bridge_27.field11, DrawMode.QUADS)
         .method43();
      field9 = Bridge.method8()
         .method80()
         .method42(field4)
         .method7(var0 ? "core/position_color" : (var1 ? "core/rendertype_gui" : "core/gui"))
         .method4(var0 ? "core/position_color" : (var1 ? "core/rendertype_gui" : "core/gui"))
         .method27(DepthTestMode.NO_DEPTH_TEST)
         .method32(Bridge2_15.field8)
         .method11(Bridge_27.field3, DrawMode.QUADS)
         .method43();
      field10 = Bridge.method8()
         .method80()
         .method42(field4)
         .method7("core/position_tex_color")
         .method4("core/position_tex_color")
         .method27(DepthTestMode.NO_DEPTH_TEST)
         .method18("Sampler0")
         .method32(Bridge2_15.field8)
         .method11(Bridge_27.field8, DrawMode.QUADS)
         .method43();
      field12 = Bridge.method8()
         .method80()
         .method42(field4)
         .method7("core/position_color")
         .method4("core/position_color")
         .method32(Bridge2_15.field8)
         .method11(Bridge_27.field3, DrawMode.QUADS)
         .method43();
      field13 = Bridge.method8()
         .method80()
         .method42(field5)
         .method7("core/rendertype_lines")
         .method4("core/rendertype_lines")
         .method22("LineWidth", GlslUniformType.FLOAT)
         .method22("ScreenSize", GlslUniformType.VEC2)
         .method32(Bridge2_15.field8)
         .method29(false)
         .method11(Bridge_27.field4, DrawMode.LINES)
         .method43();
      field14 = Bridge.method8()
         .method80()
         .method42(field5)
         .method7("core/particle")
         .method4("core/particle")
         .method18("Sampler0")
         .method18("Sampler2")
         .method11(Bridge_27.field10, DrawMode.QUADS)
         .method43();
      field15 = Bridge.method8()
         .method80()
         .method42(field7)
         .method7("core/entity")
         .method4("core/entity")
         .method18("Sampler0")
         .method18("Sampler2")
         .method11(Bridge_27.field12, DrawMode.QUADS)
         .method43();
   }
}
