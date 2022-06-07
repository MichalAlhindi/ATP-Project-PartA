package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;
    private int currNum;
    public SimpleDecompressorInputStream(InputStream is) {
        in = is;
        currNum = 0;
    }

    /**
     * override method
     * @return 0
     * @throws IOException
     */
    @Override
    public int read() throws IOException {
        return 0;
    }

    /**
     * decompress byte array : change the given byte array - convert every number to a sequence of 0/1 in the size of this number.
     * @param newB the new decompressed array
     * @return
     * @throws IOException
     */
    @Override
    public int read(byte[] newB) throws IOException {
        byte[] compressedArr = in.readAllBytes();
        byte count;
        for (int j = 0; j<12; j++){
            newB[j]= compressedArr[j];
        }
        int newIdx=12;
        for (int i = 12; i< compressedArr.length; i++){
            count = compressedArr[i];
            while(count>0){
                newB[newIdx]= (byte)currNum;
                newIdx++;
                count--;
            }
            if (count==0){
                if(currNum == 0){
                    currNum = 1;
                }
                else {
                    currNum = 0;
                }
            }
        }
        return 0;
    }

}
