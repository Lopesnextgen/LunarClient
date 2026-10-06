package com.moonsworth.lunar.client.mod.render.crosshair;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.EntityFireworkRocketBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.minecraft.EntityEquipmentSlotBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairPattern;
import com.moonsworth.lunar.client.framework.feature.crosshair.crosshairelytra.CrosshairShapeDrawer;
import com.moonsworth.lunar.client.framework.feature.crosshair.crosshairelytra.CrosshairShapeRenderer;
import com.moonsworth.lunar.client.framework.feature.crosshair.crosshairelytra.CrosshairTextureRenderer;
import com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairType;
import com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairMode;
import com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairShape;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import com.moonsworth.lunar.ichor.VersionGate;
import it.unimi.dsi.fastutil.floats.FloatFloatPair;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class CrosshairElytra extends CrosshairStyle {
   protected final IntegerOption field33 = (IntegerOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4("offset")
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(10))
         .OCRRICRIORICCCRHIOHORCICIHHICO(0, 500))
      .method31();
   protected final EnumOption<CrosshairElytra.Type> field34 = (EnumOption<CrosshairElytra.Type>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "alignment", CrosshairElytra.Type.AUTO
      )
      .method31();
   protected final ToggleOption field35 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "showElytraCrosshairFireworkIcon"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final EnumOption<CrosshairElytra.Type> field36 = (EnumOption<CrosshairElytra.Type>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "crosshairFireworkAlignment", CrosshairElytra.Type.AUTO
      )
      .method31();
   protected final IntegerOption field37 = (IntegerOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "crosshairFireworkOffset"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(7))
         .OCRRICRIORICCCRHIOHORCICIHHICO(0, 500))
      .method31();
   protected final CrosshairShapeRenderer field38;

   CrosshairElytra(Crosshair crosshair1, boolean flag2) {
      super(crosshair1, flag2, CrosshairType.NORMAL);
      this.field30 = CrosshairPattern.method5(
         "LCCH-13-eNpjYKifsTlAgss5UYOndZGHSKAIGzMDA8N/IABSDBoggsEeiDsYIAAswsgAISGKYMoYGFgZHEAUiGAEABP2DMc=#"
      );
      this.field38 = new CrosshairShapeRenderer(this);
      this.field8.HIRIHCROOIRIORCCOIRRCRHOHCCRRO(CrosshairMode.CUSTOM);
      this.method14(ModTraits.field18, arg0 -> arg0.method6(2));
   }

   @Override
   protected CrosshairTextureRenderer method13() {
      return new CrosshairTextureRenderer(this, "CROSSHAIR_ELYTRA");
   }

   @Override
   public String method14() {
      return "LCCH-13-eNpjYKifsTlAgss5UYOndZGHSKAIGzMDA8N/IABSDBoggsEeiDsYIAAswsgAISGKYMoYGFgZHEAUiGAEABP2DMc=#";
   }

   public boolean isEnabled() {
      return super.isEnabled() && Ref.MC_VERSION >= 2;
   }

   @Override
   public String getId() {
      throw new IllegalStateException("CrosshairElytraChildMod must be created using CrosshairElytraChildMod#create!");
   }

   @Override
   public void method2(RootSettingsBuilder lightingextension231) {
      this.method12(lightingextension231);
      this.method1(lightingextension231);
      this.method4(lightingextension231);
      this.method7(lightingextension231);
      this.method8(lightingextension231);
   }

   protected void method4(RootSettingsBuilder lightingextension231) {
      ((SettingsSectionImpl)lightingextension231.method1("fireworkIconOptions", arg1x -> {
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field35});
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field36, this.field37}).method3(() -> !(Boolean)this.field35.get());
      })).method6(5);
   }

   @Override
   protected void method7(RootSettingsBuilder lightingextension231) {
      lightingextension231.method1(
         "extraRenderOptions",
         arg1x -> {
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field34, this.field33});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field13,
               arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field14})
            );
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field17,
               arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
                  new ClientOption[]{this.field19, this.field18}
               )
            );
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field15,
               arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field16})
            );
         }
      );
   }

   @Override
   protected void method8(RootSettingsBuilder lightingextension231) {
      super.method8(lightingextension231);
      if (Ref.MC_VERSION >= 5) {
         this.field38.field2.ICRHORIIHOHROHOHOCOOHOOCOORRHO(CrosshairShape.ARROW, true);
      }
   }

   @Override
   public void method4() {
      super.method4();
      this.field30 = CrosshairPattern.method5(
         "LCCH-13-eNpjYKifsTlAgss5UYOndZGHSKAIGzMDA8N/IABSDBoggsEeiDsYIAAswsgAISGKYMoYGFgZHEAUiGAEABP2DMc=#"
      );
      this.field28.reset();
   }

   @Override
   public void method15(MixinHelper_4 mixinhelper_41, com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data22, boolean flag3) {
      Bridge5Extension_5 bridge5extension_54 = Ref.method7();
      if (flag3 || bridge5extension_54 != null && bridge5extension_54.bridge$getEquipmentInSlot(EntityEquipmentSlotBridge.CHEST).bridge$canElytraFly()) {
         FloatFloatPair floatfloatpair5 = this.method10(bridge5extension_54, (CrosshairElytra.Type)this.field34.get(), ((Integer)this.field33.get()).intValue(), flag3);
         float value6 = 1.0F;
         CrosshairShapeDrawer crosshairelytra7 = this.method15();
         float value8 = this.method14(flag3);
         float value9 = data22.IIRCROICCRROCOCOIOIHHOCRHOIHIR();
         float value10 = data22.CRCOHORRCCORCCIIOOIOOCIRRCRHHH();
         if (flag3) {
            this.field38.method1(mixinhelper_41, value9, value10, value8, value6 * 2.0F, crosshairelytra7);
         }

         float value11 = flag3 ? 1.0F : 0.5F;
         value9 += floatfloatpair5.firstFloat() * (flag3 ? 3.0F : value11 * 2.0F);
         value10 += floatfloatpair5.secondFloat() * (flag3 ? 3.0F : value11 * 2.0F);
         if (this.field8.get() == CrosshairMode.SIMPLE) {
            this.field38.method1(mixinhelper_41, value9, value10, value8 * value11, value6 * 2.0F * value11, crosshairelytra7);
         } else {
            this.field28.method1(mixinhelper_41, value9, value10, (value8 + value6 / 2.0F) * value11, crosshairelytra7);
         }

         if (Ref.MC_VERSION >= 5 && (Boolean)this.field35.get()) {
            this.method9(mixinhelper_41, bridge5extension_54, crosshairelytra7, value9, value10, value8, value6, flag3);
         }
      }
   }

   @VersionGate(min = 5)
   private void method9(
      MixinHelper_4 mixinhelper_41, @Nullable Bridge5Extension_5 bridge5extension_52, CrosshairShapeDrawer crosshairelytra3, float value4, float value5, float value6, float value7, boolean flag8
   ) {
      boolean flag9 = false;
      if (flag8) {
         flag9 = true;
      } else if (bridge5extension_52 != null && bridge5extension_52.bridge$isElytraFlying()) {
         List list10 = bridge5extension_52.bridge$getWorld()
            .bridge$getEntities(
               bridge5extension_52.bridge$getBoundingBox().method11(2.0), arg1x -> arg1x instanceof EntityFireworkRocketBridge bridgeextension_32x && bridgeextension_32x.bridge$getAttachedToEntity() == bridge5extension_52
            );
         flag9 = !list10.isEmpty();
      }

      if (flag9) {
         FloatFloatPair floatfloatpair14 = this.method10(bridge5extension_52, (CrosshairElytra.Type)this.field36.get(), ((Integer)this.field37.get()).intValue(), flag8);
         float value11 = 0.5F;
         float value12 = value4 + floatfloatpair14.firstFloat() * (flag8 ? 3.0F : value11 * 2.0F);
         float value13 = value5 + floatfloatpair14.secondFloat() * (flag8 ? 3.0F : value11 * 2.0F);
         this.field38.method1(mixinhelper_41, value12, value13, value6 * value11, value7 * 2.0F * value11, crosshairelytra3);
      }
   }

   private FloatFloatPair method10(@Nullable Bridge5Extension_5 bridge5extension_51, CrosshairElytra.Type type2, float value3, boolean flag4) {
      return switch (type2) {
         case ABOVE -> FloatFloatPair.of(0.0F, -value3);
         case BELOW -> FloatFloatPair.of(0.0F, value3);
         case LEFT -> FloatFloatPair.of(-value3, 0.0F);
         case RIGHT -> FloatFloatPair.of(value3, 0.0F);
         case AUTO -> {
            double value5 = bridge5extension_51 != null && !flag4 && bridge5extension_51.bridge$isElytraFlying()
               ? MathUtils.method2(bridge5extension_51.bridge$getRotationPitch() * 3.0, -90.0, 90.0)
               : -90.0;
            double value7 = Math.toRadians(value5);
            yield FloatFloatPair.of((float)Math.cos(value7) * (value3 + 2.0F), -((float)Math.sin(value7)) * value3);
         }
         default -> FloatFloatPair.of(0.0F, 0.0F);
      };
   }

   @Override
   protected void method23(DataInputStream input1) {
      super.method23(input1);
      if (input1.available() > 0) {
         this.field33.method1(input1.readInt());
         this.field34.OIRHOOIICOCIOOHICRRRICORIHHIHC(CrosshairElytra.Type.values()[input1.readInt()]);
         this.field35.method10(input1.readBoolean());
         this.field36.OIRHOOIICOCIOOHICRRRICORIHHIHC(CrosshairElytra.Type.values()[input1.readInt()]);
         this.field37.method1(input1.readInt());
      }
   }

   @Override
   protected void method24(DataOutputStream output1) {
      super.method24(output1);
      output1.writeInt((Integer)this.field33.get());
      output1.writeInt(((CrosshairElytra.Type)this.field34.get()).ordinal());
      output1.writeBoolean((Boolean)this.field35.get());
      output1.writeInt(((CrosshairElytra.Type)this.field36.get()).ordinal());
      output1.writeInt((Integer)this.field37.get());
   }

   public static CrosshairElytra method13(Crosshair crosshair0, boolean flag1) {
      return new CrosshairElytra(crosshair0, flag1) {
         @Override
         public String getId() {
            return "CROSSHAIR_ELYTRA";
         }
      };
   }

   public enum Type implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
      AUTO("auto"),
      CENTER("center"),
      ABOVE("above"),
      BELOW("below"),
      LEFT("left"),
      RIGHT("right");

      private final String id;

      public String id() {
         return this.id;
      }

      @Generated
      Type(String text3) {
         this.id = text3;
      }
   }
}
