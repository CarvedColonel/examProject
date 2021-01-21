package mondesire;

import java.io.IOException;
import java.io.RandomAccessFile;

public class username {
    private String userName;
    private int bitcoin;
    private int winCounter;
private int saveCounter;

    private final int RECSIZE = 32;

    //UserName = 10 * 2 = 20
    //gold = 4
    //winCount = 4
    //saveCounter = 4
    public username() {
        userName = "          ";
        bitcoin = 0;
        winCounter = 0;
        saveCounter = 0;
    }

    //All the sets setup the information for the file
    //All the gets get that information so we can use it
    public void setuserName(String u) {
        StringBuffer temp = new StringBuffer(u);
        temp.setLength(10);
        userName = temp.toString();
    }

    public String getuserName() {
        return userName.trim();
    }


    public void setBitcoin(int g) {
        bitcoin = g;
    }

    public int getBitcoin() {
        return bitcoin;
    }

    public void setWinCounter(int w) {
        winCounter = w;
    }

    public int getWinCounter() {
        return winCounter;
    }
    public void setSaveCounter(int s) {
        saveCounter = s;
    }

    public int getSaveCounter() {
        return saveCounter;
    }

    // The save method writes everything down in the file
    public void save(String file, int record) {
        try {
            RandomAccessFile recordfile = new RandomAccessFile(file, "rw");
            recordfile.seek(record * RECSIZE);
            recordfile.writeChars(userName);
            recordfile.writeInt(bitcoin);
            recordfile.writeInt(winCounter);
            recordfile.writeInt(saveCounter);
            recordfile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // The open method is to open the file and read the information
    public void open(String file, int record) {
        try {
            RandomAccessFile recordFile = new RandomAccessFile(file, "r");
            recordFile.seek(record * RECSIZE);
            char userN[] = new char[10];
            for (int i = 0; i < userN.length; i++) {
                userN[i] = recordFile.readChar();
            }
            userName = new String(userN);
            bitcoin = recordFile.readInt();
            winCounter = recordFile.readInt();
            saveCounter = recordFile.readInt();
            recordFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // This  will tell us how much information is written in the file and how many spots are taken
    public int numRecord(String file) {
        int numR = 0;
        try {
            RandomAccessFile recordFile = new RandomAccessFile(file, "r");
            numR = (int) (recordFile.length() / RECSIZE);

        } catch (Exception ex) {
        }
        return numR;
    }

}
