package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension;
import com.moonsworth.lunar.client.render.turbo.TurboEngineManager;
import com.moonsworth.lunar.client.render.turbo.TurboEntityRecorder;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Objects;
import lombok.Generated;

public class Rewindhandlers2_5 {
   private final ClientOption<?> option;
   private Horsestats20Extension entity = null;
   private Object lastValue = null;

   public void update() {
      if (this.entity != null) {
         Object var1 = this.option.get();
         if (!Objects.equals(var1, this.lastValue)) {
            this.lastValue = var1;
            if (ThreadModuleDump63.MC_VERSION >= 8) {
               this.applyToEntity();
            }
         }
      }
   }

   @Annotation2(min = 8)
   private void applyToEntity() {
      TurboEngineManager var1 = ThreadModuleDump63.method4().method89();
      if (var1.method3()) {
         TurboEntityRecorder var2 = var1.method26();
         var2.method13(this.entity, false);
      }
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 != null && this.getClass() == var1.getClass()) {
         Rewindhandlers2_5 var2 = (Rewindhandlers2_5)var1;
         return Objects.equals(this.option, var2.option);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(this.option);
   }

   @Generated
   public Rewindhandlers2_5(ClientOption<?> var1) {
      this.option = var1;
   }

   @Generated
   public ClientOption<?> getOption() {
      return this.option;
   }

   @Generated
   public void setEntity(Horsestats20Extension var1) {
      this.entity = var1;
   }
}
