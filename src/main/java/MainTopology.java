import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.thrift.TException;
import org.apache.storm.topology.TopologyBuilder;

public class MainTopology {
    public static void main(String[] args) throws Exception {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("IntegerCounter", new IntegerSpout());
        builder.setBolt("MultipyBolt", new MultiplyBolt()).shuffleGrouping("IntegerCounter");

        Config config = new Config();
        config.setDebug(true);

        LocalCluster cluster = new LocalCluster();
        try{
            cluster.submitTopology("MainTopology", config, builder.createTopology());
            Thread.sleep(1000);
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            cluster.shutdown();
        }
    }
}
