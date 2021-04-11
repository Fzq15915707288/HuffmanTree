import com.sun.jmx.snmp.SnmpOid;

import java.io.*;

public class filereader {
    public static void main(String[] args) throws Exception {
        Compress.countTimes("/Users/tonyfang/Desktop/Demo.txt");

    }

    static class Compress {

        public static int[] times = new int[256];
        public String[] HuffmCodes = new String[256];
//        public LinkedList<HuffmNode> list = new LinkedList<HuffmNode>();
        //统计次数

        //初始化
        public Compress() {
            for (int i = 0; i < HuffmCodes.length; i++) {
                HuffmCodes[i] = "";
            }
        }

        public  static void countTimes(String path) throws Exception {
            //构造文件输入流
            FileInputStream fis = new FileInputStream(path);
            //读取文件
            int value = fis.read();
            while (value != -1) {
//                System.out.print((char)value);
                times[value]++;
                value = fis.read();
            }
            //关闭流
            fis.close();
            for (int i = 97; i < 123; i++) {
                System.out.print((char)i);
                System.out.print(":"+times[i]+" ");
            }
        }

    }
}