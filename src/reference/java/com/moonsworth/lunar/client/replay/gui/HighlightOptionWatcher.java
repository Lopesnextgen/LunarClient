package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension;
import com.moonsworth.lunar.client.render.turbo.TurboEngineManager;
import com.moonsworth.lunar.client.render.turbo.TurboEntityRecorder;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Objects;
import lombok.Generated;

public class HighlightOptionWatcher {
   private final ClientOption<?> field1;
   private Horsestats20Extension field2 = null;
   private Object lastValue = null;

   public void update() {
      if (this.field2 != null) {
         Object obj1 = this.field1.get();
         if (!Objects.equals(obj1, this.lastValue)) {
            this.lastValue = obj1;
            if (Ref.MC_VERSION >= 8) {
               this.method1();
            }
         }
      }
   }

   @VersionGate(min = 8)
   private void method1() {
      TurboEngineManager fogiterator_31 = Ref.method4().method89();
      if (fogiterator_31.method3()) {
         TurboEntityRecorder highlight3iterator232 = fogiterator_31.method26();
         highlight3iterator232.method13(this.field2, false);
      }
   }

   @Override
   public boolean equals(Object obj1) {
      if (obj1 != null && this.getClass() == obj1.getClass()) {
         HighlightOptionWatcher rewindhandlers2_52 = (HighlightOptionWatcher)obj1;
         return Objects.equals(this.field1, rewindhandlers2_52.field1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(this.field1);
   }

   @Generated
   public HighlightOptionWatcher(ClientOption<?> lightingextension1) {
      this.field1 = lightingextension1;
   }

   @Generated
   public ClientOption<?> getOption() {
      return this.field1;
   }

   @Generated
   public void method2(Horsestats20Extension horsestats20extension1) {
      this.field2 = horsestats20extension1;
   }
}
