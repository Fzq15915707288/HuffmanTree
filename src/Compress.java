import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;


public class Compress {
    public int[] times = new int[256];//存放字母次数数组
    public String[] HuffmCodes = new String[256];//
    public LinkedList<HuffmNode> list = new LinkedList<HuffmNode>();
    public String a = "";
    public String out = "";
    public int[] save = new int[256];
    //初始化
    public Compress() {
        for (int i = 0; i < HuffmCodes.length; i++) {
            HuffmCodes[i] = "";
        }
    }
    //统计每个字符出现次数
    public void countTimes(String path) throws Exception {
        //构造文件输入流
        FileInputStream fis = new FileInputStream(path);
        //读取文件
        int value = fis.read();
        while (value != -1) {
            times[value]++;
            value = fis.read();
        }
        //关闭流
        fis.close();
        for (int i = 0; i < 123; i++) {
            if (times[i] != 0) {
                System.out.print((char) i);
                System.out.print(":" + times[i] + " ");
            }
        }
        System.out.println();
    }
    //建立二叉树
    public HuffmNode createTree() {
        for (int i = 0; i < times.length; i++) {
            if (times[i] != 0) {
                HuffmNode node = new HuffmNode(times[i], i);
                //将构造好的节点加入到容器中的正确位置
                list.add(getIndex(node), node);
            }
        }

        while (list.size() > 1) {
            HuffmNode firstNode = list.removeFirst();
            HuffmNode secondNode = list.removeFirst();
            HuffmNode fatherNode = new HuffmNode(firstNode.getData() + secondNode.getData(),-1);
            fatherNode.setLeft(firstNode);
            fatherNode.setRight(secondNode);
            //父节点加入到容器中的正确位置
            list.add(getIndex(fatherNode), fatherNode);
        }
        //最后剩下的是根结点
        return list.getFirst();
    }
    //前序遍历树，以出现频次输出
    public void getHuffmCode(HuffmNode root, String code) throws IOException {
        String a = "";
        if (root.getLeft() != null) {//向左遍历+0
            getHuffmCode(root.getLeft(), code + "0");
        }
        if (root.getRight() != null) {//向右遍历+1
            getHuffmCode(root.getRight(), code + "1");
        }
        if (root.getRight() == null && root.getLeft() == null) {//遍历到了节点，
            System.out.println((char) root.getIndex() + "该叶子节点编码为:" + code);
            HuffmCodes[root.getIndex()] = code;
        }


    }

    public void print(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        int value = fis.read();
        while (value != -1) {
            a = a + HuffmCodes[value] + "x";
            value = fis.read();
        }
        System.out.println(a);
    }

    public int getIndex(HuffmNode node) {//找结点的下标
        for (int i = 0; i < list.size(); i++) {
            if (node.getData() <= list.get(i).getData()) {
                return i;
            }
        }
        return list.size();
    }

    public void decompress() {//解码
        int find = 0;
        String[] a1 = a.split("x");
        for (int i = 0; i < a1.length; i++) {
            for (int j = 0; j < HuffmCodes.length; j++) {
                if(a1[i].equals(HuffmCodes[find])){
                    System.out.print((char)find);
                }
                find++;
            }
            find=0;

        }
    }

}