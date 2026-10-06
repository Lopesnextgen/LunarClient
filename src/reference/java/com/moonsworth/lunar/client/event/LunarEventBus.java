package com.moonsworth.lunar.client.event;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.ThreadedEvent;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
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

public class LunarEventBus {
   private static final boolean field1 = !LunarBuildData.field4;
   private static final boolean field2 = !Ref.field1
      && (
         !LunarBuildData.field4
            || LunarConstants.field30 && FeatureFlag.THROW_ON_WRONG_THREAD_BETA.isEnabled()
            || LunarConstants.field29 && FeatureFlag.THROW_ON_WRONG_THREAD_DEV.isEnabled()
      );
   private static final boolean field3 = LunarBuildData.field4 && (!LunarConstants.field30 || FeatureFlag.IGNORE_EXTERNAL_THREAD_BETA.isEnabled());
   private static final LunarEventBus field4 = field1 ? new DebuggingEventBus() : new LunarEventBus();
   private final ReentrantLock field5 = new ReentrantLock();
   protected final List<LunarEventBus.EventBusOperation<?>> field6 = new ArrayList<>();
   protected final AtomicInteger field7 = new AtomicInteger();
   protected final HashMap<Class<? extends LunarEvent>, EventListener<? extends LunarEvent>[]> field8 = new HashMap<>();
   public static final int field9 = 100;

   public <T extends LunarEvent> void method1(Class<T> clazz1, Runnable runnable2) {
      this.method2(clazz1, arg1x -> runnable2.run());
   }

   public <T extends LunarEvent> void method2(Class<T> clazz1, Consumer<T> consumer2) {
      this.method4(clazz1, consumer2, 100);
   }

   public <T extends LunarEvent> void method3(Class<T> clazz1, Runnable runnable2, int number3) {
      this.method4(clazz1, arg1x -> runnable2.run(), number3);
   }

   public <T extends LunarEvent> void method4(Class<T> clazz1, Consumer<T> consumer2, int number3) {
      if (this.field5.isHeldByCurrentThread()) {
         this.method5(clazz1, consumer2, number3);
      } else {
         this.field5.lock();

         try {
            this.method5(clazz1, consumer2, number3);
         } finally {
            this.field5.unlock();
         }
      }
   }

   private <T extends LunarEvent> void method5(Class<T> clazz1, Consumer<T> consumer2, int number3) {
      if (this.field7.get() != 0) {
         this.method21(new LunarEventBus.EventBusOperation(true, clazz1, consumer2, number3));
      } else {
         Object[] items4 = this.field8.get(clazz1);
         if (items4 == null || items4.length == 0) {
            this.method9(clazz1);
         }

         this.field8.compute(clazz1, (arg2x, arg3x) -> {
            EventListener[] items4x = new EventListener[(arg3x == null ? 0 : arg3x.length) + 1];
            items4x[arg3x == null ? 0 : arg3x.length] = new EventListener(number3, consumer2);
            if (items4x.length > 1) {
               System.arraycopy(arg3x, 0, items4x, 0, arg3x.length);
               Arrays.sort(items4x, Comparator.comparingInt(arg0x -> -arg0x.priority()));
            }

            return items4x;
         });
      }
   }

   public <T extends LunarEvent> void method6(Class<T> clazz1, Consumer<T> consumer2) {
      if (this.field5.isHeldByCurrentThread()) {
         this.method7(clazz1, consumer2);
      } else {
         this.field5.lock();

         try {
            this.method7(clazz1, consumer2);
         } finally {
            this.field5.unlock();
         }
      }
   }

   private <T extends LunarEvent> void method7(Class<T> clazz1, Consumer<T> consumer2) {
      if (this.field7.get() != 0) {
         this.method21(new LunarEventBus.EventBusOperation(false, clazz1, consumer2, 0));
      } else {
         Object obj3 = this.field8.computeIfPresent(clazz1, (arg1x, arg2x) -> {
            ArrayList list3x = new ArrayList();

            for (EventListener highlight47 : arg2x) {
               if (highlight47.method1() != consumer2) {
                  list3x.add(highlight47);
               }
            }

            return list3x.isEmpty() ? null : list3x.toArray(new EventListener[0]);
         });
         if (obj3 == null) {
            this.method10(clazz1);
         }
      }
   }

   protected <T extends LunarEvent> void method8(Class<T> clazz1, @Nullable T value2, @Nullable Supplier<T> supplier3) {
      if ((value2 != null || supplier3 != null) && !this.field6.isEmpty()) {
         boolean flag4 = false;
         int number6 = 0;

         int number5;
         do {
            number5 = this.field6.size();

            for (int index7 = number6; index7 < number5; index7++) {
               LunarEventBus.EventBusOperation data28 = this.field6.get(index7);
               if (data28.field1 && data28.field2 == clazz1) {
                  try {
                     if (value2 == null) {
                        value2 = (LunarEvent)supplier3.get();
                        if (value2 == null) {
                           return;
                        }

                        this.method18(clazz1);
                        flag4 = true;
                     }

                     data28.field3.accept((T)value2);
                  } catch (Exception | Error exception10) {
                     this.method23(exception10, clazz1);
                  }
               }
            }

            number6 = this.field6.size();
         } while (number5 != number6);

         if (flag4) {
            this.method19();
         }
      }
   }

   private void method9(Class<? extends LunarEvent> clazz1) {
      this.method11(clazz1).ifPresent(DynamicListener::method1);
   }

   private void method10(Class<? extends LunarEvent> clazz1) {
      this.method11(clazz1).ifPresent(DynamicListener::method3);
   }

   private Optional<DynamicListener> method11(Class<? extends LunarEvent> clazz1) {
      TriggeredBy annotation32 = clazz1.getAnnotation(TriggeredBy.class);
      if (!DynamicListenerEvent.class.isAssignableFrom(clazz1)) {
         if (annotation32 != null) {
            throw new RuntimeException("Event " + clazz1.getName() + " has a @TriggeredBy annotation, but does not extend DynamicListenerEvent");
         } else {
            return Optional.empty();
         }
      } else if (annotation32 == null) {
         throw new RuntimeException("DynamicListenerEvent must be annotated with @TriggeredBy, " + clazz1.getName());
      } else {
         return Optional.of(DynamicListener.method7(annotation32.value()));
      }
   }

   @Nullable
   public <T extends LunarEvent> T method12(Class<T> clazz1, Supplier<T> supplier2) {
      this.method27(clazz1);
      if (this.field5.isHeldByCurrentThread()) {
         return this.method13(clazz1, supplier2);
      }

      this.field5.lock();

      try {
         return this.method13(clazz1, supplier2);
      } finally {
         this.field5.unlock();
      }
   }

   @Nullable
   private <T extends LunarEvent> T method13(Class<T> clazz1, Supplier<T> supplier2) {
      EventListener[] items3 = this.field8.get(clazz1);
      LunarEvent highlight4 = null;
      if (items3 != null) {
         highlight4 = (LunarEvent)supplier2.get();
         if (highlight4 == null) {
            return null;
         }

         this.method18(clazz1);

         try {
            for (EventListener highlight48 : items3) {
               try {
                  Consumer consumer9 = highlight48.method1();
                  consumer9.accept(highlight4);
               } catch (Throwable exception10) {
                  this.method22(clazz1, exception10);
               }
            }
         } catch (Exception | Error exception11) {
            this.method23(exception11, clazz1);
         }
      }

      this.method8(clazz1, highlight4, supplier2);
      if (items3 != null) {
         this.method19();
      }

      return (T)highlight4;
   }

   public <T extends LunarEvent> void method14(Class<T> clazz1, Consumer<Consumer<T>> consumer2) {
      this.method27(clazz1);
      if (this.field5.isHeldByCurrentThread()) {
         this.method15(clazz1, consumer2);
      } else {
         this.field5.lock();

         try {
            this.method15(clazz1, consumer2);
         } finally {
            this.field5.unlock();
         }
      }
   }

   private <T extends LunarEvent> void method15(Class<T> clazz1, Consumer<Consumer<T>> consumer2) {
      EventListener[] items3 = this.field8.get(clazz1);
      if (items3 != null) {
         this.method18(clazz1);

         try {
            consumer2.accept(arg3x -> {
               if (arg3x != null) {
                  for (EventListener highlight47 : items3) {
                     try {
                        Consumer consumer8 = highlight47.method1();
                        consumer8.accept(arg3x);
                     } catch (Throwable exception9) {
                        this.method22(clazz1, exception9);
                     }
                  }
               }
            });
         } catch (Exception | Error exception5) {
            this.method23(exception5, clazz1);
         }
      }

      if (!this.field6.isEmpty()) {
         consumer2.accept(arg2x -> this.method8(clazz1, arg2x, null));
      }

      if (items3 != null) {
         this.method19();
      }
   }

   public <PRE extends LunarEvent, POST extends LunarEvent> void method16(
      Class<PRE> clazz1, Class<POST> clazz2, Function<LunarEventBus.EventPhase, Pair<PRE, POST>> function3, Runnable runnable4
   ) {
      this.method27(clazz1);
      this.method27(clazz2);
      if (this.field5.isHeldByCurrentThread()) {
         this.method17(clazz1, clazz2, function3, runnable4);
      } else {
         this.field5.lock();

         try {
            this.method17(clazz1, clazz2, function3, runnable4);
         } finally {
            this.field5.unlock();
         }
      }
   }

   private <PRE extends LunarEvent, POST extends LunarEvent> void method17(
      Class<PRE> clazz1, Class<POST> clazz2, Function<LunarEventBus.EventPhase, Pair<PRE, POST>> function3, Runnable runnable4
   ) {
      EventListener[] items5 = this.field8.get(clazz1);
      EventListener[] items6 = this.field8.get(clazz2);
      if (items5 != null || items6 != null) {
         try {
            Pair pair17 = (Pair)function3.apply(
               !this.field6.isEmpty()
                  ? LunarEventBus.EventPhase.BOTH
                  : (items5 == null ? LunarEventBus.EventPhase.POST : (items6 == null ? LunarEventBus.EventPhase.PRE : LunarEventBus.EventPhase.BOTH))
            );
            if (items5 != null) {
               LunarEvent highlight8 = (LunarEvent)pair17.first();
               if (highlight8 != null) {
                  this.method18(clazz1);

                  for (EventListener highlight412 : items5) {
                     try {
                        Consumer consumer13 = highlight412.method1();
                        consumer13.accept(highlight8);
                     } catch (Throwable exception15) {
                        this.method22(clazz1, exception15);
                     }
                  }

                  this.method8(clazz1, highlight8, null);
                  this.method19();
                  if (highlight8 instanceof CancellableEvent highlightimpl19 && highlightimpl19.isCancelled()) {
                     return;
                  }
               }
            } else {
               this.method8(clazz1, (LunarEvent)pair17.first(), null);
            }

            runnable4.run();
            if (items6 == null) {
               this.method8(clazz2, (LunarEvent)pair17.second(), null);
            } else {
               LunarEvent highlight18 = (LunarEvent)pair17.second();
               if (highlight18 != null) {
                  this.method18(clazz2);

                  for (EventListener highlight423 : items6) {
                     try {
                        Consumer consumer24 = highlight423.method1();
                        consumer24.accept(highlight18);
                     } catch (Throwable exception14) {
                        this.method22(clazz2, exception14);
                     }
                  }

                  this.method19();
               }
            }
         } catch (Exception | Error exception16) {
            if (!LunarBuildData.field4) {
               LunarLogger.method7("EventBus [" + clazz1 + "][" + clazz2 + "]", new Object[0]);
               throw exception16;
            }

            if (exception16 instanceof AbstractMethodError || exception16 instanceof IllegalAccessError) {
               throw exception16;
            }

            LunarLogger.method7("EventBus [" + clazz1 + "][" + clazz2 + "]: " + exception16.getClass() + ":" + exception16.getMessage(), new Object[0]);
            exception16.printStackTrace();
         }
      } else if (!this.field6.isEmpty()) {
         Pair pair7 = (Pair)function3.apply(LunarEventBus.EventPhase.BOTH);
         this.method8(clazz1, (LunarEvent)pair7.first(), null);
         runnable4.run();
         this.method8(clazz2, (LunarEvent)pair7.second(), null);
      } else {
         runnable4.run();
      }
   }

   protected void method18(Class<? extends LunarEvent> clazz1) {
      this.field7.incrementAndGet();
   }

   protected void method19() {
      if (this.field7.decrementAndGet() == 0) {
         this.method20();
      }
   }

   protected void method20() {
      if (!this.field6.isEmpty()) {
         for (LunarEventBus.EventBusOperation data22 : this.field6) {
            if (data22.field1) {
               this.method4(data22.field2, data22.field3, data22.field4);
            } else {
               this.method6(data22.field2, data22.field3);
            }
         }

         this.field6.clear();
      }
   }

   protected void method21(LunarEventBus.EventBusOperation<?> data21) {
      this.field6.add(data21);
   }

   private void method22(Class<? extends LunarEvent> clazz1, Throwable exception2) {
      LunarLogger.method7("EventBus [" + clazz1 + "]: " + exception2.getMessage(), new Object[0]);
      CrashReporter.method6(exception2, "EventBus", !LunarBuildData.field4, null);
   }

   private void method23(Throwable exception1, Class<? extends LunarEvent> clazz2) {
      try {
         if (!LunarBuildData.field4) {
            LunarLogger.method7("EventBus [" + clazz2 + "]", new Object[0]);
            throw exception1;
         }

         if (!(exception1 instanceof AbstractMethodError) && !(exception1 instanceof IllegalAccessError)) {
            LunarLogger.method7("EventBus [" + clazz2 + "]: " + exception1.getClass() + ":" + exception1.getMessage(), new Object[0]);
            exception1.printStackTrace();
         } else {
            throw exception1;
         }
      } catch (Throwable exception4) {
         throw exception4;
      }
   }

   public void method24(Runnable runnable1) {
      if (this.field5.isHeldByCurrentThread()) {
         runnable1.run();
      } else {
         this.field5.lock();

         try {
            runnable1.run();
         } finally {
            this.field5.unlock();
         }
      }
   }

   public <T> T method25(Supplier<T> supplier1) {
      if (this.field5.isHeldByCurrentThread()) {
         return (T)supplier1.get();
      }

      this.field5.lock();

      try {
         return (T)supplier1.get();
      } finally {
         this.field5.unlock();
      }
   }

   public boolean method26() {
      return this.field5.isHeldByCurrentThread();
   }

   private void method27(Class<? extends LunarEvent> clazz1) {
      if (field2) {
         if (!ThreadedEvent.class.isAssignableFrom(clazz1)) {
            if (!Bridge.method42().method2()) {
               LunarLogger.method5(
                  "Event [" + clazz1.getSimpleName() + "] was fired on the wrong thread! If this is intentional, make it implement ThreadedEvent", new Object[0]
               );
               Thread.dumpStack();
            }
         }
      }
   }

   public HashMap<Class<? extends LunarEvent>, EventListener<? extends LunarEvent>[]> method28() {
      return this.field8;
   }

   @Generated
   public LunarEventBus() {
   }

   @Generated
   public static LunarEventBus method29() {
      return field4;
   }

   @Generated
   public ReentrantLock method30() {
      return this.field5;
   }

   protected class EventBusOperation<T extends LunarEvent> {
      private final boolean field1;
      private final Class<T> field2;
      private final Consumer<T> field3;
      private final int field4;

      protected EventBusOperation(boolean flag1, Class<T> clazz2, Consumer<T> consumer3, int number4) {
         this.field1 = flag1;
         this.field2 = clazz2;
         this.field3 = consumer3;
         this.field4 = number4;
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

   public enum EventPhase {
      PRE(true, false),
      POST(false, true),
      BOTH(true, true);

      private final boolean pre;
      private final boolean post;

      public <PRE extends LunarEvent, POST extends LunarEvent> Pair<PRE, POST> wrapEvents(Supplier<PRE> supplier1, Supplier<POST> supplier2) {
         if (this == BOTH) {
            return Pair.of((LunarEvent)supplier1.get(), (LunarEvent)supplier2.get());
         } else {
            return this == PRE ? Pair.of((LunarEvent)supplier1.get(), null) : Pair.of(null, (LunarEvent)supplier2.get());
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
      EventPhase(boolean flag3, boolean flag4) {
         this.pre = flag3;
         this.post = flag4;
      }
   }
}
