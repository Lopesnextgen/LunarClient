package com.moonsworth.lunar.ichor;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.spongepowered.asm.logging.Level;
import org.spongepowered.asm.logging.LoggerAdapterAbstract;
import org.spongepowered.asm.logging.LoggerAdapterAbstract.FormattedMessage;

public class LoggerAdapterAbstractIterator extends LoggerAdapterAbstract {
   private static final SimpleDateFormat field1 = new SimpleDateFormat("HH:mm:ss");
   private PrintStream debug;
   private Ichor4 field2;
   public static final String field3 = "@Mixin target %s is public in %s and should be specified in value";
   public static final String field4 = "If this is a development environment you can ignore this message";
   public static final List<String> field5 = List.of(
      "@Mixin target %s is public in %s and should be specified in value", "If this is a development environment you can ignore this message"
   );

   public LoggerAdapterAbstractIterator(String var1, Ichor4 var2) {
      super(var1 == null ? "" : var1);
      this.field2 = var2;
   }

   public String getType() {
      return "Ichor Console Logger";
   }

   public LoggerAdapterAbstractIterator method1(PrintStream var1) {
      this.debug = var1;
      return this;
   }

   public void catching(Level var1, Throwable var2) {
      if (var2.getMessage() == null || !var2.getMessage().contains("IchorClassLoader(MIXIN) couldn't find class bytes for")) {
         this.log(Level.WARN, "Catching {}: {}", var2.getClass().getName(), var2.getMessage(), var2);
      }
   }

   public void log(Level var1, String var2, Object... var3) {
      if (!var2.contains("Error loading class: ") && (!var2.contains("@Mixin target") || !var2.contains("was not found"))) {
         PrintStream var4 = this.getOutputStream(var1);
         if (var4 != null) {
            FormattedMessage var5 = new FormattedMessage(var2, var3);
            if (this.method2(String.format("[%s] [%s(%s)/%s] %s%n", field1.format(new Date()), this.getId(), this.field2.toString(), var1, var5), var4)) {
               return;
            }

            if (var5.hasThrowable()) {
               Throwable var6 = var5.getThrowable();
               if (var6.getMessage() != null && !var6.getMessage().contains("IchorClassLoader(MIXIN) couldn't find class bytes for")) {
                  var6.printStackTrace(var4);
               }
            }

            var4.flush();
         }
      }
   }

   public void log(Level var1, String var2, Throwable var3) {
      PrintStream var4 = this.getOutputStream(var1);
      if (var4 != null) {
         if (this.method2(String.format("[%s] [%s(%s)/%s] %s%n", field1.format(new Date()), this.getId(), this.field2.toString(), var1, var2), var4)) {
            return;
         }

         var3.printStackTrace(var4);
         var4.flush();
      }
   }

   private boolean method2(String var1, PrintStream var2) {
      for (String var4 : field5) {
         if (var1.contains(var4)) {
            return true;
         }
      }

      var2.print(var1);
      return false;
   }

   public <T extends Throwable> T throwing(T var1) {
      this.log(Level.WARN, "Throwing {}: {}", var1.getClass().getName(), var1.getMessage(), var1);
      return (T)var1;
   }

   private PrintStream getOutputStream(Level var1) {
      return var1 != Level.TRACE && var1 != Level.DEBUG ? System.out : null;
   }
}
