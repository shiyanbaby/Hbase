package forHbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

public class DescTables {
    public static void main(String[] args) throws IOException {
        Configuration conf= HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","192.168.149.135");
        conf.set("hbase.zookeeper.property.clientPort","2181");

        Connection conn= ConnectionFactory.createConnection(conf);
        Admin admin=conn.getAdmin();
//       列举所有的表结构
        HTableDescriptor [] hd=admin.listTables();
        for(HTableDescriptor h:hd){
            System.out.println(h);
        }
        //列举指定的表结构
        HTableDescriptor hd1=admin.getTableDescriptor(TableName.valueOf("GoodsOrders"));
        System.out.println(hd1);


    }

}
