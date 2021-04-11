import javax.xml.bind.SchemaOutputResolver;

public class Test {
    public static void main(String[] args) throws Exception {
        Compress compress=new Compress();
        compress.countTimes("/Users/tonyfang/Desktop/程序设计/Demo.txt");
        HuffmNode root=compress.createTree();
        compress.getHuffmCode(root,"");
        compress.print("/Users/tonyfang/Desktop/程序设计/Demo.txt");
        compress.decompress();
    }
}
