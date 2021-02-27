package com.ozr.NIO_2;

/**
 * @Author OZR
 * @Date 2021/1/23 17:00
 *
 *
 *Java NIO (New IO Non Blocking IO) 是 从 Java 1.4 版本开始引入的 一 套 新
 * 的 IO API ，可以替代标准的 Java IO API 。 NIO 与原来的 IO 有同样的作用和目
 * 的，但是使用的方式完全不同， NIO 支持面向缓冲区的 (IO 是面向流的 、基于
 * 通道的 IO 操作。 NIO 将以更加高效的方式进行文件的读写操作。
 *  Java API 中提供了两套 NIO 一套是针对标准输入输出 NIO 另一套就是网
 * 络编程 NIO 。
 * java.nio.channels.Channel
 *             FileChannel: 处理本地文件
 *             SocketChannel TCP 网络编程的客户端的 Channel
 *             ServerSocketChannel:TCP 网络编程的服务器端的 Channel
 *              DatagramChannel UDP 网络编程中发送端和接收端的 Channel
 *
 *
 *
 *
 * 随着 JDK 7 的发布， Java 对 NIO 进行了极大的扩展，增强了对
 * 文件处理和文件系统特性的支持，以至于我们称他们为 NIO.2 。
 * 因为 NIO 提供的一些功能， NIO 已经成为文件处理中越来越重要
 * 的部分。
 *
 *
 *
 *Path 、 Paths 和 Files 核心 API
 *早期 的 J ava 只提供了一个 File 类来访问文件系统，但 File 类的功能比较有限，所
 * 提供的方法性能也不高。而且， 大多数方法在出错时仅返回失败，并不会提供异
 * 常信息。
 * NIO. 2 为了弥补这种不足，引入了 Path 接口，代表一个平台无关的平台路径，描
 * 述了目录结构中文件的位置。 Path 可以看成是 File 类的升级版本，实际引用的资
 * 源也可以不存在。
 *
 *在以前 IO 操作都是这样写的
 * import java.io.File;
 * File file = new File("index.html");
 * 但在 Java7 中，我们可以这样写：
 * import java.nio.file.Path;
 * import java.nio.file.Paths;
 * Path path = Paths.get("index.html");
 */
public class NIO_2Test {

}
