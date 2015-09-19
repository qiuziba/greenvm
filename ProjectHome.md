GreenJVM是一系列jre精简实现集合，它能够轻松的令java虚拟机实现绿色应用。

GreenJVM的实现原理非常简单，主要操作过程可分为三部分：

1、去除虚拟机中不被程序调用的类。
2、压缩虚拟机体积，减少空间占用。
3、制作一个能够检测本地环境的启动壳，取代java.exe加载jvm，并在执行时释放虚拟机。

就技术角度而言，这三项可说毫无新意可言，但从实用角度来说，却能令臃肿的虚拟机“瘦”下来，免除普通用户安装的烦恼，从而实现java程序的绿色发布。

精简后的jre在桌面应用中一能维持在5MB-3MB，非桌面应用则可能降低到2MB以下。

2009年4月12日更新，GreenJVM-0.1.5版BUG修订

此问题由网友反馈，内容如下[下载的GreenJVM-0.1.5 无法运行程序，rt.pack200没有解压，手工解压后也不能运行]，经查原因在于GreenJVM对于pack200解压时路径空格未经处理，导致当用户将GreenJVM丢在桌面等含有空格的文件夹时将无法运行，现已修正。

在此特别鸣谢反映此问题的不知名网友。

2009年3月30日更新，GreenJVM-0.1.5，内容如下：

1、变更jvm.dll加载模式，支持中文路径。


2、调整默认加载参数，避免因环境差异而产生的本地图形设备调用异常。


3、支持jar的2次解压缩，当通过pack200将jar压缩为后缀名“pack200”的文件时，GreenJVM将自动尝试使用unpack200解压执行目录及子目录中所有该后缀文件。


4、增加参数“SET\_START\_INIT\_BAT”，通过此参数可设定一个批处理文件于GreenJVM启动同时执行。


5、更改0.1.4版[乱数球体]运行示例为[Java版AVG游戏开发入门示例0.1.1]（增加FPS显示），在无jre的xp、2000、2003等Windows环境下测试运行正常。


![http://p.blog.csdn.net/images/p_blog_csdn_net/cping1982/EntryImages/20090330/20090330_greenjvm_00.gif](http://p.blog.csdn.net/images/p_blog_csdn_net/cping1982/EntryImages/20090330/20090330_greenjvm_00.gif)

2008年9月6日更新GreenJVM-0.1.4(即0.1.1.4)，内容如下：

1、增加参数"SET\_START\_MAIN\_CLASS\_AUTO\_LOAD"，当此项为真时，程序将设置main函数于注册表，于开机时自动执行。


2、增加初始化界面设置，参数集合如下：


1. //程序启动前使用初始界面(此项为false时,以下设定皆无效)

> SET\_START\_INIT=true

2. //使用的初始界面图片

> SET\_START\_IMAGE=init.bmp

3. //设置true时初始窗体将自动居中,同时top及left无效

> SET\_START\_LOCATION\_AUTO=true
4. // 设置初始窗体在桌面的X轴

> SET\_START\_LOCATION\_LEFT=100

5. // 设置初始窗体在桌面的Y轴

> SET\_START\_LOCATION\_TOP=100

6. // 设定初始窗体显示时间

> SET\_START\_TIME=1000


2008年8月21日-26日更新GreenJVM-0.1.1(即0.1.1.0) to 0.1.3(即0.1.1.3)，内容如下：


1、提供原始GreenJVM.exe执行文件。


2、提供原始vm.cfg文件配置方式。


3、新增[SET\_LOCAL\_JRE\_UPDATE](SET_LOCAL_JRE_UPDATE.md)项，通过此项可以命令本地JRE自动更新。


4、新增7z格式压缩包解压支持，建议使用此格式进行虚拟机压缩。



![http://p.blog.csdn.net/images/p_blog_csdn_net/cping1982/EntryImages/20080902/20080902_jvm_s_00.gif](http://p.blog.csdn.net/images/p_blog_csdn_net/cping1982/EntryImages/20080902/20080902_jvm_s_00.gif)

0.1.1.5版配置文件如下：

[LOON](LOON.md)

//设定系统JRE自动更新

SET\_LOCAL\_JRE\_UPDATE=false

//虚拟机解压方式,目前提供有7Z、RAR、ZIP三种主流压缩方式的解压,建议使用压缩比最高的7z方式

SET\_UNPACK\_METHOD=7z

//虚拟机压缩包名称,要求配置在vm.cfg同路径下

SET\_VM\_NAME=jre\_abridgment.pack

//选填,当初始化结束后.允许执行一个bat文件

//SET\_START\_INIT\_BAT=run.bat

//检测本地jvm版本,低于期望值时使用自带jvm,否则以本地虚拟机优先运行

SET\_START\_REQUIRE\_VERSION=1.6

//环境变量中的JavaHome路径

SET\_START\_ENV=JAVA\_HOME

//期望加载的jar,多包以';'分隔

SET\_START\_JAR\_PATH=AVGSimple.jar

//期望执行的main类

SET\_START\_MAIN\_CLASS=org.loon.simple.avg.start.Main

//设定注册表令开机时自动加载我们的main类

SET\_START\_MAIN\_CLASS\_AUTO\_LOAD=false

//程序启动前使用初始logo窗体(此项为false时,以下设定皆无效)

SET\_START\_INIT=false

//选填,使用的初始logo窗体图片

//SET\_START\_IMAGE=init.bmp

//设置true时初始logo窗体将自动居中,同时top及left无效

SET\_START\_LOCATION\_AUTO=true

//设置初始logo窗体在桌面的X轴

SET\_START\_LOCATION\_LEFT=150

//设置初始logo窗体在桌面的Y轴

SET\_START\_LOCATION\_TOP=150

//设定初始logo窗体显示时间

SET\_START\_TIME=1000



{GreenJVMMake}是一个非常简单的Java应用，仅有6KB。它能够记录Java应用程序与JRE中rt.jar的依赖关系，进而仅以其依赖类生成一个新的rt.jar文件，从而最大限度精简JRE体积，建议其与GreenJVM配和使用。

![http://p.blog.csdn.net/images/p_blog_csdn_net/cping1982/EntryImages/20080902/20080902_make_01.gif](http://p.blog.csdn.net/images/p_blog_csdn_net/cping1982/EntryImages/20080902/20080902_make_01.gif)

![http://p.blog.csdn.net/images/p_blog_csdn_net/cping1982/EntryImages/20080902/20080902_make_02.gif](http://p.blog.csdn.net/images/p_blog_csdn_net/cping1982/EntryImages/20080902/20080902_make_02.gif)





{GreenDotNet}是一个根据GreenJVM移植的应用,目标是实现.netframework虚拟机的绿色发布。

GreenDotNet 0.1.1 简要说明

1、本程序是一个非常简单的.Net程序免安装发布工具，可以满足绝大部分.Net framework2.0及以下环境开发之桌面应用的绿色发布及使用。

2、本程序目前阶段不尝试虚拟.Net运行环境（一是难写，二是敝人极懒，三我是Java程序员，明白？^^……），虚拟机初始化后将直接通过GreenDotNet.exe在Windows中布署最简.Net环境，故对程序运行效率无影响。

3、虚拟机压缩包中仅删除了System.Web（如果要用编码之类的功能自己实现就好，不然这堆东西也5MB左右呢|||），保留了其余一切链接库(如操作数据库必须的System.Data等，不要就自己删吧~)，因此绝大多数操作皆可在此环境下正常使用。

4、由于采用了真实的Windows环境进行布署，因此加密或混淆后的.Net程序依旧可以正常在此环境下运行。

5、相较使用Salamander .NET Linker等收费工具（含使用飞信运行框架）而言，这只是一个简写的.Net布署方式，故而绝对不会存在版权或收费等问题。


相关文件说明：

GreenDotNet.exe : 启动文件，它将起到本地环境探针及布署.Net环境的作用。

vm.cfg : 配置文件，决定GreenDotNet.exe对系统进行的相关操作。

dotnet.pack（8MB） : 虚拟机压缩包，使用7z格式产生，目前仅提供.Net 2.0版本支持，不保证2.0以下或以上版本开发之应用在此环境能够正常运行。


vm.cfg配置如下:

> //虚拟机解压方式,目前提供RAR、ZIP、7Z三种压缩方式的解压支持

> SET\_UNPACK\_METHOD=7z

> //虚拟机压缩包名称,要求配置在vm.cfg同路径下

> SET\_VM\_NAME=DotNet.pack

> //检测本地.net版本,低于期望值时使用自带.net,否则以本地虚拟机优先运行

> SET\_START\_REQUIRE\_VERSION=1.1

> //期望执行的exe文件,要求与vm.cfg同路径下

> SET\_START\_MAIN\_EXE=ComicStars.exe


下图是随其发布的应用示例ZERO网络漫画下载程序:

![http://p.blog.csdn.net/images/p_blog_csdn_net/cping1982/EntryImages/20080902/20080902_dotnet_01.gif](http://p.blog.csdn.net/images/p_blog_csdn_net/cping1982/EntryImages/20080902/20080902_dotnet_01.gif)