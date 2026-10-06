package com.moonsworth.lunar.client.framework.crash;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.Tlhelp32.MODULEENTRY32W;
import com.sun.jna.platform.win32.Tlhelp32.THREADENTRY32;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType2;

@com.moonsworth.lunar.ichor.util.Annotation4
public final class ThreadModuleDump {
   private static final int TH32CS_SNAPTHREAD = 4;
   private static final int TH32CS_SNAPMODULE = 8;
   private static final int TH32CS_SNAPMODULE32 = 16;
   private static final int THREAD_QUERY_INFORMATION = 64;
   private static final int THREAD_QUERY_LIMITED_INFORMATION = 2048;
   private static final int THREAD_SUSPEND_RESUME = 2;
   private static final int THREAD_GET_CONTEXT = 8;
   private static final int THREAD_FULL_QUERY_SUSPEND_CTX = 74;
   private static final int ThreadQuerySetWin32StartAddress = 9;
   private static final int GET_MODULE_HANDLE_EX_FLAG_FROM_ADDRESS = 4;
   private static final int GET_MODULE_HANDLE_EX_FLAG_UNCHANGED_REFCOUNT = 2;
   private static final int SYMBOL_INFO_SIZE = 88;
   private static final int SYMBOL_INFO_OFF_SIZE_OF_STRUCT = 0;
   private static final int SYMBOL_INFO_OFF_MAX_NAME_LEN = 76;
   private static final int SYMBOL_INFO_OFF_NAME = 80;
   private static final int MAX_SYM_NAME = 1024;
   private static final int SYMOPT_UNDNAME = 2;
   private static final int SYMOPT_DEFERRED_LOADS = 4;
   private static final int SYMOPT_NO_PROMPTS = 524288;
   private static final int CONTEXT_AMD64 = 1048576;
   private static final int CONTEXT_CONTROL = 1048577;
   private static final int CONTEXT_SIZE_X64 = 1232;
   private static final int CONTEXT_OFF_CONTEXT_FLAGS = 48;
   private static final int CONTEXT_OFF_RIP = 248;

   private ThreadModuleDump() {
   }

   public static String dump() {
      if (!ThreadModuleDumpType2.isWindows()) {
         throw new UnsupportedOperationException("Thread/module dump is only supported on Windows");
      }

      StringWriter var0 = new StringWriter();

      try (PrintWriter var1 = new PrintWriter(var0)) {
         int var2 = Kernel32.INSTANCE.GetCurrentProcessId();
         var1.println("Thread/module dump for PID " + var2);
         var1.println("OS:        " + System.getProperty("os.name") + " " + System.getProperty("os.version"));
         var1.println("Java:      " + System.getProperty("java.version") + " (" + System.getProperty("java.vendor") + ")");
         var1.println("Arch:      " + System.getProperty("os.arch") + "  pointer=" + Native.POINTER_SIZE + " bytes");
         var1.println("Generated: " + new Date());
         var1.println();
         Map var3 = enumerateModules(var2);
         String var4 = trySymInitialize();
         boolean var5 = var4 == null;
         ThreadModuleDump.Data var6 = captureJavaToOsThreadIds();
         var1.println("Symbol resolution:  " + (var5 ? "enabled (dbghelp)" : "unavailable (" + var4 + ")"));
         var1.println("Current PC capture: " + (Native.POINTER_SIZE == 8 ? "enabled (x64 SuspendThread/GetThreadContext)" : "disabled (non-x64)"));
         var1.println(
            "Java↔OS TID map:    "
               + (var6.field4 != null ? "unavailable (" + var6.field4 + ")" : "enabled (jdk.jfr, " + var6.field1.size() + " threads mapped)")
         );
         var1.println();

         try {
            var1.println("=== Threads ===");
            var1.println();
            writeThreads(var1, var2, var3, var5, var6);
         } finally {
            if (var5) {
               try {
                  ThreadModuleDump.DbgHelp.INSTANCE.SymCleanup(Kernel32.INSTANCE.GetCurrentProcess());
               } catch (Throwable var16) {
               }
            }
         }

         var1.println();
         var1.println("=== Loaded modules (" + var3.size() + ") ===");
         writeModules(var1, var3);
         var1.println();
         var1.println("=== Java threads (from JVM) ===");
         var1.println();
         writeJavaThreads(var1, var6);
      }

      return var0.toString();
   }

   private static ThreadModuleDump.Data captureJavaToOsThreadIds() {
      Object var0 = null;
      Path var1 = null;

      try {
         Class var2 = Class.forName("jdk.jfr.Recording");
         Class var3 = Class.forName("jdk.jfr.EventSettings");
         Class var4 = Class.forName("jdk.jfr.consumer.RecordingFile");
         var0 = var2.getDeclaredConstructor().newInstance();
         String[] var5 = new String[]{"jdk.ThreadCPULoad", "jdk.ThreadAllocationStatistics", "jdk.ExecutionSample", "jdk.NativeMethodSample"};

         for (String var9 : var5) {
            try {
               Object var10 = var2.getMethod("enable", String.class).invoke(var0, var9);
               var3.getMethod("withPeriod", Duration.class).invoke(var10, Duration.ofMillis(1L));
            } catch (Throwable var54) {
            }
         }

         String[] var59 = new String[]{
            "jdk.SocketRead",
            "jdk.SocketWrite",
            "jdk.FileRead",
            "jdk.FileWrite",
            "jdk.JavaMonitorEnter",
            "jdk.JavaMonitorWait",
            "jdk.ThreadPark",
            "jdk.ThreadSleep"
         };

         for (String var69 : var59) {
            try {
               Object var11 = var2.getMethod("enable", String.class).invoke(var0, var69);
               var3.getMethod("withThreshold", Duration.class).invoke(var11, Duration.ZERO);
            } catch (Throwable var53) {
            }
         }

         for (String var70 : new String[]{"jdk.ThreadStart", "jdk.ThreadEnd"}) {
            try {
               var2.getMethod("enable", String.class).invoke(var0, var70);
            } catch (Throwable var52) {
            }
         }

         var2.getMethod("start").invoke(var0);
         Thread.sleep(500L);
         var1 = Files.createTempFile("thread-id-snap", ".jfr");
         var2.getMethod("dump", Path.class).invoke(var0, var1);
         var2.getMethod("stop").invoke(var0);
         HashMap var62 = new HashMap();
         HashMap var65 = new HashMap();
         HashMap var68 = new HashMap();
         Object var71 = var4.getDeclaredConstructor(Path.class).newInstance(var1);

         try {
            Method var72 = var4.getMethod("hasMoreEvents");
            Method var12 = var4.getMethod("readEvent");

            while (var72.invoke(var71)) {
               Object var13 = var12.invoke(var71);
               if (var13 != null) {
                  Object var14 = var13.getClass().getMethod("getThread").invoke(var13);
                  if (var14 != null) {
                     long var15 = (Long)var14.getClass().getMethod("getJavaThreadId").invoke(var14);
                     long var17 = (Long)var14.getClass().getMethod("getOSThreadId").invoke(var14);
                     if (var15 > 0L && var17 > 0L) {
                        var62.put(var15, var17);
                        var65.put(var17, var15);

                        try {
                           String var19 = (String)var14.getClass().getMethod("getJavaName").invoke(var14);
                           if (var19 != null && !var19.isEmpty()) {
                              var68.put(var17, var19);
                           }
                        } catch (Throwable var51) {
                        }
                     }
                  }
               }
            }
         } finally {
            ((AutoCloseable)var71).close();
         }

         return new ThreadModuleDump.Data(var62, var65, var68, null);
      } catch (ClassNotFoundException var56) {
         return ThreadModuleDump.Data.method1("jdk.jfr not available on this JRE");
      } catch (Throwable var57) {
         return ThreadModuleDump.Data.method1(var57.getClass().getSimpleName() + ": " + var57.getMessage());
      } finally {
         if (var0 != null) {
            try {
               ((AutoCloseable)var0).close();
            } catch (Throwable var50) {
            }
         }

         if (var1 != null) {
            try {
               Files.deleteIfExists(var1);
            } catch (Throwable var49) {
            }
         }
      }
   }

   private static String trySymInitialize() {
      try {
         ThreadModuleDump.DbgHelp.INSTANCE.SymSetOptions(524294);
         boolean var0 = ThreadModuleDump.DbgHelp.INSTANCE.SymInitializeW(Kernel32.INSTANCE.GetCurrentProcess(), null, true);
         if (var0) {
            return null;
         }

         int var1 = Native.getLastError();
         var0 = ThreadModuleDump.DbgHelp.INSTANCE.SymInitializeW(Kernel32.INSTANCE.GetCurrentProcess(), null, false);
         return var0 ? null : String.format("SymInitializeW failed, GetLastError=%d", var1);
      } catch (Throwable var2) {
         return var2.getClass().getSimpleName() + ": " + var2.getMessage();
      }
   }

   private static long tryGetCurrentRip(HANDLE var0, int var1) {
      if (Native.POINTER_SIZE != 8) {
         return 0L;
      }

      if (ThreadModuleDump.Kernel32Extra.INSTANCE.GetCurrentThreadId() == var1) {
         return 0L;
      }

      Memory var2 = null;

      try {
         var2 = new Memory(1248L);
         var2.clear();
         long var3 = Pointer.nativeValue(var2);
         long var5 = (var3 + 15L & -16L) - var3;
         Pointer var7 = var2.share(var5);
         var7.setInt(48L, 1048577);
         int var8 = ThreadModuleDump.Kernel32Extra.INSTANCE.SuspendThread(var0);
         if (var8 == -1) {
            return 0L;
         }

         try {
            return !ThreadModuleDump.Kernel32Extra.INSTANCE.GetThreadContext(var0, var7) ? 0L : var7.getLong(248L);
         } finally {
            ThreadModuleDump.Kernel32Extra.INSTANCE.ResumeThread(var0);
         }
      } catch (Throwable var20) {
         return 0L;
      } finally {
         if (var2 != null) {
            var2.clear();
         }
      }
   }

   private static ThreadModuleDump.ResolvedAddress resolveAddress(long var0, Map<Long, ThreadModuleDump.LoadedModule> var2, boolean var3) {
      if (var0 == 0L) {
         return new ThreadModuleDump.ResolvedAddress(0L, null, null, 0L, null);
      }

      ThreadModuleDump.LoadedModule var4 = findOwningModule(var0, var2);
      String var5;
      String var6;
      long var7;
      if (var4 != null) {
         var5 = var4.field1;
         var6 = var4.field2;
         var7 = var4.base;
      } else {
         long[] var9 = new long[]{0L};
         String var10 = resolveModuleHandleWithBase(var0, var9);
         if (var10 != null) {
            int var11 = Math.max(var10.lastIndexOf(92), var10.lastIndexOf(47));
            var5 = var11 >= 0 ? var10.substring(var11 + 1) : var10;
            var6 = var10;
            var7 = var9[0];
         } else {
            var5 = null;
            var6 = null;
            var7 = 0L;
         }
      }

      String var12 = var3 ? resolveSymbol(var0) : null;
      return new ThreadModuleDump.ResolvedAddress(var0, var5, var6, var7, var12);
   }

   private static String resolveSymbol(long var0) {
      if (var0 == 0L) {
         return null;
      }

      Memory var2 = null;

      try {
         var2 = new Memory(1112L);
         var2.clear();
         var2.setInt(0L, 88);
         var2.setInt(76L, 1024);
         LongByReference var3 = new LongByReference();
         boolean var4 = ThreadModuleDump.DbgHelp.INSTANCE.SymFromAddr(Kernel32.INSTANCE.GetCurrentProcess(), var0, var3, var2);
         if (!var4) {
            return null;
         } else {
            String var5 = var2.getString(80L);
            if (var5 != null && !var5.isEmpty()) {
               long var6 = var3.getValue();
               return var6 == 0L ? var5 : var5 + "+0x" + Long.toHexString(var6);
            } else {
               return null;
            }
         }
      } catch (Throwable var12) {
         return null;
      } finally {
         if (var2 != null) {
            var2.clear();
         }
      }
   }

   private static void writeJavaThreads(PrintWriter var0, ThreadModuleDump.Data var1) {
      Map var2 = Thread.getAllStackTraces();
      var0.println("Total live Java threads: " + var2.size());
      var0.println();
      TreeMap var3 = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

      for (Thread var5 : var2.keySet()) {
         var3.put(var5.getName() + "\u0000" + var5.getId(), var5);
      }

      for (Thread var9 : var3.values()) {
         ThreadGroup var6 = var9.getThreadGroup();
         Long var7 = var1.field1.get(var9.getId());
         var0.printf(
            "  \"%-48s\"  java-id=%-5d  os-tid=%-7s  state=%-13s  daemon=%-5s  group=%s%n",
            truncate(var9.getName(), 48),
            var9.getId(),
            var7 != null ? var7.toString() : "<none>",
            var9.getState(),
            var9.isDaemon(),
            var6 != null ? var6.getName() : "<null>"
         );
      }
   }

   private static void writeThreads(PrintWriter var0, int var1, Map<Long, ThreadModuleDump.LoadedModule> var2, boolean var3, ThreadModuleDump.Data var4) {
      HANDLE var5 = Kernel32.INSTANCE.CreateToolhelp32Snapshot(new DWORD(4L), new DWORD(0L));
      if (var5 != null && !WinBase.INVALID_HANDLE_VALUE.equals(var5)) {
         try {
            THREADENTRY32 var6 = new THREADENTRY32();
            var6.dwSize = var6.size();
            if (Kernel32.INSTANCE.Thread32First(var5, var6)) {
               int var7 = 0;
               HashMap var8 = new HashMap();

               do {
                  if (var6.th32OwnerProcessID == var1) {
                     var7++;
                     int var9 = var6.th32ThreadID;
                     HANDLE var10 = Kernel32.INSTANCE.OpenThread(74, false, var9);
                     String var11 = "full+ctx";
                     if (var10 == null) {
                        var10 = Kernel32.INSTANCE.OpenThread(64, false, var9);
                        var11 = "query-only";
                     }

                     if (var10 == null) {
                        var10 = Kernel32.INSTANCE.OpenThread(2048, false, var9);
                        var11 = "limited";
                     }

                     if (var10 == null) {
                        var0.printf("TID %-6d  <OpenThread failed, err=%d>%n", var9, Native.getLastError());
                     } else {
                        try {
                           String var12 = tryGetThreadDescription(var10);
                           String var13 = var4.field3.get((long)var9);
                           Long var14 = var4.field2.get((long)var9);
                           ThreadModuleDump.ThreadStartAddress var15 = tryGetWin32StartAddress(var10);
                           ThreadModuleDump.ResolvedAddress var16 = resolveAddress(var15.field1, var2, var3);
                           long var17 = tryGetCurrentRip(var10, var9);
                           ThreadModuleDump.ResolvedAddress var19 = resolveAddress(var17, var2, var3);
                           String var20 = var19.field2 != null ? var19.field2 : (var16.field2 != null ? var16.field2 : "<unknown>");
                           var8.merge(var20, 1, Integer::sum);
                           String var21;
                           if (var12 != null && !var12.isEmpty()) {
                              var21 = var12;
                              if (var13 != null && !var13.equals(var12)) {
                                 var21 = var21 + "  [java-name=\"" + var13 + "\"]";
                              }
                           } else if (var13 != null) {
                              var21 = "<unnamed>  [java-name=\"" + var13 + "\"]";
                           } else {
                              var21 = "<unnamed>";
                           }

                           if (var14 != null) {
                              var21 = var21 + "  [java-id=" + var14 + "]";
                           }

                           var0.printf("TID %-6d  open=%-9s  name=%s%n", var9, var11, var21);
                           var0.printf("  start:   %s%n", formatAddress(var16, var15.field2, "NtQueryInformationThread"));
                           String var22;
                           if (var17 != 0L) {
                              var22 = formatAddress(var19, null, null);
                           } else if (ThreadModuleDump.Kernel32Extra.INSTANCE.GetCurrentThreadId() == var9) {
                              var22 = "<not sampled: this is the dump thread itself>";
                           } else if (!"full+ctx".equals(var11)) {
                              var22 = "<not sampled: handle lacks SUSPEND_RESUME|GET_CONTEXT (open mode '" + var11 + "')>";
                           } else {
                              var22 = "<not sampled: SuspendThread or GetThreadContext failed, err=" + Native.getLastError() + ">";
                           }

                           var0.printf("  current: %s%n", var22);
                        } finally {
                           Kernel32.INSTANCE.CloseHandle(var10);
                        }
                     }
                  }
               } while (Kernel32.INSTANCE.Thread32Next(var5, var6));

               var0.println();
               var0.println("Total threads in this process: " + var7);
               var0.println();
               var0.println("Thread count grouped by current-RIP module (real owner):");
               var8.entrySet()
                  .stream()
                  .sorted((var0x, var1x) -> Integer.compare((Integer)var1x.getValue(), (Integer)var0x.getValue()))
                  .forEach(var1x -> var0.printf("  %5d  %s%n", var1x.getValue(), var1x.getKey()));
               return;
            }

            var0.println("Thread32First failed, err=" + Native.getLastError());
         } finally {
            Kernel32.INSTANCE.CloseHandle(var5);
         }
      } else {
         var0.println("CreateToolhelp32Snapshot(thread) failed, err=" + Native.getLastError());
      }
   }

   private static String formatAddress(ThreadModuleDump.ResolvedAddress var0, String var1, String var2) {
      if (var0.field1 == 0L) {
         return var1 != null ? String.format("<%s failed: %s>", var2, var1) : "<no address>";
      }

      String var3;
      if (var0.field5 != null && var0.field2 != null) {
         var3 = var0.field2 + "!" + var0.field5;
      } else if (var0.field5 != null) {
         var3 = var0.field5;
      } else if (var0.field2 != null && var0.field4 != 0L) {
         long var4 = var0.field1 - var0.field4;
         var3 = String.format("%s+0x%x", var0.field2, var4);
      } else if (var0.field2 != null) {
         var3 = var0.field2 + "!<no symbol>";
      } else {
         var3 = "<address not in any loaded module>";
      }

      return String.format("0x%016x  %s", var0.field1, var3);
   }

   private static String tryGetThreadDescription(HANDLE var0) {
      try {
         PointerByReference var1 = new PointerByReference();
         int var2 = ThreadModuleDump.Kernel32Extra.INSTANCE.GetThreadDescription(var0, var1);
         if (var2 >= 0 && var1.getValue() != null) {
            String var3 = var1.getValue().getWideString(0L);
            Kernel32.INSTANCE.LocalFree(var1.getValue());
            return var3;
         }
      } catch (Throwable var4) {
      }

      return null;
   }

   private static ThreadModuleDump.ThreadStartAddress tryGetWin32StartAddress(HANDLE var0) {
      Memory var1 = null;

      try {
         var1 = new Memory(Native.POINTER_SIZE);
         var1.clear();
         IntByReference var2 = new IntByReference();
         int var3 = ThreadModuleDump.NtDll.INSTANCE.NtQueryInformationThread(var0, 9, var1, Native.POINTER_SIZE, var2);
         if (var3 != 0) {
            return new ThreadModuleDump.ThreadStartAddress(0L, String.format("NTSTATUS=0x%08x", var3));
         }

         long var4 = Native.POINTER_SIZE == 8 ? var1.getLong(0L) : var1.getInt(0L) & 4294967295L;
         return new ThreadModuleDump.ThreadStartAddress(var4, null);
      } catch (Throwable var6) {
         return new ThreadModuleDump.ThreadStartAddress(0L, var6.getClass().getSimpleName() + ": " + var6.getMessage());
      }
   }

   private static ThreadModuleDump.LoadedModule findOwningModule(long var0, Map<Long, ThreadModuleDump.LoadedModule> var2) {
      for (ThreadModuleDump.LoadedModule var4 : var2.values()) {
         if (Long.compareUnsigned(var0, var4.base) >= 0 && Long.compareUnsigned(var0, var4.base + var4.field3) < 0) {
            return var4;
         }
      }

      return null;
   }

   private static String resolveModuleHandleWithBase(long var0, long[] var2) {
      if (var0 == 0L) {
         return null;
      }

      try {
         PointerByReference var3 = new PointerByReference();
         boolean var4 = ThreadModuleDump.Kernel32Extra.INSTANCE.GetModuleHandleExW(6, new Pointer(var0), var3);
         if (var4 && var3.getValue() != null) {
            var2[0] = Pointer.nativeValue(var3.getValue());
            char[] var5 = new char[1024];
            int var6 = ThreadModuleDump.Kernel32Extra.INSTANCE.GetModuleFileNameW(var3.getValue(), var5, var5.length);
            return var6 > 0 ? new String(var5, 0, var6) : null;
         } else {
            return null;
         }
      } catch (Throwable var7) {
         return null;
      }
   }

   private static Map<Long, ThreadModuleDump.LoadedModule> enumerateModules(int var0) {
      HashMap var1 = new HashMap();
      HANDLE var2 = Kernel32.INSTANCE.CreateToolhelp32Snapshot(new DWORD(24L), new DWORD(var0));
      if (var2 != null && !WinBase.INVALID_HANDLE_VALUE.equals(var2)) {
         try {
            MODULEENTRY32W var3 = new MODULEENTRY32W();
            if (!Kernel32.INSTANCE.Module32FirstW(var2, var3)) {
               return var1;
            }

            do {
               long var4 = Pointer.nativeValue(var3.modBaseAddr);
               long var6 = var3.modBaseSize.longValue() & 4294967295L;
               String var8 = var3.szModule();
               String var9 = var3.szExePath();
               var1.put(var4, new ThreadModuleDump.LoadedModule(var8, var9, var4, var6));
            } while (Kernel32.INSTANCE.Module32NextW(var2, var3));
         } finally {
            Kernel32.INSTANCE.CloseHandle(var2);
         }

         return var1;
      } else {
         return var1;
      }
   }

   private static void writeModules(PrintWriter var0, Map<Long, ThreadModuleDump.LoadedModule> var1) {
      var1.values()
         .stream()
         .sorted((var0x, var1x) -> Long.compareUnsigned(var0x.base, var1x.base))
         .forEach(var1x -> var0.printf("  0x%016x  size=0x%08x  %-32s  %s%n", var1x.base, var1x.field3, truncate(var1x.field1, 32), var1x.field2));
      var0.println();
      var0.println("Likely non-system modules (outside windows dir):");
      String var2 = System.getenv("WINDIR");
      String var3 = (var2 == null ? "c:\\windows" : var2).toLowerCase(Locale.ROOT);
      var1.values()
         .stream()
         .filter(var1x -> !var1x.field2.toLowerCase(Locale.ROOT).startsWith(var3))
         .sorted((var0x, var1x) -> var0x.field2.compareToIgnoreCase(var1x.field2))
         .forEach(var1x -> var0.printf("  %s%n", var1x.field2));
   }

   private static String truncate(String var0, int var1) {
      if (var0 == null) {
         return "";
      } else {
         return var0.length() <= var1 ? var0 : var0.substring(0, var1 - 1) + "…";
      }
   }

   private static final class Data {
      final Map<Long, Long> field1;
      final Map<Long, Long> field2;
      final Map<Long, String> field3;
      final String field4;

      Data(Map<Long, Long> var1, Map<Long, Long> var2, Map<Long, String> var3, String var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }

      static ThreadModuleDump.Data method1(String var0) {
         return new ThreadModuleDump.Data(Collections.emptyMap(), Collections.emptyMap(), Collections.emptyMap(), var0);
      }
   }

   private static final class ResolvedAddress {
      final long field1;
      final String field2;
      final String field3;
      final long field4;
      final String field5;

      ResolvedAddress(long var1, String var3, String var4, long var5, String var7) {
         this.field1 = var1;
         this.field2 = var3;
         this.field3 = var4;
         this.field4 = var5;
         this.field5 = var7;
      }
   }

   private static final class LoadedModule {
      final String field1;
      final String field2;
      final long base;
      final long field3;

      LoadedModule(String var1, String var2, long var3, long var5) {
         this.field1 = var1;
         this.field2 = var2;
         this.base = var3;
         this.field3 = var5;
      }
   }

   private static final class ThreadStartAddress {
      final long field1;
      final String field2;

      ThreadStartAddress(long var1, String var3) {
         this.field1 = var1;
         this.field2 = var3;
      }
   }

   @com.moonsworth.lunar.ichor.util.Annotation4
   public interface DbgHelp extends StdCallLibrary {
      ThreadModuleDump.DbgHelp INSTANCE = (ThreadModuleDump.DbgHelp)Native.load("dbghelp", ThreadModuleDump.DbgHelp.class, W32APIOptions.DEFAULT_OPTIONS);

      boolean SymInitializeW(HANDLE var1, String var2, boolean var3);

      boolean SymCleanup(HANDLE var1);

      boolean SymFromAddr(HANDLE var1, long var2, LongByReference var4, Pointer var5);

      int SymSetOptions(int var1);
   }

   @com.moonsworth.lunar.ichor.util.Annotation4
   public interface Kernel32Extra extends StdCallLibrary {
      ThreadModuleDump.Kernel32Extra INSTANCE = (ThreadModuleDump.Kernel32Extra)Native.load(
         "kernel32", ThreadModuleDump.Kernel32Extra.class, W32APIOptions.DEFAULT_OPTIONS
      );

      boolean GetModuleHandleExW(int var1, Pointer var2, PointerByReference var3);

      int GetModuleFileNameW(Pointer var1, char[] var2, int var3);

      int GetThreadDescription(HANDLE var1, PointerByReference var2);

      int SuspendThread(HANDLE var1);

      int ResumeThread(HANDLE var1);

      boolean GetThreadContext(HANDLE var1, Pointer var2);

      int GetCurrentThreadId();
   }

   @com.moonsworth.lunar.ichor.util.Annotation4
   public interface NtDll extends StdCallLibrary {
      ThreadModuleDump.NtDll INSTANCE = (ThreadModuleDump.NtDll)Native.load("ntdll", ThreadModuleDump.NtDll.class, W32APIOptions.DEFAULT_OPTIONS);

      int NtQueryInformationThread(HANDLE var1, int var2, Pointer var3, int var4, IntByReference var5);
   }
}
