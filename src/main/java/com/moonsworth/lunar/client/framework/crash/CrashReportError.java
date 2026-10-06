package com.moonsworth.lunar.client.framework.crash;

public final class CrashReportError extends Throwable {
   private final String field1;

   public CrashReportError(Throwable exception1, String text, Throwable exception3) {
      super(text == null ? exception1.getClass().getName() : exception1.getClass().getName() + ": " + text, exception3);
      this.field1 = exception1.getClass().getName();
      this.setStackTrace(exception1.getStackTrace());
   }

   @Override
   public String toString() {
      String text1 = this.getLocalizedMessage();
      return text1 != null ? text1 : this.field1;
   }
}
