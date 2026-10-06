package com.moonsworth.lunar.ichor;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import com.moonsworth.lunar.ichor.api.IchorAPI2;
import com.moonsworth.lunar.ichor.util.FatalIchorError5;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.launch.platform.container.ContainerHandleURI;
import org.spongepowered.asm.launch.platform.container.ContainerHandleVirtual;
import org.spongepowered.asm.launch.platform.container.IContainerHandle;
import org.spongepowered.asm.logging.ILogger;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.MixinEnvironment.CompatibilityLevel;
import org.spongepowered.asm.mixin.MixinEnvironment.Phase;
import org.spongepowered.asm.mixin.MixinEnvironment.Side;
import org.spongepowered.asm.mixin.transformer.MixinProxyImpl;
import org.spongepowered.asm.service.IAdviceProvider;
import org.spongepowered.asm.service.IClassTracker;
import org.spongepowered.asm.service.IFeatureValidator;
import org.spongepowered.asm.service.IMixinAuditTrail;
import org.spongepowered.asm.service.ITransformerProvider;
import org.spongepowered.asm.service.MixinServiceAbstract;

public class MixinServiceAbstractImpl extends MixinServiceAbstract {
   private static final FatalIchorError5 field1 = new FatalIchorError5("Genesis/IchorMixinService");
   private final URLClassLoader field2;
   private final IClassProvider field3;
   private final IClassBytecodeProvider field4;

   public MixinServiceAbstractImpl() {
      ClassLoader var1 = this.getClass().getClassLoader();
      if (var1 instanceof IchorAPI2 var2) {
         this.field2 = var2.method4();
         this.field3 = new IClassProvider();
         this.field4 = new IClassBytecodeProvider(this.field2);
      } else {
         throw new IllegalStateException("Loading IchorMixinService outside of an IchorPipeline? " + var1.getClass().getName());
      }
   }

   public String getName() {
      return "Ichor";
   }

   public boolean isValid() {
      return MixinBootstrap.class.getClassLoader() instanceof IchorAPI2;
   }

   public void prepare() {
      try {
         Field var1 = this.getClass().getSuperclass().getDeclaredField("sideName");
         var1.setAccessible(true);
         var1.set(this, Side.CLIENT.name());
      } catch (Throwable var2) {
         throw var2;
      }
   }

   public Phase getInitialPhase() {
      return Phase.PREINIT;
   }

   public void init() {
      super.init();
      Mixins.registerErrorHandlerClass(IMixinErrorImpl.class.getName());
      this.field2.method19(new MixinProxyImpl(this.field2));
      field1.info("Initializing MixinExtras " + MixinExtrasBootstrap.getVersion() + " in " + this.getClass().getClassLoader().getName());
      MixinExtrasBootstrap.init();
   }

   public org.spongepowered.asm.service.IClassProvider getClassProvider() {
      return this.field3;
   }

   public org.spongepowered.asm.service.IClassBytecodeProvider getBytecodeProvider() {
      return this.field4;
   }

   public ITransformerProvider getTransformerProvider() {
      return null;
   }

   public IClassTracker getClassTracker() {
      return null;
   }

   public IMixinAuditTrail getAuditTrail() {
      return null;
   }

   public IFeatureValidator getFeatureValidator() {
      return IFeatureValidator.ALLOW_ALL;
   }

   public IAdviceProvider getAdviceProvider() {
      return IAdviceProvider.GENERIC;
   }

   public Collection<String> getPlatformAgents() {
      return new ArrayList<>();
   }

   public IContainerHandle getPrimaryContainer() {
      try {
         URL var1 = this.getClass().getProtectionDomain().getCodeSource().getLocation();
         if (var1 != null) {
            URI var2 = var1.toURI();

            try {
               Paths.get(var2);
            } catch (FileSystemNotFoundException var6) {
               try {
                  FileSystems.newFileSystem(var2, new HashMap<>());
                  field1.info("Making file system for %s", var2);
               } catch (IOException var5) {
                  field1.warn("Failed to make file system for %s", var2);
               }
            }

            return new ContainerHandleURI(var2);
         }
      } catch (URISyntaxException var7) {
      }

      return new ContainerHandleVirtual(this.getName());
   }

   public InputStream getResourceAsStream(String var1) {
      return MixinBootstrap.class.getClassLoader().getResourceAsStream(var1);
   }

   public CompatibilityLevel getMinCompatibilityLevel() {
      return this.getMaxCompatibilityLevel();
   }

   public CompatibilityLevel getMaxCompatibilityLevel() {
      return CompatibilityLevel.JAVA_21;
   }

   protected ILogger createLogger(String var1) {
      ClassLoader var2 = MixinBootstrap.class.getClassLoader();
      if (var2 instanceof IchorAPI2 var3) {
         return new LoggerAdapterAbstractIterator("Ichor/" + var1, var3.method4().method18());
      } else {
         throw new IllegalStateException("Loading IchorMixinService outside of an IchorPipeline? " + var2.getClass().getName());
      }
   }
}
