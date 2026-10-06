package com.moonsworth.lunar.client.event;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.guiRewindhandlers.Annotation3;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;
import com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate3;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.highlight.HighlightImpl;

public class ClientEventBus {
   private static final boolean field1 = !LunarBuildData.field4;
   private static final boolean field2 = !ThreadModuleDump63.field1
      && (
         !LunarBuildData.field4
            || ThreadModuleDump48.field30 && FeatureFlag.THROW_ON_WRONG_THREAD_BETA.isEnabled()
            || ThreadModuleDump48.field29 && FeatureFlag.THROW_ON_WRONG_THREAD_DEV.isEnabled()
      );
   private static final boolean field3 = LunarBuildData.field4 && (!ThreadModuleDump48.field30 || FeatureFlag.IGNORE_EXTERNAL_THREAD_BETA.isEnabled());
   private static final ClientEventBus field4 = field1 ? new ClientEventBusDebug() : new ClientEventBus();
   private final ReentrantLock field5 = new ReentrantLock();
   protected final List<ClientEventBus.EventListenerChange<?>> field6 = new ArrayList<>();
   protected final AtomicInteger field7 = new AtomicInteger();
   protected final HashMap<Class<? extends Highlight>, ListenerRegistration<? extends Highlight>[]> field8 = new HashMap<>();
   public static final int field9 = 100;

   public <T extends Highlight> void method1(Class<T> var1, Runnable var2) {
      this.method2(var1, var1x -> var2.run());
   }

   public <T extends Highlight> void method2(Class<T> var1, Consumer<T> var2) {
      this.method4(var1, var2, 100);
   }

   public <T extends Highlight> void method3(Class<T> var1, Runnable var2, int var3) {
      this.method4(var1, var1x -> var2.run(), var3);
   }

   public <T extends Highlight> void method4(Class<T> var1, Consumer<T> var2, int var3) {
      if (this.field5.isHeldByCurrentThread()) {
         this.method5(var1, var2, var3);
      } else {
         this.field5.lock();

         try {
            this.method5(var1, var2, var3);
         } finally {
            this.field5.unlock();
         }
      }
   }

   private <T extends Highlight> void method5(Class<T> var1, Consumer<T> var2, int var3) {
      if (this.field7.get() != 0) {
         this.method21(new ClientEventBus.EventListenerChange(true, var1, var2, var3));
      } else {
         Object[] var4 = this.field8.get(var1);
         if (var4 == null || var4.length == 0) {
            this.method9(var1);
         }

         this.field8.compute(var1, (var2x, var3x) -> {
            ListenerRegistration[] var4x = new ListenerRegistration[(var3x == null ? 0 : var3x.length) + 1];
            var4x[var3x == null ? 0 : var3x.length] = new ListenerRegistration(var3, var2);
            if (var4x.length > 1) {
               System.arraycopy(var3x, 0, var4x, 0, var3x.length);
               Arrays.sort(var4x, Comparator.comparingInt(var0x -> -var0x.priority()));
            }

            return var4x;
         });
      }
   }

   public <T extends Highlight> void method6(Class<T> var1, Consumer<T> var2) {
      if (this.field5.isHeldByCurrentThread()) {
         this.method7(var1, var2);
      } else {
         this.field5.lock();

         try {
            this.method7(var1, var2);
         } finally {
            this.field5.unlock();
         }
      }
   }

   private <T extends Highlight> void method7(Class<T> var1, Consumer<T> var2) {
      if (this.field7.get() != 0) {
         this.method21(new ClientEventBus.EventListenerChange(false, var1, var2, 0));
      } else {
         Object var3 = this.field8.computeIfPresent(var1, (var1x, var2x) -> {
            ArrayList var3x = new ArrayList();

            for (ListenerRegistration var7 : var2x) {
               if (var7.method1() != var2) {
                  var3x.add(var7);
               }
            }

            return var3x.isEmpty() ? null : var3x.toArray(new ListenerRegistration[0]);
         });
         if (var3 == null) {
            this.method10(var1);
         }
      }
   }

   protected <T extends Highlight> void method8(Class<T> var1, @Nullable T var2, @Nullable Supplier<T> var3) {
      if ((var2 != null || var3 != null) && !this.field6.isEmpty()) {
         boolean var4 = false;
         int var6 = 0;

         int var5;
         do {
            var5 = this.field6.size();

            for (int var7 = var6; var7 < var5; var7++) {
               ClientEventBus.EventListenerChange var8 = this.field6.get(var7);
               if (var8.field1 && var8.field2 == var1) {
                  try {
                     if (var2 == null) {
                        var2 = (Highlight)var3.get();
                        if (var2 == null) {
                           return;
                        }

                        this.method18(var1);
                        var4 = true;
                     }

                     var8.field3.accept((T)var2);
                  } catch (Exception | Error var10) {
                     this.method23(var10, var1);
                  }
               }
            }

            var6 = this.field6.size();
         } while (var5 != var6);

         if (var4) {
            this.method19();
         }
      }
   }

   private void method9(Class<? extends Highlight> var1) {
      this.method11(var1).ifPresent(DynamicListener::method1);
   }

   private void method10(Class<? extends Highlight> var1) {
      this.method11(var1).ifPresent(DynamicListener::method3);
   }

   private Optional<DynamicListener> method11(Class<? extends Highlight> var1) {
      Annotation3 var2 = var1.getAnnotation(Annotation3.class);
      if (!Nameplate2.class.isAssignableFrom(var1)) {
         if (var2 != null) {
            throw new RuntimeException("Event " + var1.getName() + " has a @TriggeredBy annotation, but does not extend DynamicListenerEvent");
         } else {
            return Optional.empty();
         }
      } else if (var2 == null) {
         throw new RuntimeException("DynamicListenerEvent must be annotated with @TriggeredBy, " + var1.getName());
      } else {
         return Optional.of(DynamicListener.method7(var2.value()));
      }
   }

   @Nullable
   public <T extends Highlight> T method12(Class<T> var1, Supplier<T> var2) {
      this.method27(var1);
      if (this.field5.isHeldByCurrentThread()) {
         return this.method13(var1, var2);
      }

      this.field5.lock();

      try {
         return this.method13(var1, var2);
      } finally {
         this.field5.unlock();
      }
   }

   @Nullable
   private <T extends Highlight> T method13(Class<T> var1, Supplier<T> var2) {
      ListenerRegistration[] var3 = this.field8.get(var1);
      Highlight var4 = null;
      if (var3 != null) {
         var4 = (Highlight)var2.get();
         if (var4 == null) {
            return null;
         }

         this.method18(var1);

         try {
            for (ListenerRegistration var8 : var3) {
               try {
                  Consumer var9 = var8.method1();
                  var9.accept(var4);
               } catch (Throwable var10) {
                  this.method22(var1, var10);
               }
            }
         } catch (Exception | Error var11) {
            this.method23(var11, var1);
         }
      }

      this.method8(var1, var4, var2);
      if (var3 != null) {
         this.method19();
      }

      return (T)var4;
   }

   public <T extends Highlight> void method14(Class<T> var1, Consumer<Consumer<T>> var2) {
      this.method27(var1);
      if (this.field5.isHeldByCurrentThread()) {
         this.method15(var1, var2);
      } else {
         this.field5.lock();

         try {
            this.method15(var1, var2);
         } finally {
            this.field5.unlock();
         }
      }
   }

   private <T extends Highlight> void method15(Class<T> var1, Consumer<Consumer<T>> var2) {
      ListenerRegistration[] var3 = this.field8.get(var1);
      if (var3 != null) {
         this.method18(var1);

         try {
            var2.accept(var3x -> {
               if (var3x != null) {
                  for (ListenerRegistration var7 : var3) {
                     try {
                        Consumer var8 = var7.method1();
                        var8.accept(var3x);
                     } catch (Throwable var9) {
                        this.method22(var1, var9);
                     }
                  }
               }
            });
         } catch (Exception | Error var5) {
            this.method23(var5, var1);
         }
      }

      if (!this.field6.isEmpty()) {
         var2.accept(var2x -> this.method8(var1, var2x, null));
      }

      if (var3 != null) {
         this.method19();
      }
   }

   public <PRE extends Highlight, POST extends Highlight> void method16(
      Class<PRE> var1, Class<POST> var2, Function<ClientEventBus.Type, Pair<PRE, POST>> var3, Runnable var4
   ) {
      this.method27(var1);
      this.method27(var2);
      if (this.field5.isHeldByCurrentThread()) {
         this.method17(var1, var2, var3, var4);
      } else {
         this.field5.lock();

         try {
            this.method17(var1, var2, var3, var4);
         } finally {
            this.field5.unlock();
         }
      }
   }

   private <PRE extends Highlight, POST extends Highlight> void method17(
      Class<PRE> var1, Class<POST> var2, Function<ClientEventBus.Type, Pair<PRE, POST>> var3, Runnable var4
   ) {
      ListenerRegistration[] var5 = this.field8.get(var1);
      ListenerRegistration[] var6 = this.field8.get(var2);
      if (var5 != null || var6 != null) {
         try {
            Pair var17 = (Pair)var3.apply(
               !this.field6.isEmpty()
                  ? ClientEventBus.Type.BOTH
                  : (var5 == null ? ClientEventBus.Type.POST : (var6 == null ? ClientEventBus.Type.PRE : ClientEventBus.Type.BOTH))
            );
            if (var5 != null) {
               Highlight var8 = (Highlight)var17.first();
               if (var8 != null) {
                  this.method18(var1);

                  for (ListenerRegistration var12 : var5) {
                     try {
                        Consumer var13 = var12.method1();
                        var13.accept(var8);
                     } catch (Throwable var15) {
                        this.method22(var1, var15);
                     }
                  }

                  this.method8(var1, var8, null);
                  this.method19();
                  if (var8 instanceof HighlightImpl var19 && var19.isCancelled()) {
                     return;
                  }
               }
            } else {
               this.method8(var1, (Highlight)var17.first(), null);
            }

            var4.run();
            if (var6 == null) {
               this.method8(var2, (Highlight)var17.second(), null);
            } else {
               Highlight var18 = (Highlight)var17.second();
               if (var18 != null) {
                  this.method18(var2);

                  for (ListenerRegistration var23 : var6) {
                     try {
                        Consumer var24 = var23.method1();
                        var24.accept(var18);
                     } catch (Throwable var14) {
                        this.method22(var2, var14);
                     }
                  }

                  this.method19();
               }
            }
         } catch (Exception | Error var16) {
            if (!LunarBuildData.field4) {
               Slayer.method7("EventBus [" + var1 + "][" + var2 + "]");
               throw var16;
            }

            if (var16 instanceof AbstractMethodError || var16 instanceof IllegalAccessError) {
               throw var16;
            }

            Slayer.method7("EventBus [" + var1 + "][" + var2 + "]: " + var16.getClass() + ":" + var16.getMessage());
            var16.printStackTrace();
         }
      } else if (!this.field6.isEmpty()) {
         Pair var7 = (Pair)var3.apply(ClientEventBus.Type.BOTH);
         this.method8(var1, (Highlight)var7.first(), null);
         var4.run();
         this.method8(var2, (Highlight)var7.second(), null);
      } else {
         var4.run();
      }
   }

   protected void method18(Class<? extends Highlight> var1) {
      this.field7.incrementAndGet();
   }

   protected void method19() {
      if (this.field7.decrementAndGet() == 0) {
         this.method20();
      }
   }

   protected void method20() {
      if (!this.field6.isEmpty()) {
         for (ClientEventBus.EventListenerChange var2 : this.field6) {
            if (var2.field1) {
               this.method4(var2.field2, var2.field3, var2.field4);
            } else {
               this.method6(var2.field2, var2.field3);
            }
         }

         this.field6.clear();
      }
   }

   protected void method21(ClientEventBus.EventListenerChange<?> var1) {
      this.field6.add(var1);
   }

   private void method22(Class<? extends Highlight> var1, Throwable var2) {
      Slayer.method7("EventBus [" + var1 + "]: " + var2.getMessage());
      Inventorymod2.method6(var2, "EventBus", !LunarBuildData.field4, null);
   }

   private void method23(Throwable var1, Class<? extends Highlight> var2) {
      try {
         if (!LunarBuildData.field4) {
            Slayer.method7("EventBus [" + var2 + "]");
            throw var1;
         }

         if (!(var1 instanceof AbstractMethodError) && !(var1 instanceof IllegalAccessError)) {
            Slayer.method7("EventBus [" + var2 + "]: " + var1.getClass() + ":" + var1.getMessage());
            var1.printStackTrace();
         } else {
            throw var1;
         }
      } catch (Throwable var4) {
         throw var4;
      }
   }

   public void method24(Runnable var1) {
      if (this.field5.isHeldByCurrentThread()) {
         var1.run();
      } else {
         this.field5.lock();

         try {
            var1.run();
         } finally {
            this.field5.unlock();
         }
      }
   }

   public <T> T method25(Supplier<T> var1) {
      if (this.field5.isHeldByCurrentThread()) {
         return (T)var1.get();
      }

      this.field5.lock();

      try {
         return (T)var1.get();
      } finally {
         this.field5.unlock();
      }
   }

   public boolean method26() {
      return this.field5.isHeldByCurrentThread();
   }

   private void method27(Class<? extends Highlight> var1) {
      if (field2) {
         if (!Nameplate3.class.isAssignableFrom(var1)) {
            if (!Bridge.method42().method2()) {
               Slayer.method5("Event [" + var1.getSimpleName() + "] was fired on the wrong thread! If this is intentional, make it implement ThreadedEvent");
               Thread.dumpStack();
            }
         }
      }
   }

   public HashMap<Class<? extends Highlight>, ListenerRegistration<? extends Highlight>[]> method28() {
      return this.field8;
   }

   @Generated
   public static ClientEventBus method29() {
      return field4;
   }

   @Generated
   public ReentrantLock method30() {
      return this.field5;
   }

   protected class EventListenerChange<T extends Highlight> {
      private final boolean field1;
      private final Class<T> field2;
      private final Consumer<T> field3;
      private final int field4;

      protected EventListenerChange(boolean var1, Class<T> var2, Consumer<T> var3, int var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }

      public boolean method1() {
         return this.field1;
      }

      public Class<T> method2() {
         return this.field2;
      }

      public Consumer<T> method3() {
         return this.field3;
      }

      public int priority() {
         return this.field4;
      }
   }

   public enum Type {
      PRE(true, false),
      POST(false, true),
      BOTH(true, true);

      private final boolean pre;
      private final boolean post;

      public <PRE extends Highlight, POST extends Highlight> Pair<PRE, POST> wrapEvents(Supplier<PRE> var1, Supplier<POST> var2) {
         if (this == BOTH) {
            return Pair.of((Highlight)var1.get(), (Highlight)var2.get());
         } else {
            return this == PRE ? Pair.of((Highlight)var1.get(), null) : Pair.of(null, (Highlight)var2.get());
         }
      }

      @Generated
      public boolean isPre() {
         return this.pre;
      }

      @Generated
      public boolean isPost() {
         return this.post;
      }

      @Generated
      Type(boolean var3, boolean var4) {
         this.pre = var3;
         this.post = var4;
      }
   }
}
