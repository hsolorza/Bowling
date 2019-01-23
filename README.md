# Bowling

This code requires Java 8 to run.

## How to run
- Navigate to the src directory level.
- Compile using `javac com/company/Main.java`
- Run using `java com.company.Main`
- You will be prompted to enter in the roll scores for the first frame. Press Enter after each score.


Spares can be represented either using `/` or having both roll scores for a frame add up to 10.

Strikes can either be represented by `X` or `x`.

If the last frame contains a strike or a spare, you will be prompted to enter in a bonus roll score. This score will be added to the last frames' total.

The program ends once the final frame has printed its scores and you see the final score printed.

### Known issues
- The code does not properly handle the case of several back to back strikes or spares.
