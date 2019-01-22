package com.company;

public class Frame {
    public static int frameScore = 0;
    public static int totalScore;
    public static int frame = 0;
    public static String roll1;
    public static String roll2 = "";
    public static int spareFlag = 0; // 0 - default, 1 - current frame is a spare, 2 - last frame was a spare
    public static int strikeFlag = 0; // 0 - default, 1 - current frame is a strike, 2 - last frame was a strike
    public static int spareFrameScore;
    public static int[] frameScores = new int[11];
    public static int[] totalScores = new int[11];
}
