package com.moonsworth.lunar.client.mod.misc.debug;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.PanelPosition;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.mod.ModSearchIndex;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugTask;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugPhase;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod.Profile;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod.ProfileEncoder;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod.ProfileUploader;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod.ProfileMetadata;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod.ThreadProfiler;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRewindUpdate;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProfilerDebugMod extends AbstractFeature {
   public static final File field8 = new File(Ref.method3().bridge$getMcDataDir(), "lunar-profiler");
   private final ToggleOption field9 = (ToggleOption)OptionFactory.method7("runProfiler").method31();
   private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
   private final List<ThreadProfiler> field10 = new CopyOnWriteArrayList<>();
   private ThreadProfiler field11 = null;
   private final Thread field12 = Thread.currentThread();
   private int field13 = 0;
   private int field14 = 0;
   private int field15 = 0;

   public ProfilerDebugMod(FpsDebugMod fpsdebugmod1) {
      super(true);
      this.method12(ModTraits.field16, ChildModBinding.method3(fpsdebugmod1));
      this.method50(this::onEnable);
      this.method3(this::onDisable);
      this.handle(EventRewindUpdate.class, this::method6);
      this.handle(EventSecond.class, this::method3);
      this.handle(EventSecond.class, this::method5);
   }

   protected void method1(boolean flag1) {
      this.method12(ModTraits.field6, ModEnabledState.method9(flag1));
      this.method12(ModTraits.field9, ModSearchIndex.method7());
      this.method12(ModTraits.field10, PanelPosition.method9());
      this.method12(ModTraits.field15, this.method3());
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field9});
   }

   private void onEnable() {
      com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod.ProfilerEngine.method1(
         arg1 -> this.field10.forEach(arg1x -> arg1x.method2(arg1))
      );
      this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
      this.scheduledExecutorService.scheduleAtFixedRate(this::method14, 0L, 1L, TimeUnit.MILLISECONDS);
   }

   private void onDisable() {
      com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod.ProfilerEngine.stop();
      this.field10.clear();
      this.scheduledExecutorService.shutdown();
      this.scheduledExecutorService = null;
   }

   private void method3(EventSecond highlightimpl41) {
      if (!this.method13()) {
         if (this.field11 != null) {
            this.field10.remove(this.field11);
            this.field11 = null;
         }
      } else {
         if (this.field11 == null) {
            this.field11 = new ThreadProfiler(this.field12);
         }

         this.field14++;
         if (this.field14 >= 10) {
            this.field14 = 0;
            this.field10.remove(this.field11);
            this.field11.stop();
            ThreadProfiler profilerdebugmod2 = this.field11;
            this.field11 = null;
            if (!profilerdebugmod2.isEmpty()) {
               ProfileMetadata profilerdebugmod33 = ProfileMetadata.create();
               String text4 = "data-" + this.field13 + ".lcprofile";
               this.field13++;
               BackgroundExecutor.method4(() -> {
                  try {
                     byte[] items3x = ProfileEncoder.method1(profilerdebugmod2.method4(), profilerdebugmod33);
                     Path path4x = field8.toPath().resolve(text4);
                     path4x.getParent().toFile().mkdirs();
                     Files.write(path4x, items3x);
                  } catch (IOException exception5) {
                     throw new RuntimeException(exception5);
                  }
               });
               this.field11 = new ThreadProfiler(this.field12);
               this.field10.add(this.field11);
            }
         }
      }
   }

   private boolean method13() {
      return (Boolean)this.field9.get() && this.method2(ModTraits.field10);
   }

   private void method5(EventSecond highlightimpl41) {
      this.field15++;
      if (this.field15 >= 60) {
         this.field15 = 0;
         BackgroundExecutor.method4(() -> {
            if (ProfileUploader.shouldProfile()) {
               BackgroundExecutor.method7(() -> {
                  ThreadProfiler profilerdebugmod1x = new ThreadProfiler(this.field12);
                  this.field10.add(profilerdebugmod1x);
                  BackgroundExecutor.method13(() -> {
                     this.field10.remove(profilerdebugmod1x);
                     profilerdebugmod1x.stop();
                     ProfileMetadata profilerdebugmod32 = ProfileMetadata.create();
                     if (!profilerdebugmod1x.isEmpty()) {
                        BackgroundExecutor.method4(() -> {
                           try {
                              byte[] items2x = ProfileEncoder.method1(profilerdebugmod1x.method4(), profilerdebugmod32);
                              ProfileUploader.method1(items2x);
                           } catch (IOException | InterruptedException exception3) {
                              exception3.printStackTrace();
                           }
                        });
                     }
                  }, 200);
               });
            }
         });
      }
   }

   private void method6(EventRewindUpdate highlightimpl61) {
      com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod.ProfilerEngine.nextFrame();
      this.field10.forEach(ThreadProfiler::method3);
   }

   private void method14() {
      try {
         this.field10.forEach(ThreadProfiler::method1);
      } catch (Exception exception2) {
         exception2.printStackTrace();
      }
   }

   @ConstantName
   public String getId() {
      return "PROFILER_DEBUG_MOD";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method8().method11(this);
   }

   public FpsDebugTask method15() {
      return new ProfilerDebugMod.Data();
   }

   private class Data implements FpsDebugTask {
      private Data() {
      }

      public String name() {
         return "profiler";
      }

      public boolean method1() {
         return true;
      }

      public Duration method2() {
         return Duration.ofSeconds(20L);
      }

      public FpsDebugPhase method3() {
         return FpsDebugPhase.PROFILER;
      }

      public Future<DebugArchive> method4() {
         return CompletableFuture.supplyAsync(() -> {
            try {
               ThreadProfiler profilerdebugmod1 = new ThreadProfiler(ProfilerDebugMod.this.field12);
               ProfilerDebugMod.this.field10.add(profilerdebugmod1);
               Thread.sleep(20000L);
               ProfilerDebugMod.this.field10.remove(profilerdebugmod1);
               profilerdebugmod1.stop();
               if (profilerdebugmod1.isEmpty()) {
                  return new DebugArchive();
               }

               ProfileMetadata profilerdebugmod32 = ProfileMetadata.create();
               byte[] items3 = ProfileEncoder.method1(profilerdebugmod1.method4(), profilerdebugmod32);
               return new DebugArchive().method1("profile.lcprofile", items3).method2("pauses.txt", this.method5(profilerdebugmod1));
            } catch (IOException | InterruptedException exception4) {
               throw new RuntimeException(exception4);
            }
         });
      }

      private String method5(ThreadProfiler profilerdebugmod1) {
         StringBuilder builder2 = new StringBuilder();

         for (int index3 = 0; index3 < profilerdebugmod1.method4().size(); index3++) {
            Profile profilerdebugmod24 = (Profile)profilerdebugmod1.method4().get(index3);

            for (com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod.ProfilerEngine.Data data6 : profilerdebugmod24.method7()) {
               builder2.append(index3)
                  .append(": ")
                  .append("type=")
                  .append(data6.method3().getName())
                  .append(",")
                  .append("timems=")
                  .append(data6.method4())
                  .append(",")
                  .append("additionalInfo=")
                  .append(data6.method5())
                  .append("\n");
            }
         }

         return builder2.toString();
      }
   }
}
