

# sink

Sink是Flink处理的最后一步，即将实时transformation后的计算结果“落地”到某个地方，
可以是Mysql、ES、Kafka、redis等。对应Source的SourceFunction()，
Sink也对应有SinkFunction()，SinkFunction()也是sink的根接口，
在自定义SinkFunction时要继承RichSinkFunction抽象类，实现其中的方法，
包括open()、invoke()、close()，其中最重要的实现就是invoke方法。
