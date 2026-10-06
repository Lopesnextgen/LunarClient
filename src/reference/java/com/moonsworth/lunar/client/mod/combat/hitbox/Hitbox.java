package com.moonsworth.lunar.client.mod.combat.hitbox;

import com.google.common.collect.Maps;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge12_3;
import com.moonsworth.lunar.bridge.EntityHorseVariantBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArrowBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_3;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.EntityItemBridge;
import com.moonsworth.lunar.bridge.EntityAnimalBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.framework.Ref;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;
import lombok.Generated;
import org.intellij.lang.annotations.Subst;

public class Hitbox extends AbstractFeature {
   private final List<Hitbox.Data> configs = Arrays.asList(
      new Hitbox.Data("Player", arg0 -> arg0 instanceof Bridge6_10, true),
      new Hitbox.Data("Item", arg0 -> arg0 instanceof EntityItemBridge, false),
      new Hitbox.Data("ExpOrb", arg0 -> arg0 instanceof Bridge12_3, false),
      new Hitbox.Data("ItemFrame", BridgeExtension::bridge$isItemFrame, false),
      new Hitbox.Data("Firework", BridgeExtension::bridge$isFirework, false),
      new Hitbox.Data("WitherSkull", BridgeExtension::bridge$isWitherSkull, false),
      new Hitbox.Data("Snowball", BridgeExtension::bridge$isSnowball, false),
      new Hitbox.Data("Fireball", BridgeExtension::bridge$isFireball, false),
      new Hitbox.Data("Arrow", arg0 -> arg0 instanceof EntityArrowBridge, false),
      new Hitbox.Data("Projectile", arg0 -> arg0 instanceof BridgeExtension2_3, false),
      new Hitbox.Data("Monster", BridgeExtension::bridge$isMonster, true),
      new Hitbox.Data("Passive", arg0 -> arg0 instanceof EntityAnimalBridge || arg0 instanceof EntityLivingBridge, true),
      new Hitbox.Data("Other", arg0 -> true, false)
   );
   private final Hitbox.Data field8 = new Hitbox.Data("Mob", arg0 -> true, false);
   private final EnumOption<Hitbox.Type> field9 = (EnumOption<Hitbox.Type>)OptionFactory.method10("hitboxLinePattern", Hitbox.Type.SOLID)
      .method31();
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("maxDistanceToggle").method31();
   private final IntegerOption field11 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "maxDistance"
            )
            .method4(64))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 128))
      .method31();

   public Hitbox() {
      super(false);
      LunarEventBus.method29().method2(EventTick.class, arg1 -> {
         boolean flag2 = Bridge.method9().bridge$getEntityRenderDispatcher().bridge$showDebugBoundingBox();
         ModEnabledState framework33 = (ModEnabledState)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field6);
         boolean flag4 = this.isEnabled();
         if (flag4 && !flag2) {
            framework33.setEnabled(false);
         } else if (!flag4 && flag2) {
            framework33.setEnabled(true);
         }
      });
   }

   public String getId() {
      return "HITBOX";
   }

   public void method3(boolean flag1) {
      Bridge.method9().bridge$getEntityRenderDispatcher().bridge$setDebugBoundingBox(flag1);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field9})).method6(5);
      lightingextension231.method7(this.field10, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field11}));
      this.configs.forEach(arg1x -> arg1x.method1(lightingextension231));
   }

   public Hitbox.Data method3(String text1) {
      return Hitbox.Data.field1.get(text1);
   }

   public Hitbox.Data method4(BridgeExtension bridgeextension1) {
      for (Hitbox.Data data3 : this.configs) {
         if (data3.field3.test(bridgeextension1)) {
            return data3;
         }
      }

      return this.configs.get(this.configs.size() - 1);
   }

   @Generated
   public List<Hitbox.Data> getConfigs() {
      return this.configs;
   }

   @Generated
   public Hitbox.Data method13() {
      return this.field8;
   }

   @Generated
   public EnumOption<Hitbox.Type> method14() {
      return this.field9;
   }

   @Generated
   public ToggleOption method15() {
      return this.field10;
   }

   @Generated
   public IntegerOption method16() {
      return this.field11;
   }

   public class Data {
      public static final Map<String, Hitbox.Data> field1 = Maps.newHashMap();
      @Subst("Player")
      private final String field2;
      private final Predicate<BridgeExtension> field3;
      private final boolean field4;
      private final FloatOption field5;
      private final ColorOption field6;
      private final ToggleOption field7;
      private final ColorOption field8;
      private final ToggleOption field9;
      private final ToggleOption field10;
      private final ColorOption field11;
      private final ToggleOption field12;
      private final ToggleOption field13;

      public Data(@Subst("Player") String text2, Predicate<BridgeExtension> predicate3, boolean flag4) {
         this.field2 = text2;
         this.field3 = predicate3;
         this.field4 = flag4;
         this.field5 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                     "hitbox" + text2 + "LineWidth"
                  )
                  .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
               .HRRCROICHIIROIHRCOIHRRHCCRIIRH(1.0F, 5.0F))
            .method31();
         this.field6 = (ColorOption)OptionFactory.method8("hitbox" + text2 + "LineColor").method9(Color.WHITE).method31();
         this.field7 = (ToggleOption)OptionFactory.method7("hitbox" + text2 + "ShowHittableColor").method31();
         this.field8 = (ColorOption)OptionFactory.method8("hitbox" + text2 + "HittableColor").method9(Color.GREEN).method31();
         this.field9 = (ToggleOption)OptionFactory.method7("hitbox" + text2 + "OnlyShowHittable").method31();
         this.field10 = (ToggleOption)OptionFactory.method7("hitbox" + text2 + "ShowDamagedColor").method31();
         this.field11 = (ColorOption)OptionFactory.method8("hitbox" + text2 + "DamagedColor").method9(Color.RED).method31();
         this.field12 = (ToggleOption)OptionFactory.method7("hitbox" + text2 + "LookVector").method31();
         this.field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hitbox" + text2 + "Show").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
            .method31();
         field1.put(text2, this);
      }

      protected void method1(RootSettingsBuilder lightingextension231) {
         lightingextension231.method7(
            this.field13,
            arg1x -> {
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field5, this.field6});
               arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field7, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field8}))
                  .method3(() -> !this.field4);
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field9}).method3(() -> !this.field4);
               arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field10, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field11}))
                  .method3(() -> !this.field4);
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field12}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(1);
            }
         );
      }

      public boolean method2(BridgeExtension bridgeextension1) {
         Bridge5Extension_5 bridge5extension_52 = Ref.method7();
         if (bridge5extension_52 == null || bridgeextension1 == null) {
            return false;
         }

         if (!(Boolean)this.field13.get()) {
            return false;
         }

         if (this.field4 && (Boolean)this.field9.get() && !this.method4(bridgeextension1)) {
            return false;
         }

         if (Ref.MC_VERSION == 0 && bridgeextension1 instanceof EntityHorseVariantBridge) {
            AxisAlignedBBBridge horsestats123 = bridgeextension1.bridge$getBoundingBox();
            if (horsestats123.method14() < 0.0 && horsestats123.method15() < 0.0 && horsestats123.method16() < 0.0) {
               return false;
            }
         }

         if ((Boolean)Hitbox.this.field10.get()) {
            int number7 = (Integer)Hitbox.this.field11.get();
            int number4 = bridge5extension_52.bridge$getBlockX() - bridgeextension1.bridge$getBlockX();
            int number5 = bridge5extension_52.bridge$getBlockY() - bridgeextension1.bridge$getBlockY();
            int number6 = bridge5extension_52.bridge$getBlockZ() - bridgeextension1.bridge$getBlockZ();
            return number4 * number4 + number5 * number5 + number6 * number6 <= number7 * number7;
         } else {
            return true;
         }
      }

      public ColorOption method3(BridgeExtension bridgeextension1) {
         if (this.field4 && (Boolean)this.field10.get() && bridgeextension1 instanceof EntityLivingBridge bridgeextension2_52 && bridgeextension2_52.bridge$getHurtTime() > 0) {
            return this.field11;
         } else {
            return this.field4 && this.field7.get() && this.method4(bridgeextension1) ? this.field8 : this.field6;
         }
      }

      private boolean method4(BridgeExtension bridgeextension1) {
         if (bridgeextension1 == null) {
            return false;
         }

         BridgeExtension bridgeextension2 = (BridgeExtension)Ref.method3().bridge$getPointedEntity().orElse(null);
         return bridgeextension2 != null && bridgeextension2.bridge$getUniqueID().equals(bridgeextension1.bridge$getUniqueID());
      }

      @Generated
      public String getType() {
         return this.field2;
      }

      @Generated
      public FloatOption method5() {
         return this.field5;
      }

      @Generated
      public ToggleOption method6() {
         return this.field12;
      }
   }

   public enum Type implements OptionEnumValue {
      SOLID(1, 65535),
      DASHED(3, 65280),
      DOTTED(1, 43690);

      private final int factor;
      private final int pattern;

      public String id() {
         return "hitbox." + this.name().toLowerCase(Locale.ROOT);
      }

      @Generated
      public int getFactor() {
         return this.factor;
      }

      @Generated
      public int getPattern() {
         return this.pattern;
      }

      @Generated
      Type(int number3, int number4) {
         this.factor = number3;
         this.pattern = number4;
      }
   }
}
