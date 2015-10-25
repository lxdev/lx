package net.lx.common.lucene;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

/**
 * Created by Administrator on 2015/8/26.
 */
public class Indexer {

//    private IndexWriter writer;
//    public Indexer(String indexDir) throws IOException {
//        Directory dir = FSDirectory.open(new File(indexDir));
//        // 创建 Lucene Index Writer
//        writer = new IndexWriter(dir,
//                new StandardAnalyzer(Version.LUCENE_30), true, IndexWriter.MaxFieldLength.UNLIMITED);
//    }
//
//    public static void main(String[] args) throws Exception {
//        if(args.length != 2){
//            throw new IllegalArgumentException("Usage: java " + Indexer.class.getName()
//            + " <index dir> <data dir>");
//        }
//        String indexDir = args[0];
//        String dataDir = args[1];
//
//        long start = System.currentTimeMillis();
//        Indexer indexer = new Indexer(indexDir);
//        int numIndexed;
//        try {
//            numIndexed = indexer.index(dataDir, new TextFilesFilter());
//        } finally {
//            indexer.close();
//        }
//        long end = System.currentTimeMillis();
//
//        System.out.println("Indexing" + numIndexed + " files took " + (end - start) + " milliseconds");
//    }
//
//    public void close() throws IOException{
//        //关闭 Index Writer
//        writer.close();
//    }
//    public int index(String dataDir, FileFilter filter) throws Exception {
//        File[] files = new File(dataDir).listFiles();
//        for(File f: files){
//            if(!f.isDirectory() &&
//                !f.isHidden() && f.exists() && f.canRead() && (filter == null || filter.accept(f)) {
//                indexFile(f);
//            }
//        }
//        //返回被索引文档数
//        return writer.numDocs();
//    }
//    private static class TextFilesFilter implements FileFilter {
//        public boolean accept(File path){
//            //6 只索引.txt文件，采用FileFilter
//            return path.getName().toLowerCase().endsWith(".txt");
//        }
//    }

//    protected Document getDocument(File f) throws Exception{
//        Document doc = new Document();
//
//    }


//    public int N = 9;
//    public int appleNum = 0;
//
//    public static void main(String[] args) throws Excpetion {
//        for(int i = 0; i < N; i++){
//
//    }

}
