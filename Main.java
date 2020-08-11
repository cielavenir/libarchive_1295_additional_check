import java.io.*;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
 
class Main{
    public static void main(String[] args){
        try{
            try(SevenZOutputFile archive = new SevenZOutputFile(new File("out.7z"))){
                for(String fileName: args){
                    File file = new File(fileName);
                    SevenZArchiveEntry entry  = archive.createArchiveEntry(file, fileName);
                    archive.putArchiveEntry(entry);
                    try(FileInputStream fis = new FileInputStream(fileName)){
                        int readlen = 0;
                        byte[] buf = new byte[65536];
                        for(;(readlen = fis.read(buf)) > 0;){
                            archive.write(buf, 0, readlen);
                        }
                    }
                    archive.closeArchiveEntry();
                }
            }
        }catch(IOException e){}
    }
}
