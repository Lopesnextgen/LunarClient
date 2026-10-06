package com.moonsworth.lunar.client.mod.misc.debug;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.mod.ModSearchIndex;
import com.moonsworth.lunar.client.command.ClientCommand;
import com.moonsworth.lunar.client.command.LiteralCommandNode;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugTask;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugCollector;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.F3DataTask;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.ProfileDataTask;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FeatureFlagsTask;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.McSettingsTask;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.GpuObjectsTask;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.MiscDataTask;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.ModsEnabledTask;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.util.io.FileExplorer;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.zip.ZipOutputStream;

public class FpsDebugMod extends AbstractFeature {
   private static final DateTimeFormatter field8 = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
   public static final Path field9 = LunarConstants.field6.resolve("lunar-fps-debug");
   private final FpsDebugCollector field10 = new FpsDebugCollector().method2(this.method14());

   public FpsDebugMod() {
      super(true);
      this.method14(new FpsDebugMod.Data());
   }

   private void method13() {
      Duration duration1 = this.field10.method3();
      Ref.method17("Collecting fps data... this will take ~" + duration1.toSeconds() + "s");
      Future future2 = this.field10.method8();
      BackgroundExecutor.method4(() -> {
         DebugArchive fpsdebugmod2x;
         try {
            fpsdebugmod2x = (DebugArchive)future2.get(duration1.toMillis() * 2L + 10000L, TimeUnit.MILLISECONDS);
         } catch (InterruptedException | ExecutionException | TimeoutException interruptedexception10) {
            BackgroundExecutor.method11(() -> Ref.method17("An unknown error occurred while collecting fps data!"));
            CrashReporter.method5(interruptedexception10, "FpsDebugMod");
            return;
         }

         try {
            Path path3 = field9.resolve("fps-dump-" + LocalDateTime.now().format(field8) + ".zip");
            path3.getParent().toFile().mkdirs();

            try (ZipOutputStream zipoutputstream4 = new ZipOutputStream(Files.newOutputStream(path3))) {
               fpsdebugmod2x.method5(zipoutputstream4);
            }

            BackgroundExecutor.method11(() -> {
               Ref.method17("Profile finished! it has been saved to " + path3);
               FileExplorer.method1(path3.toFile().getAbsoluteFile().getParentFile());
            });
         } catch (IOException exception9) {
            BackgroundExecutor.method11(() -> Ref.method17("An unknown error occurred while writing fps data!"));
            CrashReporter.method5(exception9, "FpsDebugMod");
         }
      });
   }

   protected void method1(boolean flag1) {
      this.method6(ModTraits.field6, ModEnabledState.method9(flag1));
      this.method6(ModTraits.field9, ModSearchIndex.method7());
   }

   protected List<Framework7Extension> method9() {
      this.field10.clear();
      ProfilerDebugMod profilerdebugmod1 = new ProfilerDebugMod(this);
      this.field10.method1(profilerdebugmod1.method15());
      this.field10.method2(this.method14());
      return List.of(profilerdebugmod1);
   }

   private List<FpsDebugTask> method14() {
      return List.of(
         new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.ThreadDumpTask(),
         new MiscDataTask(),
         new F3DataTask(),
         new ProfileDataTask(),
         new GpuObjectsTask(),
         new McSettingsTask(),
         new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.EventBusDebugTask(),
         new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DynamicListenerDebugTask(),
         new ModsEnabledTask(),
         new FeatureFlagsTask()
      );
   }

   @ConstantName
   public String getId() {
      return "FPS_DEBUG_MOD";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method8().method11(this);
   }

   private class Data extends ClientCommand {
      public Data() {
         super(LiteralCommandNode.method1("lcfpsdata").method3(arg1x -> FpsDebugMod.this.method13()));
      }

      public boolean method4() {
         return !LunarBuildData.field4;
      }
   }
}
