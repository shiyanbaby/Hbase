package forHbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class CreateHbase {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "192.168.149.135");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        Connection conn = ConnectionFactory.createConnection(conf);
        //建立表
        Admin admin = conn.getAdmin();
        HTableDescriptor desc = new HTableDescriptor(TableName.valueOf("GoodsOrders"));
        HColumnDescriptor h1 = new HColumnDescriptor(Bytes.toBytes("f1"));
        HColumnDescriptor h2 = new HColumnDescriptor(Bytes.toBytes("f2"));
        desc.addFamily(h1);
        desc.addFamily(h2);
        admin.createTable(desc);
        //插入数据
        Table tb = conn.getTable(TableName.valueOf("GoodsOrders"));
        Put put = new Put(Bytes.toBytes("myrow-1"));//行键
        put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("Hbase"), 1497438339495L, Bytes.toBytes("1"));
        put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("Hadoop"), 1497438308204L, Bytes.toBytes("2"));
        put.addColumn(Bytes.toBytes("f2"), Bytes.toBytes(" "), 1497438339495L, Bytes.toBytes("Tina"));

        Put put1 = new Put(Bytes.toBytes("myrow-2"));//行键
        put1.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("Hadoop"), 1497435626011L, Bytes.toBytes("2"));
        put1.addColumn(Bytes.toBytes("f2"), Bytes.toBytes(" "), 1497438308204L, Bytes.toBytes("Ben"));

        tb.put(put);
        tb.put(put1);
        tb.close();
        admin.close();
        conn.close();
    }
}
