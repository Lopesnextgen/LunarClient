package com.moonsworth.lunar.client.mod.misc.rewind;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModLifecycle;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.replay.audio.AudioStream;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import lombok.Generated;

public class RewindAudioManager extends AbstractFeature {
   private final Set<AudioStream<?>> field8 = new HashSet<>();

   public RewindAudioManager(RewindHandlers rewindhandlers1) {
      super(true);
      ModLifecycle framework10extension2 = (ModLifecycle)rewindhandlers1.method64(ModTraits.field12, arg0 -> ModLifecycle.method13());
      if (framework10extension2 != null) {
         framework10extension2.method4(this);
      }
   }

   public void method1(AudioStream<?> rewindhandlers21) {
      this.field8.add(rewindhandlers21);
      rewindhandlers21.play();
   }

   public boolean method2(AudioStream<?> rewindhandlers21) {
      return this.field8.contains(rewindhandlers21);
   }

   public void method13() {
      Iterator iterator1 = this.field8.iterator();

      while (iterator1.hasNext()) {
         AudioStream rewindhandlers22 = (AudioStream)iterator1.next();
         if (!rewindhandlers22.method23()) {
            iterator1.remove();

            try {
               rewindhandlers22.cleanup();
            } catch (IOException exception4) {
               CrashReporter.method5(exception4, "RewindMod");
            }
         }

         rewindhandlers22.method22(false);
      }
   }

   public void cleanup() {
      for (AudioStream rewindhandlers22 : this.field8) {
         try {
            rewindhandlers22.cleanup();
         } catch (IOException exception4) {
            CrashReporter.method5(exception4, "RewindMod");
         }
      }

      this.field8.clear();
   }

   public void reload() {
      for (AudioStream rewindhandlers22 : this.field8) {
         try {
            rewindhandlers22.reload();
         } catch (IOException exception4) {
            CrashReporter.method5(exception4, "RewindMod");
         }
      }
   }

   public void method4(long number1) {
      for (AudioStream rewindhandlers24 : this.field8) {
         rewindhandlers24.method14(number1);
      }
   }

   public String getId() {
      return "REWIND_HANDLERS";
   }

   @Generated
   public Set<AudioStream<?>> getSources() {
      return this.field8;
   }
}
