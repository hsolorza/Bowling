package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.company.Frame.*;

public class Main {

    public static void readInScores(int frame) throws IOException {
        int currentFrame = frame + 1;
        System.out.println("Enter the scores for frame " + currentFrame + ": <Press Enter after each roll> ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        roll1 = br.readLine();

        // If the first roll is not a strike, read in the second value
        if(roll1.matches("-?([1-9]\\d*)")) {
            roll2 = br.readLine();

            // If the second roll is not a spare - given as a slash by the user - then check the validity of the integers
            // provided by the player
            if(!(roll2.matches("\\/"))) {
                if ((Integer.parseInt(roll1) > 10) ||
                        (Integer.parseInt(roll2) > 10) ||
                        (Integer.parseInt(roll1) + Integer.parseInt(roll2) > 10)) {
                    System.out.println("Not a valid set of rolls. Please reenter the score for frame " + currentFrame);
                    readInScores(frame);
                }
            }
        }
    }

    public static void score(){
        int twoFramesAgo = frame - 2; // Used for calculating the total scores for spares

        // If the last frame was a spare, the last frames' frameScore is calculated and the total score is updated
        if(spareFlag == 2){
            int rollOne = roll1.matches("[Xx]") ? 10 : Integer.parseInt(roll1);
            frameScores[frame - 1] = 10 + rollOne;
            totalScores[frame - 1] += twoFramesAgo <= 0 ? 10 + rollOne : 10 + rollOne + totalScores[frame - 2];
        }

        // If the last frame was a strike, the last frames' frame and total scores are calculated
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

        // If roll is a strike set the current frame scores to 0 and set the flag
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

            // If the sums of the two integers are ten, it is a strike
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
    }

    public static void print(int currentFrame, int lastFrame, int prettyFrame){
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
        System.out.println("| Frame Score:  " + frameScores[currentFrame] + " |");
        System.out.println("| Total Score: " + totalScores[currentFrame] + " |");
    }
    public static void main(String[] args) throws IOException {

        while(frame < 11){
            readInScores(frame);
            score();

            int currentFrame = frame;  // current frame
            int lastFrame = frame - 1; // last frame
            int prettyFrame = frame + 1; // For printing since frame starts at 0

            print(currentFrame, lastFrame, prettyFrame);

            // If the current frame was a spare/strike, update flags to show that the counter is now past that frame
            if(spareFlag == 1) spareFlag = 2;
            if(strikeFlag == 1) strikeFlag = 2;

            frame++;
        }
    }
}