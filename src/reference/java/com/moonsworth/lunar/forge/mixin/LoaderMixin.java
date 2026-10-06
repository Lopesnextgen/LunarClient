package com.moonsworth.lunar.forge.mixin;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.forge.Ichor5Iterator;
import com.moonsworth.lunar.forge.Ichor6Iterator;
import com.moonsworth.lunar.forge.ForgeModMixinScanner;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import net.minecraftforge.classloading.FMLForgePlugin;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.discovery.ModDiscoverer;
import net.minecraftforge.fml.relauncher.FMLInjectionData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Loader.class)
public class LoaderMixin {
   @Shadow
   public static String mccversion;
   @Shadow
   public static String MC_VERSION;
   @Shadow
   public static List<String> injectedContainers;
   @Shadow
   public static String mcpversion;
   @Shadow
   public ModDiscoverer discoverer;

   public LoaderMixin() {
   }

   @Inject(method = "instance", at = @At("HEAD"))
   private static void ichor$beforeLoaderInstance(CallbackInfoReturnable<Loader> callbackinforeturnable0) {
      mccversion = MC_VERSION;
      mcpversion = "+ Ichor";
      injectedContainers = new ArrayList<>();
      FMLForgePlugin.forgeLocation = ((Path)((IchorPipeline)IchorAPI.getPipeline(callbackinforeturnable0).orElseThrow()).method11("forge").orElseThrow()).toFile();
      FMLInjectionData.mccversion = null;
      FMLInjectionData.minecraftHome = Config.method34().toFile();
      FMLInjectionData.major = String.valueOf(ForgeVersion.majorVersion);
      FMLInjectionData.minor = String.valueOf(ForgeVersion.minorVersion);
      FMLInjectionData.rev = String.valueOf(ForgeVersion.revisionVersion);
      FMLInjectionData.build = String.valueOf(ForgeVersion.buildVersion);
      FMLInjectionData.mccversion = ForgeVersion.mcVersion;
      FMLInjectionData.mcpversion = ForgeVersion.mcpVersion;
      EventBus.method2(injectedContainers, LoaderMixin.class.getClassLoader());
   }

   @Inject(
      method = "identifyMods",
      at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/Loader;identifyDuplicates(Ljava/util/List;)V"),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private void ichor$addMods(CallbackInfoReturnable<ModDiscoverer> callbackinforeturnable1, ModDiscoverer moddiscoverer2) {
      IchorPipeline ichor73 = (IchorPipeline)IchorAPI.getPipeline(LoaderMixin.class).orElseThrow();
      ichor73.method19(Ichor5Iterator.class).ifPresent(arg2x -> arg2x.method6().forEach((arg2xx, arg3x) -> EventBus.method1(ichor73, arg2xx, arg3x, moddiscoverer2)));

      for (ForgeModMixinScanner mixinhelper5 : Ichor6Iterator.field3) {
         EventBus.method1(ichor73, mixinhelper5.getKey(), mixinhelper5.method4(), moddiscoverer2);
      }
   }

   @Inject(method = "initializeMods", at = @At("HEAD"))
   private void check$initializeMods(CallbackInfo callback1) {
      Ichor6Iterator.field2.info("Loader: initializeMods", new Object[0]);
   }
}
