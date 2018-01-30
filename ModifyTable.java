package forHbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class ModifyTable {

    public static void main(String[] args) throws IOException {
        Configuration conf= HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","192.168.149.135");
        conf.set("hbase.zookeeper.property.clientPort","2181");

        Connection conn= ConnectionFactory.createConnection(conf);
        Admin admin=conn.getAdmin();

        TableName name=TableName.valueOf("GoodsOrders");
        HTableDescriptor ht=admin.getTableDescriptor(name);//获得表的结构
        //增加列族
        HColumnDescriptor hd=new HColumnDescriptor(Bytes.toBytes("f3"));
        ht.addFamily(hd);
        ht.setMaxFileSize(1024*1024*1024L);//修改最大文件限制属性
        admin.disableTable(name);

        admin.modifyTable(name,ht);
        admin.enableTable(name);

        HTableDescriptor ht1=admin.getTableDescriptor(name);
        //检查表结构是否修改成功
        System.out.println("Equals: " + ht.equals(ht1));
        System.out.println("New schema: " + ht1);
    }
}
