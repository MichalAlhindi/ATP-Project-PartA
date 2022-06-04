package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;
    public MyDecompressorInputStream(InputStream is) {
        in = is;
    }


    @Override
    public int read() throws IOException {
        return 0;
    }

    /**
     * decompress byte array : convert every number to it's 8 binary digits
     * @param newB the new decompressed array
     * @return 0
     * @throws IOException
     */
    @Override
    public int read(byte[] newB) throws IOException {
        byte[] compressedArr = in.readAllBytes();
        for (int j = 0; j<6; j++){
            newB[j]= compressedArr[j]; // write the data of the first 6's cells
        }
        int currIdx = 6; // curr index of newB
        byte val;
        for (int i = 6; i < compressedArr.length; i++){
            int k = 7;
            int num=compressedArr[i];
            if(num < 0){
                num += 256;
            }
            while(currIdx + k >= newB.length && k>0){
                k--;
            }
            while(num > 0 || k >= 0){
                val = ((byte) (num % 2));
                newB[currIdx+k] = val;
                num =  (num/2);
                k--;
            }
            currIdx = currIdx + 8;
        }
        return 0;
    }
}

