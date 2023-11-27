_[# 转化算子

用户通过算子能将一个或多个 DataStream 转换成新的 DataStream，在应用程序中可以将多个数据转换算子合并成一个复杂的数据流拓扑。

这部分内容将描述 Flink DataStream API 中基本的数据转换API，数据转换后各种数据分区方式，以及算子的链接策略。

----


Transformation	描述
Map	输入一个element，返回一个element。中间可以做一些清洗转换等操作
FlapMap	输入一个element，可以返回出0、1或多个element。
Filter	过滤函数，对传入的数据进行判断，符合条件的数据会被留下
KeyBy	根据指定的key进行分组，相同key的数据会进入同一个分区
Reduce	对数据进行聚合操作，结合当前元素和上一次reduce返回的值进行聚合操作，然后返回一个新的值
Aggregations（sum(),min(),max()等）
Window（KeyedStream - > WindowedStream）	Window可以定义在已经分区的KeyedStream上。窗口将根据一些特征（如最近5秒到达的数据）将数据按其各自的key集合在一起。
WindowAll（DataStream -> AllWindowedStream）	Window可以定义在普通的DataStream上。窗口将根据一些特征（如最近5秒到达的数据）将所有Stream事件集合在一起。
Union	将2个或多个data stream合并创建出一个新的包含所有stream的element的stream，要求合并的两个流类型必须一致
Connect	和union类似，但是只能连接两个流，两个流的数据类型可以不同，会对两个流中的数据应用不同的处理方法。
Split	根据某些标准将Stream分割成2个或更多的stream
Select（SplitStream -> DataStream）	从SplitStream中选择1个或多个stream

1、map

map:输入一个元素，输出一个元素，可以用来做一些清洗工作

2、flatMap

flatMap:打平操作，我们可以理解为将输入的元素压平，从而对输出结果的数量不做要求，可以为0、1或者多个都OK。它和Map相似，但引入flatMap的原因是因为一般java方法的返回值结果都是一个，因此引入flatMap来区别这个。

3、filter

filter:过滤筛选，将所有符合判断条件的结果集输出

4、keyBy

keyBy:在逻辑上将Stream根据指定的Key进行分区，是根据key的Hash值进行分区的。

5、reduce

reduce:属于归并操作，它能将3的keyedStream转换成DataStream，Reduce 返回单个的结果值，并且 reduce 操作每处理每一天新数据时总是创建一个新值。常用聚合操作例如min、max等都可使用reduce方法实现。这里通过实现一个Socket的wordCount简单例子，来帮助了解flatMap/keyBy/reduce/window等操作的过程。

6、aggregations

aggregations:进行一些聚合操作，例如sum()，min()，max()等,这些可以用于keyedStream从而获得聚合。用法如下

KeyedStream.sum(0)或者KeyedStream.sum(“Key”)

7、unoin

union]()_:可以将多个流合并到一个流中，以便对合并的流进行统一处理，有点类似于Storm中的将上一级的两个Bolt数据汇聚到这一级同一个Bolt中。注意，合并的流类型需要一致


9、split

split:根据规则吧一个数据流切分成多个流，可能在实际场景中，源数据流中混合了多种类似的数据，多种类型的数据处理规则不一样，所以就可以根据一定的规则把一个数据流切分成多个数据流。