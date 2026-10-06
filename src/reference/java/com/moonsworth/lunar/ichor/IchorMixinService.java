package com.moonsworth.lunar.ichor;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import com.moonsworth.lunar.ichor.api.IchorClassLoader;
import com.moonsworth.lunar.ichor.util.IchorLogger;
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

public class IchorMixinService extends MixinServiceAbstract {
   private static final IchorLogger field1 = new IchorLogger("Genesis/IchorMixinService");
   private final URLClassLoader field2;
   private final IClassProvider field3;
   private final IClassBytecodeProvider field4;

   public IchorMixinService() {
      ClassLoader classloader1 = this.getClass().getClassLoader();
      if (classloader1 instanceof IchorClassLoader ichorapi22) {
         this.field2 = ichorapi22.method4();
         this.field3 = new IClassProvider();
         this.field4 = new IClassBytecodeProvider(this.field2);
      } else {
         throw new IllegalStateException("Loading IchorMixinService outside of an IchorPipeline? " + classloader1.getClass().getName());
      }
   }

   public String getName() {
      return "Ichor";
   }

   public boolean isValid() {
      return MixinBootstrap.class.getClassLoader() instanceof IchorClassLoader;
   }

   public void prepare() {
      try {
         Field field1_ = this.getClass().getSuperclass().getDeclaredField("sideName");
         field1_.setAccessible(true);
         field1_.set(this, Side.CLIENT.name());
      } catch (Throwable exception2) {
         throw exception2;
      }
   }

   public Phase getInitialPhase() {
      return Phase.PREINIT;
   }

   public void init() {
      super.init();
      Mixins.registerErrorHandlerClass(IchorErrorHandler.class.getName());
      this.field2.method19(new MixinProxyImpl(this.field2));
      field1.info("Initializing MixinExtras " + MixinExtrasBootstrap.getVersion() + " in " + this.getClass().getClassLoader().getName(), new Object[0]);
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
         URL url1 = this.getClass().getProtectionDomain().getCodeSource().getLocation();
         if (url1 != null) {
            URI uri2 = url1.toURI();

            try {
               Paths.get(uri2);
            } catch (FileSystemNotFoundException filesystemnotfoundexception6) {
               try {
                  FileSystems.newFileSystem(uri2, new HashMap<>());
                  field1.info("Making file system for %s", new Object[]{uri2});
               } catch (IOException exception5) {
                  field1.warn("Failed to make file system for %s", new Object[]{uri2});
               }
            }

            return new ContainerHandleURI(uri2);
         }
      } catch (URISyntaxException urisyntaxexception7) {
      }

      return new ContainerHandleVirtual(this.getName());
   }

   public InputStream getResourceAsStream(String text1) {
      return MixinBootstrap.class.getClassLoader().getResourceAsStream(text1);
   }

   public CompatibilityLevel getMinCompatibilityLevel() {
      return this.getMaxCompatibilityLevel();
   }

   public CompatibilityLevel getMaxCompatibilityLevel() {
      return CompatibilityLevel.JAVA_21;
   }

   protected ILogger createLogger(String text1) {
      ClassLoader classloader2 = MixinBootstrap.class.getClassLoader();
      if (classloader2 instanceof IchorClassLoader ichorapi23) {
         return new IchorConsoleLogger("Ichor/" + text1, ichorapi23.method4().method18());
      } else {
         throw new IllegalStateException("Loading IchorMixinService outside of an IchorPipeline? " + classloader2.getClass().getName());
      }
   }
}
