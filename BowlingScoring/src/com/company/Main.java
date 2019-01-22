package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.company.Frame.*;

public class Main {

    public static void readInScores(int frame) throws IOException {
        System.out.println("Enter the scores for frame <Press Enter after each>: " + (frame + 1));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        roll1 = br.readLine();
        if(roll1.matches("-?([1-9]\\d*)"))
            roll2 = br.readLine();
    }

    public static void score(){
        // If roll is a strike
        if(roll1.matches("[Xx]")){
            strikeFlag = 1;
            frameScores[frame] = 0;
            totalScores[frame] = 0;
        }
        // If the current frame is a spare, trigger the flag and set the current frame score to 0
        else if(roll2.matches("\\/")){
            spareFlag = 1;
            frameScores[frame] = 0;
            totalScores[frame] = 0;
        }
        // If both rolls are integers, add them and update the scores
        else{
            int sum = Integer.parseInt(roll1) + Integer.parseInt(roll2);
            if(sum == 10){
                spareFlag = 1;
                frameScores[frame] = 0;
                totalScores[frame] = 0;
            }
            else {
                frameScores[frame] = Integer.parseInt(roll1) + Integer.parseInt(roll2);
                totalScores[frame] = frame - 1 < 0 ? frameScores[frame] : totalScores[frame - 1] + frameScores[frame];
            }
        }

        int twoFramesAgo = frame - 2;
        // If the last frame was a spare, the last frames' frameScore is calculated and the total score is updated
        if(spareFlag == 2){
            int rollOne = roll1.matches("[Xx]") ? 10 : Integer.parseInt(roll1);
            frameScores[frame - 1] = 10 + rollOne;
            totalScores[frame - 1] += twoFramesAgo <= 0 ? 10 + rollOne : 10 + rollOne + totalScores[frame - 2];
        }
        if(strikeFlag == 2){
            int rollOne = 0;
            int rollTwo = 0;
            if (roll1.matches("[Xx]")){
                rollOne = 10;
            }else{
                rollTwo = roll2.matches("\\/") ? 10 : Integer.parseInt(roll2);
            }

            if(twoFramesAgo < 0){
                frameScores[frame - 1] = 10 + rollOne + rollTwo;
                totalScores[frame - 1] = frameScores[frame - 1];
            }else{
                frameScores[frame - 1] = 10 + rollOne + rollTwo;
                totalScores[frame - 1] = 10 + rollOne + rollTwo + totalScores[frame - 2];
            }
        }
    }
    public static void main(String[] args) throws IOException {

        while(frame < 11){
            readInScores(frame);
            score();
            int currentFrame = frame;  // current frame
            int lastFrame = frame - 1; // last frame
            int prettyFrame = frame + 1; // For printing since frame starts at 0

            if(spareFlag == 2){
                System.out.println("| Frame:       " + frame + " |");
                System.out.println("| Frame Score:  " + frameScores[lastFrame] + " |");
                System.out.println("| Total Score: " + totalScores[lastFrame] + " |");
                spareFlag = 0;
                System.out.println("");
            }

            if(strikeFlag == 2){
                System.out.println("| Frame:       " + frame + " |");
                System.out.println("| Frame Score:  " + frameScores[lastFrame] + " |");
                System.out.println("| Total Score: " + totalScores[lastFrame] + " |");
                totalScore += totalScores[lastFrame];
                strikeFlag = 0;
                System.out.println("");
            }

            System.out.println("| Frame:       " + prettyFrame + " |");
            // System.out.println("| Result:      " + a[0] + " " + a[1] + " |");   //Fix for arrays shorter than 2
            System.out.println("| Frame Score:  " + frameScores[currentFrame] + " |");
            System.out.println("| Total Score: " + totalScores[currentFrame] + " |");


            if(spareFlag == 1) spareFlag = 2;
            if(strikeFlag == 1) strikeFlag = 2;
            frame++;
        }
    }


}
