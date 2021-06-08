import java.io.IOException;
import java.util.Random;

import static java.lang.StrictMath.abs;

public class Utility {

    public Image generateRandomImage(Image img) throws IOException {     //generates random image of that size
        Random rand = new Random();
        int r,g,b;
        Image myimg = new Image();
        myimg.width = img.width;
        myimg.height = img.height;
        myimg.fitnessValue = img.fitnessValue;
        myimg.img = new Pixel[img.width][img.height];
        for (int i = 0;i < img.width; i++){
            for (int j = 0;j < img.height; j++){
                r = rand.nextInt(256);
                g = rand.nextInt(256);
                b = rand.nextInt(256);
                myimg.img[i][j] = new Pixel (r,g,b);
            }
        }
        return myimg;
    }

    void fitnessFunction(Image p, Image orig){       //sum of absolute difference between each corresponding pixel
        double sum = 0;
        int temp1, temp2, temp3;
        for (int i = 0;i < orig.width; i++){
            for (int j = 0;j < orig.height; j++) {
                temp1 = abs(p.img[i][j].r - orig.img[i][j].r);
                temp2 = abs(p.img[i][j].g - orig.img[i][j].g);
                temp3 = abs(p.img[i][j].b - orig.img[i][j].b);
                sum += temp1 + temp2 + temp3;
            }
        }
        p.fitnessValue = sum;
    }

    void swap(Image a, Image b) {
        Image t = new Image();
        t.fitnessValue = a.fitnessValue;
        t.height = a.height;
        t.width = a.width;
        t.img = new Pixel[t.width][t.height];
        for (int i = 0;i < t.width; i++){
            for (int j = 0;j < t.height; j++) {
                t.img[i][j] = a.img[i][j];
            }
        }

        a.fitnessValue = b.fitnessValue;
        a.height = b.height;
        a.width = b.width;
        a.img = new Pixel[b.width][b.height];
        for (int i = 0;i < a.width; i++){
            for (int j = 0;j < a.height; j++) {
                a.img[i][j] = b.img[i][j];
            }
        }

        b.fitnessValue = t.fitnessValue;
        b.height = t.height;
        b.width = t.width;
        b.img = new Pixel[t.width][t.height];
        for (int i = 0;i < b.width; i++){
            for (int j = 0;j < b.height; j++) {
                b.img[i][j] = t.img[i][j];
            }
        }
    }

    void heapify(Image arr[], int n, int i) {
        int largest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;
        if (l < n && arr[l].fitnessValue > arr[largest].fitnessValue)
            largest = l;
        if (r < n && arr[r].fitnessValue > arr[largest].fitnessValue)
            largest = r;
        if (largest != i) {
            swap(arr[i], arr[largest]);
            heapify(arr, n, largest);
        }
    }

    void heapSort(Image arr[], int n) {
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        for (int i=n-1; i>=0; i--) {
            swap(arr[0], arr[i]);
            heapify(arr, i, 0);
        }
    }

    public void selection(Image [] arr, int size){      //SELECTION
        heapSort(arr,size);
    }

    public void crossover(Image [] arr,Image[] arr2, int size){       //CROSSOVER
        int popsize=(int)(size*0.2);
        int index=20;
        for (int i=0;i<popsize;i++){        //this is loop of 20
            for (int j=0;j<arr[i].width;j++)
                for(int k=0;k<arr[i].height;k++)
                    if(j>arr[i].width/2) {
                        arr2[index].img[j][k]=arr[i].img[j][k];
                        arr2[index+1].img[j][k]=arr[i+popsize].img[j][k];
                    }
                    else{
                        arr2[index].img[j][k]=arr[i+popsize].img[j][k];
                        arr2[index+1].img[j][k]=arr[i].img[j][k];
                    }
            index+=2;
        }
    }

    public void mutation(Image[] arr, int size){      //MUTATION
        Random rand=new Random();
        int popsize=(int)(size*0.2);
        int index=60;
        for (int i=0;i<popsize;i++){        //this is loop of 20
            for (int j=0;j<arr[i].width/4;j++)
                for(int k=0;k<arr[i].height/4;k++){
                    arr[i+index].img[j][k].r=rand.nextInt(255);
                    arr[i+index].img[j][k].g=rand.nextInt(255);
                    arr[i+index].img[j][k].b=rand.nextInt(255);
                }
            index++;
        }
    }
}
