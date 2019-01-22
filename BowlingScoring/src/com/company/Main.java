package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import static com.company.Frame.*;

public class Main {

    public static void readInScores(int frame) throws IOException {
        System.out.println("Enter the scores for frame <Press Enter after each>: " + frame);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        roll1 = br.readLine();
        if(roll1.matches("-?([1-9]\\d*)"))
            roll2 = br.readLine();
    }

    public static void score(){
        // If roll is a strike, frame score = 10
        if(roll1.matches("[Xx]")){
            frameScore = 10;
            totalScore += frameScore;
        }
        // If the current frame is a spare, trigger the flag and set the current frame score to 0
        else if(roll2.matches("\\/")){
            frameScore = 0;
            spareFlag = 1;
        }
        // If both rolls are integers, add them and update the scores
        else{
            frameScore = Integer.parseInt(roll1) + Integer.parseInt(roll2);
            totalScore += frameScore;
        }

        // If the last frame was a spare, the last frames' frameScore is calculated and the total score is updated
        if(spareFlag == 2){
            int rollOne = roll1.matches("[Xx]") ? 10 : Integer.parseInt(roll1);
            spareFrameScore = 10 + rollOne;
            totalScore += spareFrameScore;
        }
    }
    public static void main(String[] args) throws IOException {
        while(frame < 11){
            readInScores(frame);
            score();
            if(spareFlag == 2){
                int lastFrame = frame - 1;
                System.out.println("| Frame:       " + lastFrame + " |");
                System.out.println("| Frame Score:  " + Frame.spareFrameScore + " |");
                System.out.println("| Total Score: " + Frame.totalScore + " |");
                spareFlag = 0;
                System.out.println("");
            }
            System.out.println("| Frame:       " + Frame.frame + " |");
            // System.out.println("| Result:      " + a[0] + " " + a[1] + " |");   //Fix for arrays shorter than 2
            System.out.println("| Frame Score:  " + Frame.frameScore + " |");
            System.out.println("| Total Score: " + Frame.totalScore + " |");


            if(spareFlag == 1) spareFlag = 2;
            frame++;
        }
    }


}
