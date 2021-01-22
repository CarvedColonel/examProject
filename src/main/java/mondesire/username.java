package mondesire;

import java.io.IOException;
import java.io.RandomAccessFile;

public class username {
    private String userName;
    private int bitcoin;
    private int winCounter;
private int saveCounter;
private int damageBuff;
private  boolean holyWater;
private boolean healthBuff;
    private final int RECSIZE = 38;

    //UserName = 10 * 2 = 20
    //gold = 4
    //winCount = 4
    //saveCounter = 4
    //damageBuff = 4
    //holywater = 1
    //healthbuff = 1
    public username() {
        userName = "          ";
        bitcoin = 0;
        winCounter = 0;
        saveCounter = 0;
        damageBuff = 0;
        holyWater = false;
        healthBuff = false;
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
    public void setDamageBuff(int d) {
        damageBuff = d;
    }

    public int getDamageBuff() {
        return damageBuff;
    }
    public void setHolyBuff(boolean h) {
        holyWater = h;
    }

    public boolean getHolyBuff() {
        return holyWater;
    }
    public void setHealthBuff(boolean he) {
        healthBuff = he;
    }

    public boolean getHealthBuff() {
        return holyWater;
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
            recordfile.writeInt(damageBuff);
            recordfile.writeBoolean(holyWater);
            recordfile.writeBoolean(healthBuff);
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
            damageBuff = recordFile.readInt();
            holyWater = recordFile.readBoolean();
            healthBuff = recordFile.readBoolean();
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
