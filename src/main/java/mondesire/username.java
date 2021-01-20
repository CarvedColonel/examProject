package mondesire;

import java.io.IOException;
import java.io.RandomAccessFile;

public class username {
    private String userName;
    private int bitcoin ;
    private double x;
    private double y;


    private final int RECSIZE = 40;

    //UserName = 10 * 2 = 20
    //gold = 4
    //x = 8
    //y = 8
    public username() {
        userName = "          ";
        bitcoin = 0;
        x = 0;
        y = 0;
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
    public void setX(double Ax) {
        x = Ax;
    }
    public double getX() {
        return x;
    }   public void setY(double Ay) {
        y = Ay;
    }
    public double getY() {
        return y;
    }


    // The save method writes everything down in the file
    public void save(String file, int record) {
        try {
            RandomAccessFile recordfile = new RandomAccessFile(file, "rw");
            recordfile.seek(record * RECSIZE);
            recordfile.writeChars(userName);
            recordfile.writeInt(bitcoin);
            recordfile.writeDouble(x);
            recordfile.writeDouble(y);
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
            x = recordFile.readDouble();
            y = recordFile.readDouble();
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
