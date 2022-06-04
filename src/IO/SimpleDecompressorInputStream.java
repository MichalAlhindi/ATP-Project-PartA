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

    @Override
    public int read() throws IOException {

        return 0;
    }

    @Override
    public int read(byte[] newB) throws IOException {
        byte[] compressedArr = in.readAllBytes();
        byte count;
        for (int j = 0; j<6; j++){
            newB[j]= compressedArr[j];
        }
        int newIdx=6;
        for (int i = 6; i< compressedArr.length; i++){
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
