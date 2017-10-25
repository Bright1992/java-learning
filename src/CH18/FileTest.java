package CH18;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class FileTest {
    public static void main(String[] argv) throws Exception{
        System.out.println(dfs(new File(".")));

    }
    private static int dfs(File dir) throws Exception{
        File[] dirFile = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        String[] jFile = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.matches(".+\\.java");
            }
        });
        int total=0;
        Arrays.sort(jFile,String.CASE_INSENSITIVE_ORDER);
        for (String s:jFile) {
            DataInputStream in = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(dir.getCanonicalPath()+"\\"+s)));
            int c=0;
            while(in.readLine()!=null){
                c++;
            }
            total+=c;
            System.out.println(dir.getCanonicalPath()+"\\"+s+":"+c);
        }
        for(File f:dirFile){
            total+=dfs(f);
        }
        return total;
    }
}
