package org.sink.data_stream;

import org.apache.flink.annotation.PublicEvolving;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.operators.StreamingRuntimeContext;

import java.io.PrintStream;

@PublicEvolving
public class PrintSinkFunction<IN> extends RichSinkFunction<IN> {
    private static final long serialVersionUID = 1L;
    private static final boolean STD_OUT = false;
    private static final boolean STD_ERR = true;
    private boolean target;
    private transient PrintStream stream;
    private transient String prefix;

    //实例化标准输出的sink方法
    public PrintSinkFunction() {}

    //如果格式化打印为stdErr而不是stdOut，置stderr true
    public PrintSinkFunction(boolean stdErr) {
        target = stdErr;
    }

    public void setTargetToStandardOut() {
        target = STD_OUT;
    }

    public void setTargetToStandardErr() {
        target = STD_ERR;
    }
    //重写open方法
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        StreamingRuntimeContext context = (StreamingRuntimeContext) getRuntimeContext();
        // get the target stream
        stream = target == STD_OUT ? System.out : System.err;

        // set the prefix if we have a >1 parallelism
        prefix = (context.getNumberOfParallelSubtasks() > 1) ?
                ((context.getIndexOfThisSubtask() + 1) + "> ") : null;
    }

    @Override
    public void invoke(IN record) {
        if (prefix != null) {
            stream.println(prefix + record.toString());
        }
        else {
            stream.println(record.toString());
        }
    }

    @Override
    public void close() {
        this.stream = null;
        this.prefix = null;
    }

    @Override
    public String toString() {
        return "Print to " + (target == STD_OUT ? "System.out" : "System.err");
    }
}