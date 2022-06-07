package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream{
    private OutputStream out;
    private int count;
    private int currNum;
    public SimpleCompressorOutputStream(OutputStream os) {
        out = os;
        count = 0;
        currNum = 0;
    }

    /**
     * write a number to the outStream
     * @param b the number to write
     * @throws IOException
     */
    @Override
    public void write(int b) throws IOException {
        out.write((byte)b);
    }

    /**
     * compress byte array : for every continuity bytes in the array, write it's count to the outputStream
     * @param b byte array to be compressed
     * @throws IOException
     */
    @Override
    public void write(byte[] b) throws IOException {
        for(int i=0; i<12; i++) {
            write(b[i]);
        }
        for(int j=12; j<b.length; j++){
            if(b[j] == currNum){
                if(count>255){
                    write(255);
                    write(0);
                    count=0;
                }
                count++;
            }
            else{
                write(count);
                count = 1;
                if(currNum == 0){
                    currNum = 1;
                }
                else{
                    currNum = 0;
                }
            }
        }
        write(count);
    }
}
