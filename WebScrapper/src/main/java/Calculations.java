
import java.util.ArrayList;
import java.util.Collections;

public class Calculations  {
    protected static ArrayList<Float> arraylist;
    private float mean;
    private float SD;
    private final float pi = (float) 3.142;
    private float xVal;
    protected static ArrayList<Float> cleanlist;
    private final float e = (float) 2.718;

    public Calculations(ArrayList<Float> arraylist){
        Collections.sort(arraylist);
       this.arraylist = arraylist;
       getCleanList(this.arraylist); // to remove outliers when the object is instantiated
    }

    public float getMean(){
        float sum = 0;
        for(int i= 0; i<arraylist.size(); i++){
            sum += arraylist.get(i);
        }

        mean = sum / arraylist.size();

        return mean;

    }

    public float getMedian(){

        float median = 0;
        float temp = 0;
        if(arraylist.size()%2==0){
            temp = (arraylist.size() / 2) + 1;
            median = arraylist.get((int) temp);
        }
        else{
            temp = ((arraylist.size()) +1)/2;
            median = arraylist.get((int) temp);
        }

        return median;
    }

    public float getSD(){

        float xSqaure = 0;
        float n = arraylist.size();
        float xValSq;

        for (int i=0; i<n; i++){
            xSqaure = xSqaure += (float) Math.pow(arraylist.get(i), 2);
            xVal += arraylist.get(i);
        }
        xValSq = (float) Math.pow(xVal, 2);

        SD = (float) Math.pow(((xSqaure - (xValSq/n))/(n-1)), 0.5);

        return SD;
    }

    public float getMin(){ // get min value

        float min = arraylist.get(0);

        return min;
    }

    public float getMax(){ // get maximum value

        float max = arraylist.get(arraylist.size()-1);

        return max;
    }

    public int getSize(){ //get size of arraylist
        int size = arraylist.size();

        return size;
    }

    public float getLQR(){ // lower quartile range

        //float LQR = arraylist.get((int) ((arraylist.size()-1)*0.25));
        float post = (float) (0.25 * (CallDatabase.floatarry.size()+1));
        float LQR = arraylist.get((int) post);

        return LQR;
    }

    public float getUQR(){ // upper quartile range

       // float UQR = arraylist.get((int) ((arraylist.size()-1)*0.75));
        float post = (float) (0.75 * (CallDatabase.floatarry.size()+1));
        float UQR = arraylist.get((int) post);

        return UQR;
    }

    public float getIQR(){ // inter-quartile range

        float IQR = getUQR() - getLQR();

        return IQR;
    }

    public float getUpper(){ // get upper outlier limit
        float upperoutlier = (float) (getUQR() + (1.5*getIQR()));
        return upperoutlier;

    }

    public float getLower(){ // get lower outlier limit
        float loweroutlier = (float) (getLQR() - (1.5*getIQR()));
        return loweroutlier;
    }

    public ArrayList<Float> getCleanList(ArrayList<Float> unclean){ // method to remove outlier
        cleanlist = new ArrayList<Float>();

        for(float num : unclean) {
            if (num < getUpper() && num > getLower()) {
                cleanlist.add(num);
            }
        }
        this.arraylist = cleanlist; // make the current arraylist the clean arraylist
        return cleanlist;
    }

    public float getPDF(int i){ // probability density function

        float expo = (float) Math.pow(e, (-0.5* (Math.pow(((arraylist.get(i) - 5.1/3.1)),2))));

        float PDF = (float) (1/(3.1*(Math.pow((2*pi), 0.5)))) * expo;

        return PDF;
    }

}
