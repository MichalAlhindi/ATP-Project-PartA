package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;
    private int count;
    private int currNum;
    private byte[] byteArr;

    /**
     * constructor
     * @param os outputStream of the compressor
     */
    public MyCompressorOutputStream(OutputStream os) {
        out = os;
        count = 0;
        currNum = 0;
    }

    /**
     * write a number to to outStream
     * @param b the number to write
     * @throws IOException
     */
    @Override
    public void write(int b) throws IOException {
        out.write((byte)b);
    }

    /**
     * compress byte array : convert every 8 bytes of the array to a decimal number (in byte)
     * and write it to the outputStream
     * @param b byte array to be compressed
     * @throws IOException
     */
    @Override
    public void write(byte[] b) throws IOException {
        for(int i=0; i<12; i++) { // write the data of the first 6's cells
            write(b[i]);
        }
        int j=12;
        int sum = 0; // the current sum of the current 8 bytes
        int index=0; // the index of the current byte
        while(j < b.length){
           for (int k=7;k>=0; k--){
               if(j+k<b.length){
                    if (b[j+k]==(byte)1){
                        sum+=Math.pow(2,index);
                    }
                    index++;
               }
           }
           write(sum);
           j = j+8;
           index=0;
           sum = 0;
        }
    }
}
