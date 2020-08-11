import java.io.*;
import java.util.Arrays;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
 
class Main{
    public static void main(String[] args){
        try(SevenZOutputFile arc = new SevenZOutputFile(new File(args[0]))){
            for(String fileName: Arrays.copyOfRange(args,1,args.length)){
                File file = new File(fileName);
                SevenZArchiveEntry entry  = arc.createArchiveEntry(file, fileName);
                arc.putArchiveEntry(entry);
                try(FileInputStream f = new FileInputStream(file)){
                    int readlen = 0;
                    byte[] buf = new byte[65536];
                    for(;(readlen = f.read(buf)) > 0;){
                        arc.write(buf, 0, readlen);
                    }
                }
                arc.closeArchiveEntry();
            }
        }catch(IOException e){}
    }
}
