# JVM调参
## 现象
进程设置-Xmx2g，但是实际运行一段时间之后，实际占用内存达到4G，甚至更高。

## 分析
jvm内存使用情况：
|Memory|used|total|max|usage|
|---|---|---|---|---|
|**heap**|**680M**|**2048M**|**2048M**|**33.23%**|
|g1_eden_space|14M|1191M|-1|1.18%|
|g1_survivor_space|33M|33M|-1|100.00%|
|g1_old_gen|633M|824M|2048M|30.93%|
|**nonheap**|**284M**|**295M**|**-1**|**96.19%**|
|code_cache|110M|112M|240M|46.06%|
|metaspace|155M|164M|-1|95.01%|
|compressed_class_space|17M|19M|1024M|1.73%|
|direct|18M|18M|-|100.00%|
|mapped|0K|0K|-|0.00%|

top情况：
|PID|USER|PR|NI|VIRT|RES|SHRS|%CPU|%MEM|TIME+|COMMAND|
|---|---|---|---|---|---|---|---|---|---|---|
|1|bonree|20|0|6190268|2.7g|37816S|58.5|17.3|1558:19|java ...|

增加参数：
```
-XX:NativeMemoryTracking=detail
```

命令分析：
```
jcmd <pid> VM.native_memory
```

查询结果：

```
Native Memory Tracking:

Total: reserved=3929MB, committed=2724MB
-                 Java Heap (reserved=2048MB, committed=2048MB)
                            (mmap: reserved=2048MB, committed=2048MB) 
 
-                     Class (reserved=1149MB, committed=141MB)
                            (classes #23813)
                            (malloc=3MB #38348) 
                            (mmap: reserved=1146MB, committed=138MB) 
 
-                    Thread (reserved=266MB, committed=266MB)
                            (thread #266)
                            (stack: reserved=265MB, committed=265MB)
                            (malloc=1MB #1332) 
 
-                      Code (reserved=255MB, committed=61MB)
                            (malloc=11MB #14692) 
                            (mmap: reserved=244MB, committed=50MB) 
 
-                        GC (reserved=127MB, committed=127MB)
                            (malloc=19MB #34518) 
                            (mmap: reserved=108MB, committed=108MB) 
 
-                  Compiler (reserved=1MB, committed=1MB)
                            (malloc=1MB #1654) 
 
-                  Internal (reserved=41MB, committed=41MB)
                            (malloc=41MB #90195) 
 
-                    Symbol (reserved=30MB, committed=30MB)
                            (malloc=28MB #292525) 
                            (arena=2MB #1)
 
-    Native Memory Tracking (reserved=8MB, committed=8MB)
                            (tracking overhead=7MB)
 
-               Arena Chunk (reserved=1MB, committed=1MB)
                            (malloc=1MB) 
 
-                   Unknown (reserved=4MB, committed=0MB)
                            (mmap: reserved=4MB, committed=0MB) 
```

可以看到java进程的整个memory主要包含了Java Heap、Class、Thread、Code、GC、Internal、Symbol、Native Memory Tracking、unknown这几部分；其中reserved表示应用可用的内存大小，committed表示应用正在使用的内存大小。

jmap分析结果
```
Heap Configuration:
   MinHeapFreeRatio         = 40
   MaxHeapFreeRatio         = 70
   MaxHeapSize              = 2147483648 (2048.0MB)
   NewSize                  = 1363144 (1.2999954223632812MB)
   MaxNewSize               = 1287651328 (1228.0MB)
   OldSize                  = 5452592 (5.1999969482421875MB)
   NewRatio                 = 2
   SurvivorRatio            = 8
   MetaspaceSize            = 21807104 (20.796875MB)
   CompressedClassSpaceSize = 1073741824 (1024.0MB)
   MaxMetaspaceSize         = 17592186044415 MB
   G1HeapRegionSize         = 1048576 (1.0MB)

Heap Usage:
G1 Heap:
   regions  = 2048
   capacity = 2147483648 (2048.0MB)
   used     = 1134648576 (1082.085205078125MB)
   free     = 1012835072 (965.914794921875MB)
   52.83619165420532% used
G1 Young Generation:
Eden Space:
   regions  = 809
   capacity = 1328545792 (1267.0MB)
   used     = 848297984 (809.0MB)
   free     = 480247808 (458.0MB)
   63.851617995264405% used
Survivor Space:
   regions  = 23
   capacity = 24117248 (23.0MB)
   used     = 24117248 (23.0MB)
   free     = 0 (0.0MB)
   100.0% used
G1 Old Generation:
   regions  = 258
   capacity = 794820608 (758.0MB)
   used     = 262233344 (250.085205078125MB)
   free     = 532587264 (507.914794921875MB)
   32.99277111848615% used

60781 interned Strings occupying 7019976 bytes.

```

## 结论
java进程占用的内存构成：

```
Max Memory = eden + survivor + old + String Constant Pool + Code cache + compressed class space + Metaspace + Thread stack(*thread num) + Direct + Mapped + JVM + Native Memory
```

调参说明：

```
# 元空间限制
-XX:MetaspaceSize=64m
-XX:MaxMetaspaceSize=128m
# 直接内存使用限制：
-XX:MaxDirectMemorySize=128m
```

## 参考

JVM源码分析之Metaspace解密 http://lovestblog.cn/blog/2016/10/29/metaspace/
为什么设置-Xmx4g但是java进程内存占用达到8g？ https://developer.aliyun.com/article/1053272
Java内存分区之什么是CCS区 https://blog.csdn.net/qq_27093465/article/details/106760961
深入理解堆外内存 Metaspace https://www.javadoop.com/post/metaspace