import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class IntegerSpout extends BaseRichSpout {

    SpoutOutputCollector spoutOutputCollector;
    private Integer ctr = 0;
    private StormTimer stormTimer;

    public void open(Map<String, Object> map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.spoutOutputCollector = spoutOutputCollector;
        StormTimer timer = new StormTimer(2000, 4000, TimeUnit.MILLISECONDS);
        this.stormTimer = timer;
    }

    public void nextTuple() {
        if (stormTimer.isExpiredResetOnTrue()) {
            if (ctr < 100) {
                System.out.println("//////////////////////////////////////////////////\n" +
                        "//////////////////////////////////////////////////////\n" +
                        "//////////////////////////////////////////////////////////////");

                this.spoutOutputCollector.emit(new Values(ctr));
                ctr += 1;
//                try {
//                Thread.sleep(1000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("counter"));
    }
}
