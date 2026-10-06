package com.moonsworth.lunar.ichor;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.spongepowered.asm.logging.Level;
import org.spongepowered.asm.logging.LoggerAdapterAbstract;
import org.spongepowered.asm.logging.LoggerAdapterAbstract.FormattedMessage;

public class IchorConsoleLogger extends LoggerAdapterAbstract {
   private static final SimpleDateFormat field1 = new SimpleDateFormat("HH:mm:ss");
   private PrintStream debug;
   private IchorStage field2;
   public static final String field3 = "@Mixin target %s is public in %s and should be specified in value";
   public static final String field4 = "If this is a development environment you can ignore this message";
   public static final List<String> field5 = List.of(
      "@Mixin target %s is public in %s and should be specified in value", "If this is a development environment you can ignore this message"
   );

   public IchorConsoleLogger(String text1, IchorStage ichor42) {
      super(text1 == null ? "" : text1);
      this.field2 = ichor42;
   }

   public String getType() {
      return "Ichor Console Logger";
   }

   public IchorConsoleLogger method1(PrintStream stream1) {
      this.debug = stream1;
      return this;
   }

   public void catching(Level level1, Throwable exception2) {
      if (exception2.getMessage() == null || !exception2.getMessage().contains("IchorClassLoader(MIXIN) couldn't find class bytes for")) {
         this.log(Level.WARN, "Catching {}: {}", exception2.getClass().getName(), exception2.getMessage(), exception2);
      }
   }

   public void log(Level level1, String text2, Object... items3) {
      if (!text2.contains("Error loading class: ") && (!text2.contains("@Mixin target") || !text2.contains("was not found"))) {
         PrintStream stream4 = this.getOutputStream(level1);
         if (stream4 != null) {
            FormattedMessage formattedmessage5 = new FormattedMessage(text2, items3);
            if (this.method2(String.format("[%s] [%s(%s)/%s] %s%n", field1.format(new Date()), this.getId(), this.field2.toString(), level1, formattedmessage5), stream4)) {
               return;
            }

            if (formattedmessage5.hasThrowable()) {
               Throwable exception6 = formattedmessage5.getThrowable();
               if (exception6.getMessage() != null && !exception6.getMessage().contains("IchorClassLoader(MIXIN) couldn't find class bytes for")) {
                  exception6.printStackTrace(stream4);
               }
            }

            stream4.flush();
         }
      }
   }

   public void log(Level level1, String text2, Throwable exception3) {
      PrintStream stream4 = this.getOutputStream(level1);
      if (stream4 != null) {
         if (this.method2(String.format("[%s] [%s(%s)/%s] %s%n", field1.format(new Date()), this.getId(), this.field2.toString(), level1, text2), stream4)) {
            return;
         }

         exception3.printStackTrace(stream4);
         stream4.flush();
      }
   }

   private boolean method2(String text1, PrintStream stream2) {
      for (String text4 : field5) {
         if (text1.contains(text4)) {
            return true;
         }
      }

      stream2.print(text1);
      return false;
   }

   public <T extends Throwable> T throwing(T t) {
      this.log(Level.WARN, "Throwing {}: {}", t.getClass().getName(), t.getMessage(), t);
      return (T)t;
   }

   private PrintStream getOutputStream(Level level1) {
      return level1 != Level.TRACE && level1 != Level.DEBUG ? System.out : null;
   }
}
