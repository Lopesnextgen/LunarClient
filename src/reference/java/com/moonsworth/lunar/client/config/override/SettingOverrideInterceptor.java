package com.moonsworth.lunar.client.config.override;

import com.lunarclient.apollo.module.modsetting.ModSettingModule;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.ModSettingApolloHandler;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.config.override.SettingOverride;
import com.moonsworth.lunar.client.config.override.SettingIntercept;
import com.moonsworth.lunar.client.config.override.OverrideSource;
import com.moonsworth.lunar.client.framework.mod.ModChildren;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.EnumMap;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.function.Predicate;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class SettingOverrideInterceptor implements SettingIntercept<Framework7Extension, Boolean> {
   @Nullable
   private Boolean field1 = null;
   private EnumMap<OverrideSource, SettingOverride<Boolean>> field2;

   public boolean method1(Framework7Extension framework7extension1, OverrideSource alerttype2, @Nullable Boolean flag3) {
      ModEnabledState framework34;
      if (this.field2 == null) {
         if (flag3 == null) {
            return false;
         }

         framework34 = (ModEnabledState)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field6);
         if (framework34 == null) {
            return false;
         }

         this.field2 = new EnumMap<>(OverrideSource.class);
      } else {
         framework34 = (ModEnabledState)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field6);
         if (framework34 == null) {
            return false;
         }
      }

      Boolean flag5 = this.method3().orElse(null);
      boolean flag6 = framework34.method1().<Boolean>map(ClientOption::get).orElse(true);
      if (flag3 == null) {
         SettingOverride alert7 = this.field2.get(alerttype2);
         if (alert7 == null) {
            return false;
         }

         if (alert7.method4()) {
            this.field2.remove(alerttype2);
            if (this.field2.isEmpty()) {
               this.field2 = null;
            }
         }
      } else {
         if (this.field1 == null) {
            this.field1 = flag6;
         }

         this.field2.computeIfAbsent(alerttype2, arg0 -> new SettingOverride()).method2(alerttype2, flag3);
      }

      if (Ref.method4() != null && framework7extension1 instanceof AbstractFeature framework7extension211) {
         try {
            framework34.method1().ifPresent(arg3x -> Client.method109().method84().method3(ModSettingModule.class).ifPresent(arg4x -> {
               Boolean flag5x = flag3 != null ? flag3 : flag6;
               ((ModSettingApolloHandler)arg4x).method17().method3(arg3x, framework7extension211.getId(), flag5x);
            }));
         } catch (Throwable exception10) {
            LunarLogger.method6("Apollo", "Apollo broadcast failed", new Object[]{exception10});
         }
      }

      Boolean flag12 = this.method3().orElse(null);
      if (flag5 != flag12) {
         ClientOption lightingextension8 = framework34.method1().orElse(null);
         if (lightingextension8 == null) {
            return false;
         }

         SettingIntercept alert29 = (SettingIntercept)lightingextension8.HIRHCCHIRHRORIICOIHIHCICOIRHHC(com.moonsworth.lunar.client.config.option.OptionTraits.field5);
         if (alert29 != null) {
            alert29.method1(lightingextension8, alerttype2, flag12);
         }

         if (flag12 == null && this.field1 != null) {
            framework34.setEnabled(this.field1);
            this.field1 = null;
         }

         this.method2(framework7extension1);
         return true;
      } else {
         return false;
      }
   }

   private void method2(Framework7Extension framework7extension1) {
      framework7extension1.updateEnabled();
      ModChildren alertextension2 = (ModChildren)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5);
      if (alertextension2 != null) {
         alertextension2.method2(Framework7Extension::updateEnabled);
      }
   }

   @Nullable
   public OverrideSource method2() {
      SettingOverride alert1 = this.method8();
      return alert1 == null ? null : alert1.method5();
   }

   public Optional<Boolean> method3() {
      SettingOverride alert1 = this.method8();
      return alert1 == null ? Optional.empty() : alert1.method1();
   }

   public void method5(Framework7Extension framework7extension1, @Nullable String text2) {
      ModChildren alertextension3 = (ModChildren)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5);
      if (alertextension3 != null) {
         for (Framework7Extension framework7extension5 : alertextension3.getChildren()) {
            SettingIntercept alert26 = (SettingIntercept)framework7extension5.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field4);
            if (alert26 != null) {
               alert26.method4(framework7extension5, text2);
            }
         }
      }

      OptionContainer framework58 = (OptionContainer)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
      if (framework58 != null) {
         for (ClientOption lightingextension11 : framework58.method2()) {
            SettingIntercept alert27 = (SettingIntercept)lightingextension11.HIRHCCHIRHRORIICOIHIHCICOIRHHC(com.moonsworth.lunar.client.config.option.OptionTraits.field5);
            if (alert27 != null) {
               alert27.method4(lightingextension11, text2);
            }
         }
      }

      if (this.field2 != null) {
         SettingOverride alert10 = this.field2.get(OverrideSource.CLIENT_CRITERIA);
         if (alert10 != null && alert10.test(text2)) {
            this.method1(framework7extension1, OverrideSource.CLIENT_CRITERIA, (Boolean)alert10.method6());
         }
      }
   }

   public void method6(Framework7Extension framework7extension1) {
      ModChildren alertextension2 = (ModChildren)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5);
      if (alertextension2 != null) {
         for (Framework7Extension framework7extension4 : alertextension2.getChildren()) {
            SettingIntercept alert25 = (SettingIntercept)framework7extension4.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field4);
            if (alert25 != null) {
               alert25.method5(framework7extension4);
            }
         }
      }

      OptionContainer framework57 = (OptionContainer)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
      if (framework57 != null) {
         for (ClientOption lightingextension9 : framework57.method2()) {
            SettingIntercept alert26 = (SettingIntercept)lightingextension9.HIRHCCHIRHRORIICOIHIHCICOIRHHC(com.moonsworth.lunar.client.config.option.OptionTraits.field5);
            if (alert26 != null) {
               alert26.method5(lightingextension9);
            }
         }
      }

      if (this.field2 != null && this.field2.containsKey(OverrideSource.CLIENT_CRITERIA)) {
         this.method1(framework7extension1, OverrideSource.CLIENT_CRITERIA, null);
      }
   }

   public void method7(Predicate<String> predicate1, Boolean flag2) {
      if (this.field2 == null) {
         this.field2 = new EnumMap<>(OverrideSource.class);
      }

      SettingOverride alert3 = this.field2.computeIfAbsent(OverrideSource.CLIENT_CRITERIA, arg0 -> new SettingOverride());
      alert3.method3(predicate1, flag2);
   }

   @Nullable
   private SettingOverride<Boolean> method8() {
      if (this.field2 != null && !this.field2.isEmpty()) {
         for (Entry entry2 : this.field2.entrySet()) {
            SettingOverride alert3 = (SettingOverride)entry2.getValue();
            Boolean flag4 = (Boolean)alert3.method1().orElse(null);
            if (flag4 != null) {
               return alert3;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   @Generated
   public SettingOverrideInterceptor() {
   }

   @Nullable
   @Generated
   public Boolean method9() {
      return this.field1;
   }
}
