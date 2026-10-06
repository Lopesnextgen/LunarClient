package com.moonsworth.lunar.forge.mixin;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.forge.Ichor5Iterator;
import com.moonsworth.lunar.forge.MixinCore;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import java.util.ArrayList;
import java.util.List;
import net.minecraftforge.classloading.FMLForgePlugin;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModClassLoader;
import net.minecraftforge.fml.common.discovery.ModDiscoverer;
import net.minecraftforge.fml.relauncher.FMLInjectionData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Loader.class)
public class LoaderMixin2 {
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

   @Inject(method = "instance", at = @At("HEAD"))
   private static void ichor$beforeLoaderInstance(CallbackInfoReturnable<Loader> var0) {
      mccversion = MC_VERSION;
      mcpversion = "+ Ichor";
      injectedContainers = new ArrayList<>();
      injectedContainers.add("net.minecraftforge.fml.common.FMLContainer");
      injectedContainers.add("net.minecraftforge.common.ForgeModContainer");
      FMLForgePlugin.forgeLocation = IchorAPI.getPipeline(var0).orElseThrow().method11("forge").orElseThrow().toFile();
      FMLInjectionData.mccversion = null;
      FMLInjectionData.minecraftHome = Config.method34().toFile();
      FMLInjectionData.major = String.valueOf(ForgeVersion.majorVersion);
      FMLInjectionData.minor = String.valueOf(ForgeVersion.minorVersion);
      FMLInjectionData.rev = String.valueOf(ForgeVersion.revisionVersion);
      FMLInjectionData.build = String.valueOf(ForgeVersion.buildVersion);
      FMLInjectionData.mccversion = ForgeVersion.mcVersion;
      FMLInjectionData.mcpversion = ForgeVersion.mcpVersion;
   }

   @Redirect(
      method = "identifyMods",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraftforge/fml/common/discovery/ModDiscoverer;findClasspathMods(Lnet/minecraftforge/fml/common/ModClassLoader;)V"
      )
   )
   private void ichor$addMods(ModDiscoverer var1, ModClassLoader var2) {
      IchorPipeline var3 = IchorAPI.getPipeline(LoaderMixin2.class).orElseThrow();
      var3.method19(Ichor5Iterator.class).ifPresent(var2x -> var2x.method6().forEach((var2xx, var3x) -> MixinCore.method1(var3, var2xx, var3x, var1)));
   }
}
